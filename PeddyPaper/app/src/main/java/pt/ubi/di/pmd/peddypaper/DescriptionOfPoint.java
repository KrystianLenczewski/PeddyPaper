package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DescriptionOfPoint extends Activity {



    private TextView editable_item;
    private TextView idd;
    Button verifyPosition;
    SQLiteHelper mDatabaseHelper;
    private int idInt;
    private String selectedName;
    private String userName;

    private String description;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_of_point);

        editable_item = (TextView) findViewById(R.id.editable_item);
        idd=(TextView)findViewById(R.id.description_of_point);
        verifyPosition=(Button)findViewById(R.id.verifyPosition);

        //mDatabaseHelper = new SQLiteHelper(this);


        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        description = receivedIntent.getStringExtra("description");
        userName = receivedIntent.getStringExtra("userEmail");

        //idVerify.setText(""+selectedID);
        editable_item.setText(selectedName);
        idd.setText(description);
        verifyPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(DescriptionOfPoint.this, PositionverifierActivity.class);
                intent.putExtra("name", selectedID);
                intent.putExtra("tableName", userName);
                startActivity(intent);

            }
        });

    }



}
