/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;

/**
 * Represents timestamp of an event.
 * @author Tanmay Patil
 */
public class TimeStamp implements Serializable {
    
    /**
     * Current time in milliseconds.
     * 
     * Note that while the unit of time of the return value is a millisecond,
     * the granularity of the value depends on the underlying operating system and may be larger.
     * 
     * For example, many operating systems measure time in units of tens of milliseconds.
     * See the description of the class Date for a discussion of slight discrepancies
     * that may arise between "computer time" and coordinated universal time (UTC). 
     */
    private long timeInMillis;

    /**
     * Initializes the timestamp with given time.
     * @param timeInMillis Time of the timestamp in milliseconds
     */
    public TimeStamp(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    /**
     * Returns current time in milliseconds.
     * 
     * Note that while the unit of time of the return value is a millisecond,
     * the granularity of the value depends on the underlying operating system and may be larger.
     * 
     * For example, many operating systems measure time in units of tens of milliseconds.
     * See the description of the class Date for a discussion of slight discrepancies
     * that may arise between "computer time" and coordinated universal time (UTC). 
     * @return Time of the timestamp in milliseconds
     */
    public long getTimeInMillis() {
        return timeInMillis;
    }

    /**
     * Sets the time of the timestamp in milliseconds.
     * @param timeInMillis Time of the timestamp in milliseconds
     */
    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    @Override
    public String toString() {
        return "\n<TimeStamp>" + timeInMillis + "</TimeStamp>";
    }
}
