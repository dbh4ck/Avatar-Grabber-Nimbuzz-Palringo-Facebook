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


public class NimbuzzFragment extends Fragment implements View.OnClickListener{
    public EditText getTarNimb;
    private Button btn_ok_nimb, btnSave_Nimb;
    public ImageView imgNimb;
    private Bitmap bmpNimb;

    public NimbuzzFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nimbuzz, container, false);

        getTarNimb = (EditText) v.findViewById(R.id.txtUserNimb);
        btn_ok_nimb = (Button) v.findViewById(R.id.btnokNimb);
        btnSave_Nimb = (Button) v.findViewById(R.id.btnsaveNimb);
        imgNimb = (ImageView) v.findViewById(R.id.imgviewNimb);

        btn_ok_nimb.setOnClickListener(this);


        btnSave_Nimb.setOnClickListener(new View.OnClickListener() {
            boolean success = false;
            @Override
            public void onClick(View view) {


                File direct = new File(Environment.getExternalStorageDirectory() + "/Nimbuzz_Avatars");
                if (!direct.exists()) {
                    direct.mkdirs();
                }

                DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse("http://avatar.nimbuzz.com/getAvatar?jid="+getTarNimb.getText().toString()+"%40nimbuzz.com");
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("AvatarGrabberByDB")
                        .setDescription("coded by db~@nc")
                        .setDestinationInExternalPublicDir("/Nimbuzz_Avatars", getTarNimb.getText().toString()+".jpg");
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

        return v;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnokNimb:
                Picasso.with(getContext())
                        .load("http://avatar.nimbuzz.com/getAvatar?jid="+getTarNimb.getText().toString()+"%40nimbuzz.com")
                        .into(imgNimb);
        }

    }

}
