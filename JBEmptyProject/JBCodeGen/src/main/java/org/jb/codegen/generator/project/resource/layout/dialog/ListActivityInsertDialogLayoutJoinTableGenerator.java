package org.jb.codegen.generator.project.resource.layout.dialog;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.Operation;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ListActivityInsertDialogLayoutJoinTableGenerator extends EntityGenerator {
	AttributeDescription join;
	ClassDescription target;

	public ListActivityInsertDialogLayoutJoinTableGenerator(ClassDescription c, AttributeDescription join, DescriptionDictionary dictionary) {
		super(c, dictionary);
		this.join = join;
		// TODO Auto-generated constructor stub

		AttributeDescription attr = c.findAttributeDescriptionByName(join.getName());
		if(attr != null) {
			if(dictionary.containsClassWithKey(attr.getCollectionType())) {
				target = dictionary.getClass(attr.getCollectionType());
			}
		}
	}

	public ListActivityInsertDialogLayoutJoinTableGenerator(ClassDescription c, AttributeDescription join,
															DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		this.join = join;
		// TODO Auto-generated constructor stub

		AttributeDescription attr = c.findAttributeDescriptionByName(join.getName());
		if(attr != null) {
			if(dictionary.containsClassWithKey(attr.getCollectionType())) {
				target = dictionary.getClass(attr.getCollectionType());
			}
		}
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

		str.append("	tools:context=\"app.jb.generated." + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + "\" >\n");
		
		//TODO Transformar os Atributos em Fields
		str.append(activityResourceName.getWidgetType(c, target, Operation.DETAIL, dictionary, 2));
		
		str.append("</LinearLayout>\n");
	}
	
	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", activityResourceName.getJoinTableClassInsertDialogLayoutResourceName(c, target, Operation.LIST, dictionary) + ".xml", element);
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
