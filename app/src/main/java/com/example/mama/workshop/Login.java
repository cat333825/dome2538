package com.example.mama.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private Button btLog;
    private Button btRegis;
    private EditText edUser;
    private EditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setListener();
        validate();
    }

    private boolean validate() {
        String username = edUser.getText().toString();
        String password = edPass.getText().toString();

        if (username.isEmpty()) return false;

        if (password.isEmpty()) return  false;

        return true;
    }

    private void setListener() {
        btLog = (Button)findViewById(R.id.btLog);
        btRegis = (Button)findViewById(R.id.btRegis);
        edUser = (EditText)findViewById(R.id.edUser);
        edPass = (EditText)findViewById(R.id.edPass);

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //ToDo
                    new Login.Login2(edUser.getText().toString(),
                            edPass.getText().toString()).execute();
                }else{
                    Toast.makeText(Login.this,"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }
    private class Login2 extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;

        public Login2(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        Intent i = new Intent(Login.this, NewsList.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(Login.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException ex) {

            }

        }
    }
}

