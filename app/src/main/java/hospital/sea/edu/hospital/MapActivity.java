package hospital.sea.edu.hospital;

import android.*;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private Marker marker1;
    private Marker marker2;
    private Marker marker3;
    private Marker marker4;
    private Marker marker5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng zp1 = new LatLng(47.826785, 35.202472);
        marker1 = mMap.addMarker(new MarkerOptions()
                .position(zp1)
                .title("Поликлиника Мотор Сич")
                .snippet("ул. Брюллова, 6")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icohosp)));


        final LatLng zp2 = new LatLng(47.813780, 35.048130);
        marker2 = mMap.addMarker(new MarkerOptions()
                .position(zp2)
                .title("Центаральная поликлиника №1")
                .snippet("ул.Запорожского казачества, д. 25")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icohosp)));

        final LatLng zp3 = new LatLng(47.877429, 35.060163);
        marker3 = mMap.addMarker(new MarkerOptions()
                .position(zp3)
                .title("Городская поликлиника №9")
                .snippet("ул.Дудыкина 6")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icohosp)));

        final LatLng zp4 = new LatLng(47.858849, 35.106647);
        marker4 = mMap.addMarker(new MarkerOptions()
                .position(zp4)
                .title("Городская поликлиника №3")
                .snippet("проспект Металлургов, д. 9")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icohosp)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zp4, 10));

        final LatLng zp5 = new LatLng(47.813143, 35.179812);
        marker5 = mMap.addMarker(new MarkerOptions()
                .position(zp5)
                .title("Детская поликлиника №7")
                .snippet("ул.Горького, д. 32А")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icohosp)));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

}

