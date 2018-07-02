package com.zohaib.app.phonenumberfieldedittext.Util;

import com.zohaib.app.phonenumberfieldedittext.Models.Country;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zohaibraza on 06/06/2018.
 */

public class Parser {

    public static ArrayList<Country> parseCountiesFromJSONList(String countriesJson){
        ArrayList<Country> countries= new ArrayList<Country>();
        String country_full_name="" , dial_code="", country_short_name="", format="";
        try{
            JSONArray jsonArray= new JSONArray(countriesJson);
            for(int i=0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.has("name"))
                    country_full_name=jsonObject.getString("name");
                if(jsonObject.has("dial_code"))
                    dial_code=jsonObject.getString("dial_code");
                if(jsonObject.has("code"))
                    country_short_name=jsonObject.getString("code");
                if(jsonObject.has("format"))
                    format=jsonObject.getString("format");
                Country country= new Country(country_full_name+"",dial_code+"",country_short_name+"",format+"");
                countries.add(country);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }
}
