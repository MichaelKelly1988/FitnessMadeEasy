package ie.wit.fitnessmadeeasy;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);


    EditText name;
    EditText username;
    EditText password1;
    EditText password2;

    String namestr;
    String usernamestr;
    String password1str;
    String password2str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = (EditText) findViewById(R.id.et_name);
        username = (EditText) findViewById(R.id.et_username);
        password1 = (EditText) findViewById(R.id.et_password);
        password2 = (EditText) findViewById(R.id.et_password);

      //  namestr = name.getText().toString();
     //   usernamestr = username.getText().toString();
     //   password1str = password1.getText().toString();
     //   password2str = password2.getText().toString();
        // final EditText password2 = (EditText) findViewById(R.id.et_password);


        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (name.getText().length() == 0) {

                    name.setError("Fill Out");
                } else if (username.getText().length() == 0) {

                    username.setError("Fill Out");
                }


            }

        });


    }


    public void onRegClick(View v) {

        if (v.getId() == R.id.confirm) {
            //  EditText age = (EditText) findViewById(R.id.et_age); //age is a final variable and is only assigned to activity_register as a view


            EditText name = (EditText) findViewById(R.id.et_name);
            EditText username = (EditText) findViewById(R.id.et_username);
            EditText password1 = (EditText) findViewById(R.id.et_password);
            EditText password2 = (EditText) findViewById(R.id.et_password2);

            namestr = name.getText().toString();
            usernamestr = username.getText().toString();
            String storedUser = helper.Exist(usernamestr);
            password1str = password1.getText().toString();
            String storedPass = helper.Exists(password1str);
            password2str = password2.getText().toString();

            if (usernamestr.equals(storedUser)) {
                Toast pass = Toast.makeText(RegisterActivity.this, "Username already exist!", Toast.LENGTH_SHORT);
                pass.show();

            }
            else if(password1str.equals(storedPass)) {
                Toast pass = Toast.makeText(RegisterActivity.this, "Password already exist!", Toast.LENGTH_SHORT);
                pass.show();

            }



               else if (namestr.equals("")) {
                    Toast pass = Toast.makeText(RegisterActivity.this, "Enter Name!", Toast.LENGTH_SHORT);
                    pass.show();
                } else if (usernamestr.equals("")) {
                    Toast pass = Toast.makeText(RegisterActivity.this, "Enter UserName!", Toast.LENGTH_SHORT);
                    pass.show();

                    //If username Already Exists
                    if (usernamestr.equals(helper.getUsername())) {
                        Toast.makeText(RegisterActivity.this,
                                "Username Already Exists!!!", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }


                } else if (password1str.equals("")) {
                    Toast pass = Toast.makeText(RegisterActivity.this, "Enter Password!", Toast.LENGTH_SHORT);
                    pass.show();
                } else if (!password1str.equals(password2str)) {
                    //popup message
                    Toast pass = Toast.makeText(RegisterActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                    pass.show();
                } else {

                    // String namestr = name.getText().toString();
                    //insert details
                    RegRequest reg = new RegRequest();
                    reg.setEt_name(namestr);
                    reg.setEt_username(usernamestr);
                    reg.setEt_password(password1str);


                    Toast success = Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT);
                    success.show();


                    Intent i = new Intent(RegisterActivity.this, NavUser.class);



                    helper.insertUser(reg);
                    startActivity(i);
                }


            }
        }

    }
