package ie.wit.fitnessmadeeasy;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import ie.wit.fitnessmadeeasy.ie.app.models.Base;
import ie.wit.fitnessmadeeasy.ie.app.models.Bmi;
import static ie.wit.fitnessmadeeasy.R.id.imageViewBMI;

public class BmiActivity extends Base {

    Button calc;
    NumberPicker weight;
    NumberPicker height;
    TextView results;
    ImageView image;
    Button btSteps;
    EditText bDate;

    DataBaseHelper helper = new DataBaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        calc = (Button) findViewById(R.id.cal);
        weight = (NumberPicker) findViewById(R.id.et_weight);
        height = (NumberPicker) findViewById(R.id.et_height);
        results = (TextView) findViewById(R.id.result);
        image = (ImageView) findViewById(imageViewBMI);
        btSteps = (Button) findViewById(R.id.btSteps);
        bDate = (EditText) findViewById(R.id.bmiDate);


        image.setImageResource(R.drawable.gymshark);

        weight.setMinValue(30);
        weight.setMaxValue(300);

        height.setMinValue(60);
        height.setMaxValue(300);

        //height.getDouble().toString();
        // weight.getText().toString();



            calc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bDate.getText().length() == 0) {
                        Toast pass = Toast.makeText(BmiActivity.this, "Make Sure to Enter Date!", Toast.LENGTH_SHORT);
                        pass.show();
                    } else {
                        //  int weightlbs = weight.getValue();
                        // int heightcm = height.getValue();

                        // int weights =  weight.getValue();
                        // int heights = height.getValue();


                        // String weightStr = String.valueOf(weights);
                        // String heightStr = String.valueOf(heights);
                        // double kg = 0.45;
                        //String kgs = String.valueOf(kg);
                        // double meter = 0.025;

                        String heightStr = "" + (height.getValue());
                        String weightStr = "" + (weight.getValue());


                        // String heightStr = height.getText().toString();
                        // String weightStr = weight.getText().toString();

                        if (heightStr != null && !"".equals(heightStr)
                                && weightStr != null && !"".equals(weightStr)) {
                            double heightValue = Double.parseDouble(heightStr) / 100;
                            double weightValue = Double.parseDouble(weightStr);


                            // double bmi = weightValue / (heightValue * heightValue);
                            // String b = String.valueOf(bmi);

                            // RegRequest reg = new RegRequest();
                            // reg.setResult(b);
                            double bmi = weightValue / (heightValue * heightValue);


                            //  helper.insertUser(reg);


                            String bmiLabel;


                            if (bmi <= 15) {
                                // bmiLabel = getString(R.string.very_very_skinny);
                                image.setImageResource(R.drawable.skinny);
                            } else if (bmi > 15 && bmi <= 18.5) {
                                //  bmiLabel = getString(R.string.very_skinny);
                                image.setImageResource(R.drawable.skinny);
                            } else if (bmi > 18.5 && bmi <= 25) {
                                //  bmiLabel = getString(R.string.normal);
                                image.setImageResource(R.drawable.thumb);
                            } else {
                                //  bmiLabel = getString(R.string.obese_series_iii);
                                image.setImageResource(R.drawable.fat);

                            }



                            bmiLabel = "" + (new DecimalFormat("##.##").format(bmi));
                            String bmiDate = bDate.getText().toString();

                            //  results = bmiLabel.getText().toString();

                            results.setText(bmiLabel);


                            newBmi(new Bmi(bmiLabel, bmiDate));

                            // helper.getAllBmi(bmiLabel, bmiDate, heightStr, weightStr );


                            BmiRequest b = new BmiRequest();
                            b.setWeight(weightStr);
                            b.setHeight(heightStr);
                            b.setBmi(bmiLabel);
                            b.setDate(bmiDate);


                            helper.insertBmi(b);


                        }
                    }


                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case android.R.id.home:
                    onBackPressed();
                    return true;

                default:

                    return super.onOptionsItemSelected(item);
            }
        }
    }












