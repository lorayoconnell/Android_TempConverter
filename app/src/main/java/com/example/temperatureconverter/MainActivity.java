package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 *  Add:
 *  landscape layout
 *  change "empty" to null? currently newline
 *
 *  When CelsToFahr radio is selected, disable the opposite input textfield? & vice versa
 *
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView convHist;
    private EditText fahr;
    private EditText cels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convHist = findViewById(R.id.convHistory);
        fahr = findViewById(R.id.inputFahr);
        cels = findViewById(R.id.inputCelsius);
        convHist.setMovementMethod(new ScrollingMovementMethod());
    }

    public void convertTemp(View v) {
        RadioGroup radioChoice = findViewById(R.id.conversionChoices);
        int selectedId = radioChoice.getCheckedRadioButtonId();
        RadioButton radioBtn = findViewById(selectedId);
        convHist = findViewById(R.id.convHistory);
        String histStr = convHist.getText().toString();

        switch(radioBtn.getId()) {
            case R.id.celsiusToFahr:
                String testString = convertCtoF();
                convHist.setText(testString + histStr);
                break;
            case R.id.fahrToCelsius:
                String testStr = convertFtoC();
                convHist.setText(testStr + histStr);
                break;
            default:
                Log.d(TAG, "convertTemp: UNKNOWN VIEW SELECTED");
                break;
        }
    }

    public String convertFtoC() {
        String fahrString = fahr.getText().toString();
        if (fahrString.trim().isEmpty())
            return "empty\n";
        else {
            double fahrVal = Double.parseDouble(fahrString);
            double c = (fahrVal - 32.0) / 1.8;
            String cOutput = String.format("%.1f",c);
            cels.setText(cOutput);
            String output = String.format("F to C: %.1f -> %.1f%n", fahrVal, c);
            return output;
        }
    }

    public String convertCtoF() {
        String celsString = cels.getText().toString();
        if (celsString.trim().isEmpty())
            return "empty\n";
        else {
            double celsVal = Double.parseDouble(celsString);
            double f = (celsVal * 1.8) + 32;
            String fOutput = String.format("%.1f",f);
            fahr.setText(fOutput);
            String output = String.format("C to F: %.1f -> %.1f%n", celsVal, f);
            return output;
        }
    }

    public void clearHistory(View v) {
        convHist.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", convHist.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        convHist.setText(savedInstanceState.getString("HISTORY"));
    }

}


/*

    //@Override
    //protected void onSaveInstanceState(Bundle outState) {
    //    Log.d(TAG, "onSaveInstanceState: ");
    //    outState.putString("history", convHist.getText().toString());
    //    super.onSaveInstanceState(outState);
    //}

    //@Override
    //protected void onRestoreInstanceState(Bundle savedInstanceState) {
    //    super.onRestoreInstanceState(savedInstanceState);
    //    Log.d(TAG, "onRestoreInstanceState: ");
    //    if (savedInstanceState == null) {
    //        Log.d(TAG, "onRestoreInstanceState: Bundle is null");
    //    } else {
    //        convHist.setText(savedInstanceState.getString("history"));
    //    }
    //}


 */