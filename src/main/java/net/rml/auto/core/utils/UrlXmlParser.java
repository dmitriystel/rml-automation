//package net.rml.auto.core.utils;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UrlXmlParser {
//    public static List<String> getUrlsFromXml(String filePath) {
//        List<String> urls = new ArrayList<>();
//        try {
//            File file = new File(filePath);
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(file);
//
//            NodeList nodeList = doc.getElementsByTagName("website");
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Element element = (Element) nodeList.item(i);
//                urls.add(element.getElementsByTagName("url").item(0).getTextContent());
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error parsing XML file", e);
//        }
//        return urls;
//    }
//}
