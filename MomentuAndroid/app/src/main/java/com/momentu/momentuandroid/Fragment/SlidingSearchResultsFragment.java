package com.momentu.momentuandroid.Fragment;

/**
 * Created by Jane on 10/20/2017.
 */


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.momentu.momentuandroid.Adapter.SearchResultsListAdapter;
import com.momentu.momentuandroid.Data.DataHelper;
import com.momentu.momentuandroid.Data.MomentSuggestion;
import com.momentu.momentuandroid.Data.MomentWrapper;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.R;
import com.momentu.momentuandroid.WelcomeActivity;

import java.util.ArrayList;
import java.util.List;


public class SlidingSearchResultsFragment extends BaseFragment {
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private static final long ANIM_DURATION = 350;
    private final String TAG = "BlankFragment";
    private FloatingSearchView mSearchView;
    private RecyclerView mSearchResultsList;
    private ViewGroup.MarginLayoutParams marginLayoutParamsSearchResults;
    private SearchResultsListAdapter mSearchResultsAdapter;
    private View mDimSearchViewBackground;
    private AnimatorSet animSetSearchAndResults;
    private ColorDrawable mDimDrawable;
    private String mLastQuery = "";
    private int backPressCount; // press back twice (no focus) will log out.

    public SlidingSearchResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        backPressCount = 1;
        return inflater.inflate(R.layout.fragment_sliding_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mDimSearchViewBackground = view.findViewById(R.id.dim_background);
        mDimDrawable = new ColorDrawable(Color.BLACK);
        mDimDrawable.setAlpha(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mDimSearchViewBackground.setBackground(mDimDrawable);
        } else {
            mDimSearchViewBackground.setBackground(mDimDrawable);
        }

        mSearchResultsList = (RecyclerView) view.findViewById(R.id.search_results_list);
        marginLayoutParamsSearchResults = (ViewGroup.MarginLayoutParams) mSearchResultsList.getLayoutParams();

        animSetSearchAndResults = new AnimatorSet(); // multiple view animation together

        setupFloatingSearch();
        setupResultsList();
        setupDrawer();

        mSearchView.setSearchHint("Search hashtag...");
    }

    private void setupFloatingSearch() {
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                    mLastQuery = "";
                } else {

                    //shows the top left circular progress
                    mSearchView.showProgress();

                    //simulates a query call to a data source with a new query.
                    DataHelper.findSuggestions(getActivity(), newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<MomentSuggestion> results) {

                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mSearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mSearchView.hideProgress();
                                }
                            });
                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {

                MomentSuggestion momentSuggestion = (MomentSuggestion) searchSuggestion;
                DataHelper.findMoments(getActivity(), momentSuggestion.getBody(),
                        new DataHelper.OnFindMomentsListener() {

                            @Override
                            public void onResults(List<MomentWrapper> results) {
                                setupResultsList();
                                mSearchResultsAdapter.swapData(results);
                            }

                        });
                Log.d(TAG, "onSuggestionClicked()");
                mLastQuery = searchSuggestion.getBody();
                mSearchView.clearSearchFocus();
                mSearchView.setSearchBarTitle(mLastQuery);
            }

            @Override
            public void onSearchAction(String query) {
                mLastQuery = query;

                DataHelper.findMoments(getActivity(), query,
                        new DataHelper.OnFindMomentsListener() {

                            @Override
                            public void onResults(List<MomentWrapper> results) {
                                mSearchResultsAdapter.swapData(results);
                            }

                        });
                Log.d(TAG, "onSearchAction()");
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                backPressCount = 1; //reset

                //Animation
                int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_bar_height);
                ArrayList<ObjectAnimator> arrayListObjectAnimators = new ArrayList<ObjectAnimator>(); //ArrayList of ObjectAnimators

                //search bar view animation (floating up)
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(mSearchView, "translationY",
                        headerHeight, 0);
                arrayListObjectAnimators.add(anim1);

                //search result view animation
                marginLayoutParamsSearchResults.setMargins(0, 0, 0, 0);
                mSearchResultsList.setLayoutParams(marginLayoutParamsSearchResults);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(mSearchResultsList, "translationY",
                        headerHeight, 0);
                arrayListObjectAnimators.add(anim2);

                fadeDimBackground(0, 150, null);
                ObjectAnimator[] objectAnimators = arrayListObjectAnimators.toArray(new ObjectAnimator[arrayListObjectAnimators.size()]);
                animSetSearchAndResults.playTogether(objectAnimators);
                animSetSearchAndResults.setDuration(ANIM_DURATION);//1sec
                animSetSearchAndResults.start();

                //show suggestions when search bar gains focus (history suggestions)
                if (mSearchView.getQuery().equals("")) {
                    mSearchView.swapSuggestions(DataHelper.getHistory(getActivity(), 3));
                }

                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {
                backPressCount = 1; //reset

                //Animation
                int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_bar_height);
                ArrayList<ObjectAnimator> arrayListObjectAnimators = new ArrayList<ObjectAnimator>(); //ArrayList of ObjectAnimators

                //search bar view animation (floating down)
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(mSearchView, "translationY",
                        0, headerHeight);
                arrayListObjectAnimators.add(anim1);

                //search result view animation
                marginLayoutParamsSearchResults.setMargins(0, headerHeight, 0, 0);
                mSearchResultsList.setLayoutParams(marginLayoutParamsSearchResults);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(mSearchResultsList, "translationY",
                        headerHeight, 0);
                arrayListObjectAnimators.add(anim2);

                fadeDimBackground(150, 0, null);
                ObjectAnimator[] objectAnimators = arrayListObjectAnimators.toArray(new ObjectAnimator[arrayListObjectAnimators.size()]);
                animSetSearchAndResults.playTogether(objectAnimators);
                animSetSearchAndResults.setDuration(ANIM_DURATION);//1sec
                animSetSearchAndResults.start();

                //set the title of the bar so that when focus is returned a new query begins
                mSearchView.setSearchBarTitle(mLastQuery);

                //Can also set setSearchText(...) to make keep the query there when not focused and when focus returns
                //mSearchView.setSearchText(searchSuggestion.getBody());

                Log.d(TAG, "onFocusCleared()");
            }
        });


        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getTitle().equals("action location")) {
                    HashTagSearchActivity hashTagSearchActivity = (HashTagSearchActivity) getActivity();
                    hashTagSearchActivity.checkLocation();
                }
            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

                Log.d(TAG, "onHomeClicked()");
            }
        });

        /*
         * Here you have access to the left icon and the text of a given suggestion
         * item after as it is bound to the suggestion list. You can utilize this
         * callback to change some properties of the left icon and the text. For example, you
         * can load the left icon images using your favorite image loading library, or change text moment.
         *
         *
         * Important:
         * Keep in mind that the suggestion list is a RecyclerView, so views are reused for different
         * items in the list.
         */
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                MomentSuggestion momentSuggestion = (MomentSuggestion) item;

                String textColor = "#FF4081";
                String textLight = "#bfbfbf";

                if (momentSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = momentSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font moment=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(Html.fromHtml(text));
            }

        });

        //listen for when suggestion list expands/shrinks in order to move down/up the
        //search results list
        mSearchView.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {
                mSearchResultsList.setTranslationY(newHeight);
            }
        });

        /*
         * When the user types some text into the search field, a clear button (and 'x' to the
         * right) of the search text is shown.
         *
         * This listener provides a callback for when this button is clicked.
         */
        mSearchView.setOnClearSearchActionListener(new FloatingSearchView.OnClearSearchActionListener() {
            @Override
            public void onClearSearchClicked() {
                mSearchView.setSearchText("");
                Log.d(TAG, "onClearSearchClicked()");
            }
        });
    }

    private void setupResultsList() {
        mSearchResultsAdapter = new SearchResultsListAdapter();
        mSearchResultsAdapter.setItemsOnClickListener(new SearchResultsListAdapter.OnItemClickListener() {
            @Override
            public void onClick(MomentWrapper momentWrapper) {
                Toast.makeText(getActivity(),
                        "Selected " + momentWrapper.getHashtag(),
                        Toast.LENGTH_SHORT)
                        .show();
                //TODO: Pass the hashtag to backend, search, and populate feed activity
                Intent feedIntent = new Intent(getActivity(), FeedActivity.class);
                startActivity(feedIntent);
            }
        });
        mSearchResultsList.setAdapter(mSearchResultsAdapter);
        mSearchResultsList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public boolean onActivityBackPress() {
        //if mSearchView.setSearchFocused(false) causes the focused search
        //to close, then we don't want to close the activity. if mSearchView.setSearchFocused(false)
        //returns false, we know that the search was already closed so the call didn't change the focus
        //state and it makes sense to call supper onBackPressed() and close the activity
        if (!mSearchView.setSearchFocused(false)) {
            if (backPressCount < 2) {
                backPressCount++;
                Toast.makeText(getActivity(), "Press again will log out",
                        Toast.LENGTH_SHORT).show();
                return true;
            } else {
                backPressCount = 1;
                Intent logout = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(logout);
                Toast.makeText(getActivity(), "You have been successfully logged out",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            backPressCount = 1;
            return true;
        }
    }

    private void setupDrawer() {
        attachSearchViewActivityDrawer(mSearchView);
    }

    private void fadeDimBackground(int from, int to, Animator.AnimatorListener listener) {
        ValueAnimator anim = ValueAnimator.ofInt(from, to);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (Integer) animation.getAnimatedValue();
                mDimDrawable.setAlpha(value);
            }
        });
        if (listener != null) {
            anim.addListener(listener);
        }
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    public void injectHashTag(String hashTag){
        Log.d("Received", hashTag);
        //this textview should be bound in the fragment onCreate as a member variable
        mSearchView.setSearchText(hashTag);
        DataHelper.findMoments(getActivity(), hashTag,
                new DataHelper.OnFindMomentsListener() {
                    @Override
                    public void onResults(List<MomentWrapper> results) {
                        setupResultsList();
                        mSearchResultsAdapter.swapData(results);
                    }
                });
        mLastQuery = hashTag;
    }
}
