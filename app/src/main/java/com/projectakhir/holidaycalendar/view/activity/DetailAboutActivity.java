package com.projectakhir.holidaycalendar.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projectakhir.holidaycalendar.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailAboutActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvAddress;
    private Button btnContact;
    private CircleImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_about);

        imageProfile = findViewById(R.id.profile_image);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvAddress = findViewById(R.id.tv_address);
        btnContact = findViewById(R.id.btn_contact);

        Bundle b = getIntent().getExtras();

        int profile = b.getInt("Image", 1);
        if (profile == 1) {
            imageProfile.setImageResource(R.drawable.dzaky);
        }else {
            imageProfile.setImageResource(R.drawable.miftahur);
        }

        tvName.setText(b.getCharSequence("Name"));
        tvEmail.setText(b.getCharSequence("Email"));
        tvAddress.setText(b.getCharSequence("Address"));

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+tvEmail.getText()));
                startActivity(intent);
            }
        });
        setTitle(tvName.getText());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}