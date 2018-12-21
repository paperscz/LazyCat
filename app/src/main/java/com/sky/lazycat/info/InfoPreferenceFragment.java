package com.sky.lazycat.info;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.bumptech.glide.Glide;
import com.sky.lazycat.R;
import com.sky.lazycat.util.ToastUtils;

/**
 * Created by yuetu-develop on 2018/4/19.
 */

public class InfoPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        findPreference(InfoConstants.KEY_NIGHT_MODE).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                //设置夜间模式
                return true;
            }
        });
        // Clear the cache of glide
        findPreference(InfoConstants.KEY_CLEAR_GLIDE_CACHE).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getContext()).clearDiskCache();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showShort(getActivity(),"Success");
                            }
                        });
                    }
                }).start();
                return true;
            }
        });
    }
}
