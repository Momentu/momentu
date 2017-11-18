package com.momentu.momentuandroid.Data;

/**
 * Created by Jane on 10/19/2017.
 */


import android.content.Context;
import android.widget.Filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.momentu.momentuandroid.HashTagSearchActivity;
import com.momentu.momentuandroid.Model.Hashtag;

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

    private static List<Hashtag> sHashtags = new ArrayList<>();

    private static List<MomentSuggestion> sMomentSuggestions;

    public static List<MomentSuggestion> getHistory(Context context, int count) {

        List<MomentSuggestion> suggestionList = new ArrayList<>();
        MomentSuggestion momentSuggestion;
        for (int i = 0; i < sMomentSuggestions.size(); i++) {
            momentSuggestion = sMomentSuggestions.get(i);
            //TODO: define "recent search". for now, all are recent.
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
        initHashtagList(context);

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

    public static void initHashtagList(Context context) {
        sHashtags = ((HashTagSearchActivity) context).getStoredhashtags();
        List<MomentSuggestion> momentSuggestions = new ArrayList<>();
        for(Hashtag ht:sHashtags){
            momentSuggestions.add(new MomentSuggestion(ht.getLabel()));
        }
        sMomentSuggestions = momentSuggestions;
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
        void onResults(List<MomentSuggestion> results);
    }

}