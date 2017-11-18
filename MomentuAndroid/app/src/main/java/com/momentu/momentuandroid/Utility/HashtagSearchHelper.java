package com.momentu.momentuandroid.Utility;

/**
 * Created by Jane on 10/19/2017.
 */


import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.HashtagSuggestion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HashtagSearchHelper {

    private static final String MOMENTS_FILE_NAME = "moment.json";

    private static List<Hashtag> sHashtags = new ArrayList<>();

    private static List<HashtagSuggestion> sHashtagSuggestions;

    public static List<HashtagSuggestion> getHistory(Context context, int count) {

        List<HashtagSuggestion> suggestionList = new ArrayList<>();
        HashtagSuggestion hashtagSuggestion;
        for (int i = 0; i < sHashtagSuggestions.size(); i++) {
            hashtagSuggestion = sHashtagSuggestions.get(i);
            //TODO: define "recent search". for now, all are recent.
            hashtagSuggestion.setIsHistory(true);
            suggestionList.add(hashtagSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (HashtagSuggestion hashtagSuggestion : sHashtagSuggestions) {
            hashtagSuggestion.setIsHistory(false);
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

                HashtagSearchHelper.resetSuggestionsHistory();
                List<HashtagSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (HashtagSuggestion suggestion : sHashtagSuggestions) {
                        //TODO: Make this search algorithm smarter
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
                Collections.sort(suggestionList, new Comparator<HashtagSuggestion>() {
                    @Override
                    public int compare(HashtagSuggestion lhs, HashtagSuggestion rhs) {
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
                    listener.onResults((List<HashtagSuggestion>) results.values);
                }
            }
        }.filter(query);

    }

    public static void findMoments(Context context, String query, final OnFindMomentsListener listener) {
//        updateHashtagList(context);

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<Hashtag> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (Hashtag moment : sHashtags) {
                        if (moment.getLabel().toUpperCase()
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
                    listener.onResults((List<Hashtag>) results.values);
                }
            }
        }.filter(query);

    }

    public static void updateHashtagList(Context context) {
        sHashtags = ((HashTagSearchActivity) context).getStoredhashtags();
        List<HashtagSuggestion> hashtagSuggestions = new ArrayList<>();
        for(Hashtag ht:sHashtags){
            hashtagSuggestions.add(new HashtagSuggestion(ht.getLabel()));
        }
        sHashtagSuggestions = hashtagSuggestions;
    }

//    private static String loadJson(Context context) {
//
//        String jsonString;
//
//        try {
//            InputStream is = context.getAssets().open(MOMENTS_FILE_NAME);
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            jsonString = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//        return jsonString;
//    }

    private static List<Hashtag> deserializeMoments(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<Hashtag>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }

    public interface OnFindMomentsListener {
        void onResults(List<Hashtag> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<HashtagSuggestion> results);
    }

}