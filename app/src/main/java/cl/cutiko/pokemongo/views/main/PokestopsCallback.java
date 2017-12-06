package cl.cutiko.pokemongo.views.main;

import java.util.List;

import cl.cutiko.pokemongo.models.PokeStop;

/**
 * Created by cutiko on 06-12-17.
 */

public interface PokestopsCallback {

    void results(List<PokeStop> pokeStops);

}
