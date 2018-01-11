package org.jb.codegen.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	Filer filer;
	
	public XMLReader(Filer filer) {
		this.filer = filer;
	}
	
	public boolean hasElement(String filePath, String element) {
		FileObject file = null;
		try {
			file = filer.getResource(StandardLocation.SOURCE_OUTPUT, "res.values", filePath);
			File f = new File(file.toUri());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(element);
			if(nList.getLength() > 0) {
				return true;
			}
			return false;
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String[]> getValues(String filePath, String key) {
		List<String[]> list = new ArrayList<String[]>();
		
		FileObject file = null;
		try {
			file = filer.getResource(StandardLocation.SOURCE_OUTPUT, "res.values", filePath);
			File f = new File(file.toUri());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(key);
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String name = eElement.getAttribute("name");
					String value = eElement.getTextContent();
					list.add(new String[]{name, value});
				}
			}
			return list;
		} catch(IOException e) {
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
