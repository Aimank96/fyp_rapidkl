package com.example.acer.rapidkl;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


//wajib letak implements
public class Ticket extends AppCompatActivity implements View.OnClickListener{

    //declare
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        //cari id
        iv2=(ImageView)findViewById(R.id.iv2);

        //give function


        //utk retrieve data

        imageProcess();
    }


    public void imageProcess(){

            MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
            try{
                BitMatrix bitMatrix=multiFormatWriter.encode(SplashScreen.userID, BarcodeFormat.QR_CODE,900,900);
                BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
               iv2.setImageBitmap(bitmap);



            }
            catch (WriterException e){
                e.printStackTrace();
            }

        }


    @Override
    public void onClick(View v) {

    }
}
