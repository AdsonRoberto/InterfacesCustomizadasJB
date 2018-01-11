package org.jb.codegen.generator.project.config;

import org.jb.codegen.generator.project.Generator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ProjectConfigGenerator extends Generator {
	boolean codeGenerationEnabled;

	public ProjectConfigGenerator(Messager messager, Filer filer,
								  Element element) {
		super(messager, filer, element);
		codeGenerationEnabled = true;
	}

	@Override
	public void source() {
		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<project>\n");
		str.append("	<code_generation_enabled>" + String.valueOf(codeGenerationEnabled) + "</code_generation_enabled>\n");
		str.append("</project>\n");
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			FileObject fileTest = filer.getResource(StandardLocation.SOURCE_OUTPUT , "assets.config", "project.xml");
			if(fileTest == null) {
				file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "assets.config", "project.xml", element);

				BufferedWriter bw = new BufferedWriter(file.openWriter());
				bw.append(str.toString());
				bw.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isCodeGenerationEnabled() {
		return codeGenerationEnabled;
	}

	public void setCodeGenerationEnabled(boolean codeGenerationEnabled) {
		this.codeGenerationEnabled = codeGenerationEnabled;
	}
}
