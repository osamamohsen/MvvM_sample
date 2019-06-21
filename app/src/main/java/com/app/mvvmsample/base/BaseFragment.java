package com.app.mvvmsample.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }


    public void handleActions(String action,String baseError){
        if(getActivity() != null)
            ((ParentActivity)getActivity()).handleActions(action,baseError);
    }


    public void showError(String msg){
        if(getActivity() != null) {
            ((ParentActivity)getActivity()).showError(msg);
        }
    }

    public void toastMessage(String message , int icon , int color){
        ((ParentActivity)getActivity()).toastMessage(message,icon,color);
    }

    public void toastInfo(String message ){
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
