package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.DialogAction;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.IOException;
import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListActivityEntityGenerator extends EntityGenerator {

	public ListActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary, Messager messager,
			Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);
		ActivityResourceName activityResourceName = new ActivityResourceName();
		FragmentResourceName fragmentResourceName = new FragmentResourceName();

		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import android.os.AsyncTask;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.support.v7.app.AppCompatActivity;\n");
		str.append("import android.app.AlertDialog;\n");
		str.append("import android.app.Activity;\n");
		str.append("import android.app.FragmentManager;\n");
		//str.append("import android.app.ListFragment;\n");
		str.append("import android.content.Intent;\n");
		str.append("import android.content.DialogInterface;\n");
		str.append("import android.view.ContextMenu.ContextMenuInfo;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");
		str.append("\n");
		str.append("import java.io.IOException;\n");
		str.append("import java.net.MalformedURLException;\n");
		str.append("\n");
		str.append("import org.jb.codegen.generator.auxiliar.TypeListView;\n");
		str.append("import org.jb.persistence.web.dao.WebDAO;\n");
		str.append("\n");
		str.append("import java.util.List;\n");
		str.append("import java.util.ArrayList;\n");
		str.append("\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("import app.jb.generated." + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + ";\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		str.append("\n");
		
		//str.append("public class " + c.getName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(c, Operation.LIST, dictionary) + " extends AppCompatActivity implements " + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + " {\n");
		str.append("\n");
		
		str.append("    " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + " list = null;\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("    	TypeListView type = (TypeListView) getIntent().getSerializableExtra(\"TYPE_LIST_VIEW\");\n");
		str.append("    	int list_activity_resource = 0;\n");
		str.append("    	int list_activity_list_fragment_resource = 0;\n");
		str.append("\n");
		str.append("		if(type == TypeListView.RESULT) {\n");
		str.append("    		list_activity_resource = R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.FIND_RESULT_LIST, dictionary) +";\n");
		str.append("    		list_activity_list_fragment_resource = R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.FIND_RESULT_LIST, dictionary) +";\n");
		str.append("		}\n");
		str.append("		else if(type == TypeListView.SELECTION) {\n");
		str.append("    		list_activity_resource = R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.SELECT_LIST, dictionary) +";\n");
		str.append("    		list_activity_list_fragment_resource = R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.SELECT_LIST, dictionary) +";\n");
		str.append("		}\n");
		str.append("		else  {\n");
		str.append("    		list_activity_resource = R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.LIST, dictionary) +";\n");
		str.append("    		list_activity_list_fragment_resource = R.id." + activityResourceName.getFragmentWidgetListResourceName(c, Operation.LIST, dictionary) +";\n");
		str.append("		}\n");
		str.append("\n");
		str.append("    	setContentView(list_activity_resource);\n");
		str.append("    	FragmentManager fm = getFragmentManager();\n");
		str.append("\n");
		str.append("    	if (fm.findFragmentById(list_activity_list_fragment_resource) == null) { \n");
		str.append("    		list = new " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + "(); \n");
		str.append("    		list.onAttach(this); \n");
		str.append("    		fm.beginTransaction().add(list_activity_list_fragment_resource, list).commit(); \n");
		str.append("    	}\n");
		str.append("    	else { \n");
		str.append("    		list = (" + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + ") fm.findFragmentById(list_activity_list_fragment_resource);\n");
		str.append("    		list.onAttach(this); \n");
		str.append("    		fm.beginTransaction().commit(); \n");
		str.append("    	}\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {\n");
		str.append("    	// Inflate the context menu.\n");
		str.append("    	super.onCreateContextMenu(menu, v, menuInfo);\n");
		str.append("\n");
		str.append("    	TypeListView type = (TypeListView) getIntent().getSerializableExtra(\"TYPE_LIST_VIEW\");\n");
		str.append("		if(type == TypeListView.RESULT) {\n");
		str.append("    		getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, Operation.FIND_RESULT_LIST, dictionary) + ", menu);\n");
		str.append("		}\n");
		str.append("		else if(type == TypeListView.SELECTION) {\n");
		str.append("    		getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, Operation.SELECT_LIST, dictionary) + ", menu);\n");
		str.append("		}\n");
		str.append("		else {\n");
		str.append("    		getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, Operation.LIST, dictionary) + ", menu);\n");
		str.append("		}\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public boolean onContextItemSelected(MenuItem item) {\n");
		str.append("    	// Handle action context menu item clicks here.l\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.DETAIL) + ") {\n");
		str.append("    		openDetailSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.EDIT) + ") {\n");
		str.append("    		openEditSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.DELETE) + ") {\n");
		str.append("    		openDeleteSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, Operation.SELECT_LIST, dictionary, MenuAction.SELECT) + ") {\n");
		str.append("    		openSelectionItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	return super.onContextItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public boolean onCreateOptionsMenu(Menu menu) {\n");
		str.append("    	// Inflate the menu; this adds items to the action bar if it is present.\n");
		str.append("\n");
		str.append("    	TypeListView type = (TypeListView) getIntent().getSerializableExtra(\"TYPE_LIST_VIEW\");\n");
		str.append("		if(type == TypeListView.RESULT) {\n");
		str.append("    		//Nothing);\n");
		str.append("		}\n");
		str.append("		else if(type == TypeListView.SELECTION) {\n");
		str.append("    		//Nothing);\n");
		str.append("		}\n");
		str.append("		else {\n");
		str.append("    		getMenuInflater().inflate(R.menu." + activityResourceName.getClassMenuResourceName(c, Operation.LIST, dictionary) + ", menu);\n");
		str.append("		}\n");
		str.append("    	return true;\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public boolean onOptionsItemSelected(MenuItem item) {\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");

		if(!c.containsModifier("ABSTRACT")) {
			str.append("    	if (id == R.id." + activityResourceName.getClassMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.NEW) + ") {\n");
			str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.INSERT, dictionary) + ".class);\n");
			str.append("    		startActivity(intent);\n");
			str.append("    		return true;\n");
			str.append("    	}\n");
		}

		str.append("    	if (id == R.id." + activityResourceName.getClassMenuActionResourceName(c, Operation.LIST, dictionary, MenuAction.FIND) + ") {\n");
		str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.FIND, dictionary) + ".class);\n");
		str.append("    		startActivity(intent);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	return super.onOptionsItemSelected(item);\n");
		str.append("    }\n");

		str.append("\n");

		str.append("	//The detail view call is delegated to the concrete subclasses.\n");
		str.append("    public void openDetailSelectedItem(int index) {\n");
		if(c.containsModifier("ABSTRACT")) {
			str.append("		" + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
			str.append("	    " + c.getSimpleName() + " itemObj = dao.findById(index);\n");
			str.append("\n");

			List<ClassDescription> subClasses = dictionary.getSubClasses(c);
			for (int i = 0; i < subClasses.size(); i++) {
				ClassDescription subClass = subClasses.get(i);
				if(i == 0) {
					str.append("		if(itemObj instanceof " + subClass.getCanonicalName() + ") {\n");
					str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(subClass, Operation.DETAIL, dictionary) + ".class);\n");
					str.append("    		intent.putExtra(\"index\", index);\n");
					str.append("    		startActivity(intent);\n");
					str.append("		}\n");
				}
				else {
					str.append("		else if(itemObj instanceof " + subClass.getCanonicalName() + ") {\n");
					str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(subClass, Operation.DETAIL, dictionary) + ".class);\n");
					str.append("    		intent.putExtra(\"index\", index);\n");
					str.append("    		startActivity(intent);\n");
					str.append("		}\n");
				}
			}
			if(!subClasses.isEmpty()) {
				str.append("		else {\n");
				str.append("    		//Do nothing...\n");
				str.append("		}\n");
			}
		}
		else {
			str.append("    	Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + ".class);\n");
			str.append("    	intent.putExtra(\"index\", index);\n");
			str.append("    	startActivity(intent);\n");
		}
		str.append("    }\n");

		str.append("\n");

		str.append("	//The edit view call is delegated to the concrete subclasses.\n");
		str.append("    public void openEditSelectedItem(int index) {\n");
		if(c.containsModifier("ABSTRACT")) {
			str.append("		" + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
			str.append("	    " + c.getSimpleName() + " itemObj = dao.findById(index);\n");
			str.append("\n");

			List<ClassDescription> subClasses = dictionary.getSubClasses(c);
			for (int i = 0; i < subClasses.size(); i++) {
				ClassDescription subClass = subClasses.get(i);
				if(i == 0) {
					str.append("		if(itemObj instanceof " + subClass.getCanonicalName() + ") {\n");
					str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(subClass, Operation.EDIT, dictionary) + ".class);\n");
					str.append("    		intent.putExtra(\"index\", index);\n");
					str.append("    		startActivity(intent);\n");
					str.append("		}\n");
				}
				else {
					str.append("		else if(itemObj instanceof " + subClass.getCanonicalName() + ") {\n");
					str.append("    		Intent intent = new Intent(this, " + activityResourceName.getClassName(subClass, Operation.EDIT, dictionary) + ".class);\n");
					str.append("    		intent.putExtra(\"index\", index);\n");
					str.append("    		startActivity(intent);\n");
					str.append("		}\n");
				}
			}
			if(!subClasses.isEmpty()) {
				str.append("		else {\n");
				str.append("    		//Do nothing...\n");
				str.append("		}\n");
			}
		}
		else {
			str.append("    	Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.EDIT, dictionary) + ".class);\n");
			str.append("    	intent.putExtra(\"index\", index);\n");
			str.append("    	startActivity(intent);\n");
		}
		str.append("    }\n");

		str.append("\n");

		str.append("    public void openDeleteSelectedItem(int index) {\n");
		str.append("		getIntent().putExtra(\"index\", index);\n");
		str.append("\n");
		str.append("    	AlertDialog.Builder alert = new AlertDialog.Builder(" + activityResourceName.getClassName(c, Operation.LIST, dictionary) + ".this);\n");
		str.append("    	alert.setTitle(R.string." + activityResourceName.getClassTitleStringResourceName(c, Operation.LIST, dictionary) + ");\n");
		str.append("\n");
		str.append("    	alert.setPositiveButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.YES) + ", new DialogInterface.OnClickListener() {\n");
		str.append("    		public void onClick(DialogInterface dialog, int whichButton) {\n");
		str.append("    			// Do something with value!\n");
		str.append("    			new SendRemoveRequestTask().execute();\n");
		str.append("    		}\n");
		str.append("    	});\n");
		str.append("\n");
		str.append("    	alert.setNegativeButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.NO) + ", new DialogInterface.OnClickListener() {\n");
		str.append("    		public void onClick(DialogInterface dialog, int whichButton) {\n");
		str.append("    			// Canceled.\n");
		str.append("    		}\n");
		str.append("    	});\n");
		str.append("    	alert.show();\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    public void openSelectionItem(int index) {\n");		
		str.append("    	Intent intent = new Intent();\n");
    	str.append("    	intent.putExtra(\"index" + c.getSimpleName() + "\", index);\n");
    	str.append("    	setResult(" + c.getIndex() + ", intent);\n");
		str.append("    	this.onBackPressed();\n");
		str.append("    }\n");
		str.append("\n");
		str.append("	@Override\n");
		str.append("	public List<" + c.getSimpleName() + "> getList" + c.getSimpleName() + "() {\n");
		str.append("		TypeListView type = (TypeListView) getIntent().getSerializableExtra(\"TYPE_LIST_VIEW\");\n");
		str.append("\n");
		str.append("		WebDAO dao = new WebDAO();\n");
		str.append("		List<" + c.getSimpleName() + "> list" + c.getSimpleName() + " = new ArrayList<" + c.getSimpleName() + ">();\n");
		str.append("		try {\n");
		str.append("			if(type == TypeListView.RESULT) {\n");
		str.append("				list" + c.getSimpleName() + " = new ArrayList<" + c.getSimpleName() + ">();\n");
		str.append("			}\n");
		str.append("			else {\n");
		str.append("				list" + c.getSimpleName() + " = (List<" + c.getSimpleName() + ">) dao.findAll(" + c.getSimpleName() + ".class);\n");
		str.append("			}\n");
		str.append("		} catch (IOException e) {\n");
		str.append("			e.printStackTrace();\n");
		str.append("		}\n");
		str.append("		return list" + c.getSimpleName() + ";\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	private class SendRemoveRequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("			int index" + c.getSimpleName() + " = getIntent().getIntExtra(\"index" + c.getSimpleName() + "\", 0);\n");
		str.append("			try {\n");
		str.append("				WebDAO dao = new WebDAO();\n");
		str.append("				return dao.delete(" + c.getSimpleName() + ".class, index" + c.getSimpleName() + ");\n");
		str.append("			} catch (MalformedURLException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			} catch (IOException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			}\n");
		str.append("\n");
		str.append("			return \"Não foi possível executar a operação!\";\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		protected void onProgressUpdate(Integer... progress) {\n");
		str.append("			//TODO\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		protected void onPostExecute(String result) {\n");
		str.append("			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);\n");
		str.append("		}\n");
		str.append("	}\n");
		str.append("\n");

		str.append("}");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, Operation.LIST, dictionary) + "", element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
