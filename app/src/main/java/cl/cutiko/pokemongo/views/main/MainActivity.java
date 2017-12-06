package cl.cutiko.pokemongo.views.main;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import cl.cutiko.pokemongo.R;
import cl.cutiko.pokemongo.models.PokeStop;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PokestopsCallback {

    private static final int RC_GEO_PERMISSIONS = 456;
    private GoogleMap mGoogleMap;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To get familiar with location you should read this https://developer.android.com/training/location/index.html
        //Dont add every Google Service just the location services https://developers.google.com/android/guides/setup
        //The easiest way to set up Google maps if to create a Google Maps Activity, create one in another project if you need to, and follow the instructions in google_maps_api
        //In the raw folder you can find the file to make the gps in emulator simulate movement
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //TODO SEND THE DATA JUST ONCE, THEN ADD THE GEOPOINT MANUALLY IN THE WEB CONSOLE
        //FirebaseFirestore.getInstance().collection("pokestops").document("laslilas").set(new PokeStop(-33.4291,-70.594456,"Las Lilas", "laslilas"));


    }

    @SuppressLint("NewApi")
    private void askPermissions(){
        String permissions[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
        requestPermissions(permissions, RC_GEO_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (RC_GEO_PERMISSIONS == requestCode) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                positionRequest();
            } else {
                askPermissions();
            }
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void positionRequest(){
        final long interval = 5000;
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(interval);
        mLocationRequest.setFastestInterval(interval);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                List<Location> locations = locationResult.getLocations();
                if (locations.size() > 0) {
                    //TODO update ui with the location check slides
                    //TODO get the near by pokestops check slides
                    //new NearPokeStops(this).execute(latitude, longitude);
                }
            }
        };
        locationProviderClient.requestLocationUpdates(mLocationRequest, locationCallback, null);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            positionRequest();
        } else {
            askPermissions();
        }
    }

    @Override
    public void results(List<PokeStop> pokeStops) {
        //TODO update the UI with the pokestop
    }
}
