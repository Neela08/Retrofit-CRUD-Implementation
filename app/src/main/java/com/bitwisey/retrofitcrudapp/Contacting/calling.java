package com.bitwisey.retrofitcrudapp.Contacting;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class calling {

    private static final int REQUEST_CALL=1;
    public void makephonecall(String number, Context c, Activity activity){

        if(number.trim().length()>0)
        {
            if(ContextCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else
            {
                String dial="tel:"+number;
                activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }
        }
      /*  else
        {
            Toast.makeText(this,"enter phone number",Toast.LENGTH_SHORT).show();
        }*/
    }
}

