package org.jb.codegen.generator.project.resource.values;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.generator.project.ProjectGenerator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ResourceStylesProjectGenerator extends ProjectGenerator {

	public ResourceStylesProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ResourceStylesProjectGenerator(DescriptionDictionary dictionary, Messager messager,
			Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<resources>\n");
		str.append("\n");
		str.append("	<style name=\"AppBaseTheme\" parent=\"Theme.AppCompat.Light.DarkActionBar\">\n");
		str.append("	\n");
		str.append("	</style>\n");
		str.append("\n");
		str.append("	<style name=\"AppTheme.AppBarOverlay\" parent=\"ThemeOverlay.AppCompat.Dark.ActionBar\">\n");
		str.append("	\n");
		str.append("	</style>\n");
		str.append("\n");
		str.append("	<style name=\"AppTheme.PopupOverlay\" parent=\"ThemeOverlay.AppCompat.Light\">\n");
		str.append("	\n");
		str.append("	</style>\n");
		str.append("\n");
		str.append("	<!-- Application theme. -->\n");
		str.append("	<style name=\"AppTheme\" parent=\"AppBaseTheme\">\n");
		str.append("	\n");
		str.append("	</style>\n");
		str.append("\n");
		str.append("</resources>\n");
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.values", "styles.xml", element);
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
