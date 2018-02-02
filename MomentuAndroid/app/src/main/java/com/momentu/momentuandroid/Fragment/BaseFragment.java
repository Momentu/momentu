package com.momentu.momentuandroid.Fragment;

/**
 * Created by Jane on 10/20/2017.
 */

import android.content.Context;
import android.support.v4.app.Fragment;

import com.arlib.floatingsearchview.FloatingSearchView;

public abstract class BaseFragment extends Fragment {


    private BaseExampleFragmentCallbacks mCallbacks;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseExampleFragmentCallbacks) {
            mCallbacks = (BaseExampleFragmentCallbacks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BaseExampleFragmentCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    protected void attachSearchViewActivityDrawer(FloatingSearchView searchView) {
        if (mCallbacks != null) {
            mCallbacks.onAttachSearchViewToDrawer(searchView);
        }
    }

    public abstract boolean onActivityBackPress();

    public interface BaseExampleFragmentCallbacks {

        void onAttachSearchViewToDrawer(FloatingSearchView searchView);
    }
}
