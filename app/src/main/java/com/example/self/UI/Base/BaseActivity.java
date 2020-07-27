package com.example.self.UI.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.example.self.Application.LocaleHelper;
import com.example.self.R;
import com.example.self.Utilities.SharedPreferenceHelper;

import java.util.Locale;

/**
 * Created by Youssif Hamdy on 12/8/2019.
 */
public abstract class BaseActivity extends AppCompatActivity {


    public static Snackbar snackbar;
    BaseViewModel viewModel;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase));

    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25) {
            createConfigurationContext(overrideConfiguration);
        }
        super.applyOverrideConfiguration(getResources().getConfiguration());
    }


    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        initViewModel();
        initObservers();
        initErrorObservers();
        initLoadingObservers();

    }

    public abstract void initViewModel();
    public abstract void initObservers();
    public abstract void initErrorObservers();
    public abstract void initLoadingObservers();

    protected void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.setContext(context);
    }

    public static ProgressDialog progressDialog;

    public static void ShowProgress(String Title, String Message, Context context) {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog = ProgressDialog.show(context, Title,
                        Message, true);
            }
        } else {
            progressDialog = ProgressDialog.show(context, Title,
                    Message, true);
        }
    }


    public static void HideProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

    public void DefineToolbar(Toolbar toolbar, String Title ) {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static void showYesNoMessage(Context context, String title, String message, DialogInterface.OnClickListener onClickListener){

        MaterialAlertDialogBuilder builder =
                new MaterialAlertDialogBuilder(context, R.style.AlertDialogStyle);

        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.btn_yes, onClickListener);
        builder.setNegativeButton(context.getString(R.string.btn_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public static void showLoginDialog(Context context) {

        BaseActivity.showYesNoMessage(
                context, context.getString(R.string.login_signup),
                context.getString(R.string.need_to_login), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
             /*               Intent intent = new Intent(context, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra(MainActivity.Keys.CAME_FROM.value,MainActivity.Keys.CHECKOUT.value);
                            context.startActivity(intent);*/

                        }
                    }
                });


    }


    public void changeLanguage() {
        CharSequence[] LanguagesList = new CharSequence[1];
        String CurrentLanguage = SharedPreferenceHelper.getSharedPreferenceString(context, SharedPreferenceHelper.LANGUAGE_KEY, Locale.getDefault().getLanguage());
        switch (CurrentLanguage) {
            case "ar":
                LanguagesList[0] = context.getString(R.string.english);
                break;
            case "en":
                LanguagesList[0] = context.getString(R.string.arabic);

        }


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(getString(R.string.change_language));

        builder.setItems(LanguagesList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                CharSequence ChoosenLanguage = LanguagesList[item];
                if (ChoosenLanguage != null) {
                    String arabic = context.getString(R.string.arabic);
                    String english = context.getString(R.string.english);
                    //  String indonesian = context.getString(R.string.indonesian);

                    if (ChoosenLanguage.toString().equalsIgnoreCase(arabic)) {
                        SharedPreferenceHelper.setSharedPreferenceString(context, SharedPreferenceHelper.LANGUAGE_KEY, "ar");
                    }
                    if (ChoosenLanguage.toString().equalsIgnoreCase(english)) {
                        SharedPreferenceHelper.setSharedPreferenceString(context, SharedPreferenceHelper.LANGUAGE_KEY, "en");
                    }

                    finish();
                    context.startActivity(getIntent());
                    overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);

                }

            }
        });
        builder.show();
    }


    public static String getImagePath(String img_no) {
        String url = "http://mapp.yemensoft.net/OnyxRestaurantCustomerImages/" + img_no;
        return url.toLowerCase();
    }
    public static String replaceArabicNumbers(String original) {
        return original.replaceAll("٠","0")
                .replaceAll("١","1")
                .replaceAll("٢","2")
                .replaceAll("٣","3")
                .replaceAll("٤","4")
                .replaceAll("٥","5")
                .replaceAll("٦","6")
                .replaceAll("٧","7")
                .replaceAll("٨","8")
                .replaceAll("٩","9");

    }

}