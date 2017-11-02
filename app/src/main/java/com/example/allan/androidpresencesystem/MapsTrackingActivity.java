package com.example.allan.androidpresencesystem;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class MapsTrackingActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference locationsRef;
    private String email;
    private Double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //initialize Firebase
        locationsRef = FirebaseDatabase.getInstance().getReference("Locations");

        if (getIntent() != null){
            email = getIntent().getStringExtra("email");
            lat = getIntent().getDoubleExtra("lat", 0);
            lng = getIntent().getDoubleExtra("lng", 0);
        }

        if (!TextUtils.isEmpty(email)){
            loadLocationForThisUser(email);
        }
    }


    /**
     * Load location of all users
     * @param email
     */
    private void loadLocationForThisUser(String email) {
        Query queryUserLocation = locationsRef.orderByChild("email").equalTo(email);
        queryUserLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Tracking tracking = postSnapshot.getValue(Tracking.class);

                    //add marker for other user who is online
                    LatLng otherUserLatlong = new LatLng(Double.parseDouble(tracking.getLat()),
                            Double.parseDouble(tracking.getLng()));

                    //Create location from current user
                    Location currentUserLocation  = new Location("");
                    currentUserLocation.setLatitude(lat);
                    currentUserLocation.setLongitude(lng);

                    // Create location from other user
                    Location otherUserLocation  = new Location("");
                    currentUserLocation.setLatitude(Double.parseDouble(tracking.getLat()));
                    currentUserLocation.setLongitude(Double.parseDouble(tracking.getLng()));


                    //clear old marker
                    mMap.clear();

                    //create marker for the other users
                    mMap.addMarker(new MarkerOptions()
                            .position(otherUserLatlong)
                            .title(tracking.getEmail())
                            .snippet("Distance " + new DecimalFormat("#.#")
                            .format((currentUserLocation.distanceTo(otherUserLocation))/10000) + " km")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12.0f));
                }

                //create marker for current user
                LatLng currentUserLatLong = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(currentUserLatLong)
                .title(FirebaseAuth.getInstance().getCurrentUser().getEmail()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //Create function to calculate distance from other users
    private double distanceToOthers(Location currentUserLocation, Location otherUserLocation) {
        double theta = currentUserLocation.getLongitude() - otherUserLocation.getLongitude();
        double distance = Math.sin(deg2Rad(currentUserLocation.getLatitude()))
                * Math.sin(deg2Rad(otherUserLocation.getLatitude()))
                * Math.cos(deg2Rad(currentUserLocation.getLatitude()))
                * Math.cos(deg2Rad(otherUserLocation.getLatitude()))
                * Math.cos(deg2Rad(theta));

        distance = Math.acos(distance);
        distance = rad2Deg(distance);
        distance = distance * 60 * 1.1515;

        return distance;

    }

    //calculate radian to degree
    private double rad2Deg(double radian) {
        return radian * 180 / Math.PI;
    }

    //calculate degree to radian
    private double deg2Rad(double deg) {
        return (deg * Math.PI / 180.0);
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


    }
}
