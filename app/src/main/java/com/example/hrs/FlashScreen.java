package com.example.hrs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;

public class FlashScreen extends AppCompatActivity {

    Timer timer;
    ProgressBar mProgressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
//
//        mProgressBar = findViewById(R.id.progress_circular);
//        new Thread(new Runnable() {
//            public void run() {
//                while (progressStatus < 10) {
//                    progressStatus += 1;
//                    // Update the progress bar and display the
//                    //current value in the text view
//                    handler.post(new Runnable() {
//                        public void run() {
//                            mProgressBar.setProgress(progressStatus);
//                        }
//                    });
//                    try {
//                        // Sleep for 200 milliseconds.
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                if(progressStatus==10){
//                    Intent intent = new Intent(FlashScreen.this, MainActivity.class);
//                    startActivity(intent);}
//            }
//        }).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermision();
    }

    /**
     * Kiểm tra từ phiên bản Marshmallow cần check runtime
     */
    private void checkPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
//                    || checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{
//                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        android.Manifest.permission.INTERNET}, 911);
//            } else {
//                autoChimCut(); // Tình huống 3: đã cấp quyền rồi => CÚT
//            }
            if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.INTERNET}, 911);
            } else {
                autoChimCut(); // Tình huống 3: đã cấp quyền rồi => CÚT
            }
        } else {
            autoChimCut(); // Tình huống 2: Dưới phiên bản M => CÚT
        }
    }

    private void autoChimCut() {

        mProgressBar = findViewById(R.id.progress_circular);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 10) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            mProgressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                if(progressStatus==10){
                    Intent intent = new Intent(FlashScreen.this, MainActivity.class);
                    startActivity(intent);}
            }
        }).start();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent); // Chuyển sang MainActivity
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 911) {
            Toast.makeText(FlashScreen.this, "Đã cung cấp đủ quyền", Toast.LENGTH_SHORT).show();
            autoChimCut(); // Tình huống 1: cung cấp xong => CÚT
        }
    }
}
