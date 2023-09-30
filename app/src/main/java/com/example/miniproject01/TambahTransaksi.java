package com.example.miniproject01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniproject01.Models.Notes;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TambahTransaksi extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText nominal;
    @NotEmpty
    private EditText contentInput;
    private RadioGroup type;
    private RadioButton radioButton;
    private Notes item;
    private int index;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);

        validator = new Validator(this);
        validator.setValidationListener(this);

        nominal = findViewById(R.id.nominal);
        contentInput = findViewById(R.id.keterangan);
        type = (RadioGroup) findViewById(R.id.TambahTransaksi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable(MainActivity.NOTES_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            nominal.setText("");
            contentInput.setText(item.getContent());
        }
    }

    public void handleSubmit(View view) {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        Date dt = new Date();
        radioButton = (RadioButton) findViewById(type.getCheckedRadioButtonId());
        SimpleDateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm a");
        String title = radioButton.getText().toString();
        String content = contentInput.getText().toString();
        int jumlah = Integer.parseInt(nominal.getText().toString());

        item.setTitle(title);
        item.setNominal(jumlah);
        item.setContent(content);
        item.setWaktu(time.format(dt));
        item.setDate(date.format(dt));

        Intent intent = new Intent();
        intent.putExtra(MainActivity.NOTES_KEY, item);
        intent.putExtra(MainActivity.INDEX_KEY, index);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}