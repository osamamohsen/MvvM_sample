package com.app.mvvmsample.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.mvvmsample.R;
import com.app.mvvmsample.utils.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ParentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //progress

    public KProgressHUD hud = null;

    public KProgressHUD initializeProgress() {
        if (hud == null) {
            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setMaxProgress(100);
            hud.setCancellable(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                    onBackPressed();
                }
            });
        }
        return hud;
    }

    public void handleActions(String action, String baseError) {
        Log.e("action",action);
        if (action.equals(Constants.SHOW_PROGRESS)) showProgress();
        else if (action.equals(Constants.HIDE_PROGRESS)) hideProgress();
        else if (action.equals(Constants.FAILURE_CONNECTION)) noConnection();
        else if (action.equals(Constants.ERROR_RESPONSE)) showError(baseError);
    }

    public void noConnection() {
        Snackbar snackBar = Snackbar.make(findViewById(R.id.ll_base_container),
                "please check connection" ,Snackbar.LENGTH_LONG);
        View view = snackBar.getView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        snackBar.show();
    }

    public void showError(String msg) {
        Snackbar snackBar = Snackbar.make(findViewById(R.id.ll_base_container),
                msg, Snackbar.LENGTH_LONG);
        View view = snackBar.getView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        snackBar.show();
    }

    public void toastMessage(String message , int icon , int color){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        hud = initializeProgress();
        if (hud != null && !hud.isShowing()) {
            Log.e("show", "down22");
            hud.show();
        }
    }

    public void hideProgress() {
        hud = initializeProgress();
        if (hud != null && hud.isShowing())
            hud.dismiss();
    }


}
