package org.jb.codegen.generator.project.resource.layout;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.auxiliar.Operation;
import org.jb.codegen.generator.project.EntityGenerator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ListFragmentLayoutEntityGenerator extends EntityGenerator {

	public ListFragmentLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
											 Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		//TODO Ajustes de Layout
		str.append("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		
		str.append("	xmlns:tools=\"http://schemas.android.com/tools\"\n");
		
		//TODO Ajustes de Largura
		str.append("	android:layout_width=\"match_parent\"\n");
		str.append("	android:layout_height=\"match_parent\"\n");
		
		//TODO Ajustes de Margens
		//str.append("	android:paddingBottom=\"@dimen/activity_vertical_margin\"\n");
		//str.append("	android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n");
		//str.append("	android:paddingRight=\"@dimen/activity_horizontal_margin\"\n");
		//str.append("	android:paddingTop=\"@dimen/activity_vertical_margin\"\n");
		
		//TODO ajustes de Contexto
		str.append("	tools:context=\"app.jb.generated." + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + "\" >\n");
		str.append("\n");
		
		//TODO Transformar os Atributos em Fields
		str.append("	<ListView\n");
		str.append("		android:id=\"@+id/" + fragmentResourceName.getFragmentListViewResourceName(c, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"match_parent\">\n");
		str.append("	</ListView>\n");
		
		str.append("</RelativeLayout>\n");
	}

	@Override
	public void generate() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", fragmentResourceName.getClassLayoutResourceName(c, Operation.LIST, dictionary) + ".xml", element);
			BufferedWriter bw = new BufferedWriter(file.openWriter());
			bw.append(str.toString());
			bw.close();
			//file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
