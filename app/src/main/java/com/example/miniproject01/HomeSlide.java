package com.example.miniproject01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniproject01.Models.Rv_List;

public class HomeSlide extends AppCompatActivity {
    private Rv_List rv_list;
    private int pemasukan, pengeluaran, saldo;
    private TextView pemasukanText, pengeluaranText, saldoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_slide);

        getSupportActionBar().hide();
        pemasukanText = findViewById(R.id.pemasukan);
        pengeluaranText = findViewById(R.id.pengeluaran);
        saldoText = findViewById(R.id.saldo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rv_list = extras.getParcelable(MainActivity.NOTES_KEY);
        } else {
            rv_list = new Rv_List();
        }

        if(rv_list.getNotes().size() > 0) {
            for (int i=0; i < rv_list.getNotes().size(); i++) {
                if(rv_list.getNotes().get(i).getTitle().equalsIgnoreCase("Pemasukan")){
                    pemasukan += rv_list.getNotes().get(i).getNominal();
                }
                else if(rv_list.getNotes().get(i).getTitle().equalsIgnoreCase("Pengeluaran")) {
                    pengeluaran += rv_list.getNotes().get(i).getNominal();
                }
            }
            saldo = pemasukan - pengeluaran;

            pemasukanText.setText("Pemasukan : " + rv_list.getNotes().get(0).strResult(pemasukan));
            pengeluaranText.setText("Pengeluaran : " + rv_list.getNotes().get(0).strResult(pengeluaran));
            saldoText.setText("Saldo : " + rv_list.getNotes().get(0).strResult(saldo));
        } else {
            pemasukanText.setText("Pemasukan : Rp 0");
            pengeluaranText.setText("Pengeluran : Rp 0");
            saldoText.setText("Saldo : Rp 0");
        }
    }

    public void clickDetail(View view) {
        Intent i = new Intent(HomeSlide.this, MainActivity.class);
        i.putExtra("NOTES", rv_list);
        startActivity(i);
    }

    public void clickProfile(View view) {
        Intent i = new Intent(HomeSlide.this, Profile.class);
        i.putExtra("NOTES", rv_list);
        startActivity(i);
    }
}