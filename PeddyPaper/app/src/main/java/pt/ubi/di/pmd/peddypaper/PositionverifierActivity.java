package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PositionverifierActivity extends Activity {

    EditText positionPassword;
    Button buttonCheck;
    SQLiteHelper myDb;
    private int selected_ID;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position_verifier);
        positionPassword = (EditText)findViewById(R.id.verifyPosition);
        buttonCheck=(Button)findViewById(R.id.button_Check);
        Intent receivedIntent = getIntent();
        myDb = new SQLiteHelper(this);
        selected_ID = receivedIntent.getIntExtra("name",-1);
        userName = receivedIntent.getStringExtra("tableName");
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor data = myDb.ForVerifyPosition(selected_ID,userName);
                int itemID = -1;
                String passwordForPoint="";
                while (data.moveToNext()) {
                    passwordForPoint=data.getString(1);
                }

                if(positionPassword.getText().toString().equals(passwordForPoint))
                {
                    Toast.makeText(PositionverifierActivity.this,"Very good , You earn one point"+ passwordForPoint,Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PositionverifierActivity.this,"Wrong password, try one more time!!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
