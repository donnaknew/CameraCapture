package com.example.younghokim.samplecaptureintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.lang.String;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1001;
   // Button btnCam;
    ImageView imgView;
    File file = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        if(isSDPresent)
        {
            // yes SD-card is present
            Log.w("abcdefg:", "goodddddd");
        }
        else
        {
            // Sorry
            Log.w("bad news:", "badddddd");
        }*/
     //   btnCam = (Button)findViewById(R.id.button_camera);
        imgView = (ImageView)findViewById(R.id.imageView_1);

        try{
            file = createFile();
        }catch(IOException e){
            Toast.makeText(getApplicationContext(), "ERROR!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
       /* btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });*/

    }
    public void onButton1Clicked(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
    private File createFile() throws IOException{
        String imageFileName = "test.jpg";
        String storageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File curFile = new File(storageDir, imageFileName);
        return curFile;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){ //int requestCode == REQUEST_IMAGE_CAPTURE of startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            if(file != null){
                Log.w("file dirrrrrrrrrrrrrr: ", file.getAbsolutePath());
                Toast.makeText(getApplicationContext(), "Good!", Toast.LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imgView.setImageBitmap(bitmap);
            }else{
                Toast.makeText(getApplicationContext(), "File is null.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
