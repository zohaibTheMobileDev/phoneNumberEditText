package com.zohaib.app.phonenumberfieldedittext.Models;

/**
 * Created by zohaibraza on 06/06/2018.
 */

public class Country {

    String country_full_name , dial_code, country_short_name, format;

    public Country(String country_full_name, String dial_code, String country_short_name, String format) {
        this.country_full_name = country_full_name;
        this.dial_code = dial_code;
        this.country_short_name = country_short_name;
        this.format = format;
    }

    public String getCountry_full_name() {
        return country_full_name;
    }

    public void setCountry_full_name(String country_full_name) {
        this.country_full_name = country_full_name;
    }

    public String getDial_code() {
        return dial_code;
    }

    public void setDial_code(String dial_code) {
        this.dial_code = dial_code;
    }

    public String getCountry_short_name() {
        return country_short_name;
    }

    public void setCountry_short_name(String country_short_name) {
        this.country_short_name = country_short_name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
