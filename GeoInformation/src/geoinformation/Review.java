/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geoinformation;

import java.io.Serializable;

/**
 * Represents review associated with a place.
 * @author Tanmay Patil
 */
public class Review implements Serializable {
    
    /**
     * Author of the review.
     */
    private String author;
    
    /**
     * Content of the review.
     */
    private String content;
    
    /**
     * Timestamp value associated with the review.
     */
    private TimeStamp timeStamp;
    
    /**
     * Rating given with the review.
     */
    private byte rating;

    /**
     * Initializes complete Review object with given parameters. 
     * @param author Author of the review
     * @param content Content of the review
     * @param timeStamp Timestamp value associated with the review
     * @param rating Rating given with the review
     */
    public Review(String author, String content, TimeStamp timeStamp, double rating) {
        this.author = author;
        this.content = content;
        this.timeStamp = timeStamp;
        this.rating = (byte) (rating*100);
    }

    /**
     * Returns the author of the review.
     * @return Author of the review
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the content of the review.
     * @return Content of the review
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the timestamp associated with the review.
     * @return Timestamp associated with the review
     */
    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * Returns the rating with the review.
     * @return Rating with the review
     */
    public double getRating() {
        return rating/100;
    }

    /**
     * Sets the author of the review.
     * @param author Author of a review
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the content of the review.
     * @param content Content of the review
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the timestamp associated with the review.
     * @param timeStamp Timestamp associated with the review
     */
    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Sets the rating with the review.
     * @param rating Rating with a review
     */
    public void setRating(double rating) {
        this.rating = (byte) (rating*100);
    }

    @Override
    public String toString() {
        return "\n<Review>\nauthor = " + author + "\ncontent = " + content + timeStamp + "\nrating = " + rating + "\n</Review>";
    }
}
