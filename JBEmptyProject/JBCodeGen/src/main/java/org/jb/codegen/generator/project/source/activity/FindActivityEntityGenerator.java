package org.jb.codegen.generator.project.source.activity;

import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.ui.annotation.domain.enums.KindView;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.MenuAction;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.reader.AndroidManifestXMLReader;

import org.jb.codegen.util.DataType;
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

public class FindActivityEntityGenerator extends EntityGenerator {

	public FindActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public FindActivityEntityGenerator(ClassDescription c, DescriptionDictionary dictionary,
									   Messager messager, Filer filer, Element element) {
		super(c, dictionary, messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		AndroidManifestXMLReader reader = AndroidManifestXMLReader.getInstance(this.filer);
		ActivityResourceName activityResourceName = new ActivityResourceName();

		str.append("package app.jb.generated;\n");

		// -------------------------------------------------------------------------------------------------------
		// Adicionando os Imports para mudar a cor da ActionBar
		// -------------------------------------------------------------------------------------------------------
		str.append("import android.graphics.Color;\n");
		str.append("import android.graphics.drawable.ColorDrawable;\n");
		str.append("import android.support.v7.app.ActionBar;\n");
		// -------------------------------------------------------------------------------------------------------

		str.append("\n");
		str.append("import android.os.AsyncTask;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.support.v7.app.AppCompatActivity;\n");
		//str.append("import android.app.Activity;\n");
		str.append("import android.app.Dialog;\n");
		str.append("import android.content.Intent;\n");
		str.append("import android.view.Menu;\n");
		str.append("import android.view.MenuItem;\n");
		str.append("import android.view.View;\n");
		str.append("import android.widget.*;\n");
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

		for (String imp : imports) {
			str.append("import " + imp + ";\n");
		}
		str.append("\n");
		
		//str.append("public class " + c.getName() + "Activity extends ActionBarActivity {\n");
		str.append("public class " + activityResourceName.getClassName(c, Operation.FIND, dictionary) + " extends AppCompatActivity {\n");
		str.append("\n");
		
		//TODO Criar Atributos para os Fields
		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append("	String " + attr.getName() + " = null;\n");
			}
			str.append("\n");
		}
		
		//TODO Criar Checkbox para os Fields do Formulário
		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append(activityResourceName.getFindWidgetDeclaring(attr, Operation.FIND, dictionary, 1));
			}
			str.append("\n");
		}
		
		//TODO Transformar os Atributos em Fields
		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append(activityResourceName.getClassAttributeWidgetDeclaring(attr, Operation.FIND, dictionary, 1));
			}
			str.append("	View dialogWidget = null;\n");
			str.append("\n");
		}
		
		//TODO Metodos Padrões da Activity
		str.append("    @Override\n");
		str.append("    protected void onCreate(Bundle savedInstanceState) {\n");
		str.append("    	super.onCreate(savedInstanceState);\n");
		str.append("    	setContentView(R.layout." + activityResourceName.getClassLayoutResourceName(c, Operation.FIND, dictionary) +");\n");
		str.append("\n");
		// -------------------------------------------------------------------------------------------------------
		// Mudando a cor da ActionBar para Verde
		// -------------------------------------------------------------------------------------------------------
		str.append("		ActionBar bar = getSupportActionBar();\n");
		str.append("		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(\"#689F38\")));\n");
		str.append("\n");
		// -------------------------------------------------------------------------------------------------------


		//TODO Inicializar Checkbox dos Fields do Formulário
		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append(activityResourceName.getFindWidgetInitializing(attr, Operation.FIND, dictionary, 2));
			}
			str.append("\n");
		}

		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append(activityResourceName.getClassAttributeWidgetInitializing(attr, Operation.FIND, dictionary, 2));
			}
			str.append("\n");
		}

		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				str.append(activityResourceName.getClassAttributeWidgetListening(attr, Operation.FIND, dictionary, 2));
			}
		}
		str.append("    }\n");
		str.append("\n");
		
		str.append("    @Override\n");
		str.append("    public boolean onCreateOptionsMenu(Menu menu) {\n");
		str.append("    	// Inflate the menu; this adds items to the action bar if it is present.\n");
		str.append("    	getMenuInflater().inflate(R.menu." + activityResourceName.getClassMenuResourceName(c, Operation.FIND, dictionary) + ", menu);\n");
		str.append("    	return true;\n");
		str.append("    }\n");
		
		str.append("    @Override\n");
		str.append("    public boolean onOptionsItemSelected(MenuItem item) {\n");
		str.append("    	int id = item.getItemId();\n");
		str.append("    	if (id == R.id." + activityResourceName.getClassMenuActionResourceName(c, Operation.FIND, dictionary, MenuAction.FIND) + ") {\n");
		str.append("    		find();\n");
		str.append("    		return true;\n");
		str.append("    	}\n");
		str.append("    	return super.onOptionsItemSelected(item);\n");
		str.append("    }\n");
		str.append("\n");

		// -------------------------------------------------------------------------------------------------------
		// Adicionando a acao do metodo do botao buscar
		// -------------------------------------------------------------------------------------------------------
		str.append("	//Method onClickButtonBuscar\n");
		str.append("\n");
		str.append("	public void onClickBuscar(View view) {\n");
		str.append("		find();\n");
		str.append("		this.onBackPressed();\n");
		str.append("	}\n");
		str.append("\n");
		// -------------------------------------------------------------------------------------------------------
		
		str.append("	//Dialog Methods\n");
		str.append("\n");
		str.append("	public void selectDate(View view) {\n");
		str.append("		Dialog dialog = TimestampDialogBuilder.build(this, view);\n");
		str.append("		dialog.show();\n");
		str.append("	}\n");
		str.append("\n");

		str.append("	@Override\n");
		str.append("	protected void onActivityResult(int requestCode, int resultCode, Intent data) {\n");
		str.append("\n");
				
		for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
			if(dictionary.containsClassWithKey(attr.getType())) {
				requestCode++;

				ClassDescription classe = dictionary.getClass(attr.getType());
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
		str.append("	super.onActivityResult(requestCode, resultCode, data);\n");
		str.append("}\n");
		str.append("\n");
		
		str.append("	//Object Interactions\n");
		for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
			if(dictionary.containsClassWithKey(attr.getType())) {
				resultCode++;

				ClassDescription classe = dictionary.getClass(attr.getType());					
				str.append("	public void openSelectionObject" + attr.getName() + "(View view) {\n");
				str.append("		dialogWidget = view;\n");
				str.append("		Intent intent = new Intent(this, " + activityResourceName.getClassName(classe, Operation.LIST, dictionary) + ".class);\n");
				str.append("		intent.putExtra(\"TYPE_LIST_VIEW\", TypeListView.SELECTION);\n");
				str.append("    	startActivityForResult(intent, " + resultCode + ");\n");
				str.append("	}\n");
				str.append("\n");
			}
		}
		
		str.append("	//CRUD Methods\n");
		str.append("\n");
		str.append("    public void find() {\n");

		int countFindField = 0;
		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				if(TypeUtil.dataType(attr, dictionary) == DataType.SIMPLE ||
						TypeUtil.dataType(attr, dictionary) == DataType.ENUM) {
					str.append("		" + activityResourceName.getFindWidgetGettingValue(attr.getName(), attr, dictionary) + ";\n");
				}
				if(TypeUtil.dataType(attr, dictionary) == DataType.CLASS) {
					//onActivityResult
				}
				countFindField++;
			}
			str.append("\n");
		}
		str.append("    	Intent intent = new Intent(this, " + activityResourceName.getClassName(c, Operation.LIST, dictionary) + ".class);\n");
		str.append("		intent.putExtra(\"TYPE_LIST_VIEW\", TypeListView.RESULT);\n");
		str.append("\n");

		if(c.getAttributesForInterface(KindView.FIND).size() > 0) {
			for(AttributeDescription attr : c.getAttributesForInterface(KindView.FIND)) {
				if(!attr.isCollection()) {
					str.append("    	if(" + activityResourceName.getClassElementCheckboxFindName(attr, Operation.FIND, dictionary) + ".isChecked()) {\n");
					str.append("    		intent.putExtra(\"" + attr.getName() + "\", " + attr.getName() + ");\n");
					str.append("    	}\n");
				}
			}
			str.append("\n");
		}

		str.append("    	startActivity(intent);\n");
		str.append("	}\n");
		
		str.append("\n");

		Set<ClassDescription> distinctClasses = new HashSet<ClassDescription>();
		for(AttributeDescription ad : c.getAttributesForInterface(KindView.FIND)) {
			if(dictionary.containsClassWithKey(ad.getType())) {
				distinctClasses.add(dictionary.getClass(ad.getType()));
			}
		}

		if(!distinctClasses.isEmpty()) {
			str.append("\n");
			str.append("	//AsyncTasks for Activity Result\n");
			str.append("\n");
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

				// -------------------------------------------------------------------------------------------------------
				// Adicionando Toast
				// -------------------------------------------------------------------------------------------------------
				str.append("			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();\n");
				// -------------------------------------------------------------------------------------------------------

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
			file = filer.createSourceFile("java." + "app.jb.generated." + activityResourceName.getClassName(c, Operation.FIND, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}