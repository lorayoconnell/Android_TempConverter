package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SharedPreferences selectionPrefs;
    private RadioButton fahrRadio, celsRadio;
    private TextView convHist;
    private EditText fahr;
    private EditText cels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fahrRadio = findViewById(R.id.fahrToCelsius);
        celsRadio = findViewById(R.id.celsiusToFahr);

        selectionPrefs = getSharedPreferences("SELECTIONS", MODE_PRIVATE);
        String pref = selectionPrefs.getString("OPTION", "FAHRTOCELSIUS");

        if (pref.equals("FAHRTOCELSIUS")) {
            fahrRadio.setChecked(true);
        } else {
            celsRadio.setChecked(true);
        }

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
        SharedPreferences.Editor editor = selectionPrefs.edit();

        switch(radioBtn.getId()) {
            case R.id.celsiusToFahr:
                String testString = convertCtoF();
                convHist.setText(testString + histStr);
                editor.putString("OPTION", "CELSIUSTOFAHR");
                break;
            case R.id.fahrToCelsius:
                String testStr = convertFtoC();
                convHist.setText(testStr + histStr);
                editor.putString("OPTION", "FAHRTOCELSIUS");
                break;
            default:
                Log.d(TAG, "convertTemp: UNKNOWN VIEW SELECTED");
                break;
        }
        editor.apply();
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