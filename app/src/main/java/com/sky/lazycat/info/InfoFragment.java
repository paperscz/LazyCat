package com.sky.lazycat.info;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.lazycat.R;
import com.sky.lazycat.interfaces.OnViewScrollListener;

public class InfoFragment extends Fragment {

    OnViewScrollListener onViewScrollListener;
    private boolean mHide = false;

    public InfoFragment() {
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onViewScrollListener = (OnViewScrollListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.info_container, new InfoPreferenceFragment())
                .commit();


        return view;
    }


}
