package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText; //deklarasikan bentuk numbertlp
    private String numberTlp = " "; //deklarasikan numbertlp sbg string kosong
    private Button btnCall;
    private static final int PHONE_REQUEST_CODE = 986; //request code dapat diganti
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        editText = findViewById(R.id.et_numbertlp);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calling();
            }
        });

    }
    //membuat fungsi calling yang akan dipanggil jika sudah ada permission
    private void calling(){
        numberTlp = editText.getText().toString();
            //manifestpermission.call_phone dapat diganti dengan fitur yang lainnya
        //cek apakah permission allow or deny
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:" + numberTlp)); //tel tidak bisa dirubah, tel = telecommunication
            startActivity(intent);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE},PHONE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //untuk mengecek apakah sudah diizinkan
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            calling();
        }
        else {
            //jika tidak diizinkan
            Toast.makeText(getApplicationContext(), "Aplikasi tidak diizinkan", Toast.LENGTH_SHORT).show();
        }
    }
}
