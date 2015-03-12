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
import org.jdom2.xpath.jaxen.JDOMXPath;

@SuppressWarnings("deprecation")
public class XpathTutorial_JDOM {

	public static void main(String[] args) throws IOException {

		// Open a XML File
		SAXBuilder sax = new SAXBuilder();
		Document doc = null;
		try {
			doc = sax.build("files/isGroupStaffPlanRequest.xml");
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

		Element getElement = null;
		boolean instanceExists = false;
		boolean done = false;
		for (int x = 0; x < xPathInstance.size(); x++) {
			getElement = null;

			try {

				getElement = (Element) JDOMXPath.selectSingleNode(doc,
						xPathInstance.get(x));

			} catch (JDOMException e1) {
				// TODO Auto-generated catch block
				System.out.println("didn't work");
				e1.printStackTrace();
			}

			while (!instanceExists && !done && getElement != null) {

				switch (x) {

				case 0: {

					if (getElement.getText().contains("401K")) {
						//outPutXml(doc, "QASK", getElement, 0);
						getElement.setText("QASK");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getText().contains("PNP")) {
						//outPutXml(doc, "QASP", getElement, 0);
						getElement.setText("QASP");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getText().contains("INST")) {
						//outPutXml(doc, "QASI", getElement, 0);
						getElement.setText("QASI");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getText().contains("IN02")) {
						//outPutXml(doc, "QAS2", getElement, 0);
						getElement.setText("QAS2");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else
						System.out.println("nothing found");
					break;
				}

				case 1: {

					if (getElement.getAttributeValue("instance").contains(
							"401K")) {
						//outPutXml(doc, "QASK", getElement, 1);
						//System.out.println("QASK");
						getElement.setAttribute("instance", "QASK");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getAttributeValue("instance")
							.contains("PNP")) {
						//outPutXml(doc, "QASP", getElement, 1);
						getElement.setAttribute("instance", "QASP");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getAttributeValue("instance")
							.contains("INST")) {
						//outPutXml(doc, "QASI", getElement, 1);
						getElement.setAttribute("instance", "QASI");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else if (getElement.getAttributeValue("instance")
							.contains("IN02")) {
						//outPutXml(doc, "QAS2", getElement, 1);
						getElement.setAttribute("instance", "QAS2");
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.output(doc, System.out);
						instanceExists = true;
						done = true;
						break;
					} else
						System.out.println("nothing found");
					break;
				}

				}
			}

		}

	}

/*	private static void outPutXml(Document doc, String setInstance,
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

	}*/
}
