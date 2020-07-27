package com.example.self.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Youssif Hamdy on 12/16/2019.
 */
public class DialogHelper extends Dialog {

    private Context context;
    String UseVat;
    String UsePriceIncludeVat;

    public DialogHelper(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public DialogHelper(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogHelper(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void SetFullWidth(Boolean FullHeight, Boolean FullWidth) {
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        if (FullHeight) {
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        } else {
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if (FullWidth) {
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        } else {
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;

        }

        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);

    }


}
