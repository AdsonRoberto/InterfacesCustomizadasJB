package org.jb.persistence.web.xml;

import org.jb.persistence.web.annotation.QueryParam;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLQueryParam {
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

    public static XMLQueryParam adapt(QueryParam queryParam) {
        XMLQueryParam xmlQueryParam = new XMLQueryParam();
        xmlQueryParam.setParam(queryParam.param());
        xmlQueryParam.setJbTextValue(queryParam.jbTextValue());
        return xmlQueryParam;
    }

    @Override
    public String toString() {
        return "XMLQueryParam{" +
                "param='" + param + '\'' +
                ", jbTextValue='" + jbTextValue + '\'' +
                '}';
    }
}
