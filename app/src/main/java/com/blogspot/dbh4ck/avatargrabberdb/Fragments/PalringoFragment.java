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

public class PalringoFragment extends Fragment implements View.OnClickListener{

    public EditText getTarPal;
    private Button btn_ok_pal, btnSave_Pal;
    public ImageView imgPal;
    private Bitmap bmpPal;

    public PalringoFragment() {
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
        View v= inflater.inflate(R.layout.fragment_palringo, container, false);

        getTarPal = (EditText) v.findViewById(R.id.txtUserPal);
        btn_ok_pal = (Button) v.findViewById(R.id.btnokPal);
        btnSave_Pal = (Button) v.findViewById(R.id.btnsavePal);
        imgPal = (ImageView) v.findViewById(R.id.imgviewPal);

        btn_ok_pal.setOnClickListener(this);

        btnSave_Pal.setOnClickListener(new View.OnClickListener() {
            boolean success = false;
            @Override
            public void onClick(View view) {
                File direct = new File(Environment.getExternalStorageDirectory() + "/Palringo_Avatars");
                if (!direct.exists()) {
                    direct.mkdirs();
                }

                DownloadManager mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse("http://www.palringo.com/showavatar.php?id="+getTarPal.getText().toString()+"&type=g");
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("AvatarGrabberByDB")
                        .setDescription("coded by db~@nc")
                        .setDestinationInExternalPublicDir("/Palringo_Avatars", getTarPal.getText().toString()+".jpg");
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
    public void onClick(View v) {
        Picasso.with(getContext())
                .load("http://www.palringo.com/showavatar.php?id="+getTarPal.getText().toString())
                .into(imgPal);

    }


}
