/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;
import java.util.Vector;

/**
 * Represents the address of the place
 * @author Tanmay Patil
 */
public class Address implements Serializable {
    
    /**
     * Vicinity of the place
     */
    private String vicinity;
    
    /**
     * Type of the place
     */
    private String type;
    
    /**
     * Formatted phone number associated with the place
     */
    private String formattedPhoneNumber;
    
    /**
     * Formatted address associated with the place
     */
    private String formattedAddress;
    
    /**
     * Collection of various components of an address
     */
    private Vector<AddressComponent> addressComponents;

    /**
     * Initializes an address object with given parameters.
     * @param vicinity Vicinity of the place
     * @param type Type of the place
     * @param formattedPhoneNumber Formatted phone number associated with the place
     * @param formattedAddress Formatted address associated with the place
     * @param addressComponents Collection of various components of an address
     */
    public Address(String vicinity, String type, String formattedPhoneNumber, String formattedAddress, Vector<AddressComponent> addressComponents) {
        this.vicinity = vicinity;
        this.type = type;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.formattedAddress = formattedAddress;
        this.addressComponents = addressComponents;
    }

    /**
     * Returns the vicinity of the place.
     * @return Vicinity of the place
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * Returns the type of the place.
     * @return Type of the place
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the formatted phone number associated with the place.
     * @return Formatted phone number associated with the place
     */
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    /**
     * Returns the formatted address associated with the place.
     * @return Formatted address associated with the place
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * Returns the collection of the components of the address.
     * @return Collection of the components of the address
     */
    public Vector<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    /**
     * Sets the vicinity of the place.
     * @param vicinity Vicinity of the place
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    /**
     * Sets the type of the place.
     * @param type Type of the place
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the formatted phone number associated with the address.
     * @param formattedPhoneNumber Formatted phone number associated with the address
     */
    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    /**
     * Sets the formatted address associated with the place.
     * @param formattedAddress Formatted address associated with the place
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    /**
     * Sets the collection of the components of the address.
     * @param addressComponents Collection of the components of the address
     */
    public void setAddressComponents(Vector<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n<Address>");
        stringBuilder.append("\nvicinity = ").append(vicinity);
        stringBuilder.append("\ntype = ").append(type);
        stringBuilder.append("\nformattedPhoneNumber = ").append(formattedPhoneNumber);
        stringBuilder.append("\nformattedAddress = ").append(formattedAddress);
        for (AddressComponent addressComponent:addressComponents)
            stringBuilder.append(addressComponent);
        stringBuilder.append("\nAddress");
        return stringBuilder.toString();
    }
}
