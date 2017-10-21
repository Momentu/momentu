package com.momentu.momentuandroid.Data;

/**
 * Created by Jane on 10/19/2017.
 */


import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataHelper {

    private static final String MOMENTS_FILE_NAME = "moment.json";

    private static List<MomentWrapper> sMomentWrappers = new ArrayList<>();

    // Hard coded
    private static List<MomentSuggestion> sMomentSuggestions =
            new ArrayList<>(Arrays.asList(
                    new MomentSuggestion("#AnOpenSecret"),
                    new MomentSuggestion("#AppleMichiganAve"),
                    new MomentSuggestion("#Sixers"),
                    new MomentSuggestion("#Scandal"),
                    new MomentSuggestion("#Supernatural"),
                    new MomentSuggestion("#anniversary"),
                    new MomentSuggestion("#AOMG"),
                    new MomentSuggestion("#scnews")));

    public interface OnFindMomentsListener {
        void onResults(List<MomentWrapper> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<MomentSuggestion> results);
    }

    public static List<MomentSuggestion> getHistory(Context context, int count) {

        List<MomentSuggestion> suggestionList = new ArrayList<>();
        MomentSuggestion momentSuggestion;
        for (int i = 0; i < sMomentSuggestions.size(); i++) {
            momentSuggestion = sMomentSuggestions.get(i);
            momentSuggestion.setIsHistory(true);
            suggestionList.add(momentSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (MomentSuggestion momentSuggestion : sMomentSuggestions) {
            momentSuggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DataHelper.resetSuggestionsHistory();
                List<MomentSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (MomentSuggestion suggestion : sMomentSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<MomentSuggestion>() {
                    @Override
                    public int compare(MomentSuggestion lhs, MomentSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<MomentSuggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public static void findMoments(Context context, String query, final OnFindMomentsListener listener) {
        initMomentWrapperList(context);

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<MomentWrapper> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (MomentWrapper moment : sMomentWrappers) {
                        if (moment.getHashtag().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(moment);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<MomentWrapper>) results.values);
                }
            }
        }.filter(query);

    }

    private static void initMomentWrapperList(Context context) {

        if (sMomentWrappers.isEmpty()) {
            String jsonString = loadJson(context);
            sMomentWrappers = deserializeMoments(jsonString);
        }
    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(MOMENTS_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static List<MomentWrapper> deserializeMoments(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<MomentWrapper>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }

}