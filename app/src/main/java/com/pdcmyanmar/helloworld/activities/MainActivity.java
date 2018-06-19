package com.pdcmyanmar.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pdcmyanmar.helloworld.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //output log
                Log.d("Fun5_HelloWorld", "User tap login Begin");

                EditText etUserPhone = findViewById(R.id.et_user_phone);
                String userPhone = etUserPhone.getText().toString();
                if(TextUtils.isEmpty(userPhone)){
                    etUserPhone.setError("Enter your phone here");
                    return;
                }

                EditText etPassword = findViewById(R.id.et_user_password);
                String password = etPassword.getText().toString();
                if(TextUtils.isEmpty(password)){
                    etPassword.setError("Enter your password here");
                    return;
                }

                if(TextUtils.equals(userPhone,"09123456789")
                        && (TextUtils.equals(password, "abc"))){
                    Snackbar.make(v, "Login Success", Snackbar.LENGTH_INDEFINITE).show();//LENGTH_INDEFINITE always show the message
                } else{
                    Toast.makeText(v.getContext(),"Phone number of password is incorrect", Toast.LENGTH_SHORT).show();// show message in short time
                }
            }
        });

        final TextView tvRegister = findViewById(R.id.tv_register_new_account);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(view.getContext(), "Navigate to Register", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Navigate to Register", Snackbar.LENGTH_INDEFINITE).show();

                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);

            }
        });
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
