package com.tech.gigabyte.writeexternal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageview;
    ByteArrayOutputStream bytearrayoutputstream;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        imageview = (ImageView) findViewById(R.id.iv);

        //Implements an output stream in which the data is written into a byte array.
        bytearrayoutputstream = new ByteArrayOutputStream();

        //On Button Click Event
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                // TODO Auto-generated method stub

                //Creating Bitmap objects
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.iv_image);

                //Getting File Directory
                File f = new File(getExternalFilesDir(toString()), "/image.png");
                try {

                    //Writing streams of raw bytes such as image data
                    FileOutputStream outStream = new FileOutputStream(f);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                //Taking User to Open Saved Image from External Storage
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(f), "image/png");
                startActivity(intent);

                //When Image Saved --> A Successful Notify Toast
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

            }
        });
    }

}