package com.example.qrcode_gen_scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView QRcode_img;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button gen_btn,back_btn;
        EditText ET;


        back_btn= findViewById(R.id.id_backbtn);
        gen_btn=findViewById(R.id.id_genbtn);
        ET=findViewById(R.id.id_ET);
        QRcode_img=findViewById(R.id.id_QRcode);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_intent= new Intent(GenerateActivity.this, MainActivity.class);
                startActivity(back_intent);
            }
        });

        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(ET.getText().toString())){
                    Toast.makeText(GenerateActivity.this, "Text cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else{
                    GenerateQRCode(ET.getText().toString(),QRcode_img);

                }

            }
        });






    }// end of OnCreate
    private void GenerateQRCode (String ET, ImageView QRcode_img) {
        BarcodeEncoder barcodeEncoder= new BarcodeEncoder();
        try{
            Bitmap bitmap = barcodeEncoder.encodeBitmap(ET, BarcodeFormat.QR_CODE,400,400);
            QRcode_img.setImageBitmap(bitmap);


        }
        catch (WriterException e){
            e.printStackTrace();

        }

    };



}