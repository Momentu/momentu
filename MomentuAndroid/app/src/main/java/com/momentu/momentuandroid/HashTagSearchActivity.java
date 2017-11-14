package com.momentu.momentuandroid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.momentu.momentuandroid.Adapter.CardPagerAdapter;
import com.momentu.momentuandroid.Animation.ShadowTransformer;
import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.Fragment.BaseFragment;
import com.momentu.momentuandroid.Fragment.SlidingSearchResultsFragment;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.TrendHashTagCard;
import com.momentu.momentuandroid.Services.ConnectionService;
import com.momentu.momentuandroid.Utility.RequestPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Jane on 11/4/2017.
 */

public class HashTagSearchActivity extends AppCompatActivity implements BaseFragment.BaseExampleFragmentCallbacks, NavigationView.OnNavigationItemSelectedListener {

    /* TrendHashTag ViewPager */
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private String[] cityWideHashTags = new String[6];
    private String[] stateWideHashTags = new String[6];
//    private String[] nationWideHashTags = new String[6];

    /* Search Fragment */
    private Fragment currentFragment;

    /* Location */
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int LOCATION_INTERVAL = 1000;
    private static final int LOCATION_DISTANCE = 10;
    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };
    private LocationManager mLocationManager = null;
    private List<Address> mAddresses;
    private String mCityName;
    private String mStateName;
//    private String mCountryName;
    private Location mLocation;
    public EditText hashtagInput;

    /* Camera */
    public static final int CAMERA_REQUEST = 1888;
    private final String TAG = "HashTagSearchActivity";

    /* Other Views */
    private Button mChangeLocationDialog;
    private DrawerLayout mDrawerLayout;
    private TextView mWhereAmI;

    static String token;
    private ArrayList<Hashtag> Storedhashtags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_search);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        Log.d("SearchPage", "" +token);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mWhereAmI = (TextView) findViewById(R.id.where_am_i);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showSearchFragment(new SlidingSearchResultsFragment());

        initializeLocationManager();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        checkLocation();

        showTrendHashtagPager(Storedhashtags);

        //Change location UI
        mChangeLocationDialog = (Button) findViewById(R.id.bChangeLocation);
        mChangeLocationDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(HashTagSearchActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_change_location, null);
                mBuilder.setTitle("Change location");
                final Spinner mSpinnerState = (Spinner) mView.findViewById(R.id.spinner_state);
                final Spinner mSpinnerCity = (Spinner) mView.findViewById(R.id.spinner_city);
                //TODO: City needs to be linked with State.
                ArrayAdapter<String> adapterState = new ArrayAdapter<String>(HashTagSearchActivity.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.States));
                ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(HashTagSearchActivity.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Cities));
                adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinnerState.setAdapter(adapterState);
                mSpinnerCity.setAdapter(adapterCity);

                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newStateName = mSpinnerState.getSelectedItem().toString();
                        String newCityName = mSpinnerCity.getSelectedItem().toString();
                        if(!newStateName.equalsIgnoreCase("Please select state…") &
                                !newCityName.equalsIgnoreCase("Please select city…")){
                            Toast.makeText(HashTagSearchActivity.this,
                                    "Selected " + newStateName + " " + newCityName,
                                    Toast.LENGTH_SHORT)
                                    .show();
                            mCityName = newCityName;
                            mStateName = newStateName;
                            mWhereAmI.setText(((mCityName == null) ? "Where am I" : mCityName));
                            mViewPager.setCurrentItem(0); //ViewPager roll back to the first page (city-wide trend hashtag)
                            loadTrendingHashtags();
                            dialogInterface.dismiss();
                        }
                    };
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        //Take picture/video
        //TODO: Need a "MediaActivity" to process the photo/video taken.
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                HashTagSearchActivity.CAMERA_REQUEST);

        ImageButton cameraButton = (ImageButton) this.findViewById(R.id.bCamera);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        //setup
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(ConnectionService.MY_SERVICE_MESSAGE));
    }

    // The method is on activity result after capture a photo. A dialog will be displayed
    // for user to enter the hashtag name.
    //It passes the hashtage along with the location to RestClient to pass it to the backend
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            final Dialog dialogToPost = new Dialog(this);
            dialogToPost.setContentView(R.layout.dialog_to_post);
            Button post = (Button) dialogToPost.findViewById(R.id.post);
            Button cancel = (Button) dialogToPost.findViewById(R.id.cancel);
            hashtagInput = (EditText) dialogToPost.findViewById(R.id.hashtagInput);

            //On click listener for post button
            post.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (hashtagInput.getText().toString().contains("#")) {
                        dialogToPost.dismiss();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("hashtagLabel", hashtagInput.getText().toString());
                        params.put("city", mCityName);
                        params.put("state", mStateName);

                        RestClient restClient = new RestClient();
                        try {
                            restClient.media(params, token, HashTagSearchActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(restClient.status == 0)
                            Toast.makeText(HashTagSearchActivity.this, hashtagInput.getText().toString() + " posted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(HashTagSearchActivity.this, hashtagInput.getText().toString() + " cann't be posted", Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(HashTagSearchActivity.this, "Wrong Hashtag Format", Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialogToPost.show();

            //On click listener for cancel button
            cancel.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dialogToPost.dismiss();
                    Toast.makeText(HashTagSearchActivity.this, "Post has been canceled", Toast.LENGTH_LONG).show();
                }

            });

        }
    }



    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);
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

    @Override
    public void onBackPressed() {
        @SuppressLint("RestrictedApi") List fragments = getSupportFragmentManager().getFragments();
        BaseFragment currentFragment = (BaseFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listeners, ignore", ex);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            Log.i(TAG, "Received response for Location permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "Location permission has now been granted.");
                try {
                    mLocationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                            mLocationListeners[0]);
                    mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } catch (java.lang.SecurityException ex) {
                    Log.i(TAG, "fail to request location update, ignore", ex);
                } catch (IllegalArgumentException ex) {
                    Log.d(TAG, "gps provider does not exist " + ex.getMessage());
                }
                showLocation(mLocation);
            } else {
                Log.i(TAG, "Location permission was NOT granted.");
            }
        }
    }

    //Trend Hashtag pager
    private void showTrendHashtagPager(ArrayList<Hashtag> Storedhashtags) {

        mViewPager.addOnPageChangeListener(new PageListener());

        mCardAdapter = new CardPagerAdapter();

        //Hard coded tags- for demonstration if the number of hashtag is too small.
        cityWideHashTags[0] = "#Sixers";
        cityWideHashTags[1] = "#anniversary";
        cityWideHashTags[2] = "#Supernatural";
        cityWideHashTags[3] = "#scnews";
        cityWideHashTags[4] = "#AOMG";
        cityWideHashTags[5] = "#Scandal";

        stateWideHashTags[0] = "#AOMG";
        stateWideHashTags[1] = "#anniversary";
        stateWideHashTags[2] = "#AnOpenSecret";
        stateWideHashTags[3] = "#Scandal";
        stateWideHashTags[4] = "#Sixers";
        stateWideHashTags[5] = "#scnews";

//        nationWideHashTags[0] = "#AllStars3";
//        nationWideHashTags[1] = "#Scandal";
//        nationWideHashTags[2] = "#Supernatural";
//        nationWideHashTags[3] = "#AppleMichiganAve";
//        nationWideHashTags[4] = "#AOMG";
//        nationWideHashTags[5] = "#anniversary";

        if(Storedhashtags != null) {
            int storedHashtagLength = Storedhashtags.size();

            if(storedHashtagLength > 0){
                mViewPager.setVisibility(View.VISIBLE);

                Log.d("# Hashtag received" , Integer.toString(storedHashtagLength));
                int index=0;
                for(Hashtag ht:Storedhashtags){
//                Log.d("Tag index" , Integer.toString(index));
                    cityWideHashTags[index] = ht.getLabel();
                    stateWideHashTags[index] = ht.getLabel(); // state-wide got the same tags
                    index++;
                    if(index == 5) break; // Only display the top six tags.
                }
            } else {
                mViewPager.setVisibility(View.INVISIBLE);
            }
        } else {
            //If no trend hash tag, hide the view.
            mViewPager.setVisibility(View.INVISIBLE);
        }
        mCardAdapter.addCardItem(new TrendHashTagCard(cityWideHashTags));
        mCardAdapter.addCardItem(new TrendHashTagCard(stateWideHashTags));
//        mCardAdapter.addCardItem(new TrendHashTagCard(nationWideHashTags));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
//        mViewPager.setOffscreenPageLimit(3); // Three pages will be kept offscreen
        mViewPager.setOffscreenPageLimit(2); // Two pages will be kept offscreen
        mCardShadowTransformer.enableScaling(true);
    }

    private class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            Log.i("Trend HashTag", "page selected " + position);
            switch (position) {
                case 0:
                    mWhereAmI.setText(((mCityName == null) ? "Where am I" : mCityName));
                    break;
                case 1:
                    mWhereAmI.setText(((mStateName == null) ? "Where am I" : mStateName));
                    break;
//                case 2:
//                    mWhereAmI.setText("United States");
//                    break;
            }
        }
    }


    // Create Fragment
    private void showSearchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
        currentFragment = fragment;
    }

    // Location
    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
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
            try {
                mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                        mLocationListeners[0]);
                mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } catch (java.lang.SecurityException ex) {
                Log.i(TAG, "fail to request location update, ignore", ex);
            } catch (IllegalArgumentException ex) {
                Log.d(TAG, "gps provider does not exist " + ex.getMessage());
            }
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

            if (mAddresses != null & mAddresses.size() > 0) {
                mCityName = mAddresses.get(0).getLocality();
                mStateName = mAddresses.get(0).getAdminArea();
//                mCountryName = mAddresses.get(0).getCountryName();
                mLocationName = ((mCityName == null) ? "" : mCityName) + " " +
                        ((mStateName == null) ? "" : mStateName);
            } else {
                mLocationName = "Unknown";
            }
        }

        mWhereAmI.setText(((mCityName == null) ? "Where am I" : mCityName));
        mViewPager.setCurrentItem(0); //When location updated, roll back to the first page (city-wide trend hashtag)
        Toast.makeText(getBaseContext(), "Current location:" + mLocationName,
                Toast.LENGTH_LONG).show();

        //call the method that passes the location to the RestClient, for retrieving hashtags
        loadTrendingHashtags();
    }

    //When Trend hashtag is clicked
    public void clickToSearch(View view) {
        String mTrendingHastTag = ((Button) view).getText().toString();
        Toast.makeText(getBaseContext(), "Selected " + mTrendingHastTag,
                Toast.LENGTH_SHORT).show();

        //TODO: Pass the hashtag to backend, search, and populate feed activity
        Intent feedIntent = new Intent(this, FeedActivity.class);
        startActivity(feedIntent);
    }

    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            checkLocation();
        }

        @Override
        public void onProviderDisabled(String provider) {
            //if the provider is disabled, then open setting.
            Log.e(TAG, "onProviderDisabled: " + provider);
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    //receive response from ConnectionService
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Hashtag> hashtags = intent
                    .getParcelableArrayListExtra(ConnectionService.MY_SERVICE_PAYLOAD);
            if(hashtags == null)
                Log.d("BroadcastReceiver", "hashtags is null");
            else{
                Storedhashtags = hashtags;
                for(Hashtag hashtag:hashtags)
                Log.d("BroadcastReceiver", hashtag.getLabel() + " " + hashtag.getCount());
                showTrendHashtagPager(Storedhashtags);
            }

//            for (Hashtag hash : hashtags) {
//                Log.d("BroadCastReceiver","Hashtag: " + hash.getLabel()+ " Count:" + hash.getCount());
//            }
        }
    };
    private void loadTrendingHashtags()
    {
        if(mCityName != null && mStateName != null){
            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndpoint(RestClient.HASHTAGS_ENDPOINT);
            requestPackage.setParam("city",mCityName);
            requestPackage.setParam("state",mStateName);
            requestPackage.setToken(token);
            requestPackage.setFunction("/search/findByStateCity");

//            requestPackage.setParam("category", "Entrees");
//        requestPackage.setMethod("GET");

            Intent intent = new Intent(this, ConnectionService.class);
            intent.putExtra(ConnectionService.REQUEST_PACKAGE, requestPackage);
            startService(intent);
        }
        else
            Log.d("loadTrendingHashtags", "city and/or state is null");
    }
}