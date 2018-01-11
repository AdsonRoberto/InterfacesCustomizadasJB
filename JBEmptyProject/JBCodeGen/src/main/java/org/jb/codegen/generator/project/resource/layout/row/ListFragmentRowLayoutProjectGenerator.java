package org.jb.codegen.generator.project.resource.layout.row;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.project.ProjectGenerator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public class ListFragmentRowLayoutProjectGenerator extends ProjectGenerator {

	public ListFragmentRowLayoutProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentRowLayoutProjectGenerator(DescriptionDictionary dictionary,
			Messager messager, Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		str.append("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		str.append("	android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutProjectResourceName(dictionary) + "\"\n");
		str.append("	android:layout_width=\"match_parent\"\n");
		str.append("	android:layout_height=\"wrap_content\"\n");
		str.append("	android:orientation=\"horizontal\" >\n");
		str.append("\n");
		str.append("	<ImageView\n");
		str.append("		android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutProjectWidgetIconResourceName(dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"wrap_content\"\n");
		str.append("		android:layout_gravity=\"center_vertical\"\n");
		str.append("		android:layout_weight=\"6\"\n");
		str.append("		android:scaleType=\"centerInside\"\n");
		str.append("		android:adjustViewBounds=\"true\"\n");
		str.append("		android:src=\"@drawable/ic_launcher\"\n");
		str.append("		android:padding=\"5dp\"\n");
		str.append("	/>\n");
		str.append("\n");
		str.append("	<TextView\n");
		str.append("		android:id=\"@+id/" + fragmentResourceName.getFragmentListRowLayoutProjectWidgetTitleResourceName(dictionary) + "\"\n");
		str.append("		android:layout_width=\"match_parent\"\n");
		str.append("		android:layout_height=\"wrap_content\"\n");
		str.append("		android:layout_gravity=\"center_vertical\"\n");
		str.append("		android:layout_weight=\"1\"\n");
		str.append("		android:padding=\"5dp\"\n");
		str.append("		android:text=\"JBLarge Text\"\n");
		str.append("		android:textAppearance=\"?android:attr/textAppearanceLarge\"\n");
		str.append("	/>\n");
		str.append("\n");
		str.append("</LinearLayout>\n");
	}

	@Override
	public void generate() {
		FileObject file = null;
		try {
			FragmentResourceName fragmentResourceName = new FragmentResourceName();
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", fragmentResourceName.getFragmentListRowLayoutProjectResourceName(dictionary) + ".xml", element);
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
