package org.jb.codegen.generator.project;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class EnumUtilGenerator extends Generator {

	public EnumUtilGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnumUtilGenerator(Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("public class EnumUtil {\n");
		str.append("	public static String[] descriptionValues(Enum []e) {\n");
		str.append("		String []strValues = new String[e.length];\n");
		str.append("		for (int i = 0; i < e.length; i++) {\n");
		str.append("			strValues[i] = e[i].name();\n");
		str.append("		}\n");
		str.append("		return strValues;\n");
		str.append("	}\n");
		str.append("}\n");
	}
	
	@Override
	public void generate() {
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + "EnumUtil", element);
			file.openWriter().append(str.toString()).close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}