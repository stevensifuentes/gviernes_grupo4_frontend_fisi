package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TermsConditions extends AppCompatActivity {

    Button btn_pagar;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        btn_pagar = findViewById(R.id.btnRealizarPago);
        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), CardPayment.class);
                startActivity(inte);
            }
        });

        back = findViewById(R.id.btnBackTerms);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Signup.class);
                startActivity(inte);
            }
        });
    }
}