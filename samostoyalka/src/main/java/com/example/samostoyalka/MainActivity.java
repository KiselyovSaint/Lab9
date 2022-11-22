package com.example.samostoyalka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tvName, tvLName, tvEmail, tvPassword;
    Button btnReg;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (EditText) findViewById(R.id.tvName);
        tvLName = (EditText) findViewById(R.id.tvLName);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        tvPassword = (EditText) findViewById(R.id.tvPassword);

        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);

        loadText();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg:
                saveText();
                break;
            default:
                break;
        }
    }
    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, tvName.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        tvName.setText(savedText);
        tvLName.setText(savedText);
        tvEmail.setText(savedText);
        tvPassword.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}