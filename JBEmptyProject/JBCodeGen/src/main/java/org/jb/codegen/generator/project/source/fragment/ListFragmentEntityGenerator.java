package org.jb.codegen.generator.project.source.fragment;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.generator.project.EntityGenerator;
import org.jb.codegen.reader.AndroidManifestXMLReader;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListFragmentEntityGenerator extends EntityGenerator {

	public ListFragmentEntityGenerator(ClassDescription c, DescriptionDictionary dictionary) {
		super(c, dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentEntityGenerator(ClassDescription c, DescriptionDictionary dictionary, Messager messager,
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
		str.append("import android.app.Activity;\n");
		str.append("import android.app.ListFragment;\n");
		str.append("import android.content.Context;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");
		str.append("\n");
		str.append("import java.util.ArrayList;\n");
		str.append("import java.util.List;\n");
		str.append("\n");
		str.append("import org.jb.ui.annotation.visual.enums.DescriptionType;\n");
		str.append("import org.jb.ui.annotation.visual.util.DescriptionUtil;\n");
		str.append("\n");
		str.append("import " + this.c.getCanonicalName() + ";\n");
		str.append("\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("\n");
		str.append("public class " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + " extends ListFragment {\n");
		str.append("	" + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + " delegate;\n");
		str.append("\n");
		str.append("    List<" + this.c.getSimpleName() + "> list" + this.c.getSimpleName() + " = null;\n");
		str.append("    ArrayAdapter<String> adapter = null;\n");  
		str.append("    String[] textValues = null;\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public void onActivityCreated(Bundle savedInstanceState) {\n");
		str.append("    	super.onActivityCreated(savedInstanceState);\n");
		str.append("    	registerForContextMenu(this.getListView());\n"); 
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");
		str.append("    public void onResume() {\n");
		str.append("    	super.onResume();\n");
		str.append("    	load();\n");
		str.append("    }\n");
		str.append("\n");
		str.append("    @Override\n");  
		str.append("    public void onListItemClick(ListView l, View v, int position, long id) {\n");
		str.append("    	" + c.getSimpleName() + " obj = list" + this.c.getSimpleName() + ".get(position);\n");

		for(AttributeDescription ad : c.getAttributesForInterface()) {
			if(ad.isAttributeId()) {
				str.append("    	getActivity().getIntent().putExtra(\"index\", obj." + ad.getGetMethodDescription().getName() + "());\n");
				str.append("    	getActivity().openContextMenu(l);\n");
			}
		}

		str.append("    }\n");
		str.append("\n");		  
		str.append("    @Override\n");
		str.append("    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {\n");
		str.append("    	if(container != null) {\n");
		str.append("    		ListView listView = (ListView) container.findViewById(R.id." + fragmentResourceName.getFragmentListViewResourceName(c, Operation.LIST, dictionary) + ");\n");
		str.append("    		return listView;\n");
		str.append("    	}\n");
		str.append("    	return super.onCreateView(inflater, container, savedInstanceState);\n");
		str.append("    }\n");  
		str.append("\n");
		str.append("	@Override\n");
		str.append("	public void onAttach(Activity activity) {\n");
		str.append("		super.onAttach(activity);\n");
		str.append("		try {\n");
		str.append("			delegate = (" + fragmentResourceName.getClassDelegateName(c, Operation.LIST, dictionary) + ") activity;\n");
		str.append("    	} catch (ClassCastException castException) {\n");
		str.append("        	/** The activity does not implement the listener. */\n");
		str.append("    	}\n");
		str.append("	}\n");
		str.append("\n");

		str.append("    public void load() {\n");
		str.append("		new " + fragmentResourceName.getClassName(c, Operation.LIST, dictionary) + ".SendLoadRequestTask().execute();\n");
		str.append("    }\n");
		str.append("\n");

		str.append("	private class SendLoadRequestTask extends AsyncTask<String, Integer, String> {\n");
		str.append("		protected String doInBackground(String... urls) {\n");
		str.append("    		if(delegate != null) {\n");
		str.append("    			list" + this.c.getSimpleName() + " = delegate.getList" + this.c.getSimpleName() + "();\n");
		str.append("    		}\n");
		str.append("    		else {\n");
		str.append("    			list" + this.c.getSimpleName() + " = new ArrayList<" + this.c.getSimpleName() + ">();\n");
		str.append("    		}\n");
		str.append("			return \"\";\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		protected void onProgressUpdate(Integer... progress) {\n");
		str.append("			//TODO\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		protected void onPostExecute(String result) {\n");
		str.append("    		textValues = new String[list" + this.c.getSimpleName() + ".size()];\n");
		str.append("			for(int i = 0; i < list" + this.c.getSimpleName() + ".size(); i++) {\n");
		str.append("				textValues[i] = DescriptionUtil.extractDescription(list" + this.c.getSimpleName() + ".get(i), DescriptionType.PRIMARY);\n");
		str.append("				textValues[i] += \"___\";\n");
		str.append("				textValues[i] += DescriptionUtil.extractDescription(list" + this.c.getSimpleName() + ".get(i), DescriptionType.SECONDARY);\n");
		str.append("			}\n");
		str.append("\n");
		str.append("    		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, textValues) {\n");
		str.append("				@SuppressWarnings(\"deprecation\")\n");
		str.append("				@Override\n");
		str.append("				public View getView(int position, View convertView, ViewGroup parent) {\n");
		str.append("\n");
		str.append("			    	TwoLineListItem twoLineListItem;\n");
		str.append("\n");
		str.append("			    	if (convertView == null) {\n");
		str.append("			        	LayoutInflater inflater = (LayoutInflater) this.getContext()\n");
		str.append("			        	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n");
		str.append("			        	twoLineListItem = (TwoLineListItem) inflater.inflate(\n");
		str.append("			         	       android.R.layout.simple_list_item_2, null);\n");
		str.append("			    	} else {\n");
		str.append("			        	twoLineListItem = (TwoLineListItem) convertView;\n");
		str.append("			    	}\n");
		str.append("\n");
		str.append("			    	TextView text1 = twoLineListItem.getText1();\n");
		str.append("			    	TextView text2 = twoLineListItem.getText2();\n");
		str.append("\n");
		str.append("			    	String []values = textValues[position].split(\"___\");\n");
		str.append("			    	text1.setText(values[0]);\n");
		str.append("			    	text2.setText(values[1]);\n");
		str.append("\n");
		str.append("			    	return twoLineListItem;\n");
		str.append("				}\n");
		str.append("			};\n");
		str.append("    		setListAdapter(adapter);\n");
		str.append("		}\n");
		str.append("    }\n");
		str.append("}\n");
	}

	@Override
	public void generate() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + fragmentResourceName.getClassName(c, Operation.LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
