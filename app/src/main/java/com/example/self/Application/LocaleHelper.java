package com.example.self.Application;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.example.self.Utilities.SharedPreferenceHelper;

import java.util.Locale;

/**
 * Created by Youssif Hamdy on 3/3/2020.
 */
public class LocaleHelper {

    public static Context setLocale(Context context) {
        String Language = SharedPreferenceHelper.getSharedPreferenceString(context, SharedPreferenceHelper.LANGUAGE_KEY, SharedPreferenceHelper.EN_LANG);
        return updateResources(context, Language);

    }


    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }


}
