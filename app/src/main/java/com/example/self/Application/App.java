package com.example.self.Application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.PropertyName;


/**
 * Created by Youssif Hamdy on 3/3/2020.
 */

public class App extends Application {
    //  private static Context context;

    //**&&**

    @PropertyName("title")
    private String title;

    @PropertyName("thought")
    private String thought;

    @PropertyName("imageUrl")
    private String imageUrl;

    @PropertyName("timeAdded")
    private Timestamp timeAdded;

    @PropertyName("userName")
    private String userName;

    @PropertyName("id")
    private String id;


    //   private static App instance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

/*    public static App getInstance(){
        if (instance == null)
            instance = new App();

        return instance;
    }*/

    public App() {
    }

    //**&&***


/*
    public static Context getAppContext() {
        return App.context;
    }
*/

/*
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.setLocale(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // App.context = getApplicationContext();
        LocaleHelper.setLocale(getApplicationContext());

    }
*/


}

