package com.sauvage.training;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederick on 16-11-20.
 */

public class DBHandler extends MigratableDatabase{
    private static String DB_PATH = "";
    private static String DB_NAME = "conjugaison.db";

    private SQLiteDatabase database;
    private Activity activity;
    // Database Version
    private static final int DATABASE_VERSION = 5 ;
    
    // Database Name
    // http://www.javahelps.com/2015/04/import-and-use-external-database-in.html
    private static final String DATABASE_NAME = "conjugaison";
    
    // Contacts table name
    private static final String TABLE_VERBE = "verbe";
    private static final String TABLE_MODE = "mode";
    private static final String TABLE_TEMPS = "temps";
    private static final String TABLE_CONJUGAISON = "conjugaison";
    
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SH_ADDR = "shop_address";
    
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        activity = (Activity) context;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.open();
    }

    // Creates a empty database on the system and rewrites it with your own database.
    public void create() throws IOException {
        this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    // copy your assets db
    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        String assetsPath = activity.getAssets().toString();
        InputStream myInput = activity.getAssets().open("Databases/" + DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
        this.open();
    }

    public void migration_0(SQLiteDatabase db){
/*
        db.execSQL( "CREATE TABLE `verbe` (" +
                " `id`   INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `value`   TEXT UNIQUE," +
                " `definition` TEXT" +
                ");");

        db.execSQL("CREATE TABLE `mode` (" +
                " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `value` TEXT UNIQUE" +
                ");");

        db.execSQL("CREATE TABLE `temps` (" +
                " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `mode` INTEGER," +
                " `value` TEXT UNIQUE," +
                " FOREIGN KEY(mode) REFERENCES mode(id)" +
                ");");

        db.execSQL("CREATE TABLE `conjugaison` (" +
                " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `temps` INTEGER," +
                " `verbe` INTEGER," +
                " `participe`  TEXT," +
                " `prefixe_je` TEXT," +
                " `suffixe_je` TEXT," +
                " `prefixe_tu` TEXT," +
                " `suffixe_tu` TEXT," +
                " `prefixe_il` TEXT," +
                " `suffixe_il` TEXT," +
                " `prefixe_nous` TEXT," +
                " `suffixe_nous` TEXT," +
                " `prefixe_vous` TEXT," +
                " `prefixe_ils` TEXT," +
                " `suffixe_ils` TEXT," +
                " FOREIGN KEY(temps) REFERENCES temps(id)," +
                " FOREIGN KEY(verbe) REFERENCES verbe(id)" +
                ");");
            */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.migration_0(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Open the database
    public boolean open() {
        String myPath = DB_PATH + DB_NAME;
        try {
            database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            return true;
        } catch (SQLException e1) {
            try {
                this.create();
                database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
                return true;
            } catch (Exception e2) {
                database = null;
                e2.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }


    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public SQLiteDatabase getDatabase(){
        return database;
    }
}
