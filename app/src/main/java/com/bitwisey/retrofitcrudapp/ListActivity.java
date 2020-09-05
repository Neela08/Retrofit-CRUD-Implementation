package com.bitwisey.retrofitcrudapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitwisey.retrofitcrudapp.Adapter.CustomAdapter;
import com.bitwisey.retrofitcrudapp.Adapter.RecyclerItemClickListener;
import com.bitwisey.retrofitcrudapp.Contacting.calling;
import com.bitwisey.retrofitcrudapp.Model.RetroInfo;
import com.bitwisey.retrofitcrudapp.Networking.Client;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    Dialog dialog;
    calling c;
    EditText text1,text2;
    Button dialogButton,deleteButton,updateButton,saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
      /**  Window window = ListActivity.this.getWindow(); undo the comments if u want to change status bar color, requires API LEVEL 21

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(ListActivity.this,R.color.gold));**/

        showList();

        dialog = new Dialog(ListActivity.this);
        dialog.setContentView(R.layout.dialog_box_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setAttributes(lp);
        text1 = (EditText) dialog.findViewById(R.id.nameView);
        text2 = (EditText) dialog.findViewById(R.id.numberView);
        dialogButton = (Button) dialog.findViewById(R.id.call);
        deleteButton = (Button) dialog.findViewById(R.id.deletebtn);
        updateButton=(Button)dialog.findViewById(R.id.updatebtn);
        saveButton=(Button)dialog.findViewById(R.id.savebtn);

    }
    public void showList()
    {
        progressDialog = new ProgressDialog(ListActivity.this);
        progressDialog.setMessage("Refreshing....");
        progressDialog.show();
        Call<List<RetroInfo>> call=Client.getInstance().getMyApi().getAllInfo();

        call.enqueue(new Callback<List<RetroInfo>>() {
            @Override
            public void onResponse(Call<List<RetroInfo>> call, Response<List<RetroInfo>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<RetroInfo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(final List<RetroInfo> infoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, infoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // adapter.notifyDataSetChanged();

        // recyclerView.invalidate();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //   Toast.makeText(ListActivity.this, "on item click", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //Toast.makeText(ListActivity.this,"on long item click",Toast.LENGTH_SHORT).show();



                        text1.setEnabled(false);
                        final String n = infoList.get(position).getName();
                        text1.setText(n);
                        text2.setEnabled(false);
                        final String nu = infoList.get(position).getNumber();
                        text2.setText(nu);
                        final int id=infoList.get(position).getId();

                        saveButton.setEnabled(false);
                        dialog.show();
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // dialog.dismiss();
                                c= new calling();
                                c.makephonecall(nu,ListActivity.this,ListActivity.this);

                            }
                        });
                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                calldeleteAPI(id);


                            }
                        });
                        updateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                text1.setEnabled(true);
                                text2.setEnabled(true);

                                saveButton.setEnabled(true);
                            }
                        });

                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String _name=text1.getText().toString();
                                String _number=text2.getText().toString();
                                updateData(id,_name,_number);
                                text1.setEnabled(false);
                                text2.setEnabled(false);
                                saveButton.setEnabled(false);

                            }
                        });

                    }
                }));


    }
    public void calldeleteAPI(int id){

        Call<ResponseBody> call=Client.getInstance().getMyApi().deleteinfo(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                // adapter.notifyDataSetChanged();
                String message = response.message();
                int status = response.code();

                showList();
                dialog.dismiss();
                Toast.makeText(ListActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(ListActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT);

            }
        });


    }
    public void updateData(int id,final String name, final String number)
    {
        Call<ResponseBody> call=Client.getInstance().getMyApi().updateinfo(id,name,number);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                Toast.makeText(ListActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();


                showList();
                text1.setText(name);
                text2.setText(number);


            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(ListActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT);
            }
        });
    }
}
