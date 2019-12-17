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
    private int array[]=new int[10];
    int i=0;
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
             intent.putExtra("userEmail",EmailHolder);
             startActivity(intent);

                datahelper.newTableForUser(EmailHolder);


                Cursor data = datahelper.getData();

                int itemID = -1;
                String nameOfPoint="";

                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                    nameOfPoint=data.getString(1);
                    i=1;
                    for(int i=1;i<=6;i++){
                        if(array[i]==itemID) {
                            datahelper.insertData(nameOfPoint, EmailHolder);
                        }

                    }
                }
                }

        });

    }
    int id_start_point;
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
                    id_start_point=1;
                    break;
                case 1:
                    id_start_point=2;
            array[1]=2;


                    break;
                case 2:
                    id_start_point=3;
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                    array[2]=3;



                    break;
                case 3:
                    id_start_point=4;
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                    array[3]=4;


                    break;
                case 4:
                    id_start_point=5;
                    break;
                case 5:
                    id_start_point=6;
                    break;
                case 6:
                    id_start_point=7;
                    break;
                case 7:
                    id_start_point=8;
                    break;
                case 8:
                    id_start_point=9;
                    break;
                case 9:
                    id_start_point=10;
                    break;
                case 10:
                    id_start_point=11;
                    break;
                case 11:
                    id_start_point=12;
                    break;
                case 12:
                    id_start_point=13;
                    break;
                case 13:
                    id_start_point=14;
                    break;
                case 14:
                    id_start_point=15;
                    break;
                case 15:
                    id_start_point=16;
                    break;
                case 16:
                    id_start_point=17;
                    break;

                case 17:
                    id_start_point=18;
                    break;

                case 18:
                    id_start_point=19;
                    break;
                case 19:
                    id_start_point=20;
                    break;
                case 20:
                    id_start_point=21;
                    break;
                case 21:
                    id_start_point=22;
                    break;
                case 22:
                    id_start_point=23;
                    break;
                case 23:
                    id_start_point=24;
                    break;
                case 24:
                    id_start_point=25;
                    break;
                case 25:
                    id_start_point=26;
                    break;
                case 26:
                    id_start_point=27;
                    break;
                case 27:
                    id_start_point=28;
                    array[5]=id_start_point;
                    break;
                case 28:
                    id_start_point=29;
                    array[4]=id_start_point;
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
                    id_end_point=1;
                    break;
                case 1:
                    id_end_point=2;
                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 2:
                    id_end_point=3;
                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;

                case 17:
                    break;

                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    break;
                case 21:
                    break;
                case 22:
                    break;
                case 23:
                    break;
                case 24:
                    break;
                case 25:
                    break;
                case 26:
                    break;
                case 27:
                    break;
                case 28:
                    break;


            }
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }




}