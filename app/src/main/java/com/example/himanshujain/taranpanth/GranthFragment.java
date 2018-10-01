package com.example.himanshujain.taranpanth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GranthFragment extends Fragment  {

    private static final String TAG = "SyncFragment";

    private View mRootView;
    private Activity mActivity;

    public GranthFragment() {
        // empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_granth, container, false);
        mActivity = getActivity();

        return mRootView;
    }


    private void setAdapter(){

    }

}
