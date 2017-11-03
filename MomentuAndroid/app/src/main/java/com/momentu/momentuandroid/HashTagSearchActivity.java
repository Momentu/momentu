package com.momentu.momentuandroid;

/**
 * Created by Jane on 10/17/2017.
 */


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.momentu.momentuandroid.Fragment.BaseFragment;
import com.momentu.momentuandroid.Fragment.SlidingSearchResultsFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HashTagSearchActivity extends AppCompatActivity
        implements BaseFragment.BaseExampleFragmentCallbacks, NavigationView.OnNavigationItemSelectedListener, LocationListener {

    private final String TAG = "HashTagSearchActivity";
    private DrawerLayout mDrawerLayout;
    private TextView mWhereAmI;

    /* Location */
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int MINIMUM_TIME = 5000;
    private static final int MINIMUM_DISTANCE = 10;
    private List<Address> mAddresses;
    private String mProvider;
    private String mCityName;
    private String mStateName;
    private String mCountryName;
    private Location mLocation;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_search);
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        token = token.split(":")[1].split(",")[0];
        Log.d("SearchPage", "" +token);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mWhereAmI = (TextView) findViewById(R.id.where_am_i);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Display search fragment
        showFragment(new SlidingSearchResultsFragment());

        //Get and display location info
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        checkLocation();

        //Add new tag button
        ImageButton mAddHashTag = (ImageButton) findViewById(R.id.bAddHashTag);
        mAddHashTag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HashTagSearchActivity.this, "Add new hashtag", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);
    }

    @Override
    public void onBackPressed() {
        @SuppressLint("RestrictedApi") List fragments = getSupportFragmentManager().getFragments();
        BaseFragment currentFragment = (BaseFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
            case R.id.log_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.ask_log_out)
                        .setCancelable(false)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(HashTagSearchActivity.this, "You have been successfully logged out",
                                        Toast.LENGTH_LONG).show();
                                Intent logout = new Intent(HashTagSearchActivity.this, WelcomeActivity.class);
                                finish();
                                startActivity(logout);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                return true;
            default:
                return true;
        }
    }

    // Create Fragment
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    public void checkLocation() {
        // Check location permission
        if (ActivityCompat.checkSelfPermission(HashTagSearchActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission_Check", "Bad!");

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HashTagSearchActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d("Permission_Check", "Rationale");
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HashTagSearchActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                Log.d("Permission_Check", "No explanation");
                ActivityCompat.requestPermissions(HashTagSearchActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            Log.d("Permission_Check", "Good!");
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            mProvider = mLocationManager.getBestProvider(criteria, false);
            Log.d("Provider", mProvider);
            mLocationManager.requestLocationUpdates(mProvider, MINIMUM_TIME, MINIMUM_DISTANCE, this);
            mLocation = mLocationManager.getLastKnownLocation(mProvider);
            showLocation(mLocation);
        }
    }

    private void showLocation(Location location) {
        String mLocationName;
        if (location == null) {
            mLocationName = "Unknown";
        } else {
            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            try {
                mAddresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (mAddresses.size() > 0) {
                mCityName = mAddresses.get(0).getLocality();
                mStateName = mAddresses.get(0).getAdminArea();
                mCountryName = mAddresses.get(0).getCountryName();
                mLocationName = ((mCityName == null) ? "" : mCityName) + " " +
                        ((mStateName == null) ? "" : mStateName) + " " +
                        ((mCountryName == null) ? "" : mCountryName);
            } else {
                mLocationName = "Unknown";
            }
        }

        mWhereAmI.setText(((mCityName == null) ? "Where am I" : mCityName));
        Toast.makeText(getBaseContext(), "Current location: " + mLocationName,
                Toast.LENGTH_LONG).show();
    }

    // LocationListener implementation
    @Override
    public void onLocationChanged(Location location) {
        checkLocation();
        Log.d(TAG, "Location Changed");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        //if the provider is disabled, then open setting.
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    // When requesting location permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            Log.i(TAG, "Received response for Location permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "Location permission has now been granted.");
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                mProvider = mLocationManager.getBestProvider(criteria, false);
                Log.d("Provider", mProvider);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mLocationManager.requestLocationUpdates(mProvider, MINIMUM_TIME, MINIMUM_DISTANCE, this);
                mLocation = mLocationManager.getLastKnownLocation(mProvider);
                showLocation(mLocation);
            } else {
                Log.i(TAG, "Location permission was NOT granted.");
            }
        }
    }

}
