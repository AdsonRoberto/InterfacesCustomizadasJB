package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.common.config.enums.MappingType;
import org.jb.codegen.generator.auxiliar.DialogAction;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.persistence.web.annotation.WebAggregation;
import org.jb.persistence.web.xml.XMLRequest;
import org.jb.persistence.web.xml.XMLWebAggregation;
import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListActivityJoinTableGenerator extends EntityGenerator {
	AttributeDescription join;
	ClassDescription target;
	
	public ListActivityJoinTableGenerator(ClassDescription c, AttributeDescription join, DescriptionDictionary dictionary) {
		super(c, dictionary);
		this.join = join;
		
		AttributeDescription attr = c.findAttributeDescriptionByName(join.getName());
		if(attr != null) {
			if(dictionary.containsClassWithKey(attr.getCollectionType())) {
				target = dictionary.getClass(attr.getCollectionType());
			}
		}
	}

	public ListActivityJoinTableGenerator(ClassDescription c, AttributeDescription join, DescriptionDictionary dictionary,
			Messager messager, Filer filer, Element element) {
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
		str.append("import android.app.ListFragment;\n");
		str.append("import android.view.ContextMenu.ContextMenuInfo;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");
		str.append("import android.content.Intent;\n");
		str.append("import android.content.DialogInterface;\n");
		str.append("\n");
		str.append("import java.io.IOException;\n");
		str.append("import java.net.MalformedURLException;\n");
		str.append("\n");
		str.append("import org.jb.ui.annotation.visual.enums.DescriptionType;\n");
		str.append("import org.jb.ui.annotation.visual.util.DescriptionUtil;\n");
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
		str.append("import app.jb.generated." + this.c.getSimpleName() + "ListFragment;\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		str.append("import " + this.target.getCanonicalName() + ";\n");
		str.append("\n");

		str.append("public class " + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + " extends AppCompatActivity implements " + fragmentResourceName.getClassDelegateName(activityResourceName.getTargetClass(c, join, dictionary), Operation.LIST, dictionary) + " {\n");
		str.append("\n");
		
		str.append("    " + fragmentResourceName.getClassName(target, Operation.LIST, dictionary) + " list" + this.target.getSimpleName() + " = null;\n");
		str.append("	View dialogWidget = null;\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("\n");
		str.append("    	FragmentManager fm = getFragmentManager();\n");
		str.append("		setContentView(R.layout." + activityResourceName.getClassLayoutResourceName(c, target, Operation.LIST, dictionary) +");\n");

		str.append("    	if (fm.findFragmentById(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, target, Operation.LIST, dictionary) + ") == null) { \n");
		str.append("    		list" + target.getSimpleName() + " = new " + fragmentResourceName.getClassName(target, Operation.LIST, dictionary) + "(); \n");
		str.append("    		list" + target.getSimpleName() + ".onAttach(this); \n");
		str.append("    		fm.beginTransaction().add(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, target, Operation.LIST, dictionary) + ", list" + target.getSimpleName() + ").commit(); \n");
		str.append("    	}\n");
		str.append("    	else { \n");
		str.append("    		list" + target.getSimpleName() + " = (" + fragmentResourceName.getClassName(target, Operation.LIST, dictionary) + ") fm.findFragmentById(R.id." + activityResourceName.getFragmentWidgetListResourceName(c, target, Operation.LIST, dictionary) + ");\n");
		str.append("    		list" + target.getSimpleName() + ".onAttach(this); \n");
		str.append("    		fm.beginTransaction().commit(); \n");
		str.append("    	}\n");
		
		str.append("    }\n");
		str.append("\n");

		str.append("    @Override\n");
		str.append("    public boolean onCreateOptionsMenu(Menu menu) {\n");
		str.append("    	// Inflate the menu; this adds items to the action bar if it is present.\n");
		str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassMenuResourceName(c, target, Operation.LIST, dictionary) + ", menu);\n");
		str.append("    	return true;\n");
		str.append("    }\n");
		str.append("\n");

		str.append("    @Override\n");
		str.append("    public boolean onOptionsItemSelected(MenuItem item) {\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, target, Operation.LIST, dictionary, MenuAction.ADD) + ") {\n");
		str.append("    		" + activityResourceName.getJoinTableClassInsertDialogMethodName(c, target, Operation.LIST, dictionary) + "();\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("\n");
		str.append("    	return super.onOptionsItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    @Override\n");
		str.append("    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {\n");
		str.append("    	// Inflate the context menu.\n");
		str.append("    	super.onCreateContextMenu(menu, v, menuInfo);\n");
		str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassContextMenuResourceName(c, target, Operation.LIST, dictionary) + ", menu);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    @Override\n");
		str.append("    public boolean onContextItemSelected(MenuItem item) {\n");
		str.append("    	// Handle action context menu item clicks here.l\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, target, Operation.LIST, dictionary, MenuAction.DETAIL) + ") {\n");
		str.append("    		openDetailSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassContextMenuActionResourceName(c, target, Operation.LIST, dictionary, MenuAction.DELETE) + ") {\n");
		str.append("    		openDeleteSelectedItem(index);\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("\n");
		str.append("    	return super.onContextItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    public void openDetailSelectedItem(int index) {\n");
		str.append("		Intent intent = new Intent(this, " + activityResourceName.getClassName(target, Operation.DETAIL, dictionary) + ".class);\n");
		str.append("		intent.putExtra(\"index\", index);\n");
		str.append("		startActivity(intent);\n");
		str.append("    }\n");
		str.append("\n");
		
		str.append("    public void openDeleteSelectedItem(int index) {\n");
		str.append("		int originIndex = getIntent().getIntExtra(\"ORIGIN_INDEX\", 0);\n");
		str.append("		getIntent().putExtra(\"TARGET_INDEX\", index);\n");
		str.append("\n");
		str.append("    	AlertDialog.Builder alert = new AlertDialog.Builder(" + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + ".this);\n");
		str.append("    	alert.setTitle(R.string." + activityResourceName.getJoinTableClassRemoveDialogTitleStringResourceName(c, target, Operation.LIST, dictionary) + ");\n");
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

		str.append("    public void " + activityResourceName.getJoinTableClassInsertDialogMethodName(c, target, Operation.LIST, dictionary) + "() {\n");
		str.append("    	LayoutInflater layoutInflater = LayoutInflater.from(" + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + ".this);\n");
		str.append("    	View dialogView = layoutInflater.inflate(R.layout." + activityResourceName.getJoinTableClassInsertDialogLayoutResourceName(c, target, Operation.LIST, dictionary) + ", null);\n");
		str.append("\n");
		str.append("    	AlertDialog.Builder alert = new AlertDialog.Builder(" + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + ".this);\n");
		str.append("    	alert.setTitle(R.string." + activityResourceName.getJoinTableClassInsertDialogTitleStringResourceName(c, target, Operation.LIST, dictionary) + ");\n");
		str.append("    	alert.setView(dialogView);\n");
		str.append("\n");
		str.append("    	final TextView " + activityResourceName.getJoinTableClassInsertDialogWidgetName(c, target, Operation.LIST, dictionary) + " =\n");
		str.append("    			(TextView) dialogView.findViewById(R.id." + activityResourceName.getJoinTableClassInsertDialogWidgetResourceName(c, target, Operation.LIST, dictionary) + ");\n");

		str.append("    	alert.setPositiveButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.OK) + ", new DialogInterface.OnClickListener() {\n");
		str.append("    		public void onClick(DialogInterface dialog, int whichButton) {\n");
		str.append("    			// Do something with value!\n");
		str.append("    			new SendAddRequestTask().execute();\n");
		str.append("    		}\n");
		str.append("    	});\n");
		str.append("\n");
		str.append("    	alert.setNegativeButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.CANCEL) + ", new DialogInterface.OnClickListener() {\n");
		str.append("    		public void onClick(DialogInterface dialog, int whichButton) {\n");
		str.append("    			// Canceled.\n");
		str.append("    		}\n");
		str.append("    	});\n");
		str.append("    	alert.show();\n");
		str.append("    }\n");
		str.append("\n");

		str.append("    public void " + activityResourceName.getJoinTableClassInsertDialogObjectSelectMethodName(c, target, Operation.LIST, dictionary) + "(View view) {\n");
		str.append("		dialogWidget = view;\n");
		str.append("		Intent intent = new Intent(this, " + activityResourceName.getClassName(target, Operation.LIST, dictionary) + ".class);\n");
		str.append("		intent.putExtra(\"TYPE_LIST_VIEW\", TypeListView.SELECTION);\n");
		str.append("    	startActivityForResult(intent, " + resultCode + ");\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	@Override\n");
		str.append("	protected void onActivityResult(int requestCode, int resultCode, Intent data) {\n");
		str.append("\n");

		str.append("		if(requestCode == " + requestCode + ") {\n");
		str.append("			int index" + target.getSimpleName() + " = data.getIntExtra(\"index" + target.getSimpleName() + "\", 0);\n");
		str.append("			if(index" + target.getSimpleName() + " > 0) {\n");
		str.append("				getIntent().putExtra(\"TARGET_INDEX\", index" + target.getSimpleName() + ");\n");
		//TODO
		str.append("				new SendActivityResult" + target.getSimpleName() + "RequestTask().execute();\n");
		str.append("			}\n");
		str.append("		}\n");
		str.append("\n");

		str.append("		super.onActivityResult(requestCode, resultCode, data);\n");
		str.append("	}\n");
		str.append("\n");

		XMLWebAggregation aggregation = null;
		if(dictionary.getProjectConfiguration().getPersistenceConfiguration()
				.getMappingType() == MappingType.ANNOTATION) {
			aggregation = XMLWebAggregation.adapt(
					(WebAggregation) join.findAnnotationByType(WebAggregation.class));
		}
		else if(dictionary.getProjectConfiguration().getPersistenceConfiguration()
				.getMappingType() == MappingType.XML) {
			aggregation = (XMLWebAggregation) join.findXmlAnnotationByType(XMLWebAggregation.class);
		}
		else {
			//DO NOTHING
		}

		XMLRequest list = aggregation.getList();
		XMLRequest insert = aggregation.getInsert();
		XMLRequest delete = aggregation.getDelete();

		str.append("	@Override\n");
		str.append("	public List<" + target.getSimpleName() + "> getList" + target.getSimpleName() + "() {\n");
		str.append("		Integer originIndex = getIntent().getIntExtra(\"ORIGIN_INDEX\", 0);\n");
		str.append("		\n");
		str.append("		WebDAO dao = new WebDAO();\n");
		str.append("		List<" + target.getSimpleName() + "> " + join.getName() + " = new ArrayList<" + target.getSimpleName() + ">();\n");
		str.append("		try {\n");

		str.append("			String[] params = {");
		for(int i = 0; i < list.getQueryParameters().length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append("\"" + list.getQueryParameters()[i].getParam() + "\"");
		}
		str.append("};\n");

		str.append("			Object[] values = {");
		for(int i = 0; i < list.getQueryParameters().length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append("originIndex");
		}
		str.append("};\n");

		str.append("			" + join.getName() + " = (List<" + target.getSimpleName() + ">) dao.listCollection(" + c.getSimpleName() + ".class, \"" + join.getName() + "\", params, values);\n");
		str.append("		} catch (MalformedURLException e) {\n");
		str.append("			e.printStackTrace();\n");
		str.append("		} catch (IOException e) {\n");
		str.append("			e.printStackTrace();\n");
		str.append("		} catch (NoSuchFieldException e) {\n");
		str.append("			e.printStackTrace();\n");
		str.append("		}\n");
		str.append("		return " + join.getName() + ";\n");
		str.append("	}\n");

		str.append("\n");
		str.append("	//AsyncTasks for Remote Persistence\n");
		str.append("\n");

		str.append("	private class SendAddRequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("			int id" + c.getSimpleName() + " = getIntent().getIntExtra(\"ORIGIN_INDEX\", 0);\n");
		str.append("			int id" + target.getSimpleName() + " = getIntent().getIntExtra(\"TARGET_INDEX\", 0);\n");
		str.append("\n");

		String[] valueParams = new String[insert.getQueryParameters().length];
		if(insert.getQueryParameters().length >= 2) {
			valueParams[0] = "id" + c.getSimpleName();
			valueParams[1] = "id" + target.getSimpleName();
			for(int i = 2; i < insert.getQueryParameters().length; i++) {
				valueParams[i] = "0";
			}
		}

		str.append("			String[] params = {");
		for(int i = 0; i < insert.getQueryParameters().length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append("\"" + insert.getQueryParameters()[i].getParam() + "\"");
		}
		str.append("};\n");

		str.append("			Object[] values = {");
		for(int i = 0; i < valueParams.length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append(valueParams[i]);
		}
		str.append("};\n");

		str.append("\n");
		str.append("			try {\n");
		str.append("				WebDAO dao = new WebDAO();\n");
		str.append("				return dao.addIntoCollection(" + c.getSimpleName() + ".class, \"" + join.getName() + "\", params, values);\n");
		str.append("			} catch (MalformedURLException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			} catch (IOException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			} catch (NoSuchFieldException e) {\n");
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
		str.append("			if(list" + target.getSimpleName() + " != null)\n");
		str.append("				list" + target.getSimpleName() + ".load();\n");
		str.append("		}\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	private class SendRemoveRequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("			int id" + c.getSimpleName() + " = getIntent().getIntExtra(\"ORIGIN_INDEX\", 0);\n");
		str.append("			int id" + target.getSimpleName() + " = getIntent().getIntExtra(\"TARGET_INDEX\", 0);\n");
		str.append("\n");

		valueParams = new String[delete.getQueryParameters().length];
		if(delete.getQueryParameters().length >= 2) {
			valueParams[0] = "id" + c.getSimpleName();
			valueParams[1] = "id" + target.getSimpleName();
			for(int i = 2; i < delete.getQueryParameters().length; i++) {
				valueParams[i] = "0";
			}
		}

		str.append("			String[] params = {");
		for(int i = 0; i < delete.getQueryParameters().length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append("\"" + delete.getQueryParameters()[i].getParam() + "\"");
		}
		str.append("};\n");

		str.append("			Object[] values = {");
		for(int i = 0; i < valueParams.length; i++) {
			if(i > 0) {
				str.append(", ");
			}
			str.append(valueParams[i]);
		}
		str.append("};\n");

		str.append("\n");
		str.append("			try {\n");
		str.append("				WebDAO dao = new WebDAO();\n");
		str.append("				return dao.removeFromCollection(" + c.getSimpleName() + ".class, \"" + join.getName() + "\", params, values);\n");
		str.append("			} catch (MalformedURLException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			} catch (IOException e) {\n");
		str.append("				e.printStackTrace();\n");
		str.append("			} catch (NoSuchFieldException e) {\n");
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
		str.append("			if(list" + target.getSimpleName() + " != null)\n");
		str.append("				list" + target.getSimpleName() + ".load();\n");
		str.append("		}\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	private class SendActivityResult" + target.getSimpleName() + "RequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("			int index" + target.getSimpleName() + " = getIntent().getIntExtra(\"TARGET_INDEX\", 0);\n");
		str.append("			try {\n");
		str.append("				WebDAO dao = new WebDAO();\n");
		str.append("				" + target.getSimpleName() + " object = (" + target.getSimpleName() + ") dao.findById(" + target.getSimpleName() + ".class, index" + target.getSimpleName() + ");\n");
		str.append("				return DescriptionUtil.extractDescription(object, DescriptionType.PRIMARY);\n");
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
		str.append("			((TextView) dialogWidget).setText(result);\n");
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
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, target, Operation.LIST, dictionary) + "", element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
