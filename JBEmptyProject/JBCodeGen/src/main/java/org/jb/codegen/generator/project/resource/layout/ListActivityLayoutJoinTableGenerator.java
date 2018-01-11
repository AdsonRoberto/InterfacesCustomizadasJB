package org.jb.codegen.generator.project.resource.layout;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.FragmentResourceName;
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

public class ListActivityLayoutJoinTableGenerator extends EntityGenerator {
	AttributeDescription join;
	ClassDescription target;
	
	public ListActivityLayoutJoinTableGenerator(ClassDescription c, AttributeDescription join, DescriptionDictionary dictionary) {
		super(c, dictionary);
		this.join = join;

		AttributeDescription attr = c.findAttributeDescriptionByName(join.getName());
		if(attr != null) {
			if(dictionary.containsClassWithKey(attr.getCollectionType())) {
				target = dictionary.getClass(attr.getCollectionType());
			}
		}
	}

	public ListActivityLayoutJoinTableGenerator(ClassDescription c, AttributeDescription join,
												DescriptionDictionary dictionary, Messager messager,
												Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		this.join = join;

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
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		//TODO Ajustes de Layout
		str.append("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		
		str.append("	xmlns:tools=\"http://schemas.android.com/tools\"\n");
		
		//TODO Ajustes de Largura
		str.append("	android:layout_width=\"match_parent\"\n");
		str.append("	android:layout_height=\"match_parent\"\n");
		
		//TODO Ajustes de Margens
		str.append("	android:paddingBottom=\"@dimen/activity_vertical_margin\"\n");
		str.append("	android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n");
		str.append("	android:paddingRight=\"@dimen/activity_horizontal_margin\"\n");
		str.append("	android:paddingTop=\"@dimen/activity_vertical_margin\"\n");
		
		//TODO ajustes de Contexto
		str.append("	tools:context=\"app.jb.generated." + activityResourceName.getClassName(c, join, Operation.LIST, dictionary) + "\" >\n");
		str.append("\n");
		
		str.append("	<TextView\n");
		str.append("		android:id=\"@+id/" + activityResourceName.getClassTitleWidgetResourceName(c, join, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"wrap_content\"\n");
		str.append("		android:layout_marginTop=\"10dp\"\n");
		str.append("		android:layout_marginBottom=\"20dp\"\n");		
		str.append("		android:layout_alignParentTop=\"true\"\n");
		str.append("		android:text=\"@string/" + activityResourceName.getClassTitleStringResourceName(c, join, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:textStyle=\"bold\"\n");
		str.append("		android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
		
		str.append("	<View\n");
		str.append("		android:id=\"@+id/" + activityResourceName.getFragmentWidgetLineResourceName(c, join, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"1dp\"\n");
		str.append("		android:layout_marginTop=\"2dp\"\n");
		str.append("		android:layout_marginBottom=\"2dp\"\n");
		str.append("		android:background=\"@android:color/darker_gray\"\n");
		str.append("		android:layout_below=\"@id/" + activityResourceName.getClassTitleWidgetResourceName(c, join, Operation.LIST, dictionary) + "\" />\n");
		
		//TODO Transformar os Atributos em Fields
		str.append("	<fragment\n");
		str.append("		android:id=\"@+id/" + activityResourceName.getFragmentWidgetListResourceName(c, join, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:name=\"app.jb.generated." +  fragmentResourceName.getClassName(target, Operation.LIST, dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"match_parent\"\n");
		str.append("		android:orientation=\"vertical\"\n");
		str.append("		android:layout_below=\"@id/" + activityResourceName.getFragmentWidgetLineResourceName(c, join, Operation.LIST, dictionary) + "\">\n");
		str.append("	</fragment>\n");
		
		str.append("</RelativeLayout>\n");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", activityResourceName.getClassLayoutResourceName(c, join, Operation.LIST, dictionary) + ".xml", element);
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
