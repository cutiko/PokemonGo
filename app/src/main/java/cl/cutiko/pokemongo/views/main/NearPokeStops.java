package cl.cutiko.pokemongo.views.main;

import java.util.List;

import cl.cutiko.pokemongo.models.PokeStop;
import cl.cutiko.pokemongo.network.FindPokeStop;

/**
 * Created by cutiko on 06-12-17.
 */

public class NearPokeStops extends FindPokeStop {

    private PokestopsCallback callback;

    public NearPokeStops(PokestopsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(List<PokeStop> pokeStops) {
        if (pokeStops.size() > 0) {
            callback.results(pokeStops);
        }
    }
}
