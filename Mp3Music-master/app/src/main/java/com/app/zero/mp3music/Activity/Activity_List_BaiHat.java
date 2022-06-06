package com.app.zero.mp3music.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.zero.mp3music.Adapter.DanhsachbaihatAdapter;
import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.Model.Quangcao;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_BaiHat extends AppCompatActivity {

    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangBaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        DataIntent();
        setID();
        init();

        if (quangcao != null && !quangcao.getNameSong().equals("")) {
            setValueInView(quangcao.getNameSong(), quangcao.getImageSong());
            GetDataQuangcao(quangcao.getIdAds());
        }

        if (playlist != null && !playlist.getNamePlaylist().equals("")) {
            setValueInView(playlist.getNamePlaylist(), playlist.getImagePlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        //12/1
        if (album != null && !album.getNameAlbum().equals("")) {
            setValueInView(album.getNameAlbum(), album.getImage());
            GetDataAlbum(album.getIdAlbum());
        }
        //
    }
    //12/1
    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
//
    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void setValueInView(String nameSong, String imageSong) {
        collapsingToolbarLayout.setTitle(nameSong);
        /*try {
            URL url = new URL(imageSong);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        Picasso.get().load(imageSong).into(imgdanhsachcakhuc);
    }


    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
        floatingActionButton.setEnabled(false);
    }

    private void setID() {
        coordinatorLayout          = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout    = findViewById(R.id.collasingToolbar);
        toolbar                    = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerDanhSachBaihat);
        floatingActionButton       = findViewById(R.id.floatingActionButton);
        imgdanhsachcakhuc          = findViewById(R.id.imgdanhsachcakhuc);
    }


    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getNameSong(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            //12/1
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
            //
        }
    }

    //12/1
    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_List_BaiHat.this, Activity_Play_Nhac.class);
                intent.putExtra("cacbaihat", mangBaihat);
                startActivity(intent);
            }
        });
    }

}