package org.jb.codegen.generator.project.resource.layout.row;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.project.EntityGenerator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ListFragmentRowLayoutEntityGenerator extends EntityGenerator {

	public ListFragmentRowLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentRowLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
												Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		str.append("	android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutEntityLinearLayoutResourceName(c, dictionary) + "\"\n");
		str.append("	android:layout_width=\"match_parent\"\n");
		str.append("	android:layout_height=\"match_parent\"\n");
		str.append("	android:orientation=\"vertical\" >\n");
		str.append("\n");
		str.append("	<TextView\n");
		str.append("		android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutEntityWidgetResourceName(c, dictionary, 1) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"match_parent\"\n");
		str.append("		android:layout_weight=\"1\"\n");
		str.append("		android:textStyle=\"bold\"\n");
		str.append("	/>\n");
		str.append("\n");
		str.append("	<TextView\n");
		str.append("		android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutEntityWidgetResourceName(c, dictionary, 2) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"match_parent\"\n");
		str.append("		android:layout_weight=\"1\"\n");
		str.append("		android:textStyle=\"bold\"\n");
		str.append("	/>\n");
		str.append("\n");
		str.append("</LinearLayout>\n");	
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			FragmentResourceName fragmentResourceName = new FragmentResourceName();
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", fragmentResourceName.getFragmentListRowLayoutEntityResourceName(c, dictionary) + ".xml", element);
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
