/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;
import java.util.Vector;

/**
 * Represents a place and its details in a map.
 * @author Tanmay Patil
 */
public class Place implements Serializable {
    
    /**
     * Name of the place
     */
    private String name;
    
    /**
     * Location of the place in the map
     */
    private Location location;
    
    /**
     * Address of the place
     */
    private Address address;
    
    /**
     * Type of the location
     */
    private LocationType locationType;
    
    /**
     * Reviews associated with the place
     */
    private Vector<Review> reviews;
    
    /**
     * Events associated with the place
     */
    private Vector<Event> events;

    
    /**
     * Initializes a new Place object with given parameters.
     * @param name Name of the place
     * @param location Location of the place in the map
     * @param address Address of the place
     * @param locationType Type of the location
     * @param reviews Reviews associated with the place
     * @param events Events associated with the place
     */
    public Place(String name, Location location, Address address, LocationType locationType, Vector<Review> reviews, Vector<Event> events) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.locationType = locationType;
        this.reviews = reviews;
        this.events = events;
    }

    /**
     * Returns the name of the place.
     * @return Name of the place
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the place on the map.
     * @return Location of the place on the map
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the address of the place.
     * @return Address of the place
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the type of the place.
     * @return Type of the place
     */
    public LocationType getLocationType() {
        return locationType;
    }

    /**
     * Returns the reviews associated with the place.
     * @return Reviews associated with the place
     */
    public Vector<Review> getReviews() {
        return reviews;
    }

    /**
     * Returns the events associated with the place.
     * @return Events associated with the place
     */
    public Vector<Event> getEvents() {
        return events;
    }

    /**
     * Sets the name of the place.
     * @param name Name of the place
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the location of the place on the map.
     * @param location Location of the place on the map
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the address of the place.
     * @param address Address of the place
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets the type of the place.
     * @param locationType Type of the place
     */
    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    /**
     * Sets the reviews associated with the place.
     * @param reviews Reviews associated with the place
     */
    public void setReviews(Vector<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Sets the events associated with the place.
     * @param events Events associated with the place
     */
    public void setEvents(Vector<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n<Place>");
        stringBuilder.append("\nname = " + name).append(location).append(address).append(locationType);
        for (Review review:reviews)
            stringBuilder.append(review);
        stringBuilder.append("\n</Place>");
        return stringBuilder.toString();
    }
}
