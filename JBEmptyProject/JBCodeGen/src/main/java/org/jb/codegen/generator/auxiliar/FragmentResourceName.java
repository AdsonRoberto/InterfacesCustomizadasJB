package org.jb.codegen.generator.auxiliar;

import org.jb.codegen.dictionary.domain.ClassDescription;
import org.jb.codegen.dictionary.data.DescriptionDictionary;
import org.jb.codegen.util.StringUtil;

/**
 * Created by fabiano on 13/10/16.
 */

public class FragmentResourceName extends ResourceName {
    public FragmentResourceName() {
        super();
        setController(Controller.FRAGMENT);
    }

    public String getFragmentListViewResourceName(ClassDescription c, Operation operation, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(c, operation, dictionary)) + "ListView";
    }

    /** XML Row Layout JBEntity*/

    public String getFragmentListRowLayoutEntityResourceName(ClassDescription c, DescriptionDictionary dictionary) {
        return getClassLayoutResourceName(c, Operation.LIST, dictionary) + "_" + "row_layout";
    }

    public String getFragmentListRowLayoutEntityLinearLayoutResourceName(ClassDescription c, DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(c, Operation.LIST, dictionary)) + "RowLayout" + "LinearLayout";
    }

    public String getFragmentListRowLayoutEntityWidgetResourceName(ClassDescription c, DescriptionDictionary dictionary, int index) {
        return StringUtil.upperFirst(getClassName(c, Operation.LIST, dictionary))  + "RowLayout" + "TextView" + index;
    }

    /** XML Row Layout Project*/

    public String getFragmentListRowLayoutProjectResourceName(DescriptionDictionary dictionary) {
        return getClassLayoutResourceName(null, Operation.LIST, dictionary) + "_" + "row_layout";
    }

    public String getFragmentListRowLayoutProjectLinearLayoutResourceName(DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(null, Operation.LIST, dictionary)) + "RowLayout" + "LinearLayout";
    }

    public String getFragmentListRowLayoutProjectWidgetIconResourceName(DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(null, Operation.LIST, dictionary)) + "RowLayout" + "ImageView" + "Icon";
    }

    public String getFragmentListRowLayoutProjectWidgetTitleResourceName(DescriptionDictionary dictionary) {
        return StringUtil.upperFirst(getClassName(null, Operation.LIST, dictionary)) + "RowLayout" + "TextView" + "Title";
    }
}
