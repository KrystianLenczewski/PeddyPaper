package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DescriptionOfPoint extends Activity {



    private TextView editable_item;
    private TextView descriptionOfPoint;
    private Button verifyPosition;
    private ImageView imageOfPoint;

    private String selectedName;
    private String userTableName;
    private int id_end_point;
    private String description;
    private int selectedID;
    private int positionPoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_of_point);

        editable_item = (TextView) findViewById(R.id.editable_item);
        descriptionOfPoint=(TextView)findViewById(R.id.description_of_point);
        verifyPosition=(Button)findViewById(R.id.verifyPosition);
        imageOfPoint=(ImageView)findViewById(R.id.imageOfPoint);


        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        description = receivedIntent.getStringExtra("description");
        userTableName = receivedIntent.getStringExtra("userNameOfTable");
        id_end_point=receivedIntent.getIntExtra("end_point_number",-1);
        positionPoint=receivedIntent.getIntExtra("positionPoint",-1);


        int imageResource=getResources().getIdentifier("@drawable/t"+positionPoint,null,this.getPackageName());
        imageOfPoint.setImageResource(imageResource);

        editable_item.setText(selectedName);
        descriptionOfPoint.setText(description);
        verifyPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(DescriptionOfPoint.this, PositionverifierActivity.class);
                intent.putExtra("name", selectedID);
                intent.putExtra("userNameOfTable", userTableName);
                intent.putExtra("end_point_number", id_end_point);

                startActivity(intent);

            }
        });

    }




}
