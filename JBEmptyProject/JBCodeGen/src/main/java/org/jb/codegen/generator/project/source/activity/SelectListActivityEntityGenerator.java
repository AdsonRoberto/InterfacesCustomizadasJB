package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class SelectListActivityEntityGenerator extends EntityGenerator {

	public SelectListActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public SelectListActivityEntityGenerator(ClassDescription classInfo, DescriptionDictionary dictionary, Messager messager,
			Filer filer, Element element) {
		super(classInfo, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FragmentResourceName fragmentResourceName = new FragmentResourceName();
		AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);

		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import android.support.v7.app.AppCompatActivity;\n");
		str.append("import android.app.Activity;\n");
		str.append("import android.content.Intent;\n");
		str.append("import android.app.FragmentManager;\n");
		str.append("import android.app.ListFragment;\n");
		str.append("import android.view.ContextMenu.ContextMenuInfo;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");
		str.append("\n");
		str.append("import org.jb.codegen.generator.auxiliar.TypeListView;\n");
		str.append("import java.util.List;\n");
		str.append("\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("import app.jb.generated." + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + ";\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		str.append("\n");
		
		//str.append("public class " + c.getName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(c, Operation.SELECT_LIST, dictionary) + " extends AppCompatActivity implements " + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + " {\n");
		str.append("\n");
		
		str.append("    " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + " list = null;\n");
		str.append("	" + this.c.getSimpleName() + " obj;\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("    	setContentView(R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.SELECT_LIST, dictionary) +");\n");
		str.append("\n");
		str.append("    	FragmentManager fm = getFragmentManager();\n");
		str.append("    	if (fm.findFragmentById(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.SELECT_LIST, dictionary) + ") == null) { \n");
		str.append("    		list = new " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + "(); \n");
		str.append("    		list.onAttach(this); \n");
		str.append("    		fm.beginTransaction().add(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.SELECT_LIST, dictionary) + ", list).commit(); \n");
		str.append("    	}\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    @Override\n");
		str.append("    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {\n");
		str.append("    	// Inflate the context menu.\n");
		str.append("    	super.onCreateContextMenu(menu, v, menuInfo);\n");
		str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, Operation.SELECT_LIST, dictionary) + ", menu);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    @Override\n");
		str.append("    public boolean onContextItemSelected(MenuItem item) {\n");
		str.append("    	// Handle action context menu item clicks here.l\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.SELECT_LIST, dictionary, MenuAction.SELECT) + ") {\n");
		str.append("    		selectItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	return super.onContextItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    public void selectItem(int index) {\n");		
		str.append("    	Intent intent = new Intent();\n");
    	str.append("    	intent.putExtra(\"index" + c.getSimpleName() + "\", index);\n");
    	str.append("    	setResult(" + c.getIndex() + ", intent);\n");
		str.append("    	this.onBackPressed();\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("	@Override\n");
		str.append("	public List<" + c.getSimpleName() + "> getList" + c.getSimpleName() + "() {\n");
		str.append("	    " + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
		str.append("		return dao.findAll();\n");
		str.append("	}\n");
		str.append("\n");
		str.append("}");
	}

	@Override
	public void generate() {
		JavaFileObject file = null;
		try {
			ActivityResourceName activityResourceName = new ActivityResourceName();
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, Operation.SELECT_LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
