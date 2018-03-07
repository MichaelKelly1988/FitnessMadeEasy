package ie.wit.fitnessmadeeasy.ie.app.models;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ie.wit.fitnessmadeeasy.BmiActivity;
import ie.wit.fitnessmadeeasy.DataBaseHelper;
import ie.wit.fitnessmadeeasy.NavUser;
import ie.wit.fitnessmadeeasy.R;
import ie.wit.fitnessmadeeasy.activities.Report;


/**
 * Created by mikel_000 on 10/03/2017.
 */

public class Base extends AppCompatActivity{
    DataBaseHelper helper = new DataBaseHelper(this);

public static List<Bmi> bmis = new ArrayList<>();
    public void newBmi (Bmi bmi) {
        bmis.add(bmi);
        bmi.addAll(bmi.bmiLabel,bmi.bmiDate); //changed Add to addAll to add the date

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem bmi = menu.findItem(R.id.menuBmi);


        if(bmis.isEmpty())
            report.setEnabled(false);
        else
            report.setEnabled(true);



            if(!bmis.isEmpty()){
                report.setVisible(true);
        }
        else {
            report.setVisible(false);
            bmi.setVisible(true);
        }

        return true;
    }



    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }

    public void bmi(MenuItem item)
    {
        startActivity (new Intent(this, BmiActivity.class));
    }




}


