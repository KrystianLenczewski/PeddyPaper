package pt.ubi.di.pmd.peddypaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DescriptionOfPoint extends AppCompatActivity {


    private Button buttonSave,buttonDelete;
    private EditText editable_item;
    private EditText idd;

    SQLiteHelper mDatabaseHelper;
private int idInt;
    private String selectedName;
    private String description;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_of_point);
        buttonSave = (Button) findViewById(R.id.btnSave);
        buttonDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        idd=(EditText)findViewById(R.id.description_of_point);

        //mDatabaseHelper = new SQLiteHelper(this);


        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        description = receivedIntent.getStringExtra("description");

        //idd.setText(""+selectedID);
        editable_item.setText(selectedName);
        idd.setText(description);


    }



}
