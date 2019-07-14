package de.dhbwka.exercise.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;

/**
 * @author Leonhard Gahr
 */
public class Grid {
    Gazetteer gazetteer;

    Grid() throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, TransformerException {
        gazetteer = new Gazetteer("https://www.iai.kit.edu/javavl/data/static/karlsruhe.xml");

        float boundsSouth = Float.valueOf(gazetteer.getBoundsSouth());
        float boundsWest = Float.valueOf(gazetteer.getBoundsWest());
        float boundsNorth = Float.valueOf(gazetteer.getBoundsNorth());
        float boundsEast = Float.valueOf(gazetteer.getBoundsEast());

        float[][] points = generatePointsWithBounds(boundsWest, boundsSouth, boundsEast, boundsNorth);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(generateKML(points).getDocumentElement());
        StreamResult result = new StreamResult(new File("kml.kml"));
        transformer.transform(source, result);
    }

    private float[][] generatePointsWithBounds(float boundsWest, float boundsSouth, float boundsEast, float boundsNorth) {
        float[][] result = new float[25][5];
        float offsetWE = boundsEast - boundsWest;
        float offsetSN = boundsNorth - boundsSouth;

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                result[y*5 + x] = new float[]{boundsWest + (offsetWE - (offsetWE/25 * x)), boundsSouth + (offsetSN - (offsetSN/25 * y))};
            }
        }

        return result;
    }

    private Document generateKML(float[][] points) throws ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document doc = documentBuilder.newDocument();
        Element root = doc.createElementNS("http://earth.google.com/kml/2.2", "kml");
        doc.appendChild(root);

        Element document = doc.createElement("Document");
        root.appendChild(document);

        for (int i = 0; i < points.length; i++) {
            float[] point = points[i];
            Element placemark = doc.createElement("Placemark");
            Element name = doc.createElement("name");
            name.setTextContent("Point " + i);
            placemark.appendChild(name);

            Element description = doc.createElement("description");
            description.setTextContent("Point " + i);
            placemark.appendChild(description);

            Element pointElement = doc.createElement("Point");
            placemark.appendChild(pointElement);

            Element coordinates = doc.createElement("coordinates");
            coordinates.setTextContent(String.format("%f,%f", point[0], point[1]));

            pointElement.appendChild(coordinates);

            document.appendChild(placemark);
        }

        return doc;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, TransformerException {
        new Grid();
    }


    private static void printNode(Node rootNode, String spacer) {
        System.out.println(spacer + rootNode.getNodeName() + " -> " + rootNode.getNodeValue());
        NodeList nl = rootNode.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++)
            printNode(nl.item(i), spacer + "   ");
    }


}
