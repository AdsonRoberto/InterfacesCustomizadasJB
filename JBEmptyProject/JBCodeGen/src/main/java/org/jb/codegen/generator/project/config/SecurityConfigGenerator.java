package org.jb.codegen.generator.project.config;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import org.jb.codegen.generator.project.Generator;

public class SecurityConfigGenerator extends Generator {

	public SecurityConfigGenerator(Messager messager, Filer filer,
				Element element) {
		super(messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<security>\n");
		str.append("	<username>root</username>\n");
		str.append("	<password>root</password>\n");
		str.append("</security>\n");
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			FileObject fileTest = filer.getResource(StandardLocation.SOURCE_OUTPUT, "assets.config", "security.xml");
			if(fileTest == null) {
				file = filer.createResource(StandardLocation.SOURCE_OUTPUT, "assets.config", "security.xml", element);
				
				BufferedWriter bw = new BufferedWriter(file.openWriter());
				bw.append(str.toString());
				bw.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
