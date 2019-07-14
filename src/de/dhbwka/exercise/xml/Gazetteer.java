package de.dhbwka.exercise.xml;


import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

/**
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class Gazetteer {
    String formattedAddress;
    String longName;
    String geometryLat;
    String geometryLng;
    String boundsSouth;
    String boundsWest;
    String boundsNorth;
    String boundsEast;

    Gazetteer(String url) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(url);

        // define xPaths
        final String formatted_address = "/GeocodeResponse/result/formatted_address";
        final String long_name = "/GeocodeResponse/result/address_component/long_name";
        final String geometry_lat = "/GeocodeResponse/result/geometry/location/lat";
        final String geometry_lng = "/GeocodeResponse/result/geometry/location/lng";
        final String bounds_south = "/GeocodeResponse/result/geometry/bounds/southwest/lat";
        final String bounds_west = "/GeocodeResponse/result/geometry/bounds/southwest/lng";
        final String bounds_north = "/GeocodeResponse/result/geometry/bounds/northeast/lat";
        final String bounds_east = "/GeocodeResponse/result/geometry/bounds/northeast/lng";

        // execute xPaths
        formattedAddress = executeXPath(doc, formatted_address).getTextContent();
        longName = executeXPath(doc, long_name).getTextContent();
        geometryLat = executeXPath(doc, geometry_lat).getTextContent();
        geometryLng = executeXPath(doc, geometry_lng).getTextContent();
        boundsSouth = executeXPath(doc, bounds_south).getTextContent();
        boundsWest = executeXPath(doc, bounds_west).getTextContent();
        boundsNorth = executeXPath(doc, bounds_north).getTextContent();
        boundsEast = executeXPath(doc, bounds_east).getTextContent();
    }

    @Override
    public String toString() {
        return String.format("Address: %s\nLong Name: %s\nLocation: (latitude=%s, longitude=%s)\nBounds: (east=%s, north=%s, west=%s, south=%s)",
                formattedAddress, longName, geometryLat, geometryLng, boundsEast, boundsNorth, boundsWest, boundsSouth);
    }

    /**
     * Execute a xPath expression on an document
     *
     * @param doc   the document to execute the xpath on
     * @param xPath the xpath to execute
     * @return the Node that was selected
     * @throws XPathExpressionException on invalid xpath
     */
    private Node executeXPath(Document doc, String xPath) throws XPathExpressionException {
        XPath xPathObject = XPathFactory.newInstance().newXPath();
        return (Node) xPathObject.compile(xPath).evaluate(doc, XPathConstants.NODE);
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        System.out.println(new Gazetteer("https://www.iai.kit.edu/javavl/data/static/karlsruhe.xml"));
    }
}

interface L {
    static void main(String[] a) {
        System.out.print(new java.util.Scanner(System.in).nextInt(35) % 2 > 0 ? "dark" : "light");
    }
}