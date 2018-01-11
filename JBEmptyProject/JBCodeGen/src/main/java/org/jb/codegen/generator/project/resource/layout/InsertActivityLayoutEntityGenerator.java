package org.jb.codegen.generator.project.resource.layout;

import org.jb.ui.annotation.domain.enums.KindView;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
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

public class InsertActivityLayoutEntityGenerator extends EntityGenerator {

	public InsertActivityLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public InsertActivityLayoutEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
											   Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();

		str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		//TODO Ajustes de Layout
		str.append("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
		
		str.append("	xmlns:tools=\"http://schemas.android.com/tools\"\n");
		
		//TODO Ajustes de Largura
		str.append("	android:layout_width=\"match_parent\"\n");
		str.append("	android:layout_height=\"match_parent\"\n");

		// -------------------------------------------------------------------------------------------------------
		// Mudando a cor do Background
		// -------------------------------------------------------------------------------------------------------
		str.append("	android:background=\"#DCEDC8\"\n");
		// -------------------------------------------------------------------------------------------------------

		str.append("	android:orientation=\"vertical\"\n");

		//TODO ajustes de Contexto
		str.append("	tools:context=\"app.jb.generated." + activityResourceName.getClassName(c, Operation.INSERT, dictionary) + "\" >\n");
		str.append("\n");
		
		str.append("	<ScrollView\n");
		str.append("		android:layout_width=\"fill_parent\"\n");
		str.append("		android:layout_height=\"fill_parent\">\n");
		
		str.append("		<RelativeLayout\n");
		str.append("			android:layout_width=\"match_parent\"\n");
		str.append("			android:layout_height=\"match_parent\"\n");
		str.append("			android:paddingBottom=\"@dimen/activity_vertical_margin\"\n");
		str.append("			android:paddingLeft=\"@dimen/activity_horizontal_margin\"\n");
		str.append("			android:paddingRight=\"@dimen/activity_horizontal_margin\"\n");
		str.append("			android:paddingTop=\"@dimen/activity_vertical_margin\">\n");
		
		str.append("			<TextView\n");
		str.append("				android:id=\"@+id/" + activityResourceName.getClassTitleWidgetResourceName(c, Operation.INSERT, dictionary) + "\"\n");
		str.append("				android:layout_width=\"match_parent\"\n");
		str.append("				android:layout_height=\"wrap_content\"\n");
		str.append("				android:layout_marginTop=\"10dp\"\n");
		str.append("				android:layout_marginBottom=\"20dp\"\n");		
		str.append("				android:layout_alignParentTop=\"true\"\n");
		str.append("				android:text=\"@string/" + activityResourceName.getClassTitleStringResourceName(c, Operation.INSERT, dictionary) + "\"\n");

		// -------------------------------------------------------------------------------------------------------
		// Mudando a cor do texto
		// -------------------------------------------------------------------------------------------------------
		str.append("				android:textColor=\"#212121\"\n");
		// -------------------------------------------------------------------------------------------------------

		str.append("				android:textStyle=\"bold\"\n");
		str.append("				android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
		
		//TODO Transformar os Atributos em Fields
		str.append("			<LinearLayout\n");
		str.append("				android:layout_width=\"match_parent\"\n");
		str.append("				android:layout_height=\"wrap_content\"\n");
		str.append("				android:orientation=\"vertical\"\n");
		str.append("				android:layout_below=\"@id/" + activityResourceName.getClassTitleWidgetResourceName(c, Operation.INSERT, dictionary) + "\">\n");
		for (AttributeDescription attr : c.getAttributesForInterface(KindView.INSERT)) {
			str.append("				" + activityResourceName.getWidgetType(attr, Operation.INSERT, dictionary, 2));
		}

		// -------------------------------------------------------------------------------------------------------
		// Adicionando o Botão de Salvar
		// -------------------------------------------------------------------------------------------------------
		str.append("			<Button\n");
		str.append("			    android:id=\"@+id/btnSalvar\"\n");
		str.append("			    android:layout_width=\"wrap_content\"\n");
		str.append("			    android:layout_height=\"match_parent\"\n");
		str.append("		    	android:layout_gravity=\"center\"\n");
		str.append("	    		android:layout_marginTop=\"25dp\"\n");
		str.append("		    	android:backgroundTint=\"#689F38\"\n");
		str.append("			    android:onClick=\"onClickSalvar\"\n");
		str.append("    			android:text=\"SALVAR CADASTRO\"\n");
		str.append("	    		android:textAppearance=\"@android:style/TextAppearance.Medium\"\n");
		str.append("		    	android:textColor=\"@android:color/white\"\n");
		str.append("			    android:textStyle=\"bold\" />\n");
		str.append("\n");
		// -------------------------------------------------------------------------------------------------------

		str.append("			</LinearLayout>\n");

		str.append("		</RelativeLayout>\n");
		str.append("	</ScrollView>\n");
		
		str.append("</RelativeLayout>\n");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FileObject file = null;
		try {
			file = filer.createResource(StandardLocation.SOURCE_OUTPUT , "res.layout", activityResourceName.getClassLayoutResourceName(c, Operation.INSERT, dictionary) + ".xml", element);
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
