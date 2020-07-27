package com.example.self.Repository.Server.ResponseBody.Base;

import android.content.Context;


/**
 * Created by Youssif Hamdy on 3/22/2020.
 */
public class ErrorMessages {


    public static String getDefaultMessage(Context context, Result result) {
        int Errorcode = result.getErrorNumber();
        switch (Errorcode) {

            case 10:
                return result.getErrorMessage();
/*

            case 1:
                return context.getString(R.string.no_data_to_display);

            case 43:
                return context.getString(R.string.select_one_food_atleast);
*/

            case 100:
                return result.getErrorMessage();

            default:
                return result.getErrorMessage();

        }
    }


}
