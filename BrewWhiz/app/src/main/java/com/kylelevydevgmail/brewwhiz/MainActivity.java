package com.kylelevydevgmail.brewwhiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner currentFlavor;
    private Spinner desiredFlavor;
    private static final String[] FLAVORS = {"Aromatic","Astringent","Balanced","Beefy","Big","Bitter","Bland",
            "Bulky","Buttery","Creamy","Delicate","Dilute","Dry","Dull","Dusty","Empty","Faint","Flimsy","Fragile",
            "Fruity","Gentle","Harsh","Heavy","Hefty","Insipid","Intense","Limp","Luscious","Mouth-filling","Muted",
            "Nuanced","Nutty","Overwhelming","Pleasing","Plump","Potent","Powdery","QuickFinish","Rich","Robust","Salty",
            "Scrawny","Severe","Slender","Smooth","Soft","Soupy","Sour","Sparse","Sticky","Strong","Substantial","Sweet",
            "Tasty","Tea-like","Thick","Thin","Transparent","Underwhelming","Vegetal","Watery"};
    
    private String currentFlavorString;
    private String desiredFlavorString;
    private TextView directionsText;
    private Button brewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CoffeeCompass covfefe = new CoffeeCompass();


        currentFlavor = findViewById(R.id.currentFlavor);
        desiredFlavor = findViewById(R.id.desiredFlavor);
        directionsText = findViewById(R.id.directionsText);
        brewButton = findViewById(R.id.brewButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, FLAVORS);
        currentFlavor.setAdapter(adapter);
        desiredFlavor.setAdapter(adapter);

        //Sets default desired flavor to "Balanced"
        desiredFlavor.setSelection(2);

        currentFlavor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                currentFlavorString = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //currentFlavorString = null;
            }
        });

        desiredFlavor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                desiredFlavorString = (String) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //desiredFlavorString = null;
            }
        });

        brewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float[] directions = covfefe.calculateBrewChanges(currentFlavorString, desiredFlavorString);
                String textDirections = useDirections(directions);
                directionsText.setText(textDirections);
            }
        });


    }

    private String useDirections(float[] directions)  {
        String concat = "";
        String coffeeString = "";
        String extractString = "";
        String intensity = determineIntensity(directions[2]);
        String endString = "";

        //If both coffee and extract strings will be used, we will need to concatenate them
        if(directions[0] != 0 && directions[1] != 0){
            concat = "and use ";
        }

        if(directions[0] == 0 && directions[1] == 0 && directions[2] == 0){
            extractString =  "You already have the desired cup!";
        }

        if(directions[1] == 1){
            extractString = "Extract " + intensity + "more ";
        }
        else if(directions[1] == -1){
            extractString = "Extract " + intensity + "less ";
        }

        if(directions[0] == 1){
            coffeeString = intensity + "more coffee.";
        } else if(directions[0] == -1){
            coffeeString = intensity + "less coffee.";
        }

        //If there isn't an extract phrase, capitalize the first letter of the coffee phrase.
        //If there isn't a coffee phrase, put a period at the end.
        if(directions[1] == 0 && directions[0] != 0){
            coffeeString = coffeeString.substring(0,1).toUpperCase() + coffeeString.substring(1);
        } else if(directions[0] == 0 && directions[1] != 0){
            extractString = extractString.substring(0,extractString.length()-1) + ".";
        }

        endString = extractString + concat + coffeeString;
        return endString;
    }

    private String determineIntensity(float intensityNum){
        String word = "";
        if(intensityNum <=2) {
            word = "a tiny bit ";
        } else if(intensityNum <= 3){
            word = "a little bit ";
        } else if(intensityNum <= 4){
            word = "a little ";
        } else if(intensityNum <= 5){
            word = "";
        } else if(intensityNum > 5){
            word = "a fair amount ";
        }

        return word;
    }
}
