package com.app.zero.mp3music.Service;

import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songBanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistDay();

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @GET("baihatmyplaylist.php")
    Call<List<Baihat>> GetMyPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoQuangCao(@Field("id_ads") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoPlaylist(@Field("id_playlist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachPlaylist();

    //12/1

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("likesSong") String likesSong, @Field("idSong") String idSong);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> SearchBaiHat(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("myPlaylist.php")
    Call<String> UpdatemyPlaylist(@Field("myplaylist") String myPlaylist, @Field("idSong") String idSong);
    //
/*


    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetChuDeDay();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhsachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAlbum();




 */


}
