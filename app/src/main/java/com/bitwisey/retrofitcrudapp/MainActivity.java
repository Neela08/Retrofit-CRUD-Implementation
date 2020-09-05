package com.bitwisey.retrofitcrudapp;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bitwisey.retrofitcrudapp.Networking.Client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtname,edtnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //undo the comments if you want to change the status bar color(requires API LEVEL 21)
     /**   Window window = MainActivity.this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.gold));**/
        edtname=(EditText)findViewById(R.id.editName);
        edtnumber=(EditText)findViewById(R.id.editNumber);



    }
    public void insertInfo()
    {
        String name=edtname.getText().toString().trim();
        String number=edtnumber.getText().toString().trim();

        if (name.isEmpty()) {
            edtname.setError("Enter name");
            edtname.requestFocus();
            return;
        }
        if (number.isEmpty()) {
            edtnumber.setError("Enter number");
            edtnumber.requestFocus();
            return;
        }

        Call<ResponseBody> call=Client.getInstance().getMyApi().insertdata(name,number);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MainActivity.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(View view) {
        insertInfo();
    }

    public void list(View view) {
        Intent i=new Intent(MainActivity.this,ListActivity.class);
        startActivity(i);
    }
}
