package com.example.sercanyusuf.temazsizbankacilik;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class MainActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener{

    private TextView resultTextView;
    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);


        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        resultTextView=(TextView)findViewById(R.id.textView);


        qrCodeReaderView.setOnQRCodeReadListener(this);

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
        //resultTextView.setText(text);
        if (text.startsWith("ing") ){
            String[] veriler= text.split(",");

            //resultTextView.setText(veriler[0]);



            Intent i=new Intent(getApplicationContext(),AlisverisSonucu.class);
            i.putExtra("iban",veriler[1]);
            i.putExtra("firmaAdi",veriler[2]);
            i.putExtra("tutar",veriler[3]);
            startActivity(i);

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
}
