package com.example.sercanyusuf.temazsizbankacilik;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlisverisSonucu extends AppCompatActivity {

    private TextView tvAlisverisBilgisi,odemeCard1, odemeCard2,odemeCard3;
    private Button btnOdemeYap;

    String secilenKart;
    int tutar;
    Finans f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alisveris_sonucu);

        btnOdemeYap=(Button)findViewById(R.id.btnOdemeYap);

        odemeCard1=(TextView)findViewById(R.id.odemeCard1);
        odemeCard2=(TextView)findViewById(R.id.odemeCard2);
        odemeCard3=(TextView)findViewById(R.id.odemeCard3);

//        odemeCard1.setText(f.getHesapAdi("okan","card1"));

        tvAlisverisBilgisi=(TextView)findViewById(R.id.tvAlisverisBilgisi);


        Bundle paket= getIntent().getExtras();
        String firmaAdi= paket.getString("firmaAdi");
        tutar = Integer.parseInt(paket.getString("tutar"));

        tvAlisverisBilgisi.setText(firmaAdi +" firmasından yaptığınız " + tutar +"TL tutarındaki alışveriş için bir hesap seçiniz:");


        f=new Finans(getApplicationContext());
        f.kayitlariGetir();


        odemeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secilenKart="card1";
                odemeCard1.setBackgroundColor(getResources().getColor(R.color.yesil));
                odemeCard2.setBackgroundColor(getResources().getColor(R.color.ozan));
                odemeCard3.setBackgroundColor(getResources().getColor(R.color.ozan));


            }
        });

        odemeCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secilenKart="card2";

                odemeCard1.setBackgroundColor(getResources().getColor(R.color.ozan));
                odemeCard2.setBackgroundColor(getResources().getColor(R.color.yesil));
                odemeCard3.setBackgroundColor(getResources().getColor(R.color.ozan));

            }
        });

        odemeCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secilenKart="card3";

                odemeCard1.setBackgroundColor(getResources().getColor(R.color.ozan));
                odemeCard2.setBackgroundColor(getResources().getColor(R.color.ozan));
                odemeCard3.setBackgroundColor(getResources().getColor(R.color.yesil));

            }
        });

        btnOdemeYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert("BİLGİLENDİRME", "Ödemenizi Onaylıyor Musunuz?");

            }
        });


    }

    //ALERT
    public void alert(String title, String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);

        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                f.bakiyeDusur(tutar, secilenKart);
                // Toast.makeText(getApplicationContext(),"Ödemeniz Başarı ile Gerçekleşti :)", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"İptal Edildi", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

    }




}
