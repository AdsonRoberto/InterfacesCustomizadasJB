package org.jb.codegen.reader;

import java.io.File;
import java.io.IOException;

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

public class AndroidManifestXMLReader {
	private static AndroidManifestXMLReader instance = null;
	private Filer filer;
	private FileObject fileObject;
	
	private AndroidManifestXMLReader(Filer filer) {
		this.filer = filer;
		this.fileObject = null;
	}

	public static AndroidManifestXMLReader getInstance(Filer filer) {
		if(instance == null) {
			instance = new AndroidManifestXMLReader(filer);
			try {
				instance.setFileObject(filer.getResource(StandardLocation.SOURCE_OUTPUT, "", "AndroidManifest.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public String getPackage() {
		try {
			File file = new File(fileObject.toUri());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("manifest");
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String pack = eElement.getAttribute("package");
				return pack;
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean hasElement(String element) {
		try {
			File file = new File(fileObject.toUri());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
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

	public FileObject getFileObject() {
		return this.fileObject;
	}

	public void setFileObject(FileObject fileObject) {
		this.fileObject = fileObject;
	}
}
