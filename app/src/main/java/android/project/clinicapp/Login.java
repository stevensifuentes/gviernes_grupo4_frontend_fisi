package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Login extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_signup, btn_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_back = findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), MainActivity.class);
                startActivity(inte);
            }
        });

        btn_log = findViewById(R.id.btnLog);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Menu.class);
                startActivity(inte);
            }
        });

        btn_signup = (Button) findViewById(R.id.linkLogin);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Signup.class);
                startActivity(inte);
            }
        });
    }
}