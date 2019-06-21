
package com.app.mvvmsample.base;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Gregory Rasmussen on 7/26/17.
 */
public class ParentViewModel extends BaseObservable {
    public MutableLiveData<Object> mMutableLiveData  = new MutableLiveData<>();;
//    public SingleLiveEvent mMutableLiveData =  new SingleLiveEvent();

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static ObservableInt progress;
    public String baseError = "";

    Context context = null;

    public ParentViewModel() {
        progress = new ObservableInt(View.GONE);
    }

    protected void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
