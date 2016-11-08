package com.example.sercanyusuf.temazsizbankacilik;

import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class ParaTransferi extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView qrCodeReaderView;
    private EditText etTutar;
    private Button btnParaGonder;

    int tutar;

    Finans f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_transferi);


        etTutar = (EditText) findViewById(R.id.etTutar);
        btnParaGonder = (Button) findViewById(R.id.btnParaGonder);

        f = new Finans(getApplicationContext());


        btnParaGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert2("Kalan Bakiyeniz: 30000 TL dir");
            }


        });

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrTransfer);


        qrCodeReaderView.setOnQRCodeReadListener(ParaTransferi.this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();


    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {


        if (etTutar.getText() != null) {
            tutar = Integer.parseInt(etTutar.getText().toString());
            qrCodeReaderView.setVisibility(View.INVISIBLE);
            etTutar.setVisibility(View.INVISIBLE);
            btnParaGonder.setVisibility(View.VISIBLE);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }


    //ALERT
    public void alert2(String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(Message);

        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                f.bakiyeDusur(tutar, "card1");
                f.bakiyeArttir(tutar,"card1");


            }
        });


        builder.show();
    }

}
