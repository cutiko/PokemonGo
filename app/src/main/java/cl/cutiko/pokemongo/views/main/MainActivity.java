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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
                    Location location = locations.get(0);
                    if (mMarker != null) {
                        mMarker.remove();
                    }
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    mMarker = mGoogleMap.addMarker(new MarkerOptions().position(latLng));
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                    new NearPokeStops(MainActivity.this).execute(latitude, longitude);
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
        for (PokeStop pokeStop : pokeStops) {
            LatLng latLng = new LatLng(pokeStop.getLatitude(), pokeStop.getLongitude());
            mGoogleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        }
    }
}
