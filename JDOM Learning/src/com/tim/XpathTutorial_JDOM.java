package com.tim;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) throws IOException {

		final String xpathQry = "//*[local-name()='fromDBInstance' or local-name()='DBInstance' or local-name()='GroupToken']";

		final String FROM_DB_INSTANCE = "fromDBInstance";
		final String DBINSTANCE = "DBInstance";
		final String ATTR_INSTANCE = "instance";

		final String QASK = "QASK";
		final String QASI = "QASI";
		final String QASP = "QASP";
		final String QAS2 = "QAS2";

		// Open a XML File
		SAXBuilder sax = new SAXBuilder();
		Document doc = null;
		try {
			doc = sax
					.build("files/getCheckIfDisbursementCancelableRequest.xml");
		} catch (JDOMException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// XPath Stuff
		List<String> xPathInstance = Arrays.asList(

		"//*[local-name()='fromDBInstance' or local-name()='DBInstance']",
				"//*[@instance]");

		XPathFactory xpFactory = XPathFactory.instance();

		XPathExpression<Object> expr = xpFactory.compile(xpathQry);
		List<Object> xPathSrchNodes = expr.evaluate(doc);
		System.out.println("Search Nodes size:" + xPathSrchNodes.size());

		for (int i = 0; i < xPathSrchNodes.size(); i++) {
			Content content = (Content) xPathSrchNodes.get(i);
			Element elm = (Element) xPathSrchNodes.get(i);
			System.out.println("Content val: " + content.getValue());
			System.out.println("Element Val: " + elm.getValue());
			elm.setAttribute("instance", "QASP");

			elm.setText("QASK");

			System.out.println("Element Val after changing: " + elm.getValue());
			System.out.println("AttributeName: "
					+ elm.getAttributeValue("instance"));

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.output(doc, System.out);

		}

		/*
		 * 
		 * 
		 * Element getElement = null; boolean instanceExists = false; boolean
		 * done = false; for (int x = 0; x < xPathInstance.size(); x++) {
		 * getElement = null;
		 * 
		 * try {
		 * 
		 * getElement = (Element) JDOMXPath.selectSingleNode(doc,
		 * xPathInstance.get(x));
		 * 
		 * } catch (JDOMException e1) { // TODO Auto-generated catch block
		 * System.out.println("didn't work"); e1.printStackTrace(); }
		 * 
		 * while (!instanceExists && !done && getElement != null) {
		 * 
		 * switch (x) {
		 * 
		 * case 0: {
		 * 
		 * if (getElement.getText().contains("401K")) { outPutXml(doc, "QASK",
		 * getElement, 0); instanceExists = true; done = true; break; } else if
		 * (getElement.getText().contains("PNP")) { outPutXml(doc, "QASP",
		 * getElement, 0); instanceExists = true; done = true; break; } else if
		 * (getElement.getText().contains("INST")) { outPutXml(doc, "QASI",
		 * getElement, 0); instanceExists = true; done = true; break; } else if
		 * (getElement.getText().contains("IN02")) { outPutXml(doc, "QAS2",
		 * getElement, 0); instanceExists = true; done = true; break; } else
		 * System.out.println("nothing found"); break; }
		 * 
		 * case 1: {
		 * 
		 * if (getElement.getAttributeValue("instance").contains( "401K")) {
		 * outPutXml(doc, "QASK", getElement, 1); instanceExists = true; done =
		 * true; break; } else if (getElement.getAttributeValue("instance")
		 * .contains("PNP")) { outPutXml(doc, "QASP", getElement, 1);
		 * instanceExists = true; done = true; break; } else if
		 * (getElement.getAttributeValue("instance") .contains("INST")) {
		 * outPutXml(doc, "QASI", getElement, 1); instanceExists = true; done =
		 * true; break; } else if (getElement.getAttributeValue("instance")
		 * .contains("IN02")) { outPutXml(doc, "QAS2", getElement, 1);
		 * instanceExists = true; done = true; break; } else
		 * System.out.println("nothing found"); break; }
		 * 
		 * } }
		 * 
		 * }
		 */
	}

	private static void outPutXml(Document doc, String setInstance,
			Element getElement, int t) throws IOException {
		// Outputting - Changing the XML
		if (t == 1) {
			System.out.println(setInstance);
			getElement.setAttribute("instance", setInstance);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.output(doc, System.out);
		} else {
			System.out.println(setInstance);
			getElement.setText(setInstance);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.output(doc, System.out);
		}

	}
}
