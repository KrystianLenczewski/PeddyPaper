package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class DashboardActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String EmailHolder;
    TextView Email;
    SQLiteHelper datahelper;
    Button LogOUT ;
    Button seeRoute;
    private Spinner spinner_start_point;
    private Spinner spinner_end_point;
    ArrayList<String> list_end;
    ArrayAdapter<String> adapter_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
         datahelper=new SQLiteHelper(this);
        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        seeRoute = (Button)findViewById(R.id.seeRoute);

        Intent intent = getIntent();
        spinner_start_point=(Spinner)findViewById(R.id.static_spinner);
        spinner_end_point=(Spinner)findViewById(R.id.spinner_end_point);

        ArrayList<String> list=datahelper.getAllPoints();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list);
        spinner_start_point.setAdapter(adapter);

       // list_end=datahelper.getAllPoints();
       //adapter_end=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list_end);

        //spinner_end_point.setAdapter(adapter_end);


        spinner_start_point.setOnItemSelectedListener(this);


        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(MainActivity.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });
        seeRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent intent=new Intent(DashboardActivity.this,RouteInformationActivity.class);
             startActivity(intent);
                datahelper.newTableForUser(EmailHolder);

                   // datahelper.insertData("hello", EmailHolder);
                Cursor data = datahelper.getData();

                int itemID = -1;
                String description="";

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                    description=data.getString(1);
                    datahelper.insertData(description, EmailHolder);
                }
                }

        });

    }
    int  pom;
    String end_point;
    int id_end_point;
    int k=0;
    String tempRemovedItem ;
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(parent.getId() == spinner_start_point.getId())
        {
            k=1;
            switch (position) {
                case 0:


                    break;
                case 1:


                   // spinner_end_point.setOnItemSelectedListener(this);


                    break;
                case 2:
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                    pom=2;

                   // spinner_end_point.setOnItemSelectedListener(this);
                    break;

            }


        }

    if(k==1) {
    list_end=datahelper.getAllPoints();
    adapter_end=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list_end);
    k=0;
    list_end.remove(spinner_start_point.getSelectedItem().toString());


    tempRemovedItem = spinner_start_point.getSelectedItem().toString();
    adapter_end.notifyDataSetChanged();
    spinner_end_point.setAdapter(adapter_end);

    spinner_end_point.setOnItemSelectedListener(this);
    }
         if(parent.getId() == spinner_end_point.getId())
        {

            switch (position) {
                case 0:

                    break;
                case 1:

                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 2:

                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;

            }
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




}