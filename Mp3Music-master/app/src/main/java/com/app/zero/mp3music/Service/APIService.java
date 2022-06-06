package com.app.zero.mp3music.Service;

public class APIService {
    private static String base_url = "https://musicdohoahd.000webhostapp.com/server/";

    public static Dataservice getService() {
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
