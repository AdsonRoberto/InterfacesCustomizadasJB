package org.jb.persistence.web.xml;

import org.jb.persistence.web.annotation.PathParam;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLPathParam {
    String param = "";
    String jbTextValue = "";

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getJbTextValue() {
        return jbTextValue;
    }

    public void setJbTextValue(String jbTextValue) {
        this.jbTextValue = jbTextValue;
    }

    public static XMLPathParam adapt(PathParam pathParam) {
        XMLPathParam xmlPathParam = new XMLPathParam();
        xmlPathParam.setParam(pathParam.param());
        xmlPathParam.setJbTextValue(pathParam.jbTextValue());
        return xmlPathParam;
    }

    @Override
    public String toString() {
        return "XMLPathParam{" +
                "param='" + param + '\'' +
                ", jbTextValue='" + jbTextValue + '\'' +
                '}';
    }
}
