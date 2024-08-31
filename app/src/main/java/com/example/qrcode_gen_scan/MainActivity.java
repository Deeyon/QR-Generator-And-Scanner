package com.example.qrcode_gen_scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    TextView QRResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button genbtn,scanbtn;

        genbtn = findViewById(R.id.id_genbtn);
        scanbtn = findViewById(R.id.id_scanbtn);
        QRResults= findViewById(R.id.id_QRResults);

        genbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gen_intent= new Intent(MainActivity.this, GenerateActivity.class);
                startActivity(gen_intent);
            }
        });

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator= new IntentIntegrator(MainActivity.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();

            }
        });













    } // end of OnCreate


    protected void onActivityResult(int requestCode, int resultcode, @Nullable Intent data){

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultcode,data);
        if (intentResult!=null){
            String contents = intentResult.getContents();
            if (contents!=null){
                QRResults.setText(contents);
                //textview
            }
        }
        else{
            super.onActivityResult(resultcode,requestCode,data);
        }
    }
}