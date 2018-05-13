package com.mtag.app.muaythaiathletesguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VideosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        ImageButton wrapImageButton = (ImageButton)findViewById(R.id.wrapImageButton);
        wrapImageButton.setImageResource(R.drawable.wrap);
        wrapImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=yyDTDCvIQcc";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton punchImageButton = (ImageButton)findViewById(R.id.punchImageButton);
        punchImageButton.setImageResource(R.drawable.punch);
        punchImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=j0u8IXq6268";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton kickImageButton = (ImageButton)findViewById(R.id.kickImageButton);
        kickImageButton.setImageResource(R.drawable.kick);
        kickImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=UWW1TFWMcrE";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton lowkickImageButton = (ImageButton)findViewById(R.id.lowkickImageButton);
        lowkickImageButton.setImageResource(R.drawable.lowkick);
        lowkickImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=TT1u1KkAUy0";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton elbowImageButton = (ImageButton)findViewById(R.id.elbowImageButton);
        elbowImageButton.setImageResource(R.drawable.elbow);
        elbowImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=KcEw1_uobLQ";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton kneeImageButton = (ImageButton)findViewById(R.id.kneeImageButton);
        kneeImageButton.setImageResource(R.drawable.knee);
        kneeImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=lBQO9sEREnI";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton clinchImageButton = (ImageButton)findViewById(R.id.clinchImageButton);
        clinchImageButton.setImageResource(R.drawable.clinch);
        clinchImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=oc-cezX6Og8";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageButton teepImageButton = (ImageButton)findViewById(R.id.teepImageButton);
        teepImageButton.setImageResource(R.drawable.teep);
        teepImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=2SZxjVrguJ4";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
