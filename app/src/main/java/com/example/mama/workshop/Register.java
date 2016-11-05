package com.example.mama.workshop;

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

public class Register extends AppCompatActivity {

    private EditText edReDis;
    private EditText edReUser;
    private EditText edRePass;
    private EditText edReCon;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();
        setListener();
        validate();
    }

    private boolean validate() {
        String username = edReUser.getText().toString();
        String password = edRePass.getText().toString();
        String passwordConfirm = edReCon.getText().toString();
        String displayName = edReDis.getText().toString();

        if (username.isEmpty()) return false;

        if (password.isEmpty()) return false;

        if (passwordConfirm.isEmpty()) return false;

        if (!password.equals(passwordConfirm)) return false;

        if (displayName.isEmpty()) return false;
        return false;
    }

    private void bindWidget() {
        edReDis = (EditText) findViewById(R.id.edReDis);
        edReUser = (EditText) findViewById(R.id.edReUser);
        edRePass = (EditText) findViewById(R.id.edRePass);
        edReCon = (EditText) findViewById(R.id.edReCon);


    }

    private void setListener(){
        btRegister = (Button) findViewById(R.id.btRegis);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //ToDo
                    new Register2(edReUser.getText().toString(),
                            edRePass.getText().toString(),
                            edReCon.getText().toString(),
                            edReDis.getText().toString()).execute();
                }else{
                    Toast.makeText(Register.this,"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class Register2 extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;
        private String passwordCon;
        private String DisplayName;

        public Register2(String username, String password, String passwordCon, String displayName) {
            this.username = username;
            this.password = password;
            this.passwordCon = passwordCon;
            DisplayName = displayName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .add("passowrd_con", passwordCon)
                    .add("display_name", DisplayName)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/signup.php")
                    .post(requestBody)
                    .build();
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()){
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
            Toast.makeText(Register.this,s,Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("resilt");
                    if(resultObj.getInt("result") == 1) {
                        Toast.makeText(Register.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Register.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException ex) {

            }

        }
    }
}
