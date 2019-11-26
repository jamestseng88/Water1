package com.example.watter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edMonthly;
    boolean isNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        edMonthly = findViewById(R.id.month);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fee();
            }
        });
        Switch sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext = isChecked;
                TextView text = findViewById(R.id.type);
                text.setText(isNext ? getString(R.string.every_other_month) : getString(R.string.monthly));
            }
        });
    }

    public void fee() {
        String monthString = edMonthly.getText().toString();
        if (!TextUtils.isEmpty(monthString)) {
            float degree = Float.parseFloat(monthString);
            float fee = 0;
            if (degree >= 1 && degree <=10) {
                fee = degree*7.35f;
            } else if (degree >= 11 && degree <=30) {
                fee = degree * 9.45f - 21;
            } else if (degree >= 31 && degree <=50) {
                fee = degree * 11.55f - 84;
            } else {
                fee = degree * 12.075f - 110.5f;
            }
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(getString(R.string.extra_fee), fee);
            startActivity(intent);
//            new AlertDialog.Builder(this)
//                    .setTitle("month")
//                    .setMessage(getString(R.string.fee) + fee)
//                    .setPositiveButton(getString(R.string.ok), null)
//                    .show();
        }

        /*else {
            String nextString = edNext.getText().toString();
            if (!TextUtils.isEmpty(nextString)) {
                float degree = Float.parseFloat(nextString);
                float fee = 0;
                if (degree >= 1 && degree <=10) {
                    fee = degree*7.35f;
                } else if (degree >= 21 && degree <=60) {
                    fee = degree * 9.45f - 42;
                } else if (degree >= 61 && degree <=100) {
                    fee = degree * 11.55f - 168;
                } else {
                    fee = degree * 12.075f - 220.5f;
                }
//                new AlertDialog.Builder(this)
//                        .setTitle("next")
//                        .setMessage("Fee: " + fee)
//                        .setPositiveButton("OK", null)
//                        .show();

            }
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
