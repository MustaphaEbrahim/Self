package com.mustafa.self.UI.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.mustafa.self.R;
import com.mustafa.self.Utilities.SharedPreferenceHelper;

import java.util.Locale;

public abstract class BaseFragment extends Fragment {

    public Context context;
    BaseViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initViewModel();
        initObservers();
        initLoading();
        initOnErrorObserver();
        return view;
    }

    public abstract void initViewModel();
    public abstract void initObservers();
    public abstract void initLoading();
    public abstract void initOnErrorObserver();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }


    protected void setViewModel(BaseViewModel viewModel){
        this.viewModel=viewModel;
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

                    getActivity().finish();
                    context.startActivity(getActivity().getIntent());
                    getActivity().overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);

                }

            }
        });
        builder.show();
    }



}
