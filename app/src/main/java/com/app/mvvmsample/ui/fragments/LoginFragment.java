package com.app.mvvmsample.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.mvvmsample.R;
import com.app.mvvmsample.base.BaseFragment;
import com.app.mvvmsample.databinding.FragmentLoginBinding;
import com.app.mvvmsample.ui.activities.BaseActivity;
import com.app.mvvmsample.utils.Constants;
import com.app.mvvmsample.viewmodels.LoginViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class LoginFragment extends BaseFragment {
    View rootView;
    FragmentLoginBinding fragmentLoginBinding;
    LoginViewModel loginViewModel;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        bind();
        setEvent();
        return rootView;
    }

    private void bind() {
        loginViewModel = new LoginViewModel();
        fragmentLoginBinding.setLoginViewModel(loginViewModel);
        rootView = fragmentLoginBinding.getRoot();
    }

    private void setEvent() {
        loginViewModel.mMutableLiveData.observe(getActivity(), new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object o) {
                Intent intent = new Intent(getActivity(), BaseActivity.class);
                String action = (String) o;
                handleActions(action, loginViewModel.baseError);
                if(action.equals(Constants.HOME)){
                    getActivity().finish();
                    Toast.makeText(getActivity(), "welcome in Home", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(loginViewModel != null) loginViewModel.reset();
    }

}
