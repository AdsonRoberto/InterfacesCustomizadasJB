package org.jb.codegen.generator.project.resource.values;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.project.ProjectGenerator;
import org.jb.codegen.reader.XMLReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

//import javax.tools.Diagnostic.Kind;

public class ResourceDimensProjectGenerator extends ProjectGenerator {

	public ResourceDimensProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ResourceDimensProjectGenerator(DescriptionDictionary dictionary, Messager messager,
			Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		List<String[]> list = new XMLReader(filer).getValues("dimens.xml", "dimen");
		
		List<String> names = new ArrayList<String>();
		names.add("activity_horizontal_margin");
		names.add("activity_vertical_margin");
			
		List<String> values = new ArrayList<String>();
		values.add("16dp");
		values.add("16dp");
		
		if(list == null || list.isEmpty()) {
			//messager.printMessage(Kind.WARNING, "Dimens - Lista Vazia", element);
		}
		else {
			//messager.printMessage(Kind.WARNING, "Dimens - Lista: " + list.size(), element);
			for (int i = 0; i < list.size(); i++) {
				String[] s = list.get(i);
				if(!names.contains(s[0])) {
					names.add(s[0]);
					values.add(s[1]);
				}
			}
			
			//messager.printMessage(Kind.WARNING, "Names: " + names.size() + "- Values: " + values.size(), element);
		}

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<resources>\n");
		for (int i = 0; i < names.size(); i++) {
			str.append("	<dimen name=\"" + names.get(i) + "\">" + values.get(i) + "</dimen>\n");
		}
		str.append("</resources>");

	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.values", "dimens.xml", element);
			BufferedWriter bw = new BufferedWriter(file.openWriter());
			bw.append(str.toString());
			bw.close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
