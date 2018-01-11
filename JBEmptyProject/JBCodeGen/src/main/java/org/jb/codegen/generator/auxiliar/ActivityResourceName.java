package org.jb.codegen.generator.auxiliar;

import org.jb.codegen.dictionary.domain.AbstractTypedDescription;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.util.StringUtil;
import org.jb.codegen.util.TypeUtil;

/**
 * Created by fabiano on 13/10/16.
 */

public class ActivityResourceName extends ResourceName {
    public ActivityResourceName() {
        super();
        setController(Controller.ACTIVITY);
    }

    public String getFragmentWidgetLineResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, operation, dictionary)) + "FragmentLine";
    }

    public String getFragmentWidgetListResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, operation, dictionary)) + "FragmentList";
    }

    /** Join Tables */

    public String getFragmentWidgetLineResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, join, operation, dictionary)) + "FragmentLine";
    }

    public String getFragmentWidgetListResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, join, operation, dictionary)) + "FragmentList";
    }

    public String getFragmentWidgetLineResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, joinTarget, operation, dictionary)) + "FragmentLine";
    }

    public String getFragmentWidgetListResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, joinTarget, operation, dictionary)) + "FragmentList";
    }

    /** Find Activity */

    //Find Activity Methods

    public String getFindWidgetGettingValue(String name, AttributeDescription attr, DescriptionDictionary dictionary) {
        StringBuilder str = new StringBuilder();
        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {

            str.append(getClassAttributeWidgetName(attr, dictionary) + ".getText().toString()");

            if(attr.getType().equals("java.util.Date")) {

                str.insert(0, "DateUtil.toDate(");
                str.append(", \"" + "\")");

                str.insert(0, "DateUtil.fromDate(");
                str.append(", \"" + "\")");
            }
        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            str.append(getClassAttributeWidgetName(attr, dictionary) + /*".isChecked()*/ ".toString()");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                EnumDescription e = dictionary.getEnum(attr.getType());
                str.append("\"\" + " + e.getSimpleName() + ".valueOf(");
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".getSelectedItem().toString()");
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

    public String getFindWidgetSettingValue(String name, AttributeDescription attr, DescriptionDictionary dictionary) {
        StringBuilder str = new StringBuilder();
        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {

            str.append(getClassAttributeWidgetName(attr, dictionary) + ".setText(");

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

                str.append(", \"" + "\")");
            }
            str.append(")");
        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            str.append(getClassAttributeWidgetName(attr, dictionary) + ".setChecked(" + name + ")");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".setSelection(" + name + ".ordinal());");
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".setText(");
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

    public String getFindXMLWidget(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
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
        str.append(strNivel + "			android:id=\"@+id/" + getClassElementCheckboxFindWidgetResourceName(attr, operation, dictionary) + "\"\n");
        str.append(strNivel + "			android:layout_width=\"wrap_content\"\n");
        str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_marginTop=\"5dp\"\n");
        str.append(strNivel + "			android:layout_marginBottom=\"5dp\"\n");
        str.append(strNivel + "			android:layout_marginRight=\"15dp\"\n");
        str.append(strNivel + "			android:checked=\"true\" />\n");

        str.append(strNivel + getWidgetType(attr, operation, dictionary, nivel + 1));

        str.append(strNivel + "</LinearLayout>\n");

        return str.toString();
    }

    public String getFindWidgetDeclaring(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }

        return strNivel + "CheckBox " + getClassElementCheckboxFindName(attr, operation, dictionary) + " = null;\n";
    }

    public String getFindWidgetInitializing(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        StringBuilder str = new StringBuilder();

        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }

        str.append(strNivel + getClassElementCheckboxFindName(attr, operation, dictionary) + " = \n");
        str.append(strNivel + "	(CheckBox) findViewById(R.id." + getClassElementCheckboxFindWidgetResourceName(attr, operation, dictionary) +");\n");

        return str.toString();
    }

    public String getClassElementCheckboxFindName(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary) {
        return "checkBoxFind" + attr.getName();
    }

    /** Detail Activity */

    public String getDetailXMLWidget(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        return getWidgetType(attr, operation, dictionary, nivel + 1);
    }

    /** XML Element (JBAttribute/JBParameter) Class Names */

    public String getClassElementCheckboxFindWidgetResourceName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getClassElementCheckboxFindWidgetResourceName(element, operation, dictionary, -1);
    }

    public String getClassElementCheckboxFindWidgetResourceName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        return StringUtil.lowerFirst(getClassName(element.getClassDescription(), operation, dictionary)) + "CheckboxFind" + getElementWidgetTypeName(element, operation, dictionary, index);
    }

}
