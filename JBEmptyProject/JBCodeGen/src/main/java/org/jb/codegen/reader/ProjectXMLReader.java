package org.jb.codegen.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ProjectXMLReader {
	Filer filer;
	Messager messager;
	javax.lang.model.element.Element element;

	public ProjectXMLReader(Filer filer, Messager messager, javax.lang.model.element.Element element) {
		this.filer = filer;
		this.messager = messager;
		this.element = element;
	}
	
	public boolean isCodeGenerationEnabled() {
		FileObject file = null;
		try {
			file = filer.getResource(StandardLocation.SOURCE_OUTPUT, "assets.config", "project.xml");
			
			File f = new File(file.toUri());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("project");
			Node nNode = nList.item(0);

			NodeList _nList = nNode.getChildNodes();
			for(int i = 0; i < _nList.getLength(); i++) {
				Node _nNode = _nList.item(i);
				if (_nNode.getNodeType() == Node.ELEMENT_NODE) {
					if(_nNode.getNodeName().equals("code_generation_enabled")) {
						String enabledText = _nNode.getTextContent();
						Boolean enabled = Boolean.parseBoolean(enabledText);
						return enabled;
					}
				}
			}
			return false;
			
		} catch(IOException e) {
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			//e.printStackTrace();
		} catch (SAXException e) {
			//e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean hasElement(String element) {
		FileObject file = null;
		try {
			file = filer.getResource(StandardLocation.SOURCE_OUTPUT, "assets.config", "project.xml");
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
}
