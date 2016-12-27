package com.blogspot.dbh4ck.avatargrabberdb.Fragments;


import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.dbh4ck.avatargrabberdb.R;
import com.squareup.picasso.Picasso;

import java.io.File;


public class FacebookFragment extends Fragment implements View.OnClickListener{
    public EditText getTarFb;
    private Button btn_ok_fb, btnSave_Fb;
    public ImageView imgFb;
    private Bitmap bmpFb;


    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_facebook, container, false);

        getTarFb = (EditText) v.findViewById(R.id.txtUserFb);
        btn_ok_fb = (Button) v.findViewById(R.id.btnokFb);
        btnSave_Fb = (Button) v.findViewById(R.id.btnsaveFb);
        imgFb = (ImageView) v.findViewById(R.id.imgviewFb);

        btn_ok_fb.setOnClickListener(this);

        btnSave_Fb.setOnClickListener(new View.OnClickListener() {
            boolean success = false;
            @Override
            public void onClick(View view) {
                File direct = new File(Environment.getExternalStorageDirectory() + "/Facebook_Avatars");
                if (!direct.exists()) {
                    direct.mkdirs();
                }

                DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse("http://graph.facebook.com/"+getTarFb.getText().toString()+"/picture");
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("AvatarGrabberByDB")
                        .setDescription("coded by db~@nc")
                        .setDestinationInExternalPublicDir("/Facebook_Avatars", getTarFb.getText().toString()+".jpg");
                mgr.enqueue(request);
                success = true;

                if(success){
                    Toast.makeText(getActivity(),"Imaged Saved!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),"Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  v;
    }

    @Override
    public void onClick(View view) {

        Picasso.with(getContext())
                .load("http://graph.facebook.com/"+getTarFb.getText().toString()+"/picture")
                .resize(350,350)
                .into(imgFb);
    }
}
