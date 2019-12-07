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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_information);
        mListView = findViewById(R.id.listViewTips);
        myDb = new SQLiteHelper(this);

        dataListView();
    }


    private void dataListView() {


        Cursor data = myDb.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()) {
            listData.add(data.getString(1));

        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = myDb.getItemID(name);

                int itemID = -1;
                String description="";

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                    description=data.getString(1);
                }
                if (itemID > -1) {

                    Intent editIntent = new Intent(RouteInformationActivity.this, DescriptionOfPoint.class);
                    editIntent.putExtra("id", itemID);
                    editIntent.putExtra("name", name);
                    editIntent.putExtra("description", description);


                    startActivity(editIntent);
                }

            }
        });
    }

}