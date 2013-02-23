/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;
import java.util.Vector;

/**
 * Represents a component of the address.
 * @author Tanmay Patil
 */
public class AddressComponent implements Serializable {
    
    /**
     * Short name of the specified components of the address
     */
    private String shortName;
    
    /**
     * Long name of the specified components of the address
     */
    private String longName;
    
    /**
     * Types of the component of the address
     */
    private Vector<String> types;

    /**
     * Initializes the component of the address with specified values.
     * @param shortName Short name of the specified components of the address
     * @param longName Long  name of the specified components of the address
     * @param types Types of the component of the address
     */
    public AddressComponent(String shortName, String longName, Vector<String> types) {
        this.shortName = shortName;
        this.longName = longName;
        this.types = types;
    }

    /**
     * Returns the short name of the component of an address.
     * @return Short name of the component of an address
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns the long name of the component of an address.
     * @return Long name of the component of an address
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Returns the types associated with the component of the address.
     * @return Types associated with the component of the address
     */
    public Vector<String> getTypes() {
        return types;
    }

    /**
     * Sets the short name of the component of the address.
     * @param shortName Short name of the component of the address
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Sets the long name of the component of the address.
     * @param longName Long name of the component of the address
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * Sets the types associated with the component of the address.
     * @param types Types associated with the component of the address
     */
    public void setTypes(Vector<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n<AddressComponent>");
        stringBuilder.append("\nshortName = ").append(shortName);
        stringBuilder.append("\nlongName = ").append(longName);
        stringBuilder.append("\naddressTypes = ");
        for (String type:types)
            stringBuilder.append(type).append(" ");
        stringBuilder.append("\n</AddressComponent>");
        return stringBuilder.toString();
    }
}
