package com.example.himanshujain.taranpanth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class IntroFragment extends Fragment {

    public static final String TAG = "IntroFragment";

    private View mRootView;
    private Activity mActivity;
    WebView webView;
    private ProgressDialog progressBar;


    public IntroFragment() {
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
        mRootView = inflater.inflate(R.layout.fragment_intro, container, false);
        mActivity = getActivity();
        webView = (WebView) mRootView.findViewById(R.id.webView);
        startWebView("https://en.wikipedia.org/wiki/Taran_Panth");
        return mRootView;
    }


    private void startWebView(String url) {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setDomStorageEnabled(true);

        webView.addJavascriptInterface(new MyJavaScriptInterface(),
                "android");
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        progressBar = ProgressDialog.show(getActivity(), "", "Loading...");

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url + "?device=1");
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        webView.loadUrl(url);

    }


    class MyJavaScriptInterface {
        @JavascriptInterface
        public void onUrlChange(String url) {
            Log.d("hydrated", "onUrlChange" + url);
        }
    }

    public boolean checkBackPress() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return false;
    }

    ProgressDialog progressDialog;

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.hide();

    }

    public void showProgress(String message, boolean cancelStatus) {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(cancelStatus);
        progressDialog.setMessage(message);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
}
