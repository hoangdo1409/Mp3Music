package com.app.zero.mp3music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zero.mp3music.Activity.Activity_Play_Nhac;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPlaylistAdapter extends RecyclerView.Adapter<MyPlaylistAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Baihat> baihatArrayList;

    public MyPlaylistAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_my_playlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlaylistAdapter.ViewHolder viewHolder, int i) {
        Baihat baihat = baihatArrayList.get(i);
        viewHolder.txtCasi.setText(baihat.getSinger());
        viewHolder.txtTen.setText(baihat.getNameSong());

        Picasso.get().load(baihat.getImagesong()).into(viewHolder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtCasi, txtSoluotthich;
        ImageView imgHinh, imgLuotthich;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenMyplaylist);
            txtCasi = itemView.findViewById(R.id.txtCasiMyplaylist);
            txtSoluotthich = itemView.findViewById(R.id.countLike);
            imgHinh = itemView.findViewById(R.id.imgMyplaylist);
            imgLuotthich = itemView.findViewById(R.id.imgMyplaylist_love);
            //12/1
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", baihatArrayList.get(getPosition()).getIdSong());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            assert ketqua != null;
                            if (ketqua.equals("Success")) {
                                Toast.makeText(context, "Add!!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                    imgLuotthich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_Play_Nhac.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
