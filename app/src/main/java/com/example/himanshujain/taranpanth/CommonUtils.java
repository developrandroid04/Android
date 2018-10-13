package com.example.himanshujain.taranpanth;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;

public class CommonUtils {

    /**
     * Checking for all possible internet providers
     * *
     */
    public static boolean isConnectedToInternet(Context context) {

        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            connectivity.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);

            if (connectivity.getActiveNetworkInfo() != null
                    && connectivity.getActiveNetworkInfo().isAvailable()
                    && connectivity.getActiveNetworkInfo().isConnected()) { // Internet is available.

                return true;
            }
        } catch (Exception e) {
            // do nothing
        }

        return false;
    }

    public static String getPackageNameByAPK(String _strAPKPath, Context _activityORservice) {
        String strRetVal = "";

        try {

            if (_strAPKPath == null)
                return "";

            if (_activityORservice == null)
                return "";

            PackageManager packMan = _activityORservice.getPackageManager();
            PackageInfo packInfo = packMan.getPackageArchiveInfo(_strAPKPath, 0);
            strRetVal = packInfo.packageName;


        } catch (Exception e) {

        }

        return strRetVal;
    }
}
