/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;

/**
 * Represents the location of a place in the map.
 * @author Tanmay Patil
 */
public class Location implements Serializable {
    
    /**
     * Latitude value associated with the location.
     */
    private double latiutude;
    
    /**
     * Longitude value associated with the location.
     */
    private double longitude;

    /**
     * Initializes location object with (0, 0) latitude and longitude.
     */
    public Location() {
        latiutude = 0;
        longitude = 0;
    }

    /**
     * Initializes location object with given latitude and longitude.
     * 
     * @param latiutude Latitude of the location.
     * @param longitude Longitude of the location.
     */
    public Location(double latiutude, double longitude) {
        this.latiutude = latiutude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude value associated with the location.
     * @return Latitude associated with the location.
     */
    public double getLatiutude() {
        return latiutude;
    }

    /**
     * Returns the longitude value associated with the location.
     * @return Longitude associated with the location.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the latitude of the location.
     * @param latiutude New value of latitude.
     */
    public void setLatiutude(double latiutude) {
        this.latiutude = latiutude;
    }

    /**
     * Sets the longitude of the location.
     * @param longitude New value of longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "\n<Location>\nlatitude = " + latiutude + "\nlongitude = " + longitude + "\n</Location>";
    }
    
}
