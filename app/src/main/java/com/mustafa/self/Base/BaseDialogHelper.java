package com.mustafa.self.Base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.mustafa.self.Utilities.SharedPreferenceHelper;

import java.util.Locale;

/**
 * Created by Youssif Hamdy on 3/2/2020.
 */
public abstract class BaseDialogHelper extends Dialog {

    protected Context context;

    protected OnDialogActionResponse onDialogActionResponse;

    public BaseDialogHelper(@NonNull Context context, OnDialogActionResponse onDialogActionResponse) {
        super(context);
        this.context = context;
        this.onDialogActionResponse = onDialogActionResponse;
        setupDialogProperties();
        setupDialogView();
        show();
    }


    protected abstract int getContentViewById();

    protected abstract void setupDialogView();


    private void setupDialogProperties() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewById());
        SetFullWidth();
    }

    public void SetFullWidth() {
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);

    }


    public int getLanguageId() {

        String Language = SharedPreferenceHelper.getSharedPreferenceString(context, SharedPreferenceHelper.LANGUAGE_KEY, Locale.getDefault().getLanguage());
        if (Language.equalsIgnoreCase("ar")) {
            return 1;
        }
        if (Language.equalsIgnoreCase("en")) {
            return 2;

        }

        return 2;
    }


}
