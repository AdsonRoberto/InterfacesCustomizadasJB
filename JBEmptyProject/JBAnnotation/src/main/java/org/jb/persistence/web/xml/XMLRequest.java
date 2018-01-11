package org.jb.persistence.web.xml;

import org.jb.persistence.web.annotation.Request;
import org.jb.persistence.web.annotation.enums.ContentType;
import org.jb.persistence.web.annotation.enums.HttpMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fabiano on 10/10/17.
 */

public class XMLRequest {
    String path = "";
    HttpMethod method = HttpMethod.GET;
    ContentType consumeType = ContentType.TEXT_PLAIN;
    ContentType produceType = ContentType.TEXT_PLAIN;
    XMLPathParam[] pathParameters = {};
    XMLQueryParam[] queryParameters = {};

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public ContentType getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(ContentType consumeType) {
        this.consumeType = consumeType;
    }

    public ContentType getProduceType() {
        return produceType;
    }

    public void setProduceType(ContentType produceType) {
        this.produceType = produceType;
    }

    public XMLPathParam[] getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(XMLPathParam[] pathParameters) {
        this.pathParameters = pathParameters;
    }

    public XMLQueryParam[] getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(XMLQueryParam[] queryParameters) {
        this.queryParameters = queryParameters;
    }

    public void addPathParameter(XMLPathParam pathParameter) {
        List<XMLPathParam> list = new ArrayList<XMLPathParam>();
        list.addAll(Arrays.asList(pathParameters));
        list.add(pathParameter);
        pathParameters = list.toArray(new XMLPathParam[list.size()]);
    }

    public void addQueryParameter(XMLQueryParam queryParameter) {
        List<XMLQueryParam> list = new ArrayList<XMLQueryParam>();
        list.addAll(Arrays.asList(queryParameters));
        list.add(queryParameter);
        queryParameters = list.toArray(new XMLQueryParam[list.size()]);
    }

    public static XMLRequest adapt(Request request) {
        XMLRequest xmlRequest = new XMLRequest();
        xmlRequest.setPath(request.path());
        xmlRequest.setMethod(request.method());
        xmlRequest.setConsumeType(request.consumeType());
        xmlRequest.setProduceType(request.produceType());

        for(int i = 0; i < request.pathParameters().length; i++) {
            xmlRequest.addPathParameter(XMLPathParam.adapt(request.pathParameters()[i]));
        }

        for(int i = 0; i < request.queryParameters().length; i++) {
            xmlRequest.addQueryParameter(XMLQueryParam.adapt(request.queryParameters()[i]));
        }

        return xmlRequest;
    }

    @Override
    public String toString() {
        return "XMLRequest{" +
                "path='" + path + '\'' +
                ", method=" + method +
                ", consumeType=" + consumeType +
                ", produceType=" + produceType +
                ", pathParameters=" + Arrays.toString(pathParameters) +
                ", queryParameters=" + Arrays.toString(queryParameters) +
                '}';
    }
}
