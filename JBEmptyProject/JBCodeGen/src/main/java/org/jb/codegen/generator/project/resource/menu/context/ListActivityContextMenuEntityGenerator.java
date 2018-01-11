package org.jb.codegen.generator.project.resource.menu.context;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
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

public class ListActivityContextMenuEntityGenerator extends EntityGenerator {

	public ListActivityContextMenuEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListActivityContextMenuEntityGenerator(ClassDescription c,
												  DescriptionDictionary dictionary, Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<menu xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		str.append("	xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n");
		str.append("	xmlns:tools=\"http://schemas.android.com/tools\"\n");
		str.append("	tools:context=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.LIST, dictionary) + "\">\n");
		str.append("		<item\n");
		str.append("			android:id=\"@+id/" + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.DETAIL) + "\"\n");
		str.append("			android:orderInCategory=\"100\"\n");
		str.append("			android:title=\"@string/" + activityResourceName.getClassContextMenuActionStringResourceName(MenuAction.DETAIL) + "\"\n");
		str.append("		/>\n");
		str.append("		<item\n");
		str.append("			android:id=\"@+id/" + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.EDIT) + "\"\n");
		str.append("			android:orderInCategory=\"100\"\n");
		str.append("			android:title=\"@string/" + activityResourceName.getClassContextMenuActionStringResourceName(MenuAction.EDIT) + "\"\n");
		str.append("		/>\n");
		str.append("		<item\n");
		str.append("			android:id=\"@+id/" + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.DELETE) + "\"\n");
		str.append("			android:orderInCategory=\"100\"\n");
		str.append("			android:title=\"@string/" + activityResourceName.getClassContextMenuActionStringResourceName(MenuAction.DELETE) + "\"\n");
		str.append("		/>\n");
		str.append("</menu>\n");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.menu", activityResourceName.getClassContextMenuResourceName(c, Operation.LIST, dictionary) + ".xml", element);
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
