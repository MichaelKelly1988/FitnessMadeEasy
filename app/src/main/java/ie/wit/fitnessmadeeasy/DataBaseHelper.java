package ie.wit.fitnessmadeeasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import static ie.wit.fitnessmadeeasy.R.id.count;

/**
 * Created by mikel_000 on 14/02/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 21;
    private static final String DATABASE_NAME = "users.db";

    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";


    private static final String T_NAME = "bmi";
    private static final String _ID = "id";
    private static final String _WEIGHT = "weight";
    private static final String _HEIGHT = "height";
    private static final String _BMI = "bmi";
    private static final String _DATE = "date";

    private static final String TABLE = "run";
    private static final String _ID_ = "id";
    private static final String _DIST = "distance";
    private static final String _USERNAME = "usernam";




    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table users (id integer primary key autoincrement, " +
    " name text not null, username text not null unique, password text not null);";

    private static final String TABLE_C = "create table bmi (id integer primary key autoincrement, " +
            " weight text not null, height text not null, bmi text not null, date text not null);";

    private static final String TABLE_RUN = "create table run (id integer primary key autoincrement, " +
            " distance text not null);";

    private final Context c;
    private DataBaseHelper helper;



    public DataBaseHelper open() throws SQLException {
        helper = new DataBaseHelper(c);
        db = helper.getWritableDatabase();
        return this;
    }

    public DataBaseHelper(Context context)
    {

        super(context , DATABASE_NAME , null, DATABASE_VERSION);
        this.c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(TABLE_CREATE);
        this.db = db;



    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        String querys = "DROP TABLE IF EXISTS " + T_NAME;
        db.execSQL(querys);
        String queryse = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(queryse);



        db.execSQL(TABLE_C);
        this.db = db;

        db.execSQL(TABLE_RUN);
        this.db = db;







        this.onCreate(db);

    }



    public boolean isExist(String uName){
        String query = "SELECT * FROM users WHERE username= "+uName;
        Cursor row = db.rawQuery(query, null);
        return row.moveToFirst();

    }

    public void update(int id, String text2, String text3, String text4) {
        db.execSQL("UPDATE "+TABLE_NAME+" SET name='"+text2+"', username='"+text3+"', password='"+text4+"', WHERE id=" + id);
    }


    public void delete(int id) {
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id="+id);
    }






    public String searchPassstr(String et_username){
        db = this.getReadableDatabase();
        String query = "select * from users";
        String unameString, passWordStr;
        Cursor cursor = db.rawQuery(query, null);
        passWordStr = "Password not Found!!";

        if(cursor.moveToFirst())
        {
            do{

                unameString = cursor.getString(2);


                if(unameString.equals(et_username)) {
                    passWordStr = cursor.getString(3);
                    break;
                }



            }
            while(cursor.moveToNext());

        }
        cursor.close();
        return passWordStr;
        }





    public String[] getNameAndUsername() throws SQLException {
        String name = "";
        String username = "";

        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COLUMN_NAME, COLUMN_USERNAME },
                null, null, null, null, null);
        //if (cursor.moveToFirst()) {
        //    do {
        //        name = cursor.getString(0);
        //
        //    } while (cursor.moveToNext());
        //}

        cursor.moveToLast();
        name = cursor.getString(0);
        username = cursor.getString(1);

        cursor.close();

        String[] nameAndUsername = new String[2];
        nameAndUsername[0] = name;
        nameAndUsername[1] = username;
        return nameAndUsername;
    }




    public String getName() throws SQLException {
        String name = "";

        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COLUMN_NAME },
                null, null, null, null, null);
        //if (cursor.moveToFirst()) {
        //    do {
        //        name = cursor.getString(0);
        //
        //    } while (cursor.moveToNext());
        //}

        cursor.moveToLast();
        name = cursor.getString(0);

        cursor.close();

        return name;
    }







    public String getUsername() throws SQLException {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COLUMN_USERNAME },
                null, null, null, null, null);
        cursor.moveToLast();
        username = cursor.getString(0);
        //if (cursor.moveToFirst()) {
        //    do {
        //        username = cursor.getString(0);
         //   } while (cursor.moveToNext());
        //}

        cursor.close();

        return username;
    }


    public String getBmi() throws SQLException {
        String bmi = "";
        Cursor cursor = this.getReadableDatabase().query(
                T_NAME, new String[] { _BMI },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                bmi = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return bmi;
    }

    public String getHeight() throws SQLException {
        String height = "";
        Cursor cursor = this.getReadableDatabase().query(
                T_NAME, new String[] { _HEIGHT },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                height = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return height;
    }

    public String getWeight() throws SQLException {
        String weight = "";
        Cursor cursor = this.getReadableDatabase().query(
                T_NAME, new String[] { _WEIGHT },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                weight = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return weight;
    }

    public void addUsers(RegRequest reg){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, reg.getEt_name());
        values.put(COLUMN_USERNAME, reg.getEt_username());

    }

    public String getDate() throws SQLException {
        String date = "";
        Cursor cursor = this.getReadableDatabase().query(
                T_NAME, new String[] { _DATE },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                date = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return date;
    }








    public void insertUser(RegRequest reg) {
    db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    String query = "select * from users";
    Cursor cursor = db.rawQuery(query, null);
    int count = cursor.getCount();
    values.put(COLUMN_ID, count);
    values.put(COLUMN_NAME, reg.getEt_name());
    values.put(COLUMN_USERNAME, reg.getEt_username());
    values.put(COLUMN_PASSWORD, reg.getEt_password());

      //  values.put(COLUMN_BMI, reg.getResult());




        db.insert(TABLE_NAME, null, values);
    db.close();
    }



    public String Exist(String user) {
        String username="";
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, COLUMN_USERNAME + "=?", new String[]{String.valueOf(user)},null, null, null);

            if (c == null) {
                return username;
            }
            else {
                c.moveToFirst();
                username = c.getString(c.getColumnIndex(COLUMN_USERNAME));
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return username;
    }

    public String Exists(String passw) {
        String password="";
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, COLUMN_PASSWORD + "=?", new String[]{String.valueOf(passw)},null, null, null);

            if (c == null) {
                return password;
            }
            else {
                c.moveToFirst();
                password = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return password;
    }


    public void insertBmi(BmiRequest b) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from bmi";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(_ID, count);
        values.put(_HEIGHT, b.getHeight());
        values.put(_WEIGHT, b.getWeight());
        values.put(_BMI, b.getBmi());
        values.put(_DATE, b.getDate());

        //  values.put(COLUMN_BMI, reg.getResult());




        db.insert(T_NAME, null, values);
        db.close();
    }





}

