package org.jb.codegen.generator.auxiliar;

import org.jb.codegen.dictionary.domain.AbstractTypedDescription;

import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.domain.AttributeDescription;
import org.jb.codegen.dictionary.domain.MethodDescription;
import org.jb.codegen.dictionary.domain.EnumDescription;
import org.jb.codegen.dictionary.domain.ParameterDescription;

import org.jb.codegen.util.StringUtil;
import org.jb.codegen.util.TypeUtil;

/**
 * Created by fabiano on 12/10/16.
 */

public abstract class ResourceName {
    protected Controller controller;

    public ResourceName() {
        super();
    }


    protected Controller getController() {
        return controller;
    }

    protected void setController(Controller controller) {
        this.controller = controller;
    }

    /** XML Class String Name */

    public String getClassString(ClassDescription c) {
        String className = (c == null)
                ? "Project"     //Project
                : c.getSimpleName();  //JBEntity
        return className;
    }

    /** RuntimeClassDescription */

    public ClassDescription getTargetClass(ClassDescription c, AttributeDescription join, DescriptionDictionary dictionary) {
        ClassDescription target = null;
        if(dictionary.containsClassWithKey(join.getCollectionType())) {
            target = dictionary.getClass(join.getCollectionType());
        }
        else {
            target = join.getClassDescription();
        }
        return target;
    }

    /** XML Class Operation Name */

    public String getClassOperationName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassString(c)) + operation.desc();
    }

    public String getClassOperationName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassString(c)) + "JoinTable" + StringUtil.upperFirst(getClassString(getTargetClass(c, join, dictionary))) + operation.desc();
    }

    public String getClassOperationName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassString(c)) + "JoinTable" + StringUtil.upperFirst(getClassString(joinTarget)) + operation.desc();
    }

    public String getOperationControllerName(Operation operation, DescriptionDictionary dictionary) {
        return operation.desc() + controller.desc();
    }

    /** XML Class Name */

    public String getClassName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return getClassOperationName(c, operation, dictionary) + controller.desc();
    }

    public String getClassName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return getClassOperationName(c, join, operation, dictionary) + controller.desc();
    }

    public String getClassName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return getClassOperationName(c, joinTarget, operation, dictionary) + controller.desc();
    }

    /** XML Class Delegate Name */

    public String getClassDelegateName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return getClassName(c, operation, dictionary) + "Delegate";
    }

    /** XML Class Layout Name */

    public String getClassLayoutResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + operation._desc();
    }

    public String getClassLayoutResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(getTargetClass(c, join, dictionary)).toLowerCase() + "_" + operation._desc();
    }

    public String getClassLayoutResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + operation._desc();
    }

    /** XML JoinTable Class Dialog Name */

    public String getJoinTableClassInsertDialogLayoutResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "dialog_" + controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + operation._desc() + "_add";
    }

    public String getJoinTableClassInsertDialogTitleStringResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "dialog_" + controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + operation._desc() + "_add_title";
    }

    public String getJoinTableClassRemoveDialogTitleStringResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "dialog_" + controller._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + operation._desc() + "_remove_title";
    }

    public String getJoinTableClassInsertDialogWidgetLabelResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return getClassString(c).toLowerCase() + "JoinTable" + getClassString(joinTarget) + "InsertDialog" + "TextView" + "Label" + getClassString(joinTarget);
    }

    public String getJoinTableClassInsertDialogWidgetLabelStringResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return getClassTitleStringResourceName(joinTarget, Operation.DETAIL, dictionary);
    }

    public String getJoinTableClassInsertDialogWidgetResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return getClassString(c).toLowerCase() + "JoinTable" + getClassString(joinTarget) + "InsertDialog" + "TextView" + getClassString(joinTarget);
    }

    public String getJoinTableClassInsertDialogWidgetName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "textView" + "Object" + getClassString(joinTarget);
    }

    public String getJoinTableClassInsertDialogMethodName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "objectDefaultActionAdd" + getClassString(joinTarget);
    }

    public String getJoinTableClassInsertDialogObjectSelectMethodName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "openSelectionObject" + getClassString(joinTarget);
    }

    /** XML Class Menu Name */

    public String getClassMenuResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return "menu" + "_" + operation._desc() + "_" + getClassString(c).toLowerCase() + "_" + controller._desc();
    }

    public String getClassContextMenuResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return "context" + "_" + getClassMenuResourceName(c, operation, dictionary);
    }

    public String getClassMenuResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return "menu" + "_" + operation._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(getTargetClass(c, join, dictionary)).toLowerCase() + "_" + controller._desc();
    }

    public String getClassContextMenuResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return "context" + "_" + getClassMenuResourceName(c, join, operation, dictionary);
    }

    public String getClassMenuResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "menu" + "_" + operation._desc() + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + controller._desc();
    }

    public String getClassContextMenuResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return "context" + "_" + getClassMenuResourceName(c, joinTarget, operation, dictionary);
    }

    /** XML Class Menu JBAction Name */

    public String getClassMenuActionResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassMenuResourceName(c, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    public String getClassContextMenuActionResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassContextMenuResourceName(c, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    public String getClassMenuActionResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassMenuResourceName(c, join, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    public String getClassContextMenuActionResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassContextMenuResourceName(c, join, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    public String getClassMenuActionResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassMenuResourceName(c, joinTarget, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    public String getClassContextMenuActionResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary, MenuAction action) {
        return getClassContextMenuResourceName(c, joinTarget, operation, dictionary) + "_" + "action" + "_" + action._desc();
    }

    /** XML Menu JBAction String Name */

    public String getClassMenuActionStringResourceName(MenuAction action) {
        return "menu" + "_" + "action" + "_" + action._desc();
    }

    public String getClassContextMenuActionStringResourceName(MenuAction action) {
        return getClassMenuActionStringResourceName(action);
    }

    /** Dialog Options */

    public String getClassDialogActionStringResourceName(DialogAction action) {
        return "dialog" + "_" + "action" + "_" + action._desc();
    }

    /** XML Class Title */

    public String getClassTitleWidgetResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, operation, dictionary)) + "TextView" + "Title";
    }

    public String getClassTitleStringResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return  controller._desc() + "_" + "title" + "_" + getClassString(c).toLowerCase() + "_" + operation._desc();
    }

    public String getClassTitleWidgetResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(c, join, operation, dictionary)) + "TextView" + "Title";
    }

    public String getClassTitleStringResourceName(ClassDescription c, AttributeDescription join, Operation operation, DescriptionDictionary dictionary) {
        return controller._desc() + "_" + "title" + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(getTargetClass(c, join, dictionary)).toLowerCase() + "_" + operation._desc();
    }

    public String getClassTitleWidgetResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.lowerFirst(getClassName(c, joinTarget, operation, dictionary)) + "TextView" + "Title";
    }

    public String getClassTitleStringResourceName(ClassDescription c, ClassDescription joinTarget, Operation operation, DescriptionDictionary dictionary) {
        return controller._desc() + "_" + "title" + "_" + getClassString(c).toLowerCase() + "_" + "jointable" + "_" + getClassString(joinTarget).toLowerCase() + "_" + operation._desc();
    }
    /** XML Element (JBAttribute/JBParameter) Names */

    public String getElementWidgetTypeName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getElementWidgetTypeName(element, operation, dictionary, -1);
    }

    public String getElementWidgetTypeName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        String name = StringUtil.upperFirst(element.getName());

        if(element.getType().equals("java.lang.Long") || element.getType().equals("long") ||
                element.getType().equals("java.lang.Integer") || element.getType().equals("int") ||
                element.getType().equals("java.lang.Float") || element.getType().equals("float") ||
                element.getType().equals("java.lang.Double") || element.getType().equals("double") ||
                element.getType().equals("java.lang.String") ||
                element.getType().equals("java.util.Date")) {
            return "EditText" + name;
        }
        if(element.getType().equals("java.lang.Boolean") || element.getType().equals("boolean")) {
            return "Switch" + name;
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(element.getType())) {
                return "Spinner" + name;
            }
            else if(dictionary.containsClassWithKey(element.getType())) {
                return "TextViewObject" + name;
            }
            else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(element.getType()))) {
                if(dictionary.containsClassWithKey(element.getCollectionType())) {
                    return "TextViewCollection" + name;
                }
                else if(dictionary.containsEnumWithKey(element.getCollectionType())) {
                    if(index < 0)
                        return "LinearLayoutCollection" + name;
                    return "CheckBoxItem" + element.getName() + index;
                }
                return "TextView" + name;
            }
            else {
                return "TextView" + name;
            }
        }
    }

    public String getElementLabelWidgetTypeName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getElementLabelWidgetTypeName(element, operation, dictionary, -1);
    }

    public String getElementLabelWidgetTypeName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        String name = StringUtil.upperFirst(element.getName());
        return "TextView" + "Label" + name;
    }


    /** XML Element (JBAttribute) Class Names */

    public String getClassAttributeLabelWidgetResourceName(AttributeDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getClassAttributeLabelWidgetResourceName(element, operation, dictionary, -1);
    }

    public String getClassAttributeLabelWidgetResourceName(AttributeDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        return StringUtil.lowerFirst(getClassName(element.getClassDescription(), operation, dictionary)) + "Label" + "JBAttribute" + getElementLabelWidgetTypeName(element, operation, dictionary, index);
    }

    public String getClassAttributeWidgetResourceName(AttributeDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getClassAttributeWidgetResourceName(element, operation, dictionary, -1);
    }

    public String getClassAttributeWidgetResourceName(AttributeDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        return StringUtil.lowerFirst(getClassName(element.getClassDescription(), operation, dictionary)) + "JBAttribute" + getElementWidgetTypeName(element, operation, dictionary, index);
    }

    /** XML Element (JBParameter) Class Names */

    public String getMethodParameterLabelWidgetResourceName(ParameterDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getMethodParameterLabelWidgetResourceName(element, operation, dictionary, -1);
    }

    public String getMethodParameterLabelWidgetResourceName(ParameterDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        return StringUtil.lowerFirst(getClassName(element.getMethodDescription().getClassDescription(), operation, dictionary)) + "Label" + "Method" + StringUtil.upperFirst(element.getMethodDescription().getName()) + getElementLabelWidgetTypeName(element, operation, dictionary, index);
    }

    public String getMethodParameterWidgetResourceName(ParameterDescription element, Operation operation, DescriptionDictionary dictionary) {
        return getMethodParameterWidgetResourceName(element, operation, dictionary, -1);
    }

    public String getMethodParameterWidgetResourceName(ParameterDescription element, Operation operation, DescriptionDictionary dictionary, int index) {
        return StringUtil.lowerFirst(getClassName(element.getMethodDescription().getClassDescription(), operation, dictionary)) + "Method" + StringUtil.upperFirst(element.getMethodDescription().getName()) + getElementWidgetTypeName(element, operation, dictionary, index);
    }

    /** XML Element (JBAttribute/JBParameter) JBAction String Names */

    public String getClassElementWidgetActionStringResourceName(TypedElementAction action) {
        return "element" + "_" + "action" + "_" + action._desc();
    }

    public String getClassElementWidgetActionStringResourceName(AbstractTypedDescription element, Operation operation, DescriptionDictionary dictionary, TypedElementAction action) {
        return action.action() + StringUtil.upperFirst(element.getName());
    }

    public String getClassElementWidgetActionStringResourceName(ClassDescription element, Operation operation, DescriptionDictionary dictionary, TypedElementAction action) {
        return action.action() + StringUtil.upperFirst(element.getSimpleName());
    }

    /** XML Element (JBAttribute/Method/JBParameter) Label String Names */

    public String getClassAttributeLabelStringResourceName(AttributeDescription attr) {
        return "action_label_class_" + getClassString(attr.getClassDescription()).toLowerCase() + "_attribute_" + attr.getName().toLowerCase();
    }

    public String getClassMethodLabelStringResourceName(MethodDescription meth) {
        return "action_label_class_" + getClassString(meth.getClassDescription()).toLowerCase() + "_method_" + meth.getName().toLowerCase();
    }

    public String getClassMethodParameterLabelStringResourceName(ParameterDescription param) {
        return "action_label_class_" + getClassString(param.getMethodDescription().getClassDescription()).toLowerCase() + "_method_" + param.getMethodDescription().getName().toLowerCase() + "_parameter_" + param.getName().toLowerCase();
    }

    /** XML Dialog method */

    public String getClassMethodDialogResourceName(MethodDescription meth) {
        return "dialog_class_" + getClassString(meth.getClassDescription()).toLowerCase() + "_method_" + meth.getName().toLowerCase();
    }

    public String getClassMethodMenuActionResourceName(MethodDescription meth) {
        return "action_class_" + getClassString(meth.getClassDescription()).toLowerCase() + "_method_" + meth.getName().toLowerCase();
    }

    public String getClassMethodActionName(MethodDescription meth) {
        return "objectAction" + StringUtil.upperFirst(meth.getName());
    }

    /** XML App String Name */

    public String getAppStringName() {
        return "app_name";
    }

    /** XML Widget Resource */

    public String getWidgetType(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
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

        /** Label */

        str.append(strNivel + "		<TextView\n");
        str.append(strNivel + "			android:id=\"@+id/" + getClassAttributeLabelWidgetResourceName(attr, operation, dictionary) + "\"\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
        str.append(strNivel + "			android:text=\"@string/" + getClassAttributeLabelStringResourceName(attr) + "\"\n");
        str.append(strNivel + "			android:textStyle=\"bold\"\n");
        str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");

        str.append(strNivel + "		<View\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"1dp\"\n");
        str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
        str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
        str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");

        /** Field */

        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {

            str.append(strNivel + "		<EditText\n");
            str.append(strNivel + "			android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
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
                str.append(strNivel + "			android:inputType=\"" + "\"\n");
                str.append(strNivel + "			android:onClick=\"selectDate\"\n");
                str.append(strNivel + "			android:focusableInTouchMode=\"false\"\n");
            }

            str.append(strNivel + "			android:hint=\"\"\n");
            str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");

        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            str.append(strNivel + "		<Switch\n");
            str.append(strNivel + "			android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
            str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
            str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
            str.append(strNivel + "			android:layout_weight=\"1\"\n");
            str.append(strNivel + "			android:text=\"\" />\n");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                str.append(strNivel + "		<Spinner\n");
                str.append(strNivel + "			android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
                str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "			android:text=\"\" />\n");
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
                TypedElementAction typeAction = (operation == Operation.DETAIL) ? TypedElementAction.DETAIL : TypedElementAction.SELECT;

                str.append(strNivel + "		<LinearLayout\n");
                str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "			android:padding=\"1dp\"\n");
                str.append(strNivel + "			android:background=\"#cccccc\"");
                str.append(strNivel + "			android:onClick=\"" + getClassElementWidgetActionStringResourceName(attr, operation, dictionary, typeAction) + "\">\n");

                str.append(strNivel + "			<LinearLayout\n");
                str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "				android:background=\"#ffffff\"\n");
                str.append(strNivel + "				android:layout_weight=\"1\"\n");
                str.append(strNivel + "				android:orientation=\"horizontal\">\n");

                str.append(strNivel + "				<TextView\n");
                str.append(strNivel + "					android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
                str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "					android:padding=\"5dp\"\n");
                str.append(strNivel + "					android:layout_weight=\"1\"\n");
                str.append(strNivel + "					android:text=\"@string/" + getClassElementWidgetActionStringResourceName(typeAction) + "\"\n");
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
                    str.append(strNivel + "			android:background=\"#cccccc\"");
                    if(operation == Operation.DETAIL) {
                        str.append("\n");
                        str.append(strNivel + "			android:onClick=\"" + getClassElementWidgetActionStringResourceName(attr, operation, dictionary, TypedElementAction.COLLECTION) + "\">\n");
                    }
                    else {
                        str.append(">\n");
                    }

                    str.append(strNivel + "			<LinearLayout\n");
                    str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
                    str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
                    str.append(strNivel + "				android:background=\"#ffffff\"\n");
                    str.append(strNivel + "				android:layout_weight=\"1\"\n");
                    str.append(strNivel + "				android:orientation=\"horizontal\">\n");

                    str.append(strNivel + "				<TextView\n");
                    str.append(strNivel + "					android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
                    str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
                    str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
                    str.append(strNivel + "					android:padding=\"5dp\"\n");
                    str.append(strNivel + "					android:layout_weight=\"1\"\n");
                    str.append(strNivel + "					android:text=\"@string/" + getClassElementWidgetActionStringResourceName(TypedElementAction.COLLECTION) + "\"\n");
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
                    str.append(strNivel + "			android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary) + "\"\n");
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
                        str.append(strNivel + "					android:id=\"@+id/" + getClassAttributeWidgetResourceName(attr, operation, dictionary, i) + "\"\n");
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

    public String getWidgetType(ClassDescription cd, ClassDescription target, Operation operation, DescriptionDictionary dictionary, int nivel) {
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

        /** Label */

        str.append(strNivel + "		<TextView\n");
        str.append(strNivel + "			android:id=\"@+id/" + getJoinTableClassInsertDialogWidgetLabelResourceName(cd, target, operation, dictionary) + "\"\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
        str.append(strNivel + "			android:text=\"@string/" + getJoinTableClassInsertDialogWidgetLabelStringResourceName(cd, target, operation, dictionary) + "\"\n");
        str.append(strNivel + "			android:textStyle=\"bold\"\n");
        str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");

        str.append(strNivel + "		<View\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"1dp\"\n");
        str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
        str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
        str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");

        /** Field */

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
        str.append(strNivel + "					android:id=\"@+id/" + getJoinTableClassInsertDialogWidgetResourceName(cd, target, operation, dictionary) + "\"\n");
        str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
        str.append(strNivel + "					android:padding=\"5dp\"\n");
        str.append(strNivel + "					android:layout_weight=\"1\"\n");
        str.append(strNivel + "					android:text=\"@string/" + getClassElementWidgetActionStringResourceName(TypedElementAction.SELECT) + "\"\n");
        str.append(strNivel + "					android:textStyle=\"italic\"\n");
        str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\"\n");
        str.append(strNivel + "			        android:onClick=\"" + getClassElementWidgetActionStringResourceName(target, operation, dictionary, TypedElementAction.SELECT) + "\"/>\n");

        str.append(strNivel + "				<ImageView\n");
        str.append(strNivel + "					android:layout_width=\"wrap_content\"\n");
        str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
        str.append(strNivel + "					android:src=\"@android:drawable/ic_menu_search\" />\n");

        str.append(strNivel + "			</LinearLayout>\n");

        str.append(strNivel + "		</LinearLayout>\n");

        str.append(strNivel + "</LinearLayout>\n");

        return str.toString();
    }

    public String getWidgetType(ParameterDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
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

        /** Label */

        str.append(strNivel + "		<TextView\n");
        str.append(strNivel + "			android:id=\"@+id/" + getMethodParameterLabelWidgetResourceName(attr, operation, dictionary) + "\"\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"wrap_content\"\n");
        str.append(strNivel + "			android:text=\"@string/" + getClassMethodParameterLabelStringResourceName(attr) + "\"\n");
        str.append(strNivel + "			android:textStyle=\"bold\"\n");
        str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceSmall\" />\n");

        str.append(strNivel + "		<View\n");
        str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
        str.append(strNivel + "			android:layout_height=\"1dp\"\n");
        str.append(strNivel + "			android:layout_marginTop=\"2dp\"\n");
        str.append(strNivel + "			android:layout_marginBottom=\"2dp\"\n");
        str.append(strNivel + "			android:background=\"@android:color/darker_gray\" />\n");

        /** Field */

        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {

            str.append(strNivel + "		<EditText\n");
            str.append(strNivel + "			android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
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
                str.append(strNivel + "			android:inputType=\"" + "\"\n");
                str.append(strNivel + "			android:onClick=\"selectDate\"\n");
                str.append(strNivel + "			android:focusableInTouchMode=\"false\"\n");
            }

            str.append(strNivel + "			android:hint=\"\"\n");
            str.append(strNivel + "			android:textAppearance=\"?android:attr/textAppearanceMedium\" />\n");

        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            str.append(strNivel + "		<Switch\n");
            str.append(strNivel + "			android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
            str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
            str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
            str.append(strNivel + "			android:layout_weight=\"1\"\n");
            str.append(strNivel + "			android:text=\"\" />\n");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                str.append(strNivel + "		<Spinner\n");
                str.append(strNivel + "			android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
                str.append(strNivel + "			android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "			android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "			android:text=\"\" />\n");
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
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
                str.append(strNivel + "					android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
                str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
                str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
                str.append(strNivel + "					android:padding=\"5dp\"\n");
                str.append(strNivel + "					android:layout_weight=\"1\"\n");
                str.append(strNivel + "					android:text=\"@string/" + getClassElementWidgetActionStringResourceName(TypedElementAction.SELECT) + "\"\n");
                str.append(strNivel + "					android:textStyle=\"italic\"\n");
                str.append(strNivel + "					android:textAppearance=\"?android:attr/textAppearanceMedium\"\n");
                str.append(strNivel + "			        android:onClick=\"" + getClassElementWidgetActionStringResourceName(attr, operation, dictionary, TypedElementAction.SELECT) + "\"/>\n");


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
                    str.append(strNivel + "			android:onClick=\"" + getClassElementWidgetActionStringResourceName(attr, operation, dictionary, TypedElementAction.COLLECTION) + "\">\n");

                    str.append(strNivel + "			<LinearLayout\n");
                    str.append(strNivel + "				android:layout_width=\"match_parent\"\n");
                    str.append(strNivel + "				android:layout_height=\"match_parent\"\n");
                    str.append(strNivel + "				android:background=\"#ffffff\"\n");
                    str.append(strNivel + "				android:layout_weight=\"1\"\n");
                    str.append(strNivel + "				android:orientation=\"horizontal\">\n");

                    str.append(strNivel + "				<TextView\n");
                    str.append(strNivel + "					android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
                    str.append(strNivel + "					android:layout_width=\"match_parent\"\n");
                    str.append(strNivel + "					android:layout_height=\"match_parent\"\n");
                    str.append(strNivel + "					android:padding=\"5dp\"\n");
                    str.append(strNivel + "					android:layout_weight=\"1\"\n");
                    str.append(strNivel + "					android:text=\"@string/" + getClassElementWidgetActionStringResourceName(TypedElementAction.COLLECTION) + "\"\n");
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
                    str.append(strNivel + "			android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary) + "\"\n");
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
                        str.append(strNivel + "					android:id=\"@+id/" + getMethodParameterWidgetResourceName(attr, operation, dictionary, i) + "\"\n");
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

    /** Class JBAttribute Widget */

    public String getClassAttributeWidgetType(AttributeDescription attr, DescriptionDictionary dictionary) {
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

    public String getClassAttributeWidgetName(AttributeDescription attr, DescriptionDictionary dictionary) {
        String name = StringUtil.upperFirst(attr.getName());

        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {
            return "editText" + name;
        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            return "switch" + name;
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                return "spinner" + name;
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
                return "textViewObject" + name;
            }
            else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
                if(dictionary.containsClassWithKey(attr.getCollectionType())) {
                    return "textViewCollection" + name;
                }
                else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
                    return "checkBoxCollection" + name;
                }
                return "textViewCollection" + name;
            }
            else {
                return "textView" + name;
            }
        }
    }

    public String getClassAttributeWidgetGettingValue(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary) {
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

            str.append(getClassAttributeWidgetName(attr, dictionary) + ".getText().toString()");

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
            str.append(getClassAttributeWidgetName(attr, dictionary) + ".isChecked()");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                EnumDescription e = dictionary.getEnum(attr.getType());
                str.append(e.getSimpleName() + ".valueOf(");
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".getSelectedItem().toString()");
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
                    str.append("obj." + attr.getGetMethodDescription().getName() + "().clear();\n");											str.append("		");
                    str.append("for(int i = 0; i < " + getClassAttributeWidgetName(attr, dictionary) + ".length; i++) {\n");			str.append("		");
                    str.append("	if(" + getClassAttributeWidgetName(attr, dictionary) + "[i].isChecked()) {\n");					str.append("		");
                    str.append("		obj." + attr.getGetMethodDescription().getName()+ "().add(" + attr.getName() + "[i]);\n");			str.append("		");
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

    public String getClassAttributeWidgetSettingValue(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary) {
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
                str.append("obj." + attr.getGetMethodDescription().getName() + "())");
            }
            if(attr.getType().equals("java.lang.Integer") || attr.getType().equals("int")) {
                str.append("Integer.toString(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "())");
            }
            if(attr.getType().equals("java.lang.Float") || attr.getType().equals("float")) {
                str.append("Float.toString(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "())");
            }
            if(attr.getType().equals("java.lang.Double") || attr.getType().equals("double")) {
                str.append("Double.toString(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "())");
            }
            if(attr.getType().equals("java.lang.String")) {
                str.append("new String(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "())");
            }
            if(attr.getType().equals("java.util.Date")) {
                str.append("DateUtil.fromDate(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "()");

                str.append(", \"" + "\")");
            }
            str.append(")");
        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            str.append(getClassAttributeWidgetName(attr, dictionary) + ".setChecked(obj." + attr.getGetMethodDescription().getName() + "())");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".setSelection(obj." + attr.getGetMethodDescription().getName() + "().ordinal());");
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
                str.append(getClassAttributeWidgetName(attr, dictionary) + ".setText(");
                //str.append("obj." + attr.getGetMethodDescription().getName() + "()");
                //str.append(".toPrimaryDescription())");

                str.append("DescriptionUtil.extractDescription(");
                str.append("obj." + attr.getGetMethodDescription().getName() + "()");
                str.append(", DescriptionType.PRIMARY))");
            }
            else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
                if(dictionary.containsClassWithKey(attr.getCollectionType())) {
                    return "";
                }
                else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
                    EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
                    str.append("for(" + eInfo.getSimpleName() + " " + attr.getName() + " : obj." + attr.getGetMethodDescription().getName() + "()) {\n");			str.append("		");
                    str.append("	" + getClassAttributeWidgetName(attr, dictionary) + "[" + attr.getName() + ".ordinal()].setChecked(true);\n");	str.append("		");
                    str.append("}\n");																									str.append("		");
                }
            }
            else {
                return "";
            }
        }

        return str.toString();
    }

    public String getClassAttributeWidgetDeclaring(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }

        return strNivel + getClassAttributeWidgetType(attr, dictionary) + " " + getClassAttributeWidgetName(attr, dictionary) + " = null;\n";
    }

    public String getClassAttributeWidgetInitializing(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        StringBuilder str = new StringBuilder();

        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }
        if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType())) &&
                dictionary.containsEnumWithKey(attr.getCollectionType())) {
            EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());

            str.append(strNivel + getClassAttributeWidgetName(attr, dictionary) + " = ");
            str.append("new CheckBox[" + eInfo.getConstants().size() + "];\n");

            for (int i = 0; i < eInfo.getConstants().size(); i++) {
                str.append(strNivel + getClassAttributeWidgetName(attr, dictionary) + "[" + i + "] = ");
                str.append("(CheckBox) findViewById(R.id." + getClassAttributeWidgetResourceName(attr, operation, dictionary, i) + ");\n");
            }

        }
        else {
            str.append(strNivel + getClassAttributeWidgetName(attr, dictionary) + " = ");
            str.append("(" + getClassAttributeWidgetType(attr, dictionary) + ") ");
            str.append("findViewById(R.id." + getClassAttributeWidgetResourceName(attr, operation, dictionary) +");\n");
        }

        return str.toString();
    }

    public String getClassAttributeWidgetReadOnly(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
        StringBuilder str = new StringBuilder();

        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }

        if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType())) &&
                dictionary.containsEnumWithKey(attr.getCollectionType())) {
            EnumDescription eInfo = dictionary.getEnum(attr.getCollectionType());
            str.append(strNivel + "for(int i = 0; i < " + eInfo.getConstants().size() + "; i++) {\n");
            str.append(strNivel + "		" + getClassAttributeWidgetName(attr, dictionary) + "[i].setEnabled(false);\n");
            str.append(strNivel + "}\n");
        }
        else {
            str.append(strNivel + getClassAttributeWidgetName(attr, dictionary) + ".setEnabled(false);\n");
        }

        return str.toString();
    }

    public String getClassAttributeWidgetListening(AttributeDescription attr, Operation operation, DescriptionDictionary dictionary, int nivel) {
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
                    str.append(strNivel + getClassAttributeWidgetName(attr, dictionary) + ".setAdapter(dataAdapter);\n");
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

    /** Class Method JBParameter Widget */

    public String getMethodParameterWidgetType(ParameterDescription attr, DescriptionDictionary dictionary) {
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

    public String getMethodParameterWidgetName(ParameterDescription attr, DescriptionDictionary dictionary) {
        String name = StringUtil.upperFirst(attr.getName());

        if(attr.getType().equals("java.lang.Long") || attr.getType().equals("long") ||
                attr.getType().equals("java.lang.Integer") || attr.getType().equals("int") ||
                attr.getType().equals("java.lang.Float") || attr.getType().equals("float") ||
                attr.getType().equals("java.lang.Double") || attr.getType().equals("double") ||
                attr.getType().equals("java.lang.String") ||
                attr.getType().equals("java.util.Date")) {
            return "editText" + name;
        }
        else if(attr.getType().equals("java.lang.Boolean") || attr.getType().equals("boolean")) {
            return "switch" + name;
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(attr.getType())) {
                return "spinner" + name;
            }
            else if(dictionary.containsClassWithKey(attr.getType())) {
                return "textViewObject" + name;
            }
            else if(TypeUtil.isList(TypeUtil.getSimpleTypeName(attr.getType()))) {
                if(dictionary.containsClassWithKey(attr.getCollectionType())) {
                    return "textViewCollection" + name;
                }
                else if(dictionary.containsEnumWithKey(attr.getCollectionType())) {
                    return "checkBoxCollection" + name;
                }
                return "textViewCollection" + name;
            }
            else {
                return "textView" + name;
            }
        }
    }

    public String getMethodParameterWidgetDeclaring(ParameterDescription param, Operation operation, DescriptionDictionary dictionary, int nivel) {
        StringBuilder str = new StringBuilder();

        String strNivel = "";
        for (int i = 0; i < nivel; i++) {
            strNivel = strNivel + '\t';
        }

        str.append(strNivel + getMethodParameterWidgetType(param, dictionary) + " " + getMethodParameterWidgetName(param, dictionary) + " = \n");
        str.append(strNivel + "	        (" + getMethodParameterWidgetType(param, dictionary) + ") ");
        str.append(strNivel + "	dialogView.findViewById(R.id." + getMethodParameterWidgetResourceName(param, operation, dictionary, nivel) +");\n");

        return str.toString();
    }

    public String getMethodParameterWidgetListening(ParameterDescription param, Operation operation, DescriptionDictionary dictionary, int nivel) {
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
                    str.append(strNivel + getMethodParameterWidgetName(param, dictionary) + ".setAdapter(dataAdapter);\n");
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

    public String getMethodParameterWidgetGettingValue(ParameterDescription param, Operation operation, DescriptionDictionary dictionary) {

        StringBuilder str = new StringBuilder();
        if(param.getType().equals("java.lang.Long") || param.getType().equals("long") ||
                param.getType().equals("java.lang.Integer") || param.getType().equals("int") ||
                param.getType().equals("java.lang.Float") || param.getType().equals("float") ||
                param.getType().equals("java.lang.Double") || param.getType().equals("double") ||
                param.getType().equals("java.lang.String") ||
                param.getType().equals("java.util.Date")) {

            str.append(getMethodParameterWidgetName(param, dictionary) + ".getText().toString()");

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
            str.append(getMethodParameterWidgetName(param, dictionary) + ".isChecked()");
        }
        else { //Enum ou Class
            if(dictionary.containsEnumWithKey(param.getType())) {
                EnumDescription e = dictionary.getEnum(param.getType());
                str.append(e.getSimpleName() + ".valueOf(");
                str.append(getMethodParameterWidgetName(param, dictionary) + ".getSelectedItem().toString()");
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
