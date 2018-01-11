package org.jb.codegen.generator.project.source.fragment;

import org.jb.codegen.generator.auxiliar.FragmentResourceName;
import org.jb.codegen.generator.project.ProjectGenerator;
import org.jb.ui.annotation.domain.JBEntity;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

import org.jb.common.config.enums.MappingType;
import org.jb.codegen.generator.auxiliar.ActivityResourceName;
import org.jb.codegen.generator.auxiliar.Operation;

import org.jb.codegen.reader.AndroidManifestXMLReader;
import org.jb.ui.xml.domain.XMLJBEntity;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class ListFragmentProjectGenerator extends ProjectGenerator {

	public ListFragmentProjectGenerator(DescriptionDictionary dictionary) {
		super(dictionary);
		// TODO Auto-generated constructor stub
	}

	public ListFragmentProjectGenerator(DescriptionDictionary dictionary, Messager messager,
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
		str.append("import android.app.Activity;\n");
		str.append("import android.app.FragmentManager;\n");
		str.append("import android.app.ListFragment;\n");
		str.append("import android.os.Bundle;\n");
		str.append("import android.view.*;\n");
		str.append("import android.widget.*;\n");
		str.append("import android.content.Context;\n");

		String packProject = reader.getPackage();

		if(packProject != null)
			str.append("import " + packProject + ".*;\n");
		str.append("\n");
		str.append("public class " + fragmentResourceName.getClassName(null, Operation.LIST, dictionary) + " extends ListFragment {\n");
		str.append("	" + fragmentResourceName.getClassDelegateName(null, Operation.LIST, dictionary) + " delegate;\n");
		str.append("\n");
		str.append("    String[] entityLabelArray = new String[] { ");

		int totalClasses = dictionary.getTotalClasses();

		for(ClassDescription c : dictionary.getClassValues()) {
			XMLJBEntity entityAnnot = null;
			if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
					.getMappingType() == MappingType.ANNOTATION) {
				entityAnnot = XMLJBEntity.adapt((JBEntity) c.findAnnotationByType(JBEntity.class));
			}
			else if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
					.getMappingType() == MappingType.XML) {
				entityAnnot = (XMLJBEntity) c.findXmlAnnotationByType(XMLJBEntity.class);
			}
			else {
				//DO NOTHING
			}

			if(entityAnnot != null) {
				str.append("\"" + entityAnnot.getCollectionLabel() + "\"");
				totalClasses--;
				if (totalClasses > 0)
					str.append(", ");
			}
		}
		str.append("};\n"); 
		str.append("    String[] entityIconArray = new String[] { ");
		totalClasses = dictionary.getTotalClasses();
		for(ClassDescription c : dictionary.getClassValues()) {
			XMLJBEntity entityAnnot = null;
			if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
					.getMappingType() == MappingType.ANNOTATION) {
				entityAnnot = XMLJBEntity.adapt((JBEntity) c.findAnnotationByType(JBEntity.class));
			}
			else if(dictionary.getProjectConfiguration().getCodeGenConfiguration()
					.getMappingType() == MappingType.XML) {
				entityAnnot = (XMLJBEntity) c.findXmlAnnotationByType(XMLJBEntity.class);
			}
			else {
				//DO NOTHING
			}

			if(entityAnnot != null) {
				str.append("\"" + entityAnnot.getIcon() + "\"");
				totalClasses--;
				if (totalClasses > 0)
					str.append(", ");
			}
		}
		str.append("};\n");   
		str.append("    Class<?>[] entityActivityArray = new Class<?>[] { ");
		totalClasses = dictionary.getTotalClasses();
		for(ClassDescription c : dictionary.getClassValues()) {
			str.append(activityResourceName.getClassName(c, Operation.LIST, dictionary) + ".class");
			totalClasses--;
			if(totalClasses > 0)
				str.append(", ");
		}

		str.append("};\n"); 
		str.append("\n");
		
		str.append("    @Override\n");  
		str.append("    public void onListItemClick(ListView l, View v, int position, long id) {\n");
		str.append("    	if(this.delegate != null)\n");
		str.append("    		this.delegate.openActivity(entityActivityArray[position]);\n");
		str.append("    	else\n");
		str.append("    		Toast.makeText(getActivity(), entityLabelArray[position], Toast.LENGTH_SHORT).show();\n");
		str.append("    }\n");
		str.append("\n");		  
		str.append("    @Override\n");
		str.append("    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {\n");
		str.append("    	ArrayElement []elements = new ArrayElement[entityLabelArray.length];\n");
		str.append("    	for(int i = 0; i < elements.length; i++) {\n");
		str.append("    		int resourceId = inflater.getContext().getResources()\n");
		str.append("    			.getIdentifier(entityIconArray[i], \"drawable\", inflater.getContext().getPackageName());\n");
		str.append("    		if(resourceId == 0) \n");
		str.append("    			resourceId = R.drawable.ic_launcher;\n");
		str.append("    		elements[i] = new ArrayElement(resourceId, entityLabelArray[i]);\n");
		str.append("    	}\n");
		str.append("\n");
		str.append("    	StableArrayAdapter adapter = new StableArrayAdapter(inflater.getContext(), R.layout." + fragmentResourceName.getFragmentListRowLayoutProjectResourceName(dictionary) + ", elements);\n");
		str.append("    	setListAdapter(adapter);\n");  
		str.append("    	return super.onCreateView(inflater, container, savedInstanceState);\n");
		str.append("    }\n");
		str.append("\n");		  
		str.append("	@Override\n");
		str.append("	public void onAttach(Activity activity) {\n");
		str.append("		super.onAttach(activity);\n");
		str.append("		try {\n");
		str.append("			delegate = (" + fragmentResourceName.getClassDelegateName(null, Operation.LIST, dictionary) + ") activity;\n");
		str.append("    	} catch (ClassCastException castException) {\n");
		str.append("    	    /** The activity does not implement the listener. */\n");
		str.append("    	}\n");
		str.append("	}\n");
		str.append("\n");
		str.append("	private class StableArrayAdapter extends ArrayAdapter<ArrayElement> {\n");
		str.append("		private final Context context;\n");
		str.append("		private final int resourceId;\n");
		str.append("		private final ArrayElement[] values;\n");
		str.append("\n");
		str.append("		public StableArrayAdapter(Context context, int resourceId, ArrayElement[] values) {\n");
		str.append("			super(context, resourceId, values);\n");
		str.append("			this.context = context;\n");
		str.append("			this.resourceId = resourceId;\n");
		str.append("			this.values = values;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		@Override\n");
		str.append("	  	public View getView(int position, View convertView, ViewGroup parent) {\n");
		str.append("	    	View rowView = convertView;\n");
		str.append("	    	if(rowView == null) {\n");
		str.append("	    		LayoutInflater inflater = (LayoutInflater) context\n");
		str.append("			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n");
		str.append("	    		rowView = inflater.inflate(resourceId, parent, false);\n");
		str.append("	    	}\n");
		str.append("\n");
		str.append("	    	TextView textView = (TextView) rowView.findViewById(R.id." + fragmentResourceName.getFragmentListRowLayoutProjectWidgetTitleResourceName(dictionary) + ");\n");
		str.append("	    	ImageView imageView = (ImageView) rowView.findViewById(R.id." + fragmentResourceName.getFragmentListRowLayoutProjectWidgetIconResourceName(dictionary) + ");\n");
		str.append("\n");
		str.append("			ArrayElement aElement = values[position];\n");
		str.append("\n");
		str.append("	    	textView.setText(aElement.getTitle());\n");
		str.append("	    	imageView.setImageResource(aElement.getIconResource());\n");
		str.append("\n");
		str.append("	    	return rowView;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		@Override\n");
		str.append("		public long getItemId(int position) {\n");
		str.append("			ArrayElement item = getItem(position);\n");
		str.append("			return 0;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		@Override\n");
		str.append("		public boolean hasStableIds() {\n");
		str.append("			return true;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("	}\n");
		str.append("\n");
		str.append("	private class ArrayElement {\n");
		str.append("		int iconResource;\n");
		str.append("		String title;\n");
		str.append("\n");
		str.append("		public ArrayElement(int iconResource, String title) {\n");
		str.append("			this.iconResource = iconResource;\n");
		str.append("			this.title = title;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		public int getIconResource() {\n");
		str.append("			return iconResource;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		public void setIconResource(int iconResource) {\n");
		str.append("			this.iconResource = iconResource;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		public String getTitle() {\n");
		str.append("			return title;\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		public void setTitle(String title) {\n");
		str.append("			this.title = title;\n");
		str.append("		}\n");
		str.append("	}\n");
		str.append("}\n");
	}
	
	@Override
	public void generate() {
		FragmentResourceName fragmentResourceName = new FragmentResourceName();
		JavaFileObject file;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + fragmentResourceName.getClassName(null, Operation.LIST, dictionary), element);
			file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
