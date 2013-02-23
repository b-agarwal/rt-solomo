/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gmapcrawler;

import geoinformation.Address;
import geoinformation.AddressComponent;
import geoinformation.Event;
import geoinformation.Location;
import geoinformation.LocationType;
import geoinformation.Place;
import geoinformation.Review;
import geoinformation.TimeStamp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author eklavya
 */
public class GMapCrawler {

  public static String parseXmlFile(String xml_result, String fileName) {
    //get the factory
    String nextPageToken = "";
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      //Using factory get an instance of document builder
      DocumentBuilder db = dbf.newDocumentBuilder();
      //parse using builder to get DOM representation of the XML file
      Document doc = db.parse(new InputSource(new StringReader(xml_result)));
      //doc.getDocumentElement().normalize();
      
      NodeList results = doc.getElementsByTagName("result");
      System.out.println("    Results Found : " + results.getLength());
      
      Vector<Place> placeList = new Vector<>();
      for(int i=0; i<results.getLength(); i++) {
        Element result = (Element) results.item(i);
        String placeName = result.getElementsByTagName("name").item(0).getTextContent();
        
        Element placeLocation = (Element) result.getElementsByTagName("location").item(0);
        Element placeLatitude = (Element) placeLocation.getElementsByTagName("lat").item(0);
        Element placeLongitude = (Element) placeLocation.getElementsByTagName("lng").item(0);
        Location location = new Location();
        location.setLatiutude(Double.parseDouble(placeLatitude.getTextContent()));
        location.setLongitude(Double.parseDouble(placeLongitude.getTextContent()));
        
        NodeList reviews = result.getElementsByTagName("review");  
        Vector<Review> reviewVector = new Vector<>();
        for (int j = 0; j < reviews.getLength(); j++) {
          Element reviewResult = (Element) reviews.item(i);
          Element author = (Element) reviewResult.getElementsByTagName("author_name").item(0);
          Element content = (Element) reviewResult.getElementsByTagName("text").item(0);
          Element timestamp = (Element) reviewResult.getElementsByTagName("time").item(0);
          Element rating = (Element) reviewResult.getElementsByTagName("rating").item(0);
          
          Review review = new Review(
                  author.getTextContent(),
                  content.getTextContent(),
                  new TimeStamp(Long.parseLong(timestamp.getTextContent())),
                  Double.parseDouble(rating.getTextContent()));
        
          reviewVector.add(review);
        }
        
        Element vicinity = 
                (Element) (result.getElementsByTagName("vicinity").getLength() > 0 ? 
                result.getElementsByTagName("vicinity").item(0) : null);
        Element formattedPhoneNumber = 
                (Element) (result.getElementsByTagName("formatted_phone_number").getLength() > 0 ?
                result.getElementsByTagName("formatted_phone_number").item(0) : null);
        Element formattedAddress = 
                (Element) (result.getElementsByTagName("formatted_address").getLength() > 0 ? 
                result.getElementsByTagName("formatted_address").item(0) : null);
        NodeList addressComponents = result.getElementsByTagName("address_component");
        Vector<AddressComponent> addressComponentList = new Vector<>();
        for (int j = 0; j < addressComponents.getLength(); j++) {
          Element component = (Element) addressComponents.item(j);
          Element longName = (Element) component.getElementsByTagName("long_name").item(0);
          Element shortName = (Element) component.getElementsByTagName("short_name").item(0);
          Element type = (Element) component.getElementsByTagName("type").item(0);
          
          Vector<String> typeList = new Vector<>();
          typeList.add(type.getTextContent());
          AddressComponent addressComponent = new AddressComponent(
                  shortName.getTextContent(), 
                  longName.getTextContent(), 
                  typeList);
          addressComponentList.add(addressComponent);
        }
        
        Address address = new Address(
                vicinity == null ? "" : vicinity.getTextContent().toString(),
                "",
                formattedPhoneNumber == null ? "" : formattedPhoneNumber.getTextContent().toString(),
                formattedAddress == null ? "" : formattedAddress.getTextContent().toString(),
                addressComponentList);
        
        Place place = new Place(
                placeName, 
                location, 
                address, 
                LocationType.Restaurent, 
                reviewVector, 
                new Vector<Event>());
        placeList.add(place);
      }
      
      if(results.getLength() == 0) {
        return "";
      }
      
      java.io.File file;
      file = new java.io.File("Data/" + fileName + ".dat");
      java.io.FileOutputStream fOS = new java.io.FileOutputStream(file, true);
      java.io.ObjectOutputStream oOS = new java.io.ObjectOutputStream(fOS);
      oOS.writeObject(placeList);
      oOS.flush();
      oOS.close();
      fOS.close();
      
      nextPageToken = doc.getElementsByTagName("next_page_token").getLength() > 0 ? 
              doc.getElementsByTagName("next_page_token").item(0).getTextContent() : "";
      
      // geoinformation.Place myPlace = 
    } catch (ParserConfigurationException | SAXException | IOException pce) {
      System.out.println("Exception captured");
      pce.printStackTrace();
    }
    return nextPageToken;
  }

  /**
   * Makes a HTTP request to a URL and receive response
   *
   * @param requestUrl the URL address
   * @param method Indicates the request method, "POST" or "GET"
   * @param params a map of parameters send along with the request
   * @return An array of String containing text lines in response
   * @throws IOException
   */
//  public static String[] sendHttpRequest(String requestUrl, String method,
//          Map<String, String> params) throws IOException {
  public static String sendHttpRequest(String requestUrl, String method, String fileName) throws IOException {

    List<String> response = new ArrayList<>();

    StringBuilder requestParams = new StringBuilder();

//    if (params != null && params.size() > 0) {
//      Iterator<String> paramIterator = params.keySet().iterator();
//      while (paramIterator.hasNext()) {
//        String key = paramIterator.next();
//        String value = params.get(key);
//        requestParams.append(URLEncoder.encode(key, "UTF-8"));
//        requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
//        requestParams.append("&");
//      }
//    }
    requestParams.append(URLEncoder.encode(requestUrl, "UTF-8"));

    URL url = new URL(requestUrl);
    URLConnection urlConn = url.openConnection();
    urlConn.setUseCaches(false);

    // the request will return a response
    urlConn.setDoInput(true);

    if ("POST".equals(method)) {
      // set request method to POST
      urlConn.setDoOutput(true);
    } else {
      // set request method to GET
      urlConn.setDoOutput(false);
    }

//    if ("POST".equals(method) && params != null && params.size() > 0) {
//      OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
//      writer.write(requestParams.toString());
//      writer.flush();
//    }

    // reads response, store line by line in an array of Strings
    BufferedReader reader;
    reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

    String line;
    line = "";
    String xml_results = "";
    while ((line = reader.readLine()) != null) {
  //    System.out.println(line);
//      response.add(line);
      xml_results += line;
    }
    String nextPageToken = parseXmlFile(xml_results, fileName);
    reader.close();

    return nextPageToken;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {
    // TODO code application logic here
    double lat, lng;
    lat = 22.321512;
    lng = 87.311262;
    
    System.out.println("Hello World");
    System.getProperties().put("proxySet", "true");
    System.getProperties().put("proxyHost", "144.16.192.247");
    System.getProperties().put("proxyPort", "8080");
    
    int k = 0;
    for (int i = -20; i < 20; i++) {
      for (int j = -20; j < 20; j++) {
        k++;
        System.out.println("Crawling at ( " + i + " , " + j + " )");
        String fileName = Integer.toString(k);
        getResponse(createRequestUriString(lat+i*0.01, lng+j*0.01), fileName);    
      }
    }
  }  

  private static String createRequestUriString(double lat, double lng) {
    String gmap_url_first;
    gmap_url_first = "https://maps.googleapis.com/maps/api/place/" + 
            "nearbysearch" + 
            "/xml?location=" + lat + "," + lng + 
            "&radius=1000" + 
            "&keyword=food" + 
            "&sensor=false" + 
            "&key=AIzaSyA50f7RTHDoY2NiEmvK2inaKCIOPJisP00";
    return gmap_url_first;
  }

  private static void getResponse(String gmap_url_first, String fileName) throws IOException {
    String gmap_url = gmap_url_first;            
    String nextPageToken;
    do {
      nextPageToken = sendHttpRequest(gmap_url, "POST", fileName);
      gmap_url = gmap_url_first + "&pagetoken=" + nextPageToken;
      
      try {
        if(!nextPageToken.equals("")) {
          Thread.sleep(1500);
        }
      } catch (InterruptedException ex) {
        Logger.getLogger(GMapCrawler.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    while(!nextPageToken.equals(""));
  
    //displayFile(fileName);
  }

  private static void displayFile(String fileName) throws FileNotFoundException, IOException {
    java.io.File file = new java.io.File("Data/" + fileName + ".dat");
    
    java.io.FileInputStream fIS = new java.io.FileInputStream(file);
    java.io.ObjectInputStream oIS = new java.io.ObjectInputStream(fIS);
    Vector<Place> pL;
    try {
      pL = (Vector<Place>) oIS.readObject();
      for (Place place:pL) {
        System.out.println(place);
      }
    } catch (IOException | ClassNotFoundException ex) {
      Logger.getLogger(GMapCrawler.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
