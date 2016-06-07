package no.hyper.current.cloudinarysample.cloudinary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import no.hyper.current.cloudinarysample.R;

public class CloudinaryActivity extends AppCompatActivity {
    private static final String TAG = CloudinaryActivity.class.getSimpleName();

    private static final String CLOUD_NAME = "hyper";

    private static final String API_KEY = "449292143527987";

    private static final String API_SECRET = "iUvSb0OWJYyjtuA2d33H6lH3Qds";

    Cloudinary cloudinary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map config = new HashMap();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        cloudinary = new Cloudinary(config);
    }

    public void uploadVideo(View v) {
        isStoragePermissionGranted();
        Toast.makeText(this, "start uploading", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                File downloadDir  = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File toUpload = new File(downloadDir, "hyper.mp4");
                try {
                    Map result = cloudinary.uploader().upload(toUpload, ObjectUtils.asMap("resource_type", "video", "upload_preset", "hyper-preset"));
                    Set<Map.Entry> set = result.entrySet();
                    for (Map.Entry entry: set) {
                        Log.v(TAG, entry.getKey() + " ===> " + entry.getValue());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.v(TAG, "upload finished");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CloudinaryActivity.this, "uploading done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {
                Log.v(TAG,"Permission is revoked");
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
        }
    }


}
