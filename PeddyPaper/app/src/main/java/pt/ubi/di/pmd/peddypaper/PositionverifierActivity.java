package pt.ubi.di.pmd.peddypaper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PositionverifierActivity extends Activity {

    EditText positionPassword;
    Button buttonCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position_verifier);
        positionPassword = (EditText)findViewById(R.id.verifyPosition);
        buttonCheck=(Button)findViewById(R.id.button_Check);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

}
