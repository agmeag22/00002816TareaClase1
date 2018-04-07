package com.agmeag.a00002816tareaclase1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageButton sndBtn;
        sndBtn = (ImageButton) findViewById(R.id.Send);
        sndBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: "+getResources().getText(R.string.nombre)+"\nCarrera: "+getResources().getText(R.string.carrera)+"\nGithub: "+getResources().getText(R.string.github)+"\nTelefono: "+getResources().getText(R.string.telefono)+"\nEmail: "+getResources().getText(R.string.email)+"\nTwitter: "+getResources().getText(R.string.twitter));
//                sendIntent.setType("text/plain");
//                startActivity(Intent.createChooser(sendIntent, "Sharing"));
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.perfil);
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/LatestShare.png";
                OutputStream out = null;
                File file=new File(path);
                try {
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG ,100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                path=file.getPath();
                Uri bmpUri = Uri.parse("file://"+path);
                Intent shareIntent;
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: "
                        +getResources().getText(R.string.nombre) +"\nCarrera: "
                        +getResources().getText(R.string.carrera)+"\nGithub: "
                        +getResources().getText(R.string.github)+"\nTelefono: "
                        +getResources().getText(R.string.telefono)+"\nEmail: "
                        +getResources().getText(R.string.email)+"\nTwitter: "
                        +getResources().getText(R.string.twitter));
                shareIntent.setType("image/png");
                startActivity(Intent.createChooser(shareIntent,"Sharing"));
            }
        });

    }
}


