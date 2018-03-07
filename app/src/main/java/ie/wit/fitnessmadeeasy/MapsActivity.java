package ie.wit.fitnessmadeeasy;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LatLng latLng;
    public static ArrayList<LatLng> latLngs;
    TextView dist;
    TextView speeds;
    TextView ca;
    DataBaseHelper helper = new DataBaseHelper(this);

    //String totCal;
    Polyline line;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latLngs = new ArrayList<LatLng>();
        dist = (TextView) findViewById(R.id.tv_dist);
        speeds = (TextView) findViewById(R.id.tv_speed);
       // cal_cal = (Button) findViewById(R.id.cal_cal);
        ca = (TextView) findViewById(R.id.cal);
        // dist.setText(totalDistance());





        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }


    @Override
    public void onLocationChanged(Location l) {
        double latitude = l.getLatitude();
        double longitude = l.getLongitude();
        latLng = new LatLng(latitude, longitude);
        latLngs.add(latLng);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

        if(l == null){
            speeds.setText("-.-- m/s");
        }
        else
        {
            float nCurrentSpeed = l.getSpeed();
            float mCurrentSpeed = (nCurrentSpeed * 3600)/1000;
           // String oCurrentSpeed = mCurrentSpeed + " Km/h";
            speeds.setText(mCurrentSpeed + " km/h");
        }


        drawPath();
        totalDistance();


    }


    public void drawPath() {

        mMap.clear();  //clears all Markers and Polylines

        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < latLngs.size(); i++) {
            LatLng point = latLngs.get(i);
            options.add(point);
        }
        // mMap.addMarker; //add Marker in current position
        line = mMap.addPolyline(options); //add Polyline

    }

    public String totalDistance() {

        String totDist = "";
        double distance = 0;


        for (int i = 0; i < latLngs.size() - 1; i++) {
            LatLng first = latLngs.get(i);
            LatLng second = latLngs.get(i + 1);

            float[] results = new float[1];

            Location.distanceBetween(
                    first.latitude,
                    first.longitude,
                    second.latitude,
                    second.longitude,
                    results);

            distance += results[0];
            double di = (distance/1000);
            totDist =(new DecimalFormat("##.##").format(di) + " Km");
           // totDist = di + " Km";
            dist.setText(totDist);
            //  totDist = "" + (distance.getValue());
        }


        return totDist;
    }

   /**
         public String getCal() {
        //  Kcal/Min ~= 0.0005 * bodyMassKg * metersWalkedInAMin + 0.0035

        //Double bmiStr = helper.getBmi();
        double weightStr = Double.parseDouble(helper.getWeight());
        double diStr = Double.parseDouble(totalDistance());

        double cal = (0.57 * (weightStr * 2.2));
        double ano = (cal * diStr);


        totCal = "" + (new DecimalFormat("##.##").format(ano));

       // ca.setText(totCal);


    return totCal;
}
**/


   public void save (View v){
       SharedPreferences sharedPref = getSharedPreferences("DistanceTravelled", Context.MODE_PRIVATE);

       SharedPreferences.Editor editor = sharedPref.edit();
       editor.putString("distance", dist.getText().toString());
       editor.apply();
       String d = sharedPref.getString("distance", "");
       ca.setText(d);



       Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

   }




    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}






    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}
