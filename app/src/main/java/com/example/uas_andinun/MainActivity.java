package com.example.uas_andinun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder alertDialogBuilder;

    ListView lv;

    int[] iconList = new int[]{
         R.drawable.calculator, R.drawable.maps, R.drawable.camera, R.drawable.stopwatch,
            R.drawable.senter, R.drawable.messages, R.drawable.profile
    };

    String[] Headline = {
            "Calculator", "Maps", "Camera", "Stopwatch", "Senter", "SMS", "Profile"
    };

    String[] Subhead = {
            "Alat penghitung angka", "Google Maps", "Kamera", "Stopwatch", "Pengolah Pesan", "My Profile"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialogBuilder = new AlertDialog.Builder(this);

        lv = findViewById(R.id.list);
        ListAdapter ListAdapter = new com.example.uas_andinun.ListAdapter(this, iconList, Headline, Subhead);
        lv.setAdapter(ListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),
                        "Aplikasi di Pilih : " + Headline[position],
                        Toast.LENGTH_SHORT).show();

                //Posisi kalkulator
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), calculator.class);
                    startActivityForResult(intent, 0);
                }

                //Posisi maps
                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), MapsActivity.class);
                    startActivityForResult(intent, 0);
                }

                //Posisi kamera
                if (position == 2) {
                    Intent intent = new Intent(view.getContext(), camera.class);
                    startActivityForResult(intent, 0);
                }

                //posisi stopwatch
                if (position == 3) {
                    Intent intent = new Intent(view.getContext(), stopwatch.class);
                    startActivityForResult(intent, 0);
                }

                //posisi senter
                if (position == 4) {
                    Intent intent = new Intent(view.getContext(), senter.class);
                    startActivityForResult(intent, 0);
                }



                //posisi SMS
                if (position == 5) {
                    Intent intent = new Intent(view.getContext(), messages.class);
                    startActivityForResult(intent, 0);
                }

                //posisi profil
                if (position == 6) {
                    Intent intent = new Intent(view.getContext(), profile.class);
                    startActivityForResult(intent, 0);
                }

            }
        });
    }
}