package com.example.himanshujain.taranpanth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GranthFragment extends Fragment implements GranthAdapter.GranthListener {

    public static final String TAG = "SyncFragment";

    private View mRootView;
    private Activity mActivity;
    private LayoutInflater mInflater;

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
        mInflater = inflater;
        createGranthNames();
        return mRootView;
    }


    public void createGranthNames() {
        List<String> nameList = new ArrayList<>();
        nameList.add("श्री तारण तरण अध्यात्म वाणी जी");
        nameList.add("श्री मालारोहण जी  ग्रंथादिराज");
        nameList.add("श्री पंडित पूजा जी ग्रंथादिराज");
        nameList.add("श्री कमल बत्तीसी ग्रंथादिराज");
        nameList.add("श्री श्रावकाचार जी ग्रंथादिराज");
        nameList.add("श्री उपदेश शुद्धसार जी ग्रंथादिराज");


        GranthAdapter granthAdapter = new GranthAdapter(getActivity(), nameList, mInflater, GranthFragment.this);
        ((RecyclerView) mRootView.findViewById(R.id.grathlistview)).setAdapter(granthAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ((RecyclerView) mRootView.findViewById(R.id.grathlistview)).setLayoutManager(mLayoutManager);


    }


    @Override
    public void onGranthClick(String name) {
        new DownloadAPKTask(getContext(), "TranaPanth").execute("https://github.com/Himanshujain0133/Android/raw/master/%E0%A4%B6%E0%A5%8D%E0%A4%B0%E0%A5%80%20%E0%A4%89%E0%A4%AA%E0%A4%A6%E0%A5%87%E0%A4%B6%20%E0%A4%B6%E0%A5%81%E0%A4%A6%E0%A5%8D%E0%A4%A7%20%E0%A4%B8%E0%A4%BE%E0%A4%B0%20%E0%A4%9C%E0%A5%80%20%E0%A4%97%E0%A5%8D%E0%A4%B0%E0%A4%82%E0%A4%A5.pdf");

    }


    public class DownloadAPKTask extends AsyncTask<String, Integer, Boolean> {

        Context mContext;
        public static final String PATH = "/mnt/sdcard/Download/";
        private boolean appStatus = false;
        String appName;
        String fileName;
        int lastProgressedValue;

        ProgressDialog progress;


        public DownloadAPKTask(Context mContext, String appName) {
            this.appName = appName;
            this.mContext = mContext;
            progress = new ProgressDialog(getActivity());
            progress.setMessage("Downloading Music");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            // Update progress
            super.onProgressUpdate(values);
            progress.setProgress((values[0]));
        }

        @Override
        protected Boolean doInBackground(String... aurl) {
            int count;

            if (CommonUtils.isConnectedToInternet(mContext)) {

                try {

                    URL url = new URL(aurl[0]);
                    Log.i("URL:", "" + url);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    File file = new File(PATH);
                    file.mkdirs();
                    File outputFile = new File(file, appName + ".apk");
                    if (outputFile.exists()) {
                        outputFile.delete();
                    }


                    int lengthOfFile = connection.getContentLength();
                    Log.i("LENGTHOFDATA", "" + lengthOfFile);


                    InputStream input = new BufferedInputStream(url.openStream());
                    OutputStream output = new FileOutputStream(outputFile);

                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        int progressValue = (int) ((total * 100) / lengthOfFile);
                        if (lastProgressedValue < progressValue) {
                            publishProgress(progressValue);
                        }
                        output.write(data, 0, count);
                    }

                    output.flush();
                    output.close();
                    input.close();
                    String packageName = CommonUtils.getPackageNameByAPK(PATH + appName + ".pdf", mContext);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(PATH + appName + ".pdf")), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
                    startActivity(intent);

                    appStatus = false;

                } catch (Exception e) {
                    Log.i("Error", "" + e);

                    e.printStackTrace();
                    return false;
                }
            }
            return true;

        }


        @Override
        protected void onPostExecute(Boolean unused) {
            progress.dismiss();

            if (!unused) {
                if (isAdded()) {

                }
            }

        }


    }

}
