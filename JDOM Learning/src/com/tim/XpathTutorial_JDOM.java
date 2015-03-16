package com.tim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.jdom2.xpath.jaxen.JDOMXPath;

@SuppressWarnings("deprecation")
public class XpathTutorial_JDOM {
	private static String xpathQry = "";
	private static String fromDBInstance = "";

	private static String dbInstance = "";
	private static String attrInstance = "";

	private static String db401K = "";
	private static String dbINST = "";
	private static String dbPNP = "";
	private static String dbIN02 = "";
	private static Properties props = null;
	private static String PROPERTIES_FILE = "files/config.properties";
	private static String xmlFile = "";

	public static void main(String[] args) throws IOException {

		getProperties();

		// Open a XML File
		SAXBuilder sax = new SAXBuilder();
		Document doc = null;
		try {
			doc = sax.build(xmlFile);
		} catch (JDOMException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// XPath Stuff

		XPathFactory xpFactory = XPathFactory.instance();

		XPathExpression<Object> expr = xpFactory.compile(xpathQry);
		List<Object> xPathSrchNodes = expr.evaluate(doc);
		System.out.println("Search Nodes size:" + xPathSrchNodes.size());

		for (int i = 0; i < xPathSrchNodes.size(); i++) {
			Content content = (Content) xPathSrchNodes.get(i);
			Element elm = (Element) xPathSrchNodes.get(i);
			System.out.println("Content val: " + content.getValue());
			System.out.println("Element Val: " + elm.getValue());

			System.out.println("Element Val after changing: " + elm.getValue());

			System.out.println("AttributeName: " + attrInstance.toLowerCase()
					+ " " + elm.getAttributeValue(attrInstance));
			String attr = elm.getAttributeValue(attrInstance);
			if (attr == null) {
				System.out.println("You aren't using an attribute ");
				if (elm.getText().contains("401K")) {
					elm.setText(db401K);
				} else if (elm.getText().contains("PNP")) {
					elm.setText(dbPNP);
				} else if (elm.getText().contains("INST")) {
					elm.setText(dbINST);
				} else if (elm.getText().contains("IN02")) {
					elm.setText(dbIN02);
				} else {
					System.out.println("nothing found");
				}
			} else {
				System.out.println("You ARE using an attribute ");
				if (elm.getAttributeValue(attrInstance).contains("401K")) {
					elm.setAttribute(attrInstance, db401K);
				} else if (elm.getAttributeValue(attrInstance).contains("PNP")) {
					elm.setAttribute(attrInstance, dbPNP);
				} else if (elm.getAttributeValue(attrInstance).contains("INST")) {
					elm.setAttribute(attrInstance, dbINST);
				} else if (elm.getAttributeValue(attrInstance).contains("IN02")) {
					elm.setAttribute(attrInstance, dbIN02);
				} else
					System.out.println("nothing found");
			}

			// System.out.println(elm.getContentSize());

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.output(doc, System.out);

		}

	}

	private static void getProperties() throws FileNotFoundException,
			IOException {
		FileInputStream is = new FileInputStream(PROPERTIES_FILE);
		props = new Properties();
		props.load(is);

		// System.out.println("There are: " + props.size() + " properties");

		xmlFile = props.getProperty("xmlFile");
		xpathQry = props.getProperty("xpathQry");
		fromDBInstance = props.getProperty("fromDBInstance");
		dbInstance = props.getProperty("dbInstance");
		attrInstance = props.getProperty("attrInstance");
		db401K = props.getProperty("db401K");
		dbINST = props.getProperty("dbINST");
		dbPNP = props.getProperty("dbPNP");
		dbIN02 = props.getProperty("dbIN02");

	}

}
