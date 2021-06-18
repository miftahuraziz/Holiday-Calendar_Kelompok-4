package com.projectakhir.holidaycalendar.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.view.activity.DetailAboutActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutFragment extends Fragment{

    private Context context;
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();
        CircleImageView profileDzaky = view.findViewById(R.id.profile_dzaky);
        TextView tvNameDzaky = view.findViewById(R.id.tv_name_dzaky);
        TextView tvEmailDzaky = view.findViewById(R.id.tv_email_dzaky);
        TextView tvNameMiftahur = view.findViewById(R.id.tv_name_miftahur);
        TextView tvEmailMiftahur = view.findViewById(R.id.tv_email_miftahur);

        CardView cvDzaky = view.findViewById(R.id.cv_developer_dzaky);
        cvDzaky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAboutActivity.class);

                Bundle bundleDzaky = new Bundle();

                bundleDzaky.putInt("Image", 1);
                bundleDzaky.putString("Name", tvNameDzaky.getText().toString());
                bundleDzaky.putString("Email", tvEmailDzaky.getText().toString());
                bundleDzaky.putString("Address", "Yogyakarta, DIY");
                intent.putExtras(bundleDzaky);

                context.startActivity(intent);
            }
        });

        CardView cvMiftahur = view.findViewById(R.id.cv_developer_miftahur);
        cvMiftahur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAboutActivity.class);

                Bundle bundleMiftahur = new Bundle();

                bundleMiftahur.putInt("Image", 2);
                bundleMiftahur.putString("Name", tvNameMiftahur.getText().toString());
                bundleMiftahur.putString("Email", tvEmailMiftahur.getText().toString());
                bundleMiftahur.putString("Address", "Sleman, DIY");
                intent.putExtras(bundleMiftahur);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}