package com.tim;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.jaxen.JDOMXPath;

@SuppressWarnings("deprecation")
public class XpathTutorial_JDOM {

public static void main(String[] args) throws IOException {

// Open a XML File
SAXBuilder sax = new SAXBuilder();
Document doc = null;
try {
doc = sax.build("C://isGroupStaffPlanRequest.xml");
} catch (JDOMException e2) {
// TODO Auto-generated catch block
e2.printStackTrace();
} catch (IOException e2) {
// TODO Auto-generated catch block
e2.printStackTrace();
}
// System.out.println(doc);

// XPath Stuff
List<String> xPathInstance = Arrays.asList("//*[@instance]",
"//*[DBInstance]", "//*[fromDBInstance]");
// System.out.println(xPathInstance);
JDOMXPath xPathExpression = null;
Element getElement = null;
boolean instanceExists = false;
for (int x = 0; x < xPathInstance.size(); x++) {


try {
xPathExpression = new JDOMXPath(xPathInstance.get(x));
//System.out.println(xPathExpression);
} catch (JDOMException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
try {
getElement = (Element) xPathExpression.selectSingleNode(doc);
} catch (JDOMException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
//System.out.println(getElement.toString());
//String getTheElementText = getElement.toString();
//System.out.println(getElement.getText());
//System.out.println(getElement.getAttributeValue("instance"));
while (instanceExists == false) {
System.out.println(getElement.getAttributeValue("instance"));

if (getElement.getAttributeValue("instance").contains("401K")) {
outPutXml(doc, "QASK", getElement);
instanceExists = true;
} else if (getElement.getAttributeValue("instance").contains(
"PNP")) {
outPutXml(doc, "QASP", getElement);
instanceExists = true;
} else if (getElement.getAttributeValue("instance").contains(
"INST")) {
outPutXml(doc, "QASI", getElement);
instanceExists = true;
} else if (getElement.getAttributeValue("instance").contains(
"IN02")) {
outPutXml(doc, "QAS2", getElement);
instanceExists = true;
} else
System.out.println("nothing found");

}

}

}

private static void outPutXml(Document doc, String setInstance,
Element getElement) throws IOException {
// Outputting - Changing the XML

System.out.println(setInstance);
getElement.setAttribute("instance", setInstance);
XMLOutputter xmlOutput = new XMLOutputter();
xmlOutput.output(doc, System.out);

}
}