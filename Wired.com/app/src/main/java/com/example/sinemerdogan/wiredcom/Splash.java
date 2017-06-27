package com.example.sinemerdogan.wiredcom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sinem erdoğan on 14.10.2016.
 */

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        // Thread hazırlanıyor
        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    synchronized (this) {
                        // Uygulama 4 saniye aynı ekranda bekliyor
                        wait(2000);
                    }
                } catch (InterruptedException e) {

                    // Hata yönetimi

                } finally {

                    finish();

                    // Yeni açılmak istenen Intent
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }

            }
        };

        // Thread başlatılıyor
        thread.start();

    }
}

