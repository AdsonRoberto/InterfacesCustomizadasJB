package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.project.ProjectGenerator;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListActivityProjectGenerator extends ProjectGenerator {

	public ListActivityProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListActivityProjectGenerator(DescriptionDictionary dictionary, Messager messager,
			Filer filer, Element element) {
		super(dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import android.support.v7.app.AppCompatActivity;\n");
		str.append("import android.app.Activity;\n");
		str.append("import android.content.Intent;\n");
		str.append("import android.app.FragmentManager;\n");
		str.append("import android.app.ListFragment;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("import app.jb.generated." + fragmentResourceName.getClassName(null, Operation.LIST, dictionary) + ";\n");
		str.append("\n");
		
		//str.append("public class " + c.getName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(null, Operation.LIST, dictionary) + " extends AppCompatActivity implements " + fragmentResourceName.getClassDelegateName(null, Operation.LIST, dictionary) + " {\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("\n");
		str.append("    	FragmentManager fm = getFragmentManager();\n");
		str.append("    	if (fm.findFragmentById(android.R.id.content) == null) { \n");
		str.append("    		" + fragmentResourceName.getClassName(null, Operation.LIST, dictionary) + " list = new " + fragmentResourceName.getClassName(null, Operation.LIST, dictionary) + "(); \n");
		str.append("    		list.onAttach(this); \n");
		str.append("    		fm.beginTransaction().add(android.R.id.content, list).commit(); \n");
		str.append("    	}\n");
		str.append("\n");
		str.append("    	createDatabase();\n");
		str.append("    }\n");
		
		str.append("\n");
		str.append("    public void openActivity(Class<?> activity) {\n");
		str.append("    	Intent intent = new Intent(this, activity);\n");
		str.append("    	startActivity(intent);\n");
		str.append("    }\n");

		str.append("\n");
		str.append("    public void createDatabase() {\n");

		str.append("\n");

		str.append("    }\n");
		str.append("\n");
		
		str.append("\n");
		str.append("}");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(null, Operation.LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
