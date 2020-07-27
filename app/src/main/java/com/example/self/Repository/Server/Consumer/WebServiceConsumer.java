package com.example.self.Repository.Server.Consumer;

import android.content.Context;

import com.example.self.Application.App;
import com.example.self.R;
import com.example.self.Repository.DataProviders.Base.OnDataProviderResponseListener;
import com.example.self.Repository.Server.ResponseBody.Base.ResponseObject;
import com.example.self.Repository.Server.ResponseBody.Base.Result;
import com.example.self.Utilities.NetworkUtil;
import com.example.self.Utilities.SharedPreferenceHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceConsumer {
 /*   public static WebServiceConsumer sharedInstance = new WebServiceConsumer();

    private String baseServerUrl;
    private Retrofit retrofit;
    private Context context;
    private MyApiEndpointInterface distTrackApi;

    private WebServiceConsumer() {
        defineRetrofit();
    }

    private void defineRetrofit() {

        context = App.getAppContext();
        baseServerUrl = SharedPreferenceHelper.getSharedPreferenceString(context, Keys.SERVER_KEY.value, Keys.DEFAULT_SERVER_URL.value);
        baseServerUrl = baseServerUrl + Keys.SERVICE_PATH.value;
        retrofit = this.buildRetrofit();
        distTrackApi = retrofit.create(MyApiEndpointInterface.class);
    }


    public void redefineServerUrl() {
        defineRetrofit();
    }

    public MyApiEndpointInterface getApi() {
        return distTrackApi;
    }

    public <T extends ResponseObject> void callService(Call<T> requestCall, OnDataProviderResponseListener<T> onResponseListener) {

        if (NetworkUtil.isConnectionAvaiable(context)) {
            requestCall.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    handleResponse(response, onResponseListener);
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    Result result = new Result();
                    result.setErrorMessage(t.getLocalizedMessage());
                    onResponseListener.onError(result);

                }

            });
        } else {
            Result result = new Result();
            result.setErrorMessage(context.getString(R.string.no_internet_connection));
            onResponseListener.onError(result);
        }

    }

    private Retrofit buildRetrofit() {
        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("token", "Loolooa1098767890")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        try {

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseServerUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    //     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return retrofit;

    }

    private <T extends ResponseObject> void handleResponse(Response<T> response, OnDataProviderResponseListener<T> onResponseListener) {
        try {
            if (response.isSuccessful()) {
                ResponseObject responseBody = response.body();
                if (responseBody.getResult() != null) {
                    if (responseBody.getResult().getErrorNumber() == 0) {
                        onResponseListener.onSuccess(response.body());
                    } else {
                        onResponseListener.onError(responseBody.getResult());
                    }
                } else {
                    Result result = new Result();
                    result.setErrorMessage(response.message());
                    onResponseListener.onError(result);
                }
            } else {
                String errorMessage = context.getString(R.string.server_error);
                String errorNo = response.code() + "";
                Result result = new Result();

               *//* if (response.errorBody() != null) {
                    if(response.code()==400){
                        result.setErrorMessage(context.getString(R.string.unauthorized_access));
                    }else {
                        result.setErrorMessage(errorMessage);
                    }

                } else {
                    result.setErrorMessage(errorMessage);
                }
*//*
                result.setErrorMessage(errorMessage + " " + errorNo);

                onResponseListener.onError(result);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result();
            result.setErrorMessage(e.getLocalizedMessage());
            onResponseListener.onError(result);
        }
    }


    public enum Keys {

        DEFAULT_SERVER_URL("http://mapp.yemensoft.net"),
        SERVICE_PATH("/OnyxRestaurantCustomerService/Service.svc/"),
        SERVER_KEY("server_url");

        public String value;

        Keys(String value) {
            this.value = value;
        }

    }*/
}