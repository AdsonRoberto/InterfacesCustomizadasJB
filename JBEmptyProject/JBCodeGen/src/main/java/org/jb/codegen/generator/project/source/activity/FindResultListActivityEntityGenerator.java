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

public class FindResultListActivityEntityGenerator extends EntityGenerator {

	public FindResultListActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public FindResultListActivityEntityGenerator(ClassDescription classInfo, DescriptionDictionary dictionary, Messager messager,
												 Filer filer, Element element) {
		super(classInfo, dictionary, messager, filer, element);
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
		str.append("import android.view.ContextMenu.ContextMenuInfo;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.view.*;\n");
		str.append("\n");
		str.append("import org.jb.codegen.generator.auxiliar.TypeListView;\n");
		str.append("\n");
		str.append("import java.util.List;\n");
		str.append("\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("import app.jb.generated." + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + ";\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		str.append("\n");
		
		//str.append("public class " + c.getSimpleName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(c, Operation.FIND_RESULT_LIST, dictionary) + " extends AppCompatActivity implements " + activityResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + " {\n");
		str.append("\n");
		
		str.append("    " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + " list = null;\n");
		str.append("	" + this.c.getSimpleName() + " obj;\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("    	setContentView(R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.FIND_RESULT_LIST, dictionary) + ");\n");
		str.append("\n");
		str.append("    	FragmentManager fm = getFragmentManager();\n");
		str.append("    	if (fm.findFragmentById(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.FIND_RESULT_LIST, dictionary) + ") == null) { \n");
		str.append("    		list = new " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + "(); \n");
		str.append("    		list.onAttach(this); \n");
		str.append("    		fm.beginTransaction().add(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.FIND_RESULT_LIST, dictionary) + ", list).commit(); \n");
		str.append("    	}\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {\n");
		str.append("    	// Inflate the context menu.\n");
		str.append("    	super.onCreateContextMenu(menu, v, menuInfo);\n");
		str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, Operation.FIND_RESULT_LIST, dictionary) + ", menu);\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public boolean onContextItemSelected(MenuItem item) {\n");
		str.append("    	// Handle action context menu item clicks here.l\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.FIND_RESULT_LIST, dictionary, MenuAction.DETAIL) + ") {\n");
		str.append("    		openDetailSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.FIND_RESULT_LIST, dictionary, MenuAction.EDIT) + ") {\n");
		str.append("    		openEditSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.FIND_RESULT_LIST, dictionary, MenuAction.DELETE) + ") {\n");
		str.append("    		openDeleteSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	return super.onContextItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    public void openDetailSelectedItem(int index) {\n");
		str.append("    	Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + ".class);\n");
		str.append("    	intent.putExtra(\"index\", index);\n");
		str.append("    	startActivity(intent);\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    public void openEditSelectedItem(int index) {\n");
		str.append("    	Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.EDIT, dictionary) + ".class);\n");
		str.append("    	intent.putExtra(\"index\", index);\n");
		str.append("    	startActivity(intent);\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    public void openDeleteSelectedItem(int index) {\n");
		str.append("    	" + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
		str.append("    	" + c.getSimpleName() + " obj = dao.findById(index);\n");
		str.append("    	dao.delete(obj);\n");
		str.append("    	list.load();\n");
		str.append("    }\n");
		str.append("\n");
		str.append("	@Override\n");
		str.append("	public List<" + c.getSimpleName() + "> getList" + c.getSimpleName() + "() {\n");
		str.append("		String query = getIntent().getStringExtra(\"query\");\n");
		str.append("\n");
		str.append("	    " + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
		str.append("		return dao.findByQuery(query);\n");
		str.append("	}\n");
		str.append("\n");
		str.append("}");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, Operation.FIND_RESULT_LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
