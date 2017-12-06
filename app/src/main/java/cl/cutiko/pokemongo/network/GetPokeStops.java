package cl.cutiko.pokemongo.network;

import java.util.List;

import cl.cutiko.pokemongo.models.PokeStop;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cutiko on 06-12-17.
 */

public interface GetPokeStops {

    @GET("nearPokestop")
    Call<List<PokeStop>> getNearBy(@Query("uid") String uid, @Query("latitude") double latitude, @Query("longitude") double longitude);

}
