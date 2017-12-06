package cl.cutiko.pokemongo.network;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.cutiko.pokemongo.models.PokeStop;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by cutiko on 06-12-17.
 */

public class FindPokeStop extends AsyncTask<Double, Void, List<PokeStop>> {

    @Override
    protected List<PokeStop> doInBackground(Double... params) {
        GetPokeStops getPokeStops = new PokeInterceptor().get();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Call<List<PokeStop>> call = getPokeStops.getNearBy(uid, params[0], params[1]);
        List<PokeStop> pokeStops = new ArrayList<>();
        try {
            Response<List<PokeStop>> response = call.execute();
            if (200 == response.code() && response.isSuccessful()) {
                List<PokeStop> list = response.body();
                if (list != null && list.size() > 0) {
                    pokeStops.addAll(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokeStops;
    }
}
