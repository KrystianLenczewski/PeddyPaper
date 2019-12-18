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

    //public static final String Start_Point_Name="start_point_name";
   // public static final String End_Point_Name="end_point_nme";

    public static final String TABLE_POINTS="PointsTable";
    public static final String Id_Point="Id";
    public static final String Name="Name";
    public static final String Description="Description";
    public static final String CheckPointName="CheckPointName";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
        String CREATE_TABLE_POINTS="CREATE TABLE IF NOT EXISTS "+TABLE_POINTS+" ("+Id_Point+" INTEGER PRIMARY KEY, "+Name+" VARCHAR, "+Description+" VARCHAR, "+CheckPointName+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
        database.execSQL(CREATE_TABLE_POINTS);

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('MUSEUM','Wool Museum, Royal Cloth Factory, “Corridor das Furnaces ”(space that gives access to the Parade).','POINT1')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('CREA','Do you want to go on radio or television? If so, go to the Teaching Resource Center and Learning. There are activities to do ... Participate, register and continue.','POINT2')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('FILOSOFAR','Philosophers Walk (passage between Faculty and Library) Sit by the small lake, we suggest to take a group photo and take breath before leaving for the next point.','POINT3')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('FABLAB','Head to the covered first floor of the Silo-auto at the College of Engineering','POINT4')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('DCTT','Look for the Confection Lab at the Department of Science and Textile Technology. Come in, be curious.','POINT5')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('DCA LAP','Aerodynamics and Propulsion Laboratory (LAP) of the Department of Aerospace Sciences (DCA). As for the clues to find the location: - Enter the main gate of Engineering; - go down the hallway past the bar until you find a elevator on the left side; -rub a floor; enter the first door on the right when you exit the elevator (Secretariat DCA); - ask where the LAP is','POINT6')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('DI','Look for the building of the 6th phase of the University of Beira Interior. In the Department of Informatics, many laboratories are to explore:                 SOCIA LAB (artificial intelligence, computer vision) ReGain (games, augmented reality, virtual reality) NetGNA (vehicular networks, Internet of Things)RELEASE (reliable and secure computing) On the first floor choose a lab to socialize.','POINT7')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('MATH','Look for the building of the 6th phase of the University of Beira Interior. R Go up one floor and find a glass room.','POINT8')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('FIS','Physics: Optics Center Directions to arrive: Move into the world of the infinitely small The window for the microscope is the door in the middle of the staircase next to airplanes Challenge Let is look at the little insect world using a High Resolution Equipment - Electron Microscope Sweeping; • the task to be done is to try to find out which insect observed in the scanning electron microscope','POINT9')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('CCECV','Clinical and Experimental Center of Vision Sciences  Do you see well? Check it out at “The Factory” where they test the View. Where is the hidden tiger?','POINT10')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('TINT A','To punctuate the Dry Cleaning exhibition you should visit .','POINT11')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('TINT B','To score, next to the Dry Cleaning, in two bridges your team must frame.','POINT12')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('TINT C','To score, next to the Dry Cleaning, in each rectangle of the mirror of water only one team member should be.','POINT13')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('BIBLIO A','In the empire of wisdom (where silence is king) you will have to venture out ','POINT14')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('BIBLIO B','In the empire of wisdom (where silence is king) you will have to venture out ','POINT15')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('BIBLIO C','In the empire of wisdom (where silence is king) you will have to venture out ','POINT16')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('ARCO','To score  in a bow you must be.','POINT17')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('AZUL A','To punctuate the path to Serra da Estrela in tiles you must meet.','POINT18')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('AZUL B','To punctuate a tile panel on the facade of one of our Buildings must find','POINT19')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('MALUFA','Take a panoramic photo from the Malufa gardens.','POINT20')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('AV','Come with me to see the ...','POINT21')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('MOUSE X','Watch the sky from the bowels of the Mouse','POINT22')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('MOUSE Y','Join the rodent in your lair','POINT23')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('INFINITY','Enter the infinite corridor','POINT24')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('QUI','Go to the laboratory on the 4th floor of the Chemistry department (the entrance can be done directly down the street or down the stairs from the concierge). Do the experience you are asked to.','POINT25')");

        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('DECA','Go to the structural analysis laboratory (room 9.27). In this lab we use computational methods and models scale to analyze the behavior of structures, both due to static actions (for example snow in the a building) as dynamic actions (eg earthquake effect on a building). Now they will try to understand what the relationship between the load (weight of the people) and the arrow (the vertical displacement) on a pedestrian bridge.','POINT26')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('ARMAS','The symbolism of the emblematic is as follows: Of metals, gold symbolizes righteousness, silver symbolizes faithfulness. Of the nail polish, red symbolizes the mood, blue the loyalty, the black the science. Of the pieces, the two litde brandões symbolize the knowledge theoretical and practical, the industry sprocket and carbuncle the wisdom. Find and photograph the UBI Coat of Arms. What does the motto mean: Scientia et Labore Altiora Petimus?','POINT27')");
        database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('ENCADEADO','Forever in my heart...','POINT28')");
       // database.execSQL("INSERT INTO " + TABLE_POINTS+ "(Name,Description,CheckPointName ) VALUES ('TERMINUS','Return to the designated location within the time limit. Every minute of delay will be heavily penalized.','POINT29')");



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


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
int startDateQueryDate=1;
int endDateQueryDate=28;

            String query = "SELECT * FROM " + TABLE_POINTS;
            Cursor data = db.rawQuery(query, null);

            return data;

    }
    public Cursor getDataFormUserTable(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int startDateQueryDate=1;
        int endDateQueryDate=20;

        String query = "SELECT * FROM " + name + " where Id BETWEEN " + startDateQueryDate  + " AND " + endDateQueryDate;
        Cursor data = db.rawQuery(query, null);

        return data;

    }



    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Id_Point + "," + Description + " FROM " + TABLE_POINTS +
                " WHERE " + Name + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getItemIDD(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Id_Point + "," + CheckPointName + " FROM " + TABLE_POINTS +
                " WHERE " + Name + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void newTableForUser( String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+name);

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+name+" ("+Id_Point+" INTEGER PRIMARY KEY, "+Name+" VARCHAR)";
        db.execSQL(CREATE_TABLE);

    }
    public boolean insertData(String name,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name,name);
        long result = db.insert(tableName,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


}