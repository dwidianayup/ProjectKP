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

public class DetailMapsActivity extends AppCompatActivity implements RMAccountManager.RMAccountManagerListener {

    private MapView mMapView;
    private Map mMap;

    Double latitude;
    Double longitude;

    PermissionHelper permissionHelper;
    private Marker markerRealLoc;
    private Drawable drawableCurrentLoc;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_detail);

        setLatLong();

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

        mMapView.setMapPosition(new GeoPoint(getLatitude(), getLongitude()), 15);
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
        getLatitude();
        getLongitude();
        if (markerRealLoc == null) {
            markerRealLoc = new Marker(mMapView, this);
            markerRealLoc.setMarkerSpot(MarkerSymbol.HotspotPlace.CENTER);
            markerRealLoc.showBubble(true);
        }

        if (drawableCurrentLoc == null)
            drawableCurrentLoc = getResources().getDrawable(R.drawable.marker_red);

        markerRealLoc.add("Real Location", "Puskesmas Poncol",
                new GeoPoint(getLatitude(), getLongitude()),
                drawableCurrentLoc, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        locationHelper.requestPermissionCallback(requestCode, permissions, grantResults);
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatLong() {
        this.longitude = getIntent().getDoubleExtra("longitude",0);;
        this.latitude = getIntent().getDoubleExtra("latitude",0);;
    }


}