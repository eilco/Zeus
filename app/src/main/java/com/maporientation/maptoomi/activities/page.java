package com.maporientation.maptoomi.activities;

/**
 * Created by MOZZ on 03/02/2016.
 */
public class page {
    String id ;
    String ville;
    String url;
    float longitude;
    float latitude;

    public page(){

    }
    public page(String id, String ville, String url, float latitude, float longitude) {
        this.id = id;
        this.ville = ville;
        this.url = url;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
