package ie.wit.fitnessmadeeasy.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import ie.wit.fitnessmadeeasy.R;
import ie.wit.fitnessmadeeasy.ie.app.models.Base;
import ie.wit.fitnessmadeeasy.ie.app.models.Bmi;

public class Report extends Base {
    ListView listView;
  //   EditText edtSearch;
  //  final BmiAdapter b = new BmiAdapter(this, bmis);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  edtSearch = (EditText)findViewById(R.id.edtSearch);
        listView = (ListView) findViewById(R.id.reportList);
        final BmiAdapter b = new BmiAdapter(this, bmis);
        listView.setAdapter(b);








        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {



                AlertDialog.Builder alertD=new AlertDialog.Builder(Report.this);

                alertD.setTitle("Delete?");
                alertD.setMessage("Are you sure you want to delete your Bmi ");
                final int positionToRemove = position;

                alertD.setNegativeButton("Cancel" , null);
                alertD.setPositiveButton("Ok", new AlertDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        bmis.remove(positionToRemove);
                        b.notifyDataSetChanged();
                    }
                });


                AlertDialog alertdialog = alertD.create();

                alertdialog.show();
                Button positive = alertdialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positive.setTextColor(Color.BLACK);
                Button negative = alertdialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setTextColor(Color.BLACK);

            }
        });


    }







}

class BmiAdapter extends ArrayAdapter<Bmi>{

    public List<Bmi> bmis;
    private Context context;

    public BmiAdapter(Context context, List<Bmi> bmis) {

        super(context, R.layout.row_bmi, bmis);
        this.context = context;
        this.bmis = bmis;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflater.inflate(R.layout.row_bmi,parent, false);
        Bmi bmi = bmis.get(position);


        TextView result = (TextView) view.findViewById(R.id.row_amount);
        TextView Dat = (TextView) view.findViewById(R.id.row_method);

        result.setText(bmi.bmiLabel);
        Dat.setText(bmi.bmiDate);



        return view;
    }



    @Override
    public int getCount()
    {
        return bmis.size();
    }







}






