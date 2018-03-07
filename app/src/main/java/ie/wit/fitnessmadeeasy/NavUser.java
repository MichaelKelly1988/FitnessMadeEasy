package ie.wit.fitnessmadeeasy;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class NavUser extends AppCompatActivity {

   // private TextView mTextMessage;
    DataBaseHelper db = new DataBaseHelper(this);
    // RegRequest reg = new RegRequest();

    ListView myList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(NavUser.this, StopWatchActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    Intent in = new Intent(NavUser.this, BmiActivity.class);
                    startActivity(in);
                    return true;
                case R.id.navigation_notifications:
                  //  mTextMessage.setText(R.string.title_notifications);
                    Intent inte = new Intent(NavUser.this, MapsActivity.class);
                    startActivity(inte);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_user);

       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // Get ListView object from xml
        myList = (ListView) findViewById(R.id.list);

        String[] nameAndUsername = db.getNameAndUsername();
        String name = nameAndUsername[0];
        String username = nameAndUsername[1];



        String[] values = new String[]{


                "Name:           " + name,
                "Username:   " + username,
                "Date: " + db.getDate(),
                "BMI: " + db.getBmi(),
                "Weight: " + db.getWeight(),
                "Height: " + db.getHeight()

        };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        myList.setAdapter(adapter);

        // ListView Item Click Listener
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position + 1;

                // ListView Clicked item value
                String itemValue = (String) myList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
            }

        });

    }
}
