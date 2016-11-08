package com.example.sercanyusuf.temazsizbankacilik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Options extends AppCompatActivity {

    private Button mQROdeme, mParaTransferi;
    TextView tvKullaniciAdi;
    ImageView profilFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        mQROdeme=(Button)findViewById(R.id.qrOdeme);
        mParaTransferi=(Button)findViewById(R.id.paraTransferi);
        tvKullaniciAdi=(TextView) findViewById(R.id.tvKullaniciAdi);
        profilFoto=(ImageView)findViewById(R.id.imageView2);

        Bundle paket= getIntent().getExtras();
        String kullaniciAdi= paket.getString("kullaniciAdi");

        Log.v("isim",String.valueOf(kullaniciAdi.compareTo("okan")));
        if(kullaniciAdi.toLowerCase().compareTo("okan")==0){
            tvKullaniciAdi.setText("Okan AYDIN");
            profilFoto.setImageResource(R.mipmap.image);

        }
        else if (kullaniciAdi.toLowerCase().compareTo("sercan")==0){
            tvKullaniciAdi.setText("Sercan YUSUF");
            profilFoto.setImageResource(R.mipmap.sercan);
        }

        mQROdeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mParaTransferi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ParaTransferi.class));
            }
        });


    }
}
