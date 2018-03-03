package com.adwidian.ramaniapi.projectkp;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.ujuizi.ramani.mapsapi.MapEventListener;
import com.ujuizi.ramani.mapsapi.MapView;
import com.ujuizi.ramani.mapsapi.account.RMAccountManager;
import com.ujuizi.ramani.mapsapi.input.Marker;

import org.oscim.android.cache.TileCache;
import org.oscim.core.GeoPoint;
import org.oscim.core.MapPosition;
import org.oscim.layers.marker.MarkerSymbol;
import org.oscim.layers.tile.bitmap.BitmapTileLayer;
import org.oscim.layers.tile.buildings.BuildingLayer;
import org.oscim.layers.tile.vector.VectorTileLayer;
import org.oscim.layers.tile.vector.labeling.LabelLayer;
import org.oscim.map.Map;
import org.oscim.theme.ThemeLoader;
import org.oscim.theme.VtmThemes;
import org.oscim.tiling.TileSource;
import org.oscim.tiling.source.bitmap.DefaultSources;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements RMAccountManager.RMAccountManagerListener {

    private MapView mMapView;
    private Map mMap;

    PermissionHelper permissionHelper;
    private Marker markerRealLoc,markerLoc,markerLoc1,markerLoc2,markerLoc3,markerLoc4,markerLoc5,
            markerLoc6,markerLoc7,markerLoc8,markerLoc9,markerLoc10,markerLoc11;
    private Drawable drawableCurrentLoc,drawableLoc,drawableLoc1,drawableLoc2,drawableLoc3,drawableLoc4,drawableLoc5,
            drawableLoc6,drawableLoc7,drawableLoc8,drawableLoc9,drawableLoc10,drawableLoc11;
    private Location currentLocation,currentLoc,currentLoc1,currentLoc2,currentLoc3,currentLoc4,currentLoc5,
            currentLoc6,currentLoc7,currentLoc8,currentLoc9,currentLoc10,currentLoc11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        RMAccountManager.init(this, this, "eae82448b0f1890d5285cf46d505ea3a");

        permissionHelper = new PermissionHelper(this);
        permissionHelper.setPermissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone(int REQUEST_CODE) {
                onPermissionDone(REQUEST_CODE);
            }
        });
        List<String> listPermissions = new ArrayList<>();
        listPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        listPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionHelper.checkAndRequestPermissions(listPermissions);

    }

    private void onPermissionDone(int REQUEST_CODE) {
        RMAccountManager.init(this, this, "eae82448b0f1890d5285cf46d505ea3a");
    }


    float bearing = 30.0f;
    float lon = 10.0f;

    @Override
    public void onMapAuthDone() {
        mMapView = findViewById(R.id.mapView);
        mMap = mMapView.map();
        //offlineMap();
        setUpMap();
        updateLocationMarker();
        addLocationMarker();
        addLocationMarker1();
        addLocationMarker2();
        addLocationMarker3();
        addLocationMarker4();
        addLocationMarker5();
        addLocationMarker6();
        addLocationMarker7();
        addLocationMarker8();
        addLocationMarker9();
        addLocationMarker10();
        addLocationMarker11();

        mMapView.setGestureListener(new MapEventListener() {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }


            @Override
            public boolean onLongPress(GeoPoint geoPoint) {
                MapPosition mapPosition = mMap.getMapPosition();
//                mapPosition.setBearing(bearing);
                mapPosition.setPosition(new GeoPoint(mapPosition.getLatitude(), mapPosition.getLongitude() - lon));
                mMapView.setMapPosition(mapPosition);
                bearing = bearing + 15.0f;
                if (bearing >= 360.f)
                    bearing = 0f;
                return false;
            }

            @Override
            public boolean onSingleTapUp(GeoPoint geoPoint) {
                return false;
            }

            @Override
            public boolean onDoubleTap(GeoPoint geoPoint) {
                return false;
            }
        });

    }


    private void setUpMap() {
//      OSciMap4TileSource tileSource = new OSciMap4TileSource();
        TileSource tileSource = DefaultSources.OPENSTREETMAP.build();

        mMapView.setMapPosition(new GeoPoint(-6.983805, 110.410544), 15);
        TileCache mCache = new TileCache(this, null, "dataopenstreetmaps-tiles.db");
        mCache.setCacheSize(512 * (1 << 10));
        tileSource.setCache(mCache);

        VectorTileLayer mBaseLayer = mMap.setBaseMap(tileSource);
        mMap.layers().add(0,new BitmapTileLayer(mMap, tileSource));
        mMap.layers().add(1,new BuildingLayer(mMap, mBaseLayer));
        mMap.layers().add(2,new LabelLayer(mMap, mBaseLayer));
        mMap.setTheme(ThemeLoader.load(VtmThemes.OSMARENDER), true);
    }


    private void updateLocationMarker() {
        currentLocation = new Location("");
        currentLocation.setLatitude(-6.973777);
        currentLocation.setLongitude(110.414669);
        if (markerRealLoc == null) {
            markerRealLoc = new Marker(mMapView, this);
            markerRealLoc.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerRealLoc.showBubble(true);
        }

        if (drawableCurrentLoc == null)
            drawableCurrentLoc = getResources().getDrawable(R.drawable.marker_red);

        markerRealLoc.add("Real Location", "Puskesmas Poncol",
                new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude()),
                drawableCurrentLoc, false);
    }

    private void addLocationMarker(){
        currentLoc = new Location("");
        currentLoc.setLatitude(-6.986941);
        currentLoc.setLongitude(110.414635);
        if (markerLoc == null) {
            markerLoc = new Marker(mMapView, this);
            markerLoc.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc.showBubble(true);
        }

        if (drawableLoc == null)
            drawableLoc = getResources().getDrawable(R.drawable.marker_red);

        markerLoc.add("Real Location", "Puskesmas Pandanaran",
                new GeoPoint(currentLoc.getLatitude(), currentLoc.getLongitude()),
                drawableLoc, false);
    }

    private void addLocationMarker1(){
        currentLoc1 = new Location("");
        currentLoc1.setLatitude(-6.975124);
        currentLoc1.setLongitude(110.406982);
        if (markerLoc1 == null){
            markerLoc1 = new Marker(mMapView,this);
            markerLoc1.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc1.showBubble(true);
        }

        if (drawableLoc1 == null)
            drawableLoc1 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc1.add("Real Location", "Puskesmas Bulu Lor",
                new GeoPoint(currentLoc1.getLatitude(), currentLoc1.getLongitude()),
                drawableLoc1, false);
    }

    private void addLocationMarker2() {
        currentLoc2 = new Location("");
        currentLoc2.setLatitude(-6.983519);
        currentLoc2.setLongitude(110.419010);
        if(markerLoc2 == null){
            markerLoc2 = new Marker(mMapView,this);
            markerLoc2.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc2.showBubble(true);
        }

        if (drawableLoc2 == null)
            drawableLoc2 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc2.add("Real Location","Puskesmas Miroto",
                new GeoPoint(currentLoc2.getLatitude(), currentLoc2.getLongitude()),
                drawableLoc2,false);
    }

    private void addLocationMarker3() {
        currentLoc3 = new Location("");
        currentLoc3.setLatitude(-6.994792);
        currentLoc3.setLongitude(110.437767);
        if(markerLoc3 == null){
            markerLoc3 = new Marker(mMapView,this);
            markerLoc3.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc3.showBubble(true);
        }

        if (drawableLoc3 == null)
            drawableLoc3 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc3.add("Real Location","Puskesmas Halmahera",
                new GeoPoint(currentLoc3.getLatitude(), currentLoc3.getLongitude()),
                drawableLoc3,false);
    }

    private void addLocationMarker4() {
        currentLoc4 = new Location("");
        currentLoc4.setLatitude(-7.000170);
        currentLoc4.setLongitude(110.435669);
        if(markerLoc4 == null){
            markerLoc4 = new Marker(mMapView,this);
            markerLoc4.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc4.showBubble(true);
        }

        if (drawableLoc4 == null)
            drawableLoc4 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc4.add("Real Location","Puskesmas Pembantu Muktiharjo Kidul",
                new GeoPoint(currentLoc4.getLatitude(), currentLoc4.getLongitude()),
                drawableLoc4,false);
    }

    private void addLocationMarker5() {
        currentLoc5 = new Location("");
        currentLoc5.setLatitude(-6.999884);
        currentLoc5.setLongitude(110.448831);
        if(markerLoc5 == null){
            markerLoc5 = new Marker(mMapView,this);
            markerLoc5.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc5.showBubble(true);
        }

        if (drawableLoc5 == null)
            drawableLoc5 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc5.add("Real Location","Puskesmas Gayamsari",
                new GeoPoint(currentLoc5.getLatitude(), currentLoc5.getLongitude()),
                drawableLoc5,false);
    }

    private void addLocationMarker6() {
        currentLoc6 = new Location("");
        currentLoc6.setLatitude(-6.991396);
        currentLoc6.setLongitude(110.425618);
        if(markerLoc6 == null){
            markerLoc6 = new Marker(mMapView,this);
            markerLoc6.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc6.showBubble(true);
        }

        if (drawableLoc6 == null)
            drawableLoc6 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc6.add("Real Location","Puskesmas Gubug1",
                new GeoPoint(currentLoc6.getLatitude(), currentLoc6.getLongitude()),
                drawableLoc6,false);
    }

    private void addLocationMarker7() {
        currentLoc7 = new Location("");
        currentLoc7.setLatitude(-6.987349);
        currentLoc7.setLongitude(110.407786);
        if(markerLoc7 == null){
            markerLoc7 = new Marker(mMapView,this);
            markerLoc7.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc7.showBubble(true);
        }

        if (drawableLoc7 == null)
            drawableLoc7 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc7.add("Real Location","RS Bhakti Wira Tamtama",
                new GeoPoint(currentLoc7.getLatitude(), currentLoc7.getLongitude()),
                drawableLoc7,false);
    }

    private void addLocationMarker8() {
        currentLoc8 = new Location("");
        currentLoc8.setLatitude(-6.985648);
        currentLoc8.setLongitude(110.412866);
        if(markerLoc8 == null){
            markerLoc8 = new Marker(mMapView,this);
            markerLoc8.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc8.showBubble(true);
        }

        if (drawableLoc8 == null)
            drawableLoc8 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc8.add("Real Location","Rumah Sakit Hermina",
                new GeoPoint(currentLoc8.getLatitude(), currentLoc8.getLongitude()),
                drawableLoc8,false);
    }

    private void addLocationMarker9() {
        currentLoc9 = new Location("");
        currentLoc9.setLatitude(-6.993613);
        currentLoc9.setLongitude(110.407265);
        if(markerLoc9 == null){
            markerLoc9 = new Marker(mMapView,this);
            markerLoc9.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc9.showBubble(true);
        }

        if (drawableLoc9 == null)
            drawableLoc9 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc9.add("Real Location","RSUP Kariadi",
                new GeoPoint(currentLoc9.getLatitude(), currentLoc9.getLongitude()),
                drawableLoc9,false);
    }

    private void addLocationMarker10() {
        currentLoc10 = new Location("");
        currentLoc10.setLatitude(-6.996784);
        currentLoc10.setLongitude(110.405100);
        if(markerLoc10 == null){
            markerLoc10 = new Marker(mMapView,this);
            markerLoc10.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc10.showBubble(true);
        }

        if (drawableLoc10 == null)
            drawableLoc10 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc10.add("Real Location","RSU William Booth",
                new GeoPoint(currentLoc10.getLatitude(), currentLoc10.getLongitude()),
                drawableLoc10,false);
    }

    private void addLocationMarker11() {
        currentLoc11 = new Location("");
        currentLoc11.setLatitude(-6.987919);
        currentLoc11.setLongitude(110.426224);
        if (markerLoc11 == null){
            markerLoc11 = new Marker(mMapView,this);
            markerLoc11.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerLoc11.showBubble(true);
        }

        if (drawableLoc11 == null)
            drawableLoc11 = getResources().getDrawable(R.drawable.marker_red);

        markerLoc11.add("Real Location","SMC Telogorejo",
                new GeoPoint(currentLoc11.getLatitude(),currentLoc11.getLongitude()),
                drawableLoc11,false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        locationHelper.requestPermissionCallback(requestCode, permissions, grantResults);
    }


}