package com.example.mgota.aad_ex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText price, rating;
    private TextView calcFail, calcResult;
    private Button compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        price = (EditText) findViewById(R.id.price);
        rating = (EditText) findViewById(R.id.rating);
        compute = (Button) findViewById(R.id.compute);
        calcFail = (TextView) findViewById(R.id.calcFail);
        calcResult = (TextView) findViewById(R.id.calcResult);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean calcCheck = true;       //boolean to check if all calculations go accordingly
                int ratingInput = 0;    //rating inputted by user
                double priceInput = 0, priceTip = 0, priceFinal = 0;    //variables for calculation
                String stringPrice, stringRating;   //string for reading

                stringPrice = price.getText().toString();   //reads input
                if(TextUtils.isEmpty(stringPrice)) calcCheck = false;   //if empty, bool set to false
                else priceInput = Double.parseDouble(price.getText().toString()); //other, convert input to float

                stringRating = rating.getText().toString(); //rating inputted by user
                if(TextUtils.isEmpty(stringRating)) calcCheck = false;  //if empty, bool set to false
                else {
                    ratingInput = Integer.parseInt(rating.getText().toString());    //read rating
                    if (ratingInput <= 3 && ratingInput >= 1) priceTip = 1.10;
                    else if (ratingInput <= 5 && ratingInput >= 4) priceTip = 1.13;
                    else if (ratingInput <= 7 && ratingInput >= 6) priceTip = 1.15; //set tip for calculation
                    else if (ratingInput <= 9 && ratingInput >= 8) priceTip = 1.20;
                    else if (ratingInput == 10) priceTip = 1.25;
                    else if (ratingInput == 0 || ratingInput > 10) calcCheck = false;   //unchecks bool if not within range
                }

                if(calcCheck == false){     //if bool has been unchecked because of incorrect input
                    calcFail.setVisibility(View.VISIBLE);   //show error message
                    calcResult.setVisibility(View.INVISIBLE);
                }
                else{                       //all input is correct, make the necessary calculations
                    priceFinal = priceInput * priceTip;     //Total price after tip
                    priceTip = priceFinal - priceInput;     //Tip
                    String formatInput = String.format("%.2f", priceInput); //
                    String formatTip = String.format("%.2f", priceTip);     //string formatting
                    String formatTotal = String.format("%.2f", priceFinal); //
                    calcFail.setVisibility(View.INVISIBLE);
                    calcResult.setVisibility(View.VISIBLE);
                    calcResult.setText("Calculation complete!\n" +
                            "Initial Price: $" + formatInput + "\n" +
                            "Rating: " + ratingInput + "\n" +
                            "Tip: $" + formatTip + "\n" +               //display results in TextView
                            "Total Price: $" + formatTotal);
                }
            }
        });
    }
}
