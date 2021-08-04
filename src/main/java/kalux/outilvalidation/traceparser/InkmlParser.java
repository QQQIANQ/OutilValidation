package kalux.outilvalidation.traceparser;

import java.io.File;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

// DOM document object model

public class InkmlParser {
	public Trace xmlParser(File inputFile, Trace trace) {
		try {
			// Initialisation analyseur document
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("annotation");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getAttribute("type").equals("expectedLetterDetails")) {
						trace.setExpectedLetterDetails(eElement.getTextContent());
					}
					if (eElement.getAttribute("type").equals("groundTruthLetterDetails")) {

						trace.setGroundTruthLetterDetails(eElement.getTextContent());

					}
					/*
					 * if(eElement.getAttribute("type").equals("expectedValue")){
					 * trace.setExpectedValue(eElement.getTextContent()); }
					 */
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return trace;
	}

	public int compterTolerer(ArrayList<Trace> traces) {
		int result = 0;
		for (int i = 0; i < traces.size(); i++) {
			if (traces.get(i).getExpectedLetterDetails().equals(traces.get(i).getGroundTruthLetterDetails())) {
				result++;
			}
		}
		return result;
	}
	public int compterNonTolerer(ArrayList<Trace> traces) {
		int result = 0;
		for (int i = 0; i < traces.size(); i++) {
			if (!(traces.get(i).getExpectedLetterDetails().equals(traces.get(i).getGroundTruthLetterDetails()))) {
				result++;
			}
		}
		return result;
	}

	public static ArrayList<String> getFiles(String path) {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File(path);
		File[] tempList = file.listFiles();

		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				files.add(tempList[i].toString());
			}
			if (tempList[i].isDirectory()) {
			}
		}
		return files;
	}

}
