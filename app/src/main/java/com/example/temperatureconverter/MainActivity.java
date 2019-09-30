package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertTemp(View v) {

        String output;

        // if radio button is on Fahrenheit to Celsius
        output = convertFtoC();

        // else radio button is on Cels to Fahr
        output = convertCtoF();


        TextView outputText = findViewById(R.id.convHistory);
        String orig = outputText.getText().toString();
        outputText.setText(output + orig);
    }


    public String convertFtoC() {
        EditText fahr = findViewById(R.id.fahrInput);   // get value from fahr field
        String fahrString = fahr.getText().toString();
        double fahrVal = Double.parseDouble(fahrString);

        double c = (fahrVal - 32.0) / 1.8;
        String output = String.format("F to C: %.1f -> %.1f%n", fahrVal, c);
        return output;
    }

    public String convertCtoF() {
        EditText cels = findViewById(R.id.celcInput);   // get value from cels field
        String celsString = cels.getText().toString();
        double celsVal = Double.parseDouble(celsString);

        double f = (celsVal * 1.8) + 32;
        String output = String.format("C to F: %.1f -> %.1f%n", celsVal, f);
        return output;
    }

    public void clearHistory(View v) {
        TextView outputText = findViewById(R.id.convHistory);
        outputText.setText("");
    }

}
