package com.example.sercanyusuf.temazsizbankacilik;



import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class Finans {

    FirebaseDatabase database;
    DatabaseReference myRef;
    String adSoyad,guvenlikKodu,iban,kartNo,sonKullanmaTarihi,hesapAdi;
    Context mCon;

    public Finans(Context con){
        mCon=con;
        database = FirebaseDatabase.getInstance();
    }




    public void bakiyeDusur(final int tutar,String card){
        myRef = database.getReference("Kullanicilar/okan/"+card+"/bakiye");
        myRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(final MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(1);
                } else {
                    currentData.setValue((Long) currentData.getValue() + tutar);

                }

                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(DatabaseError firebaseError, boolean committed, DataSnapshot currentData) {

                Toast.makeText(mCon,"Ödemeniz Başarı ile Gerçekleşti :)", Toast.LENGTH_SHORT).show();


            }
        });

    }

    public void bakiyeArttir(final int tutar,String card){
        myRef = database.getReference("Kullanicilar/sercan/"+card+"/bakiye");
        myRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(final MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(1);
                } else {
                    currentData.setValue((Long) currentData.getValue() - tutar);

                }

                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(DatabaseError firebaseError, boolean committed, DataSnapshot currentData) {

                Toast.makeText(mCon,"Ödemeniz Başarı ile Gerçekleşti :)", Toast.LENGTH_SHORT).show();


            }
        });

    }



    public void getAdSoyad(final TextView tv){

        myRef=database.getReference("adSoyad");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }
        });

    }

    public String getHesapAdi(String hesapSahibi, String card){

        myRef = database.getReference("Kullanicilar/"+hesapSahibi+"/"+card+"/hesapAdi");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hesapAdi = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }
        });
            return  hesapAdi;
    }

    public void getIban(final TextView tv){

        myRef=database.getReference("iban");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }
        });

    }

    public void getKartNo(final TextView tv){

        myRef=database.getReference("kartNo");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                tv.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }
        });

    }

    public void getBakiye(final TextView tv){

        myRef=database.getReference("bakiye");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                tv.setText(String.valueOf(value));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }
        });

    }


    public void kayitlariGetir(){

        myRef = database.getReference("Kullanicilar/okan");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String adres=dataSnapshot.getValue(Kullanici.class).getAdres();
                //Log.v("adres",String.valueOf(dataSnapshot.getChildrenCount()));

            for(DataSnapshot card:dataSnapshot.getChildren()){
                //Log.v("iban",card.getValue(Card.class).getIban()+"\n");

//                String iban=card.getValue(Card.class).getIban();
               // String bakiye=card.getValue(Card.class).getBakiye();

            }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(mCon,"hata",Toast.LENGTH_SHORT).show();

            }
        });

    }




}
