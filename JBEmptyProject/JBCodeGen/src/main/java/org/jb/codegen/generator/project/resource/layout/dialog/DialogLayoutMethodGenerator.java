package org.jb.codegen.generator.project.resource.layout.dialog;

import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.Operation;
import org.jb.codegen.generator.project.MethodGenerator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class DialogLayoutMethodGenerator extends MethodGenerator {

	public DialogLayoutMethodGenerator(MethodDescription m, DescriptionDictionary dictionary) {
		super(m, dictionary);
		// TODO Auto-generated constructor stub
	}

	public DialogLayoutMethodGenerator(MethodDescription m,
									   DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(m, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();

		//TODO Ajustes de Layout
		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<LinearLayout\n");
		str.append("	xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		str.append("	xmlns:tools=\"http://schemas.android.com/tools\"\n");

		//TODO Ajustes de Largura
		str.append("	android:layout_width=\"wrap_content\"\n");
		str.append("	android:layout_height=\"wrap_content\"\n");
		
		//TODO Ajustes de Orientação
		str.append("	android:orientation=\"vertical\"\n");
		
		//TODO Ajustes de Margens
		str.append("	android:paddingBottom=\"@dimen/activity_vertical_margin\"\n");
		str.append("	android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n");
		str.append("	android:paddingRight=\"@dimen/activity_horizontal_margin\"\n");
		str.append("	android:paddingTop=\"@dimen/activity_vertical_margin\"\n");

		str.append("	tools:context=\"app.jb.generated." + activityResourceName.getClassName(m.getClassDescription(), Operation.DETAIL, dictionary) + "\" >\n");
		
		//TODO Transformar os Atributos em Fields
		for (ParameterDescription param : m.getParameterDescriptions()) {
			str.append(activityResourceName.getWidgetType(param, Operation.DETAIL, dictionary, 2));
		}
		
		str.append("</LinearLayout>\n");
	}
	
	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", activityResourceName.getClassMethodDialogResourceName(m) + ".xml", element);
					//.createSourceFile(this.c.getName() + "_layout.xml", element);
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
