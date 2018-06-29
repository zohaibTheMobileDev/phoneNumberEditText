package com.zohaib.app.phonenumberfieldedittext;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zohaib.app.phonenumberfieldedittext.Adapters.CountrySelectorAdapter;
import com.zohaib.app.phonenumberfieldedittext.Models.Country;
import com.zohaib.app.phonenumberfieldedittext.Util.Parser;
import com.zohaib.app.phonenumberfieldedittext.Util.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner countrySelectorSpinner;
    private EditText PhoneEditText;
    private TextView PhoneTextView;
    private ArrayList<Country> countries= new ArrayList<Country>();
    private CountrySelectorAdapter countrySelectorAdapter;
    private String Field_Format="";
    private ArrayList<Integer> positionsOfX= new ArrayList<Integer>();
    private ArrayList<String> formattedText= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /*Function to Set Views*/
    public void init(){
        countrySelectorSpinner=(Spinner)findViewById(R.id.countrySelectorSpinner);
        PhoneEditText=(EditText)findViewById(R.id.PhoneEditText);
        PhoneTextView=(TextView)findViewById(R.id.PhoneTextView);
        getCountriesFromJson();
        /*You can Define type parameter if you want to define other Layout Resource file as you need*/
        countrySelectorAdapter= new CountrySelectorAdapter(MainActivity.this, "", countries);
        /*Set Adapeter on Spinner*/
        countrySelectorSpinner.setAdapter(countrySelectorAdapter);
        /*Item Selected Listener of Spinner*/
        setItemSlectedListerner();
        /*Text Change Listener of PhoneEditText*/
        setTextWatcher();
    }

    public void setTextWatcher(){
        PhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //PhoneEditText.setText("12");
                computeFormattedText(editable.toString()+"");
//                if(editable.toString().length()>0)
//                    PhoneTextView.setText(editable.toString());
//                else
//                    PhoneTextView.setText(Field_Format);
            }
        });
    }

    /*Item Selected Listener of Country Selector Spinner*/
    public void setItemSlectedListerner(){

        countrySelectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(countries!=null && countries.size()>0){
                    Field_Format=countries.get(i).getFormat();
                    setPhoneEditTextMaxLength(Field_Format);
                    positionsOfX=Utils.getPositionsOfXX(Field_Format+"");
                    formattedText=Utils.getFromattedArrayList(Field_Format+"");
                    PhoneEditText.setText("");
                    setNumber(Field_Format);
                    Utils.showKeyBoard(MainActivity.this, PhoneEditText);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*get Countries From Json File*/
    public void getCountriesFromJson(){

        /*Write this in try-catch so that any exception may not occur getting Json File*/
        try{
            String Json_string= Utils.getCountriesJSONList(MainActivity.this, R.raw.country_codes);
            countries= Parser.parseCountiesFromJSONList(Json_string);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*Function to Set Custom Font*/
    public void setTypeFace(Typeface tf){
        if(tf!=null && PhoneTextView!=null){
            PhoneTextView.setTypeface(tf);
        }
    }

    /*Function to set Max Number of Characters of PhoneEditText*/
    public void setPhoneEditTextMaxLength(String numberFormat){
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(numberFormat.length()+0);
        PhoneEditText .setFilters(filters);
    }

    /*Set Text on PhoneTextView*/
    public void setNumber(String number){
        if(number!=null && !number.equals("") && PhoneTextView!=null)
            PhoneTextView.setText(number);
    }

    /*set Hint*/
    public void setHint(String hint){
        if(hint!=null && !hint.equals("") && PhoneEditText!=null){
            PhoneEditText.setHint(hint);
        }
    }

    /*Function to compute Formatted Text*/
    public void computeFormattedText(String userEnteredText){
        int counter=0;
        for(int i=0; i < positionsOfX.size();i++){
            if(counter<userEnteredText.length()){
                formattedText.set(positionsOfX.get(i), userEnteredText.charAt(counter)+"");
                counter++;
            }
            else{
                formattedText.set(positionsOfX.get(i), "X");
            }
        }
        setFormattedText();
    }

    /*set Fromatted Text*/
    public void setFormattedText(){
        String strngNumber="";
        for(int i=0; i < formattedText.size();i++)
            strngNumber+=formattedText.get(i);
        PhoneTextView.setText(strngNumber);
    }



    @Override
    protected void onResume(){
        super.onResume();
        PhoneEditText.requestFocus();
    }
}
