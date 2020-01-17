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
    String id_user_current;
    private int selected_ID;
    String userTableName;
    int id_end_point;
    int userScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position_verifier);

        positionPassword = (EditText)findViewById(R.id.verifyPosition);
        buttonCheck=(Button)findViewById(R.id.button_Check);
      
        Intent receivedIntent = getIntent();
        myDb = new SQLiteHelper(this);
        selected_ID = receivedIntent.getIntExtra("name",-1);
        userTableName = receivedIntent.getStringExtra("userNameOfTable");
        id_end_point=receivedIntent.getIntExtra("end_point_number",-1);
        id_end_point=id_end_point+1;
         id_user_current=userTableName.substring(1);

        Cursor dataUser=myDb.getScore(id_user_current);
        while (dataUser.moveToNext()) {
            userScore=dataUser.getInt(1);
        }

        buttonCheck.setOnClickListener(new View.OnClickListener() {
           int position_of_point;
            @Override
            public void onClick(View view) {

                Cursor data = myDb.ForVerifyPosition(selected_ID,userTableName);
                int itemID = -1;
                String passwordForPoint="";
                while (data.moveToNext()) {
                    passwordForPoint=data.getString(1);
                    position_of_point=data.getInt(2);
                }

                if(positionPassword.getText().toString().equals(passwordForPoint)&&id_end_point==position_of_point)
                {
                    userScore=userScore+1;
                    myDb.updateUserScore(id_user_current,userScore);

                    Toast.makeText(PositionverifierActivity.this,"Very good , You earn one more point!! This is your last point",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(PositionverifierActivity.this, UserStatsActivity.class);
                    intent.putExtra("userNameOfTable",userTableName);
                    intent.putExtra("userScore",userScore);
                    startActivity(intent);


                }

                else if(positionPassword.getText().toString().equals(passwordForPoint))
                {
                    userScore=userScore+1;
                    myDb.updateUserScore(id_user_current,userScore);
                    Toast.makeText(PositionverifierActivity.this,"Very good , You earn one more point!!",Toast.LENGTH_LONG).show();

                }
                else if(!positionPassword.getText().toString().equals(passwordForPoint))
                {
                    Toast.makeText(PositionverifierActivity.this,"Wrong password, try one more time!!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
