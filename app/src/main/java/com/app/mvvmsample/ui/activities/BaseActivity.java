package com.app.mvvmsample.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.app.mvvmsample.R;
import com.app.mvvmsample.base.ParentActivity;
import com.app.mvvmsample.base.ParentViewModel;
import com.app.mvvmsample.databinding.ActivityBaseBinding;
import com.app.mvvmsample.ui.fragments.LoginFragment;
import com.app.mvvmsample.utils.Constants;
import com.app.mvvmsample.utils.MovementHelper;
import com.app.mvvmsample.viewmodels.BaseViewModel;

public class BaseActivity extends ParentActivity {
    ActivityBaseBinding activityBaseBinding;
    ParentViewModel baseViewModel;
    boolean notification_checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LanguagesHelper.setLanguage(this,"ar");
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        baseViewModel = new BaseViewModel();
        activityBaseBinding.setBaseViewModel(baseViewModel);
        Log.e("page", "Done");
        MovementHelper.addFragment(this, new LoginFragment(), "");
    }
}
