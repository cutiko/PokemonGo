package cl.cutiko.pokemongo.models;

/**
 * Created by cutiko on 06-12-17.
 */

public class PokeStop {

    private double latitude, longitude;
    private String name, key;

    public PokeStop() {
    }

    public PokeStop(double latitude, double longitude, String name, String key) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.key = key;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
