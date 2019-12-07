package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
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
    Button LogOUT ;
    Button seeRoute;
    private Spinner spinner_start_point;
    private Spinner spinner_end_point;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SQLiteHelper datahelper=new SQLiteHelper(this);
        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        seeRoute = (Button)findViewById(R.id.seeRoute);

        Intent intent = getIntent();
        spinner_start_point=(Spinner)findViewById(R.id.static_spinner);
        spinner_end_point=(Spinner)findViewById(R.id.spinner_end_point);

        ArrayList<String> list=datahelper.getAllPoints();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list);
        spinner_start_point.setAdapter(adapter);

        ArrayList<String> list_end=datahelper.getAllPoints();
        ArrayAdapter<String> adapter_end=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text, list_end);
        spinner_end_point.setAdapter(adapter_end);

        spinner_start_point.setOnItemSelectedListener(this);
        spinner_end_point.setOnItemSelectedListener(this);

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

            }
        });

    }
    String start_point;
    String end_point;
    int id_end_point;
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(parent.getId() == spinner_start_point.getId())
        {
            switch (position) {
                case 0:


                    break;
                case 1:

                    break;
                case 2:
                    Toast.makeText(DashboardActivity.this,"You choose position 3", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
         if(parent.getId() == spinner_end_point.getId())
        {
            switch (position) {
                case 0:

                    break;
                case 1:
                    end_point = spinner_end_point.getSelectedItem().toString();
                    id_end_point=position;
                    Toast.makeText(DashboardActivity.this,"You chosen "+id_end_point, Toast.LENGTH_SHORT).show();

                    break;
                case 2:
                    end_point = spinner_end_point.getSelectedItem().toString();
                    id_end_point=position;
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