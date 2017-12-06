package cl.cutiko.pokemongo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cutiko on 06-12-17.
 */

public class PokeInterceptor {

    private static final String BASE_URL = "https://us-central1-pokemongo-614b4.cloudfunctions.net/";

    public GetPokeStops get() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return interceptor.create(GetPokeStops.class);
    }

}
