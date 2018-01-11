package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.generator.auxiliar.DialogAction;
import org.jb.ui.annotation.domain.enums.KindView;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.Operation;
import org.jb.codegen.generator.auxiliar.TypedElementAction;

import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.persistence.web.annotation.WebAggregation;
import org.jb.persistence.web.annotation.WebComposition;
import org.jb.persistence.web.xml.XMLWebAggregation;
import org.jb.persistence.web.xml.XMLWebComposition;
import org.jb.codegen.reader.AndroidManifestXMLReader;

import org.jb.codegen.util.StringUtil;
import org.jb.codegen.util.TypeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class DetailActivityEntityGenerator extends EntityGenerator {

	public DetailActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public DetailActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
										 Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);
		ActivityResourceName activityResourceName = new ActivityResourceName();

		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import android.os.AsyncTask;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.support.v7.app.AppCompatActivity;\n");
		str.append("import android.view.LayoutInflater;\n");
		str.append("import android.view.Menu;\n");
		str.append("import android.view.MenuItem;\n");
		str.append("import android.view.View;\n");
		str.append("import android.widget.*;\n");
		str.append("import android.app.AlertDialog;\n");
		str.append("import android.app.Activity;\n");
		str.append("import android.app.Dialog;\n");
		str.append("import android.content.DialogInterface;\n");
		str.append("import android.content.Intent;\n");
		str.append("\n");
		str.append("import java.io.IOException;\n");
		str.append("import java.net.MalformedURLException;\n");
		str.append("\n");
		str.append("import org.jb.ui.annotation.visual.enums.DescriptionType;\n");
		str.append("import org.jb.ui.annotation.visual.util.DescriptionUtil;\n");
		str.append("\n");
		str.append("import org.jb.codegen.generator.auxiliar.TypeListView;\n");
		str.append("import org.jb.codegen.util.*;\n");
		str.append("\n");
		str.append("import org.jb.persistence.web.dao.WebDAO;\n");
		str.append("\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		
		List<String> imports = new ArrayList<String>();
		
		for (EnumDescription ed : c.getInternalEnums()) {
			if(!imports.contains(ed.getCanonicalName()))
				imports.add(ed.getCanonicalName());
		}
		for (ClassDescription cd : c.getInternalClasses()) {
			if(!imports.contains(cd.getCanonicalName()))
				imports.add(cd.getCanonicalName());
		}
		
		for(AttributeDescription at : c.getAllAttributeDescriptions()) {
			if(dictionary.containsClassWithKey(at.getType()) ||
					dictionary.containsEnumWithKey(at.getType())) {
				if(!imports.contains(at.getType()))
					imports.add(at.getType());
			}
		}
		
		for(AttributeDescription at : c.getAllAttributeDescriptions()) {
			if(TypeUtil.isList(TypeUtil.getSimpleTypeName(at.getType()))) {
				if(!imports.contains(at.getCollectionType()))
					imports.add(at.getCollectionType());
			}
		}
		
		for(MethodDescription met : c.getAllMethodDescriptions()) {
			for(ParameterDescription param : met.getParameterDescriptions()) {
				if(dictionary.containsClassWithKey(param.getType()) ||
						dictionary.containsEnumWithKey(param.getType())) {
					if(!imports.contains(param.getType()))
						imports.add(param.getType());
				}
			}
		}
		
		for (String imp : imports) {
			str.append("import " + imp + ";\n");
		}
		str.append("\n");
		
		//str.append("public class " + c.getName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + " extends AppCompatActivity {\n");
		str.append("\n");
		str.append("	" + this.c.getSimpleName() + " obj;\n");
		
		//TODO Transformar os Atributos em Fields
		if(c.getAttributesForInterface(KindView.DETAIL).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
				str.append(activityResourceName.getClassAttributeWidgetDeclaring(attr, Operation.DETAIL, dictionary, 1));
			}
			//str.append("\n");
		}
		
		str.append("	View dialogWidget = null;\n");
		str.append("\n");
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("    	setContentView(R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.DETAIL, dictionary) +");\n");

		if(c.getAttributesForInterface(KindView.DETAIL).size() > 0) {
			str.append("\n");
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
				str.append(activityResourceName.getClassAttributeWidgetInitializing(attr, Operation.DETAIL, dictionary, 2));
			}
		}

		if(c.getAttributesForInterface(KindView.DETAIL).size() > 0) {
			str.append("\n");
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
				str.append(activityResourceName.getClassAttributeWidgetReadOnly(attr, Operation.DETAIL, dictionary, 2));
			}
		}

		if(c.getAttributesForInterface(KindView.DETAIL).size() > 0) {
			str.append("\n");
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
				str.append(activityResourceName.getClassAttributeWidgetListening(attr, Operation.DETAIL, dictionary, 2));
			}
		}
		str.append("    }\n");

		str.append("\n");

		str.append("    @Override\n");
		str.append("    public void onResume() {\n");
		str.append("    	super.onResume();\n");
		str.append("    	load();\n");
		str.append("    }\n");
		str.append("\n");

		str.append("    @Override\n");
		str.append("    public boolean onCreateOptionsMenu(Menu menu) {\n");
		str.append("    	// Inflate the menu; this adds items to the action bar if it is present.\n");
		if(!c.getAllMethodDescriptions().isEmpty()) {
			str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassMenuResourceName(c, Operation.DETAIL, dictionary) + ", menu);\n");
		}
		str.append("    	return true;\n");
		str.append("    }\n");
		str.append("\n");

		str.append("    @Override\n");
		str.append("    public boolean onOptionsItemSelected(MenuItem item) {\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("\n");
		
		if(c.getMethodsForInterface().size() > 0) {
			for(MethodDescription met : c.getMethodsForInterface()) {
				str.append("    	if (id == R.id." + activityResourceName.getClassMethodMenuActionResourceName(met) + ") {\n");
				str.append("    		" + activityResourceName.getClassMethodActionName(met) + "();\n");
				str.append("    		return true;\n");
				str.append("    	}\n");
			}
		}
	
		str.append("    	return super.onOptionsItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");

		str.append("	public void selectDate(View view) {\n");
		str.append("		Dialog dialog = TimestampDialogBuilder.build(this, view);\n");
		str.append("		dialog.show();\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	@Override\n");
		str.append("	protected void onActivityResult(int requestCode, int resultCode, Intent data) {\n");
		str.append("\n");
		for(MethodDescription met : c.getMethodsForInterface()) {
			for(ParameterDescription param : met.getParameterDescriptions()) {
				if(dictionary.containsClassWithKey(param.getType())) {
					requestCode++;

					ClassDescription classe = dictionary.getClass(param.getType());
					str.append("		if(requestCode == " + requestCode + ") {\n");
					str.append("			int index" + classe.getSimpleName() + " = data.getIntExtra(\"index" + classe.getSimpleName() + "\", 0);\n");
					str.append("			if(index" + classe.getSimpleName() + " > 0) {\n");
					str.append("				getIntent().putExtra(\"index" + classe.getSimpleName() + "\", index" + classe.getSimpleName() + ");\n");
					//TODO
					str.append("				new SendActivityResult" + classe.getSimpleName() + "RequestTask().execute();\n");
					str.append("			}\n");
					str.append("		}\n");
					str.append("\n");
				}
			}
		}
		str.append("		super.onActivityResult(requestCode, resultCode, data);\n");
		str.append("	}\n");
		str.append("\n");
		
		str.append("	//Object Interactions\n");
		for(MethodDescription met : c.getMethodsForInterface()) {
			for(ParameterDescription param : met.getParameterDescriptions()) {
				if(dictionary.containsClassWithKey(param.getType())) {
					resultCode++;

					ClassDescription classe = dictionary.getClass(param.getType());
					str.append("	public void " + activityResourceName.getClassElementWidgetActionStringResourceName(param, Operation.DETAIL, dictionary, TypedElementAction.SELECT) + "(View view) {\n");
					str.append("		dialogWidget = view;\n");
					str.append("		Intent intent = new Intent(this, " + activityResourceName.getClassName(classe, Operation.LIST, dictionary) + ".class);\n");
					str.append("		intent.putExtra(\"TYPE_LIST_VIEW\", TypeListView.SELECTION);\n");
					str.append("    	startActivityForResult(intent, " + resultCode + ");\n");
					str.append("	}\n");
					str.append("\n");
				}
			}
		}
		
		for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
			if(dictionary.containsClassWithKey(attr.getType())) {
				ClassDescription classInfo = dictionary.getClass(attr.getType());

				str.append("	public void " + activityResourceName.getClassElementWidgetActionStringResourceName(attr, Operation.DETAIL, dictionary, TypedElementAction.DETAIL) + "(View view) {\n");
				str.append("		dialogWidget = view;\n");
				str.append("		Intent intent = new Intent(this, " + classInfo.getSimpleName() + "DetailActivity.class);\n");

				if (c.containsIdAttribute()) {
					AttributeDescription id = c.findIdAttributeDescription();
					if (id.containsGetMethod()) {
						str.append("		intent.putExtra(\"index\", obj." + id.getGetMethodDescription().getName() + "());\n");
					} else {
						str.append("		intent.putExtra(\"index\", 0);\n");
					}
				} else {
					str.append("		intent.putExtra(\"index\", 0);\n");
				}

				str.append("		startActivity(intent);\n");
				str.append("	}\n");
			}
			if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					ClassDescription targetClassInfo = dictionary.getClass(attr.getCollectionType());

					if (attr.isAnnotatedWith(WebAggregation.class)
							|| attr.isXmlAnnotatedWith(XMLWebAggregation.class)) {
						str.append("	public void " + activityResourceName.getClassElementWidgetActionStringResourceName(attr, Operation.DETAIL, dictionary, TypedElementAction.COLLECTION) + "(View view) {\n");
						str.append("		dialogWidget = view;\n");
						str.append("		Intent intent = new Intent(this, " + c.getSimpleName() + "JoinTable" + targetClassInfo.getSimpleName() + "ListActivity.class);\n");

						if (c.containsIdAttribute()) {
							AttributeDescription id = c.findIdAttributeDescription();
							if (id.containsGetMethod()) {
								str.append("		intent.putExtra(\"ORIGIN_INDEX\", obj." + id.getGetMethodDescription().getName() + "());\n");
							} else {
								str.append("		intent.putExtra(\"ORIGIN_INDEX\", 0);\n");
							}
						} else {
							str.append("		intent.putExtra(\"ORIGIN_INDEX\", 0);\n");
						}

						str.append("		startActivity(intent);\n");
						str.append("	}\n");
					}
					else if (attr.isAnnotatedWith(WebComposition.class)
							|| attr.isXmlAnnotatedWith(XMLWebComposition.class)) {
						str.append("	public void " + activityResourceName.getClassElementWidgetActionStringResourceName(attr, Operation.DETAIL, dictionary, TypedElementAction.COLLECTION) + "(View view) {\n");
						str.append("		dialogWidget = view;\n");
						str.append("		Intent intent = new Intent(this, " + targetClassInfo.getSimpleName() + "ListActivity.class);\n");

						if (c.containsIdAttribute()) {
							AttributeDescription id = c.findIdAttributeDescription();
							if (id.containsGetMethod()) {
								str.append("		intent.putExtra(\"ORIGIN_INDEX\", obj." + id.getGetMethodDescription().getName() + "());\n");
							} else {
								str.append("		intent.putExtra(\"ORIGIN_INDEX\", 0);\n");
							}
						} else {
							str.append("		intent.putExtra(\"ORIGIN_INDEX\", 0);\n");
						}

						str.append("		startActivity(intent);\n");
						str.append("	}\n");
					}
					else {
						str.append("	public void " + activityResourceName.getClassElementWidgetActionStringResourceName(attr, Operation.DETAIL, dictionary, TypedElementAction.DETAIL) + "(View view) {\n");
						str.append("	\n");
						str.append("	}\n");
					}
					str.append("\n");
				}
			}
		}
		
		str.append("	//Method Calls\n");
		if(c.getAllMethodDescriptions().size() > 0) {
			for(MethodDescription met : c.getMethodsForInterface()) {

				str.append("    public void " + activityResourceName.getClassMethodActionName(met) + "() {\n");
				if(met.getParameterDescriptions().isEmpty()) {
					str.append("    	obj." + met.getName() + "();\n");
					str.append("		" + c.getSimpleName() + "DAO dao = new " + c.getSimpleName() + "DAO(this);\n");
					str.append("		dao.update(obj);\n");
					str.append("    	load();\n");
				}
				else {
					str.append("		LayoutInflater layoutInflater = LayoutInflater.from(" + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + ".this);\n");
					str.append("		View dialogView = layoutInflater.inflate(R.layout." + activityResourceName.getClassMethodDialogResourceName(met) + ", null);\n");
					str.append("\n");
					str.append("		AlertDialog.Builder alert = new AlertDialog.Builder(" + activityResourceName.getClassName(c, Operation.DETAIL, dictionary) + ".this);\n");
					str.append("		alert.setTitle(R.string." + activityResourceName.getClassMethodLabelStringResourceName(met) + ");\n");
					str.append("		alert.setView(dialogView);\n");
					str.append("\n");
					
					for(ParameterDescription param : met.getParameterDescriptions()) {
						str.append("		final " + activityResourceName.getMethodParameterWidgetDeclaring(param, Operation.DETAIL, dictionary, 0));
					}
					
					for(ParameterDescription param : met.getParameterDescriptions()) {
						str.append(activityResourceName.getMethodParameterWidgetListening(param, Operation.DETAIL, dictionary, 2));
					}

					str.append("		alert.setPositiveButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.OK) + ", new DialogInterface.OnClickListener() {\n");
					str.append("			public void onClick(DialogInterface dialog, int whichButton) {\n");
					str.append("	  			// Do something with value!\n");

					str.append("    			new SendAction" + StringUtil.upperFirst(met.getName()) + "RequestTask().execute();\n");
					str.append("    			load();\n");
					str.append("	  		}\n");
					str.append("		});\n");
					str.append("\n");
					str.append("		alert.setNegativeButton(R.string." + activityResourceName.getClassDialogActionStringResourceName(DialogAction.CANCEL) + ", new DialogInterface.OnClickListener() {\n");
					str.append("	  		public void onClick(DialogInterface dialog, int whichButton) {\n");
					str.append("	    		// Canceled.\n");
					str.append("	  		}\n");
					str.append("		});\n");
					str.append("\n");
					str.append("		alert.show();\n");
				}
				str.append("    }\n");
				str.append("\n");
			}
		}

		str.append("\n");
		str.append("	//Load Method\n");
		str.append("    public void load() {\n");
		str.append("		new SendLoadRequestTask().execute();\n");
		str.append("    }\n");

		str.append("\n");
		str.append("	//AsyncTasks for Remote Persistence\n");
		str.append("\n");
		str.append("	private class SendLoadRequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("			int index = getIntent().getIntExtra(\"index\", 0);\n");
		str.append("\n");
		str.append("			try {\n");
		str.append("				WebDAO dao = new WebDAO();\n");
		str.append("				obj = (" + c.getSimpleName() + ") dao.findById(" + c.getSimpleName() + ".class, index);\n");
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
		if(c.getAttributesForInterface(KindView.DETAIL).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.DETAIL)) {
				String line = activityResourceName.getClassAttributeWidgetSettingValue(attr, Operation.DETAIL, dictionary);
				if(line != null && line.length() > 0) {
					str.append("			" + line + ";\n");
				}
			}
		}
		str.append("		}\n");
		str.append("	}\n");

		str.append("\n");

		for(MethodDescription md : c.getMethodsForInterface()) {
			str.append("	private class SendAction" + StringUtil.upperFirst(md.getName()) + "RequestTask extends AsyncTask<String, Integer, String> {\n");
			str.append("		protected String doInBackground(String... urls) {\n");
			str.append("			int index" + c.getSimpleName() + " = getIntent().getIntExtra(\"index" + c.getSimpleName() + "\", 0);\n");
			str.append("\n");
			str.append("			//TODO\n");
			str.append("\n");
			str.append("			return \"The Body of the Method (" + md.getName() + ") is Empty!\";\n");
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
		}

		Set<ClassDescription> distinctClasses = new HashSet<ClassDescription>();
		for(AttributeDescription ad : c.getAttributesForInterface(KindView.DETAIL)) {
			if(dictionary.containsClassWithKey(ad.getType())) {
				distinctClasses.add(dictionary.getClass(ad.getType()));
			}
		}
		for(MethodDescription md : c.getMethodsForInterface()) {
			for(ParameterDescription pd : md.getParameterDescriptions()) {
				if(dictionary.containsClassWithKey(pd.getType())) {
					distinctClasses.add(dictionary.getClass(pd.getType()));
				}
			}
		}

		if(!distinctClasses.isEmpty()) {
			for (ClassDescription cd : distinctClasses) {
				str.append("	private class SendActivityResult" + cd.getSimpleName() + "RequestTask extends AsyncTask<String, Integer, String> {\n");
				str.append("		protected String doInBackground(String... urls) {\n");
				str.append("			int index" + cd.getSimpleName() + " = getIntent().getIntExtra(\"index" + cd.getSimpleName() + "\", 0);\n");
				str.append("			try {\n");
				str.append("				WebDAO dao = new WebDAO();\n");
				str.append("				" + cd.getSimpleName() + " object = (" + cd.getSimpleName() + ") dao.findById(" + cd.getSimpleName() + ".class, index" + cd.getSimpleName() + ");\n");
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
			}
		}

		str.append("}");
	}

	@Override
	public void generate() {
		ActivityResourceName activityResourceName = new ActivityResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, Operation.DETAIL, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
