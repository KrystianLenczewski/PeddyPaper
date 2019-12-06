package pt.ubi.di.pmd.peddypaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="Peddy.db";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_Password="password";

    public static final String TABLE_POINTS="PointsTable";
    public static final String Id_Point="Id";
    public static final String Name="Name";
    public static final String Description="Description";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
        String CREATE_TABLE_POINTS="CREATE TABLE IF NOT EXISTS "+TABLE_POINTS+" ("+Id_Point+" INTEGER PRIMARY KEY, "+Name+" VARCHAR, "+Description+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
        database.execSQL(CREATE_TABLE_POINTS);
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('MUSEUM','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('CREA','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('FILOSOFAR','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('FABLAB','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('DCTT','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('DCA LAP','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('DI','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('MATH','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('FIS','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('CCECV','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('TINT A','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('TINT B','BEATIFUL')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('TINT C','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('BIBLIO A','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('BIBLIO B','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('BIBLIO C','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('ARCO','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('AZUL A','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('AZUL B','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('MALUFA','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('AV','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('MOUSE X','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('MOUSE Y','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('INFINITY','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('QUI','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('ARMAS','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('ENCADEADO','BEATIFUL')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description ) VALUES ('TERMINUS','BEATIFUL')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_POINTS);
        onCreate(db);

    }

    public ArrayList<String> getAllPoints(){

        ArrayList<String> list=new ArrayList<String>();
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();


        try
        {

            String selectQuery = "SELECT * FROM "+ TABLE_POINTS;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)

            {
                while (cursor.moveToNext()) {
                    // Add province name to arraylist
                    String pname= cursor.getString(cursor.getColumnIndex("Name"));
                    list.add(pname);

                }


            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();

            // Close database
        }
        return list;


    }










}