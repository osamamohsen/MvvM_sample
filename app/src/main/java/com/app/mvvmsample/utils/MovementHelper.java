package com.app.mvvmsample.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.app.mvvmsample.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MovementHelper {


    //---------Fragments----------//
    private static final int CONTAINER_ID = R.id.fl_home_container;

    public static void popAllFragments(Context context) {
        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public static void addFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(CONTAINER_ID, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }


    public static void addFragmentTag(Context context, Fragment fragment,String tag, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(CONTAINER_ID, fragment ,tag);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }

    public static void replaceFragmentTag(Context context, Fragment fragment,String tag, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(CONTAINER_ID, fragment ,tag);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }



    public static void replaceFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(CONTAINER_ID, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }



    public static void popLastFragment(Context context) {
        ((FragmentActivity) context).getSupportFragmentManager().popBackStack();
    }


    //-----------Activities-----------------//

    public static void startActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);

    }

    public static void startWebPage(Context context, String page) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(page)));
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
}
