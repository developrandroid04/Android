package com.example.himanshujain.taranpanth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressBar;
    Toolbar mToolbar;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.app_name));


        findViewById(R.id.layout_intro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText("तारण स्वामी का परिचय");
                showIntroFragment(new IntroFragment(), IntroFragment.TAG);
            }
        }); findViewById(R.id.layout_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText("तारण देशना");
                showIntroFragment(new VideoFragment(), VideoFragment.TAG);
            }
        });
        findViewById(R.id.layout_granth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText("तारण स्वामी के ग्रन्थ");
                showIntroFragment(new GranthFragment(), GranthFragment.TAG);
            }
        });
    }

    public void showIntroFragment(Fragment fragment, String headerText) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, fragment, headerText).commit();
    }

    @Override
    public void onBackPressed() {
        IntroFragment introFragment = (IntroFragment) getSupportFragmentManager().findFragmentByTag(IntroFragment.TAG);
        if (introFragment != null && introFragment.isAdded()) {
            if (introFragment.checkBackPress())
                return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            ((TextView) mToolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.app_name));

        }
        super.onBackPressed();

    }


}
