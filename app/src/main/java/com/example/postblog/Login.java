package com.example.postblog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postblog.API.APIClient;
import com.example.postblog.API.APIinterface;
import com.example.postblog.Method.User;
import com.example.postblog.Method.UserResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

EditText u,p;
Button login;

    ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            u=findViewById(R.id.u);
            p=findViewById(R.id.p);
            login=findViewById(R.id.b);
            arrayList=new ArrayList<>();


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user= u.getText().toString();
                    String pass=p.getText().toString();
                    ProgressDialog progressDialog=new ProgressDialog(Login.this);
                    progressDialog.setMessage("Signing");
                    progressDialog.show();

                try {

                    APIinterface apIinterface = APIClient.getclient().create(APIinterface.class);
                    Call<UserResult> call = apIinterface.login(user,pass);
                    call.enqueue(new Callback<UserResult>() {
                        @Override
                        public void onResponse(Call<UserResult> call, Response<UserResult> response) {

                            arrayList = (ArrayList<User>) response.body().getPost();
                            Toast.makeText(Login.this, "Login Sucusess", Toast.LENGTH_SHORT).show();
                            String u = arrayList.get(0).getUserName();
                            String p = arrayList.get(0).getPassword();
                            if (user.equals(u) && pass.equals(p)) {
                                SharedPreferences preferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor ed = preferences.edit();
                                ed.putString("user_name", u);
                                ed.putString("password", p);
                                ed.putString("id",arrayList.get(0).getId());
                                ed.putString("email", arrayList.get(0).getEmail());
                                ed.putString("dp", arrayList.get(0).getDp());
                                ed.putString("bio", arrayList.get(0).getBio());
                                ed.putBoolean("is_regi", true);

                                ed.apply();

                                progressDialog.dismiss();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();

                            } else {
                            }


                        }

                        @Override
                        public void onFailure(Call<UserResult> call, Throwable t) {
                            Toast.makeText(Login.this, "Please Try Again!", Toast.LENGTH_SHORT).show();
                            Log.e("error", String.valueOf(t));
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(Login.this, "error =>"+e, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

                }
            });



    }
}