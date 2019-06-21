package com.app.mvvmsample.retrofit;

import com.app.mvvmsample.BuildConfig;
import com.app.mvvmsample.app.MyApplication;
import com.app.mvvmsample.utils.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Elmohandes on 20/09/2018.
 */

public class RetrofitSingleton {

    public static Retrofit retrofit = null;
    public static int bufferSize = 256*1024;
    public static Api webService(){
        if (retrofit == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                    .build();


            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    //.addInterceptor(interceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .certificatePinner(new CertificatePinner.Builder()
                            .add("drakeet.me", "sha256/gGOcYKAwzEaUfun6YdxZvFSQq/x2lF/R8UizDFofveY=")
                            .build())
                    .socketFactory(new RestrictedSocketFactory(bufferSize));



            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            }else{
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            okHttpClientBuilder.addInterceptor(logging);
            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newRequest = request.newBuilder().header("Authorization","secret-key")
                            .addHeader("Lang", "en");
                    return  chain.proceed(newRequest.build());
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
        }
        return retrofit.create(Api.class);
    }



}
