package com.zohaib.app.phonenumberfieldedittext.Adapters;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zohaib.app.phonenumberfieldedittext.Models.Country;
import com.zohaib.app.phonenumberfieldedittext.R;
import com.zohaib.app.phonenumberfieldedittext.Util.Utils;

import java.util.ArrayList;

/**
 * Created by zohaibraza on 06/06/2018.
 */

public class CountrySelectorAdapter extends BaseAdapter {

    Context context;
    String type;
    ArrayList<Country> countries;
    LayoutInflater inflater;

    public CountrySelectorAdapter(Context context, String type, ArrayList<Country> countries) {
        this.context = context;
        this.type = type;
        this.countries = countries;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.country_selector_item ,null);
        ImageView iv = view.findViewById(R.id.country_img);
        TextView name = view.findViewById(R.id.country_short_name);
        TextView code = view.findViewById(R.id.country_code);
        TextView format=view.findViewById(R.id.format);
        name.setText(countries.get(i).getCountry_short_name());
        code.setText(countries.get(i).getDial_code());
        format.setText(countries.get(i).getFormat());
        try{
            iv.setImageResource(Utils.getFlagResource(countries.get(i).getCountry_short_name() , context));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
