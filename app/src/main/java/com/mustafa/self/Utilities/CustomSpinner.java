package com.mustafa.self.Utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mustafa.self.R;

import java.util.List;

/**
 * Created by Youssif Hamdy on 3/3/2020.
 */
public class CustomSpinner extends RelativeLayout {
    Context mContext;
    Spinner mSpinner;
    public static final int COUNTRIES = 1;
    public static final int LANGUAGES = 2;
    public static final int SPECIALITIES = 3;
    public static final int CITIES = 4;


    TextView txtError;
    TextView txtTitle;
    RelativeLayout spinnerBackground;
    ImageView ivArrow;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private ArrayAdapter<Object> spinnerArrayAdapterNew;

    public CustomSpinner(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs, Context mContext) {
        super(context, attrs);
        this.mContext = context;
        init();
        setAttributeSet(attrs);

    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
        setAttributeSet(attrs);


    }


    private void setAttributeSet(AttributeSet attrs) {
        String title = "", listColor = "";
        boolean showBackground = true;
        boolean showTitle = true;
        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.CustomSpinner);

        try {
            title = arr.getString(R.styleable.CustomSpinner_title);
            showBackground = arr.getBoolean(R.styleable.CustomSpinner_showbackground, true);
            showTitle = arr.getBoolean(R.styleable.CustomSpinner_showTitle, true);
            listColor = arr.getString(R.styleable.CustomSpinner_listColor);
        } finally {
            arr.recycle();
            txtTitle.setText(title);
            if (showBackground) {
                spinnerBackground.setBackgroundResource(R.drawable.spinner_background);
            }
            if (!showTitle) {
                txtTitle.setVisibility(View.GONE);
            }
            if (listColor != null) {
                if (listColor.matches("blue"))
                    ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_blue_arrow_down));
            }
        }

    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, Context mContext) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setAttributeSet(attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.custome_spinner, this);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        spinnerBackground = (RelativeLayout) findViewById(R.id.spinner_container);
        txtTitle = (TextView) findViewById(R.id.spinner_title);
        txtError = (TextView) findViewById(R.id.error_msg);
        ivArrow = (ImageView) findViewById(R.id.iv_arrow);
    }

    public Spinner getSpinner() {
        return mSpinner;
    }


    public void SetError(String error) {
        txtError.setText(error);
        txtError.setVisibility(View.VISIBLE);
        txtError.requestFocus();
    }


    public void hideError() {
        txtError.setText("");
        txtError.setVisibility(View.GONE);
    }


    public void DefineSpinner(final List<Object> mListOfObject, final int SpinnerType, final boolean emptyValue) {
        // Initializing an ArrayAdapter
        spinnerArrayAdapterNew = new ArrayAdapter<Object>(mContext, android.R.layout.simple_spinner_dropdown_item) {
            @Override
            public boolean isEnabled(int position) {
                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
              /*  if (position == 0 && !(defaultValue.isEmpty())) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {*/
                tv.setTextColor(Color.BLACK);
                //}
                return view;

            }
        };

        spinnerArrayAdapterNew.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (emptyValue) {
            spinnerArrayAdapterNew.add("");
        }


        if (mListOfObject != null) {
            for (int i = 0; i < mListOfObject.size(); i++) {
                Object data = mListOfObject.get(i);
                if (data != null) {
                    spinnerArrayAdapterNew.add(data);
                }

            }
        }


        mSpinner.setAdapter(spinnerArrayAdapterNew);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           /*     int index = i;
                if (AddEmptyValue) {
                    index = index - 1;
                }

                if (index < 0) {
                    EventBus.getDefault().post(new SpinnerEvent(SpinnerType, null));
                } else {
                    EventBus.getDefault().post(new SpinnerEvent(SpinnerType, mListOfObject.get(index)));
                }*/


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public ArrayAdapter<String> getSpinnerArrayAdapter() {
        return spinnerArrayAdapter;
    }

    public int getSelectedItemPosition() {

        return mSpinner.getSelectedItemPosition();

    }
}

