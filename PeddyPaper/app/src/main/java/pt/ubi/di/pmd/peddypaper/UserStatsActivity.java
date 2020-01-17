package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserStatsActivity extends Activity {


    SQLiteHelper myDb;
    Button goBack;
    TextView textScore;
    int score_user;
    private String userTableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_stats_layout);

        goBack = (Button)findViewById(R.id.button_back);
        textScore = (TextView) findViewById(R.id.outputScore);

        Intent receivedIntent = getIntent();
        userTableName = receivedIntent.getStringExtra("userNameOfTable");
        score_user = receivedIntent.getIntExtra("userScore",0);

        String userScore=Integer.toString(score_user);
        textScore.setText("Your score :"+userScore);
        myDb = new SQLiteHelper(this);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User_Id=userTableName.substring(1);
                myDb.resetScore(User_Id);

                Intent intent = new Intent(UserStatsActivity.this, DashboardActivity.class);
                intent.putExtra("userNameOfTable",userTableName);
                startActivity(intent);

            }
        });
    }


}
