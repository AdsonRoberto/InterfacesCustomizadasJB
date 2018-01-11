package org.jb.codegen.util;

import org.jb.ui.annotation.visual.enums.TemporalType;

import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;

public class WidgetUtil {
	
	public static String getWidget(AttributeDescription attr, DescriptionDictionary dictionary) {
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			return "EditText editText" + attr.getName();
		}
		if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			return "Switch switch" + attr.getName();
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				return "Spinner spinner" + attr.getName();
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				return "TextView textViewObject" + attr.getName();
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					return "TextView textViewCollection" + attr.getName();
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					return "CheckBox []checkBoxCollection" + attr.getName();
				}
				return "TextView textView" + attr.getName();
			}
			else { 
				return "TextView textView" + attr.getName();
			}
		}
	}
	
	public static String getXMLWidget(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + "<LinearLayout\n");
		str.append(strNivel + "		android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "		android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "		android:layout_marginTop=\"5dp\"\n");
		str.append(strNivel + "		android:layout_marginBottom=\"5dp\"\n");
		str.append(strNivel + "		android:orientation=\"vertical\" >\n");
		
		str.append(strNivel + "		<TextView\n");
		str.append(strNivel + "			android:id=\"@+id/textView" + attr.getName() + "\"\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "			android:text=\"@string/action_label_class_" + attr.getClassDescription().getSimpleName().toLowerCase() + "_attribute_" + attr.getName().toLowerCase() + "\"\n");
		str.append(strNivel + "			android:textStyle=\"bold\"\n");
		str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");
		
		str.append(strNivel + "		<View\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"1dp\"\n");
		str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
		str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
		str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");
		
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			
			str.append(strNivel + "		<EditText\n");
			str.append(strNivel + "			android:id=\"@+id/editText" + attr.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			
			if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
					attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
				str.append(strNivel + "			android:inputType=\"number\"\n");
			}
			
			if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
					attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
				str.append(strNivel + "			android:inputType=\"numberDecimal\"\n");
			}
			
			if(attr.getType().equals("java.lang.String"))
				str.append(strNivel + "			android:inputType=\"text\"\n");
			
			if(attr.getType().equals("java.util.Date")) {
				TemporalType type = TemporalType.TIMESTAMP;

				str.append(strNivel + "			android:inputType=\"" + DateUtil.inputType(type) + "\"\n");
				str.append(strNivel + "			android:onClick=\"selectDate\"\n");
				str.append(strNivel + "			android:focusableInTouchMode=\"false\"\n");
			}
			
			str.append(strNivel + "			android:hint=\"\"\n");
			str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");

		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(strNivel + "		<Switch\n");
			str.append(strNivel + "			android:id=\"@+id/switch" + attr.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			str.append(strNivel + "			android:text=\"\" />\n");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				str.append(strNivel + "		<Spinner\n");
				str.append(strNivel + "			android:id=\"@+id/spinner" + attr.getName() + "\"\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:text=\"\" />\n");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				str.append(strNivel + "		<LinearLayout\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:padding=\"1dp\"\n");
				str.append(strNivel + "			android:background=\"#cccccc\"\n");
				str.append(strNivel + "			android:onClick=\"openSelectionObject" + attr.getName() + "\">\n");
				
				str.append(strNivel + "			<LinearLayout\n");
				str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "				android:background=\"#ffffff\"\n");
				str.append(strNivel + "				android:layout_weight=\"1\"\n");
				str.append(strNivel + "				android:orientation=\"horizontal\">\n");
				
				str.append(strNivel + "				<TextView\n");
				str.append(strNivel + "					android:id=\"@+id/textViewObject" + attr.getName() + "\"\n");
				str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:padding=\"5dp\"\n");
				str.append(strNivel + "					android:layout_weight=\"1\"\n");
				str.append(strNivel + "					android:text=\"@string/action_select_object\"\n");
				str.append(strNivel + "					android:textStyle=\"italic\"\n");
				str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
				
				str.append(strNivel + "				<ImageView\n");
				str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_search\" />\n");
				
				str.append(strNivel + "			</LinearLayout>\n");
				
				str.append(strNivel + "		</LinearLayout>\n");
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"horizontal\">\n");
					
					str.append(strNivel + "				<TextView\n");
					str.append(strNivel + "					android:id=\"@+id/textViewCollection" + attr.getName() + "\"\n");
					str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:padding=\"5dp\"\n");
					str.append(strNivel + "					android:layout_weight=\"1\"\n");
					str.append(strNivel + "					android:text=\"@string/action_show_collection\"\n");
					str.append(strNivel + "					android:textStyle=\"italic\"\n");
					str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
					
					str.append(strNivel + "				<ImageView\n");
					str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_more\" />\n");
					
					str.append(strNivel + "			</LinearLayout>\n");
					
					str.append(strNivel + "		</LinearLayout>\n");
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
					
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:id=\"@+id/enumViewCollection" + attr.getName() + "\"\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"vertical\">\n");

					int i = 0;
					for (String enumConstant : eInfo.getConstants()) {
						str.append(strNivel + "				<CheckBox\n");
						str.append(strNivel + "					android:id=\"@+id/checkBoxItem" + attr.getName() + i + "\"\n");
						str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
						str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
						str.append(strNivel + "					android:layout_marginTop=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginBottom=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginRight=\"15dp\"\n");
						str.append(strNivel + "					android:text=\"" + enumConstant + "\"\n");
						str.append(strNivel + "					android:checked=\"false\" />\n");
						i++;
					}
					str.append(strNivel + "			</LinearLayout>\n");
					str.append(strNivel + "		</LinearLayout>\n");
				}
			}
		}
		
		str.append(strNivel + "</LinearLayout>\n");
		
		return str.toString();
	}
	
	public static String getWidgetName(AttributeDescription attr, DescriptionDictionary dictionary) {
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			return "editText" + attr.getName();
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			return "switch" + attr.getName();
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				return "spinner" + attr.getName();
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				return "textViewObject" + attr.getName();
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					return "textViewCollection" + attr.getName();
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					return "checkBoxCollection" + attr.getName();
				}
				return "textViewCollection" + attr.getName();
			}
			else { 
				return "textView" + attr.getName();
			}
		}
	}
	
	public static String getWidgetType(AttributeDescription attr, DescriptionDictionary dictionary) {
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			return "EditText";
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			return "Switch";
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				return "Spinner";
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				return "TextView";
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					return "TextView";
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					return "CheckBox []";
				}
				return "TextView";
			}
			else { 
				return "TextView";
			}
		}
	}
	
	public static String getWidgetGettingValue(AttributeDescription attr, DescriptionDictionary dictionary) {
		String setName = "set" + 
				attr.getName().substring(0, 1).toUpperCase() + 
				attr.getName().substring(1);
		
		StringBuilder str = new StringBuilder();
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {

			str.append(getWidgetName(attr, dictionary) + ".getText().toString()");
			
			if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long")) {
				str.insert(0, "Long.parseLong(");
				str.append(")");
			}
			if(attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
				str.insert(0, "Integer.parseInt(");
				str.append(")");
			}
			if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float")) {
				str.insert(0, "Float.parseFloat(");
				str.append(")");
			}
			if(attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
				str.insert(0, "Double.parseDouble(");
				str.append(")");
			}
			if(attr.getType().equals("java.lang.String")) {
				//TODO
			}
			if(attr.getType().equals("java.util.Date")) {
				str.insert(0, "DateUtil.toDate(");
				str.append(")");
			}
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(getWidgetName(attr, dictionary) + ".isChecked()");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				EnumDescription e = dictionary.getEnum(attr.getType());
				str.append(e.getSimpleName() + ".valueOf(");
				str.append(getWidgetName(attr, dictionary) + ".getSelectedItem().toString()");
				str.append(")");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				return "";
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					return "";
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
					str.append(eInfo.getSimpleName() + " []" + attr.getName() + " = " + eInfo.getSimpleName() + ".values();\n");	str.append("		");
					str.append("obj." + attr.getGetMethodDescription() + "().clear();\n");											str.append("		");
					str.append("for(int i = 0; i < checkBoxCollection" + attr.getName() + ".length; i++) {\n");			str.append("		");
					str.append("	if(checkBoxCollection" + attr.getName() + "[i].isChecked()) {\n");					str.append("		");
					str.append("		obj." + attr.getGetMethodDescription() + "().add(" + attr.getName() + "[i]);\n");			str.append("		");
					str.append("	}\n");																				str.append("		");
					str.append("}\n");																					str.append("		");
					return str.toString();
				}
			}
			else { 
				return "";
			}
		}
		
		str.insert(0, "obj." + setName + "(");
		str.append(")");
		
		return str.toString();
	}
	
	public static String getWidgetSettingValue(AttributeDescription attr, DescriptionDictionary dictionary) {		
		StringBuilder str = new StringBuilder();
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {

			str.append(getWidgetName(attr, dictionary) + ".setText(");
			
			if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long")) {
				str.append("Long.toString(");
				str.append("obj." + attr.getGetMethodDescription() + "())");
			}
			if(attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
				str.append("Integer.toString(");
				str.append("obj." + attr.getGetMethodDescription() + "())");
			}
			if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float")) {
				str.append("Float.toString(");
				str.append("obj." + attr.getGetMethodDescription() + "())");
			}
			if(attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
				str.append("Double.toString(");
				str.append("obj." + attr.getGetMethodDescription() + "())");
			}
			if(attr.getType().equals("java.lang.String")) {
				str.append("new String(");
				str.append("obj." + attr.getGetMethodDescription() + "())");
			}
			if(attr.getType().equals("java.util.Date")) {
				str.append("DateUtil.fromDate(");
				str.append("obj." + attr.getGetMethodDescription() + "()");
				
				TemporalType type = TemporalType.TIMESTAMP;
				
				str.append(", \"" + DateUtil.stringFormat(type) + "\")");
			}
			str.append(")");
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(getWidgetName(attr, dictionary) + ".setChecked(obj." + attr.getGetMethodDescription() + "())");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				str.append(getWidgetName(attr, dictionary) + ".setSelection(obj." + attr.getGetMethodDescription() + "().ordinal());");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				str.append(getWidgetName(attr, dictionary) + ".setText(");
				str.append("obj." + attr.getGetMethodDescription() + "()");
				str.append(".toPrimaryDescription());");
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					return "";
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
					str.append("for(" + eInfo.getSimpleName() + " " + attr.getName() + " : obj." + attr.getGetMethodDescription() + "()) {\n");			str.append("		");
					str.append("	checkBoxCollection" + attr.getName() + "[" + attr.getName() + ".ordinal()].setChecked(true);\n");	str.append("		");
					str.append("}\n");																									str.append("		");
				}
			}
			else { 
				return "";
			}
		}
		
		return str.toString();
	}
	
	public static String getWidgetDeclaring(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		return strNivel + getWidget(attr, dictionary) + " = null;\n";
	}
	
	public static String getWidgetInitializing(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType())) && 
				dictionary.containsEnumWithKey(attr.getCollectionType())) {
			EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
			
			str.append(strNivel + WidgetUtil.getWidgetName(attr, dictionary) + " = ");
			str.append("new CheckBox[" + eInfo.getConstants().size() + "];\n");
			
			for (int i = 0; i < eInfo.getConstants().size(); i++) {
				str.append(strNivel + WidgetUtil.getWidgetName(attr, dictionary) + "[" + i + "] = ");
				str.append("(CheckBox) findViewById(R.id.checkBoxItem" + attr.getName() + i + ");\n");
			}
			
		}
		else {
			str.append(strNivel + WidgetUtil.getWidgetName(attr, dictionary) + " = ");
			str.append("(" + WidgetUtil.getWidgetType(attr, dictionary) + ") ");
			str.append("findViewById(R.id." + WidgetUtil.getWidgetName(attr, dictionary) +");\n");
		}
		str.append("\n");
		
		return str.toString();
	}
	
	public static String getWidgetReadOnly(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType())) && 
				dictionary.containsEnumWithKey(attr.getCollectionType())) {
			EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
			str.append(strNivel + "for(int i = 0; i < " + eInfo.getConstants().size() + "; i++) {");
			str.append(strNivel + "		" + WidgetUtil.getWidgetName(attr, dictionary) + "[i].setEnabled(false);\n");
			str.append(strNivel + "}\n");
		}
		else {
			str.append(strNivel + WidgetUtil.getWidgetName(attr, dictionary) + ".setEnabled(false);\n");
		}
		
		return str.toString();
	}
	
	public static String getWidgetListening(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			//TODO
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			//TODO
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				EnumDescription e = dictionary.getEnum(attr.getType());
				if(e != null) {
					str.append(strNivel + "ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>\n");
					str.append(strNivel + "		(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(" + e.getSimpleName() + ".values()));\n");
					str.append(strNivel + "dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);\n");
					str.append(strNivel + getWidgetName(attr, dictionary) + ".setAdapter(dataAdapter);\n");
				}
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				//TODO
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				//TODO
			}
			else { 
				//TODO
			}
		}
		return str.toString();
	}
	
	//Detail Activity Methods
	
	public static String getDetailXMLWidget(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + "<LinearLayout\n");
		str.append(strNivel + "		android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "		android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "		android:layout_marginTop=\"5dp\"\n");
		str.append(strNivel + "		android:layout_marginBottom=\"5dp\"\n");
		str.append(strNivel + "		android:orientation=\"vertical\" >\n");
		
		str.append(strNivel + "		<TextView\n");
		str.append(strNivel + "			android:id=\"@+id/textView" + attr.getName() + "\"\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "			android:text=\"@string/action_label_class_" + attr.getClassDescription().getSimpleName().toLowerCase() + "_attribute_" + attr.getName().toLowerCase() + "\"\n");
		str.append(strNivel + "			android:textStyle=\"bold\"\n");
		str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");
		
		str.append(strNivel + "		<View\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"1dp\"\n");
		str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
		str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
		str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");
		
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {
			
			str.append(strNivel + "		<EditText\n");
			str.append(strNivel + "			android:id=\"@+id/editText" + attr.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			str.append(strNivel + "			android:enabled=\"false\"\n");
			
			if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
					attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
				str.append(strNivel + "			android:inputType=\"number\"\n");
			}
			
			if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
					attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
				str.append(strNivel + "			android:inputType=\"numberDecimal\"\n");
			}
			
			if(attr.getType().equals("java.lang.String"))
				str.append(strNivel + "			android:inputType=\"text\"\n");
			
			if(attr.getType().equals("java.util.Date")) {
				TemporalType type = TemporalType.TIMESTAMP;
				
				str.append(strNivel + "			android:inputType=\"" + DateUtil.inputType(type) + "\"\n");
				str.append(strNivel + "			android:onClick=\"selectDate\"\n");
				str.append(strNivel + "			android:focusableInTouchMode=\"false\"\n");
			}
			
			str.append(strNivel + "			android:hint=\"\"\n");
			str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");

		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(strNivel + "		<Switch\n");
			str.append(strNivel + "			android:id=\"@+id/switch" + attr.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			str.append(strNivel + "			android:clickable=\"false\"\n");
			str.append(strNivel + "			android:text=\"\" />\n");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				str.append(strNivel + "		<Spinner\n");
				str.append(strNivel + "			android:id=\"@+id/spinner" + attr.getName() + "\"\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:enabled=\"false\"\n");
				str.append(strNivel + "			android:text=\"\" />\n");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				str.append(strNivel + "		<LinearLayout\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:padding=\"1dp\"\n");
				str.append(strNivel + "			android:background=\"#cccccc\"\n");
				str.append(strNivel + "			android:onClick=\"openDetailObject" + attr.getName() + "\">\n");
				
				str.append(strNivel + "			<LinearLayout\n");
				str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "				android:background=\"#ffffff\"\n");
				str.append(strNivel + "				android:layout_weight=\"1\"\n");
				str.append(strNivel + "				android:orientation=\"horizontal\">\n");
				
				str.append(strNivel + "				<TextView\n");
				str.append(strNivel + "					android:id=\"@+id/textViewObject" + attr.getName() + "\"\n");
				str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:enabled=\"false\"\n");
				str.append(strNivel + "					android:padding=\"5dp\"\n");
				str.append(strNivel + "					android:layout_weight=\"1\"\n");
				str.append(strNivel + "					android:text=\"@string/action_select_object\"\n");
				str.append(strNivel + "					android:textStyle=\"italic\"\n");
				str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
				
				str.append(strNivel + "				<ImageView\n");
				str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_search\" />\n");
				
				str.append(strNivel + "			</LinearLayout>\n");
				
				str.append(strNivel + "		</LinearLayout>\n");
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				if(dictionary.containsClassWithKey(attr.getCollectionType())) {
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\"\n");
					str.append(strNivel + "			android:onClick=\"openCollectionObject" + attr.getName() + "\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"horizontal\">\n");
					
					str.append(strNivel + "				<TextView\n");
					str.append(strNivel + "					android:id=\"@+id/textViewCollection" + attr.getName() + "\"\n");
					str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:padding=\"5dp\"\n");
					str.append(strNivel + "					android:layout_weight=\"1\"\n");
					str.append(strNivel + "					android:text=\"@string/action_show_collection\"\n");
					str.append(strNivel + "					android:textStyle=\"italic\"\n");
					str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
					
					str.append(strNivel + "				<ImageView\n");
					str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_more\" />\n");
					
					str.append(strNivel + "			</LinearLayout>\n");
					
					str.append(strNivel + "		</LinearLayout>\n");
				}
				else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
					EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
					
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:id=\"@+id/enumViewCollection" + attr.getName() + "\"\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"vertical\">\n");

					int i = 0;
					for (String enumConstant : eInfo.getConstants()) {
						str.append(strNivel + "				<CheckBox\n");
						str.append(strNivel + "					android:id=\"@+id/checkBoxItem" + attr.getName() + i + "\"\n");
						str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
						str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
						str.append(strNivel + "					android:layout_marginTop=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginBottom=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginRight=\"15dp\"\n");
						str.append(strNivel + "					android:text=\"" + enumConstant + "\"\n");
						str.append(strNivel + "					android:checked=\"false\" />\n");
						i++;
					}
					str.append(strNivel + "			</LinearLayout>\n");
					str.append(strNivel + "		</LinearLayout>\n");
				}
			}
		}
		
		str.append(strNivel + "</LinearLayout>\n");
		
		return str.toString();
	}
	
	
	//Find Activity Methods
	
	public static String getFindWidgetGettingValue(String name, AttributeDescription attr, DescriptionDictionary dictionary) {
		StringBuilder str = new StringBuilder();
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {

			str.append(getWidgetName(attr, dictionary) + ".getText().toString()");
			
			if(attr.getType().equals("java.util.Date")) {
				TemporalType type = TemporalType.TIMESTAMP;
				
				str.insert(0, "DateUtil.toDate(");
				str.append(", \"" + DateUtil.stringFormat(type) + "\")");
				
				str.insert(0, "DateUtil.fromDate(");
				str.append(", \"" + DateUtil.stringDatabaseFormat(type) + "\")");
			}
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(getWidgetName(attr, dictionary) + ".isChecked().toString()");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				EnumDescription e = dictionary.getEnum(attr.getType());
				str.append("\"\" + " + e.getSimpleName() + ".valueOf(");
				str.append(getWidgetName(attr, dictionary) + ".getSelectedItem().toString()");
				str.append(").ordinal()");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				return "";
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				return "";
			}
			else { 
				return "";
			}
		}
		
		str.insert(0, name + " = ");
		
		return str.toString();
	}
	
	public static String getFindWidgetSettingValue(String name, AttributeDescription attr, DescriptionDictionary dictionary) {		
		StringBuilder str = new StringBuilder();
		if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
				attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") || 
				attr.getType().equals("java.lang.Float") || attr.getType().equals("float") || 
				attr.getType().equals("java.lang.Double") || attr.getType().equals("double") || 
				attr.getType().equals("java.lang.String") ||
				attr.getType().equals("java.util.Date")) {

			str.append(getWidgetName(attr, dictionary) + ".setText(");
			
			if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long")) {
				str.append("Long.toString(");
				str.append(name + ")");
			}
			if(attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
				str.append("Integer.toString(");
				str.append(name + ")");
			}
			if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float")) {
				str.append("Float.toString(");
				str.append(name + ")");
			}
			if(attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
				str.append("Double.toString(");
				str.append(name + ")");
			}
			if(attr.getType().equals("java.lang.String")) {
				str.append("new String(");
				str.append(name + ")");
			}
			if(attr.getType().equals("java.util.Date")) {
				str.append("DateUtil.fromDate(");
				str.append(name);
				
				TemporalType type = TemporalType.TIMESTAMP;
				
				str.append(", \"" + DateUtil.stringFormat(type) + "\")");
			}
			str.append(")");
		}
		else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
			str.append(getWidgetName(attr, dictionary) + ".setChecked(" + name + ")");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(attr.getType())) {
				str.append(getWidgetName(attr, dictionary) + ".setSelection(" + name + ".ordinal());");
			}
			else if(dictionary.containsClassWithKey(attr.getType())) {
				str.append(getWidgetName(attr, dictionary) + ".setText(");
				str.append(name);
				str.append(".toPrimaryDescription());");
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
				return "";
			}
			else { 
				return "";
			}
		}
		
		return str.toString();
	}
	
	public static String getFindXMLWidget(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + "<LinearLayout\n");
		str.append(strNivel + "		android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "		android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "		android:orientation=\"horizontal\" >\n");
		
		str.append(strNivel + "		<CheckBox\n");
		str.append(strNivel + "			android:id=\"@+id/checkBox" + attr.getName() + "\"\n");
		str.append(strNivel + "			android:layout_width=\"wrap_content\"\n");
		str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_marginTop=\"5dp\"\n");
		str.append(strNivel + "			android:layout_marginBottom=\"5dp\"\n");
		str.append(strNivel + "			android:layout_marginRight=\"15dp\"\n");
		str.append(strNivel + "			android:checked=\"true\" />\n");
		
		str.append(strNivel + getXMLWidget(attr, dictionary, nivel + 1));
		
		str.append(strNivel + "</LinearLayout>\n");
		
		return str.toString();
	}
	
	public static String getFindWidgetDeclaring(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		return strNivel + "CheckBox checkBox" + attr.getName() + " = null;\n";
	}
	
	public static String getFindWidgetInitializing(AttributeDescription attr, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + "checkBox" + attr.getName() + " = \n");
		str.append(strNivel + "	(CheckBox) findViewById(R.id.checkBox" + attr.getName() +");\n");
		
		return str.toString();
	}
	
	//---------------
	
	public static String getWidget(ParameterDescription param, DescriptionDictionary dictionary) {
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") || param.getType().equals("java.util.Date")) {
			return "EditText editText" + param.getName();
		}
		if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			return "Switch switch" + param.getName();
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				return "Spinner spinner" + param.getName();
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				return "TextView textViewObject" + param.getName();
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				return "TextView textViewCollection" + param.getName();
			}
			else { 
				return "TextView textView" + param.getName();
			}
		}
	}
	
	public static String getXMLWidget(ParameterDescription param, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + "<LinearLayout\n");
		str.append(strNivel + "		android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "		android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "		android:layout_marginTop=\"5dp\"\n");
		str.append(strNivel + "		android:layout_marginBottom=\"5dp\"\n");
		str.append(strNivel + "		android:orientation=\"vertical\" >\n");
		
		str.append(strNivel + "		<TextView\n");
		str.append(strNivel + "			android:id=\"@+id/textView" + param.getName() + "\"\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
		str.append(strNivel + "			android:text=\"@string/action_label_method_" + param.getMethodDescription().getName().toLowerCase() + "_parameter_" + param.getName().toLowerCase() + "\"\n");
		str.append(strNivel + "			android:textStyle=\"bold\"\n");
		str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");
		
		str.append(strNivel + "		<View\n");
		str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
		str.append(strNivel + "			android:layout_height=\"1dp\"\n");
		str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
		str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
		str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");
		
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") ||
				param.getType().equals("java.util.Date")) {
			
			str.append(strNivel + "		<EditText\n");
			str.append(strNivel + "			android:id=\"@+id/editText" + param.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			
			if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
					param.getType().equals("java.lang.Integer") || param.getType().equals("int")) {
				str.append(strNivel + "			android:inputType=\"number\"\n");
			}
			
			if(param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
					param.getType().equals("java.lang.Double") || param.getType().equals("double")) {
				str.append(strNivel + "			android:inputType=\"numberDecimal\"\n");
			}
			
			if(param.getType().equals("java.lang.String"))
				str.append(strNivel + "			android:inputType=\"text\"\n");
			
			if(param.getType().equals("java.util.Date")) {
				TemporalType type = TemporalType.TIMESTAMP;
				
				str.append(strNivel + "			android:inputType=\"" + DateUtil.inputType(type) + "\"\n");
				str.append(strNivel + "			android:onClick=\"selectDate\"\n");
				str.append(strNivel + "			android:focusableInTouchMode=\"false\"\n");
			}
			
			str.append(strNivel + "			android:hint=\"\"\n");
			str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");

		}
		else if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			str.append(strNivel + "		<Switch\n");
			str.append(strNivel + "			android:id=\"@+id/switch" + param.getName() + "\"\n");
			str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
			str.append(strNivel + "			android:layout_weight=\"1\"\n");
			str.append(strNivel + "			android:text=\"\" />\n");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				str.append(strNivel + "		<Spinner\n");
				str.append(strNivel + "			android:id=\"@+id/spinner" + param.getName() + "\"\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:text=\"\" />\n");
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				str.append(strNivel + "		<LinearLayout\n");
				str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "			android:padding=\"1dp\"\n");
				str.append(strNivel + "			android:background=\"#cccccc\">\n");
				
				str.append(strNivel + "			<LinearLayout\n");
				str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "				android:background=\"#ffffff\"\n");
				str.append(strNivel + "				android:layout_weight=\"1\"\n");
				str.append(strNivel + "				android:orientation=\"horizontal\">\n");
				
				str.append(strNivel + "				<TextView\n");
				str.append(strNivel + "					android:id=\"@+id/textViewObject" + param.getName() + "\"\n");
				str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:padding=\"5dp\"\n");
				str.append(strNivel + "					android:layout_weight=\"1\"\n");
				str.append(strNivel + "					android:text=\"@string/action_select_object\"\n");
				str.append(strNivel + "					android:textStyle=\"italic\"\n");
				str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\"\n");
				str.append(strNivel + "					android:onClick=\"openSelectionObject" + param.getName() + "\"/>\n");

				
				str.append(strNivel + "				<ImageView\n");
				str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
				str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
				str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_search\" />\n");
				
				str.append(strNivel + "			</LinearLayout>\n");
				
				str.append(strNivel + "		</LinearLayout>\n");
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				if(dictionary.containsClassWithKey(param.getCollectionType())) {
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"horizontal\">\n");
					
					str.append(strNivel + "				<TextView\n");
					str.append(strNivel + "					android:id=\"@+id/textViewCollection" + param.getName() + "\"\n");
					str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:padding=\"5dp\"\n");
					str.append(strNivel + "					android:layout_weight=\"1\"\n");
					str.append(strNivel + "					android:text=\"@string/action_show_collection\"\n");
					str.append(strNivel + "					android:textStyle=\"italic\"\n");
					str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");
					
					str.append(strNivel + "				<ImageView\n");
					str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
					str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
					str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_more\" />\n");
					
					str.append(strNivel + "			</LinearLayout>\n");
					
					str.append(strNivel + "		</LinearLayout>\n");
				}
				else if(dictionary.containsEnumWithKey(param.getCollectionType())) {
					EnumDescription eInfo = dictionary.getEnum(param.getCollectionType());
					
					str.append(strNivel + "		<LinearLayout\n");
					str.append(strNivel + "			android:id=\"@+id/enumViewCollection" + param.getName() + "\"\n");
					str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "			android:padding=\"1dp\"\n");
					str.append(strNivel + "			android:background=\"#cccccc\">\n");
					
					str.append(strNivel + "			<LinearLayout\n");
					str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
					str.append(strNivel + "				android:layout_height=\"wrap_content\"\n");
					str.append(strNivel + "				android:background=\"#ffffff\"\n");
					str.append(strNivel + "				android:layout_weight=\"1\"\n");
					str.append(strNivel + "				android:orientation=\"vertical\">\n");

					int i = 0;
					for (String enumConstant : eInfo.getConstants()) {
						str.append(strNivel + "				<CheckBox\n");
						str.append(strNivel + "					android:id=\"@+id/checkBoxItem" + param.getName() + i + "\"\n");
						str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
						str.append(strNivel + "					android:layout_height=\"wrap_content\"\n");
						str.append(strNivel + "					android:layout_marginTop=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginBottom=\"5dp\"\n");
						str.append(strNivel + "					android:layout_marginRight=\"15dp\"\n");
						str.append(strNivel + "					android:text=\"" + enumConstant + "\"\n");
						str.append(strNivel + "					android:checked=\"false\" />\n");
						i++;
					}
					str.append(strNivel + "			</LinearLayout>\n");
					str.append(strNivel + "		</LinearLayout>\n");
				}
			}
		}
		
		str.append(strNivel + "</LinearLayout>\n");
		
		return str.toString();
	}
	
	public static String getWidgetType(ParameterDescription param, DescriptionDictionary dictionary) {
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") || param.getType().equals("java.util.Date")) {
			return "EditText";
		}
		else if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			return "Switch";
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				return "Spinner";
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				return "TextView";
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				return "TextView";
			}
			else { 
				return "TextView";
			}
		}
	}
	
	public static String getWidgetName(ParameterDescription param, DescriptionDictionary dictionary) {
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") || param.getType().equals("java.util.Date")) {
			return "editText" + param.getName();
		}
		else if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			return "switch" + param.getName();
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				return "spinner" + param.getName();
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				return "textViewObject" + param.getName();
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				return "textViewCollection" + param.getName();
			}
			else { 
				return "textView" + param.getName();
			}
		}
	}
	
	public static String getWidgetDeclaring(ParameterDescription param, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		str.append(strNivel + getWidget(param, dictionary) + " = \n");
		str.append(strNivel + "	(" + WidgetUtil.getWidgetType(param, dictionary) + ") ");
		str.append(strNivel + "	dialogView.findViewById(R.id." + WidgetUtil.getWidgetName(param, dictionary) +");\n");
		
		return str.toString();
	}
	
	public static String getWidgetListening(ParameterDescription param, DescriptionDictionary dictionary, int nivel) {
		StringBuilder str = new StringBuilder();
		
		String strNivel = "";
		for (int i = 0; i < nivel; i++) {
			strNivel = strNivel + '\t';
		}
		
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") ||
				param.getType().equals("java.util.Date")) {
			//TODO
		}
		else if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			//TODO
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				EnumDescription e = dictionary.getEnum(param.getType());
				if(e != null) {
					str.append(strNivel + "ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>\n");
					str.append(strNivel + "		(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(" + e.getSimpleName() + ".values()));\n");
					str.append(strNivel + "dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);\n");
					str.append(strNivel + getWidgetName(param, dictionary) + ".setAdapter(dataAdapter);\n");
				}
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				//TODO
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				//TODO
			}
			else { 
				//TODO
			}
		}
		return str.toString();
	}
	
	public static String getWidgetGettingValue(ParameterDescription param, DescriptionDictionary dictionary) {

		StringBuilder str = new StringBuilder();
		if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
				param.getType().equals("java.lang.Integer") || param.getType().equals("int") || 
				param.getType().equals("java.lang.Float") || param.getType().equals("float") || 
				param.getType().equals("java.lang.Double") || param.getType().equals("double") || 
				param.getType().equals("java.lang.String") ||
				param.getType().equals("java.util.Date")) {

			str.append(getWidgetName(param, dictionary) + ".getText().toString()");
			
			if(param.getType().equals("java.lang.Long") || param.getType().equals("long")) {
				str.insert(0, "Long.parseLong(");
				str.append(")");
			}
			if(param.getType().equals("java.lang.Integer") || param.getType().equals("int")) {
				str.insert(0, "Integer.parseInt(");
				str.append(")");
			}
			if(param.getType().equals("java.lang.Float") || param.getType().equals("float")) {
				str.insert(0, "Float.parseFloat(");
				str.append(")");
			}
			if(param.getType().equals("java.lang.Double") || param.getType().equals("double")) {
				str.insert(0, "Double.parseDouble(");
				str.append(")");
			}
			if(param.getType().equals("java.lang.String")) {
				//TODO
			}
			if(param.getType().equals("java.util.Date")) {
				str.insert(0, "DateUtil.toDate(");
				str.append(")");
			}
		}
		else if(param.getType().equals("java.lang.Boolean") || param.getType().equals("boolean")) {
			str.append(getWidgetName(param, dictionary) + ".isChecked()");
		}
		else { //Enum ou Class
			if(dictionary.containsEnumWithKey(param.getType())) {
				EnumDescription e = dictionary.getEnum(param.getType());
				str.append(e.getSimpleName() + ".valueOf(");
				str.append(getWidgetName(param, dictionary) + ".getSelectedItem().toString()");
				str.append(")");
			}
			else if(dictionary.containsClassWithKey(param.getType())) {
				return "";
			}
			else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(param.getType()))) {
				return "";
			}
			else { 
				return "";
			}
		}
		
		return str.toString();
	}
}
