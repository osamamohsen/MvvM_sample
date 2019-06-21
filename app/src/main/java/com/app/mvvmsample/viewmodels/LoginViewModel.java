
package com.app.mvvmsample.viewmodels;

import android.util.Log;

import com.app.mvvmsample.R;
import com.app.mvvmsample.base.ParentViewModel;
import com.app.mvvmsample.models.login.LoginRequest;
import com.app.mvvmsample.models.login.LoginResponse;
import com.app.mvvmsample.retrofit.RetrofitSingleton;
import com.app.mvvmsample.utils.Constants;

import androidx.databinding.ObservableField;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gregory Rasmussen on 7/26/17.
 */
public class LoginViewModel extends ParentViewModel {
    public ObservableField usernameOrPhoneError,passwordError;
    public LoginResponse loginResponse = null;
    public LoginRequest loginRequest;

    public LoginViewModel() {
        init();
    }

    private void init() {
        loginRequest = new LoginRequest("","");
        usernameOrPhoneError = new ObservableField();
        passwordError = new ObservableField();
    }


    public void loginSubmit(){
        Log.e("start","submit");
        if (validateInput()) {
            Log.e("callService","done");
            callService();
        }
    }

    private boolean validateInput() {
        Log.e("validate","submit");
        boolean valid = true;
        String enter = " please enter ";
        if (loginRequest.username.equalsIgnoreCase("")) {
            usernameOrPhoneError.set(enter + "username");
            valid = false;
        }
        if (loginRequest.password.equalsIgnoreCase("")) {
            passwordError.set(enter + "password");
            valid = false;
        }
        return valid;
    }


    private void callService() {
        mMutableLiveData.setValue(Constants.SHOW_PROGRESS);
        String firebase_token = "token";
        compositeDisposable.add(RetrofitSingleton.webService().login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(final LoginResponse resp) throws Exception {
                        mMutableLiveData.setValue(Constants.HIDE_PROGRESS);
                        loginResponse = resp;
                        if(loginResponse != null){
                            if(loginResponse.status.equals("failed")){
                                baseError = loginResponse.msg;
                                mMutableLiveData.setValue(Constants.ERROR_RESPONSE);
                            }else if(loginResponse.status.equals("successful")){
                                if(loginResponse.data != null && loginResponse.data.activationStatus.equals("1")){
                                   //save user
                                }else if(loginResponse.data != null && loginResponse.data.activationStatus.equals("0")){
                                    baseError = loginResponse.msg;
                                    //write code
                                }
                            }else
                            {
                                baseError = "please try again";
                                mMutableLiveData.setValue(Constants.ERROR_RESPONSE);
                            }
                        }else{
                            baseError = "please try again";
                            mMutableLiveData.setValue(Constants.ERROR_RESPONSE);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mMutableLiveData.setValue(Constants.HIDE_PROGRESS);
                        Log.e("error",throwable.getMessage());
                        mMutableLiveData.setValue(Constants.FAILURE_CONNECTION);
                    }
                }));
    }


//    public void socialLogin(SocialLoginRequest socialLoginRequest){
//        showProgress();
//        compositeDisposable.add(RetrofitSingleton.webService(context).socialLogin(socialLoginRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(final LoginResponse resp) throws Exception {
//                        hideProgress();
//                        LoginResponse loginResponse = resp;
//                        Log.e("code",loginResponse.msg);
//                        if(loginResponse != null){
//                            Log.e("social",loginResponse.msg);
//                            if(loginResponse.status.equals("not active")) {
//                                mMutableLiveData.setValue(Constants.WRITE_CODE);
//                            }else if(loginResponse.status.equals("failed")){
//                                Toast.makeText(context, ""+loginResponse.msg, Toast.LENGTH_SHORT).show();
//                            }else if(loginResponse.status.equals("success")){
//                                Log.e("social",loginResponse.status);
//                                Toast.makeText(context, ""+loginResponse.msg, Toast.LENGTH_SHORT).show();
//                                if(loginResponse.data != null){
//                                    loginResponse.data.type = socialLoginRequest.type;
//                                    UserHelper.saveUserDetails(context,loginResponse.data);
//                                    mMutableLiveData.setValue(Constants.HOME);
//                                }
//                            }
//                        }else
//                            Toast.makeText(context, ""+context.getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        hideProgress();
//                        Toast.makeText(context, "" + context.getString(R.string.please_check_connection), Toast.LENGTH_SHORT).show();
//                    }
//                }));
//    }


    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
    }



}
