// Duncan Harris, Student Number: 220110530
package za.ac.cput.covidtracker;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import za.ac.cput.covidtracker.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private final long MIN_TIME = 1000;
    private final long MIN_DIST =  5;

    private LatLng latLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng capeTown = new LatLng(-33.918861, 18.423300);
        mMap.addMarker(new MarkerOptions().position(capeTown).title("Marker in Cape Town"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(capeTown));

        locationListener = new LocationListener()
        {

            @Override
            public void onLocationChanged(@NonNull Location location)
            {
                try
                {
                    latLng  = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                }
                catch (SecurityException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLocationChanged(@NonNull List<Location> locations)
            {
                LocationListener.super.onLocationChanged(locations);
            }

            @Override
            public void onProviderEnabled(@NonNull String provider)
            {
                LocationListener.super.onProviderEnabled(provider);
            }

            @Override
            public void onProviderDisabled(@NonNull String provider)
            {
                LocationListener.super.onProviderDisabled(provider);
            }
        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try
        {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }

    }
}