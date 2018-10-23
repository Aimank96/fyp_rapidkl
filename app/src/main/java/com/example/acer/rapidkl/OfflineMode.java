package com.example.acer.rapidkl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class OfflineMode extends AppCompatActivity {
TextView textView;
ImageView iv3;
    final String DEFAULT="N?A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode);
        textView=(TextView)findViewById(R.id.textView);
        iv3=(ImageView)findViewById(R.id.ivQr3);
        SharePreferenceGet();
    }

    private void SharePreferenceGet() {
        SharedPreferences sharedPreference=getSharedPreferences("userData",MODE_PRIVATE);
        String userID=sharedPreference.getString("ID",DEFAULT);

        if(userID.equals(DEFAULT)){
            textView.setText("no ID"+userID );
        }
        else {
            textView.setText(userID+"");
            imgQR(userID);
        }
    }

    private void imgQR(String userID) {

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(userID, BarcodeFormat.QR_CODE,900,900);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            iv3.setImageBitmap(bitmap);



        }
        catch (WriterException e){
            e.printStackTrace();
        }


    }
}
