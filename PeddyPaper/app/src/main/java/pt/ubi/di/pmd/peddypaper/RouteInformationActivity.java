package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RouteInformationActivity extends Activity {

    SQLiteHelper myDb;
    private ListView mListView;
    private String userTableName;
    private int id_end_point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_information);

        mListView = findViewById(R.id.listViewTips);
        myDb = new SQLiteHelper(this);

        dataListView();
    }


    private void dataListView() {

        Intent receivedIntent = getIntent();
        myDb = new SQLiteHelper(this);
        userTableName = receivedIntent.getStringExtra("userNameOfTable");
        id_end_point=receivedIntent.getIntExtra("end_point_number",-1);
        Cursor data = myDb.getDataFormUserTable(userTableName);
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()) {
            listData.add(data.getInt(4)+". "+data.getString(1));


        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDb.getDataFromUserTablee(i+1,userTableName);

                int itemID = -1;
                String description="";
                int positionPoint=0;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                    description=data.getString(1);
                    positionPoint=data.getInt(2);
                }
                if (itemID > -1) {

                    Intent editIntent = new Intent(RouteInformationActivity.this, DescriptionOfPoint.class);
                    editIntent.putExtra("id", itemID);
                    editIntent.putExtra("name", name);
                    editIntent.putExtra("description", description);
                    editIntent.putExtra("userNameOfTable", userTableName);
                    editIntent.putExtra("end_point_number", id_end_point);
                    editIntent.putExtra("positionPoint", positionPoint);


                    startActivity(editIntent);
                }

                mListView.getChildAt(i).setEnabled(false);
                mListView.getChildAt(i).setClickable(true);


            }

        });
    }


}