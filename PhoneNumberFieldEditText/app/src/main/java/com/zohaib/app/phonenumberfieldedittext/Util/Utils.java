package com.zohaib.app.phonenumberfieldedittext.Util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zohaib.app.phonenumberfieldedittext.Models.Country;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by zohaibraza on 06/06/2018.
 */

public class Utils {

    public static String getCountriesJSONList(Context context, int resource) {
        String json;
        try {
            InputStream inputStream = context.getResources().openRawResource(resource);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static int getFlagResource(String country_short_name , Context mContext) {
        if(country_short_name!=null && !country_short_name.equals("") && mContext!=null){
            try{
                return mContext.getResources().getIdentifier("country_" + country_short_name.toLowerCase().trim(), "drawable", mContext.getPackageName());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void hideKeyBoard(Context context, View view) {
        if (context != null && view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyBoard(Context context , EditText et){
        if (context != null && et != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static ArrayList<Integer> getPositionsOfXX(String format){
        ArrayList<Integer> positions= new ArrayList<Integer>();
        for(int i=0; i < format.length();i++){
            if(format.charAt(i)=='X'){
                positions.add(i);
            }
        }
        return positions;
    }

    public static ArrayList<String> getFromattedArrayList(String format){
        ArrayList<String> positions= new ArrayList<String>();
        for(int i=0; i < format.length();i++){
            if(format.charAt(i)!=' ')
                positions.add(format.charAt(i)+"");
            else
                positions.add(" ");
        }
        return positions;
    }
}
