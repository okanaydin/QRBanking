package com.example.sercanyusuf.temazsizbankacilik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivty extends AppCompatActivity {

    private Button mLoginButton;
    EditText edtKullaniciAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        mLoginButton=(Button)findViewById(R.id.loginButton);
        edtKullaniciAdi=(EditText) findViewById(R.id.kullaniciAdi);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Options.class);
                i.putExtra("kullaniciAdi",edtKullaniciAdi.getText().toString());
                startActivity(i);
            }
        });

    }
}
