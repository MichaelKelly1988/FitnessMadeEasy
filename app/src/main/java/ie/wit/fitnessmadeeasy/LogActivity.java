package ie.wit.fitnessmadeeasy;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);


    EditText uname, pass;
    String unameStr, passStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        uname = (EditText) findViewById(R.id.et_username);
        unameStr = uname.getText().toString();
        pass = (EditText) findViewById(R.id.et_password);
        passStr = pass.getText().toString();


        TextView registerLink = (TextView) findViewById(R.id.regHere); //register link creates a link between the two pages

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LogActivity.this, RegisterActivity.class);
                LogActivity.this.startActivity(registerIntent);  //what this does it creates an intent that opens the registeravtivity then it tells the current activity to perform the intent and open the register page
            }
        });
    }



    // Button login = (Button) findViewById(R.id.login);




    public void onLogClick(View view) {
        if (view.getId() == R.id.login) {

            uname = (EditText) findViewById(R.id.et_username);
            unameStr = uname.getText().toString();
            pass = (EditText) findViewById(R.id.et_password);
            passStr = pass.getText().toString();


            String Password = helper.searchPassstr(unameStr);
            Log.v("pass", passStr);
            Log.v("pass", Password);



            if(uname.getText().length()==0 ){
                Toast pass = Toast.makeText(LogActivity.this, "Enter UserName!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else if(pass.getText().length()==0 ){
                Toast pass = Toast.makeText(LogActivity.this, "Enter Password!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else if (passStr.equals(Password)) {

                Intent i = new Intent(LogActivity.this, NavUser.class);
                i.putExtra("username", unameStr);

                startActivity(i);
            } else {
                Toast temp = Toast.makeText(LogActivity.this, "Username and Password don't Match", Toast.LENGTH_SHORT);
                temp.show();
            }

        }

    }
}





