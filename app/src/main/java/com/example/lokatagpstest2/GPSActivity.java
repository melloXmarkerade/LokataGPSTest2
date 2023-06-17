package com.example.lokatagpstest2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.lokatagpstest2.databinding.ActivityGpsactivityBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class GPSActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGpsactivityBinding binding;

    private final int FINE_PERMISSION_CODE = 1, COARSE_PERMISSION_CODE = 1;

    Button Direction;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    //NOTE: PROTOTYPE ACTIVITY ONLY AND CONCEPTS OF MANIPULATION OF FUNCTIONS OF GOOGLE API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGpsactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


        /*Direction = findViewById(R.id.Direction);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Direction();
            }
        });*/

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Current Location
        LatLng CurrentLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions options = new MarkerOptions().position(CurrentLocation).title("Our Current Position");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(options);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(CurrentLocation, 12);
        googleMap.animateCamera(cameraUpdate);

        // Add a marker in Mandaue Impoundment Office
        LatLng MadaueImpoundment = new LatLng(10.323482, 123.941507);
        mMap.addMarker(new MarkerOptions().position(MadaueImpoundment).title("Mandaue Impoundment Office"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MadaueImpoundment));
        CameraUpdate cameraUpdate1 = CameraUpdateFactory.newLatLngZoom(MadaueImpoundment , 12);
        googleMap.animateCamera(cameraUpdate1);

        // Add a marker in Cebu City Transportation Office
        LatLng CCTOImpoundmentOffice = new LatLng(10.265406, 123.873938);
        mMap.addMarker(new MarkerOptions().position(CCTOImpoundmentOffice).title("CCTO Impoundment Office"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CCTOImpoundmentOffice));
        CameraUpdate cameraUpdate2 = CameraUpdateFactory.newLatLngZoom(CCTOImpoundmentOffice, 12);
        googleMap.animateCamera(cameraUpdate2);

        // Add a marker in Lapu-Lapu Impoundment Area
        LatLng LapuLapuImpoundmentArea = new LatLng(10.304700, 123.957575);
        mMap.addMarker(new MarkerOptions().position(LapuLapuImpoundmentArea).title("Lapu-Lapu ImpoundmentArea"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LapuLapuImpoundmentArea));
        CameraUpdate cameraUpdate3 = CameraUpdateFactory.newLatLngZoom(LapuLapuImpoundmentArea, 11);
        googleMap.animateCamera(cameraUpdate3);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE || requestCode == COARSE_PERMISSION_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(this, "Location Permission is Block, Please allow in the Application Permission", Toast.LENGTH_LONG).show();
            }
        }
    }
   /* private void Direction(){

        //Current Location
        LatLng CurrentLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions options = new MarkerOptions().position(CurrentLocation).title("Our Current Position");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(options);

        // Add a marker in Mandaue Impoundment Office
        LatLng MadaueImpoundment = new LatLng(10.323482, 123.941507);
        mMap.addMarker(new MarkerOptions().position(MadaueImpoundment).title("Mandaue Impoundment Office"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(MadaueImpoundment));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //In order to give permission of the application if the permission is granted
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        Direction.setOnClickListener(v -> {
            String userLocation = String.valueOf(CurrentLocation);
            String userDestination = String.valueOf(MadaueImpoundment);

            if (userLocation.equals(" ") || userDestination.equals(" ")) {
                Toast.makeText(this, "Error Location", Toast.LENGTH_SHORT).show();
            }else {
                getDirection(userLocation,userDestination);
            }
        });
    }
    private void getDirection(String userLocation, String userDestination){
        try{
            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+userLocation+"/"+userDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(String.valueOf(Intent.FLAG_ACTIVITY_NEW_TASK));
            startActivity(intent);
        }catch (ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en&gl=US");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(String.valueOf(Intent.FLAG_ACTIVITY_NEW_TASK));
            startActivity(intent);
        }
    }*/
    private void getLastLocation() {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //In order to give permission of the application if the permission is granted
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);

                return;
            }
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location !=null){
                        currentLocation = location;
                            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                            mapFragment.getMapAsync(GPSActivity.this);
                    }
                }
            });


    }



}