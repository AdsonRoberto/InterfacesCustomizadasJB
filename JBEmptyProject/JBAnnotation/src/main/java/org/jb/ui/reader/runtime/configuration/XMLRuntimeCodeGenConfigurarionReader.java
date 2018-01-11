package org.jb.ui.reader.runtime.configuration;

import org.jb.common.config.Configuration;
import org.jb.common.reader.runtime.configuration.XMLRuntimeConfigurarionReader;
import org.jb.ui.config.CodeGenConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeCodeGenConfigurarionReader extends XMLRuntimeConfigurarionReader {
    public XMLRuntimeCodeGenConfigurarionReader() {
        super();
        configuration = new CodeGenConfiguration();
    }

    public XMLRuntimeCodeGenConfigurarionReader(Field field) {
        super(field);
        configuration = new CodeGenConfiguration();
    }

    public XMLRuntimeCodeGenConfigurarionReader(Method method) {
        super(method);
        configuration = new CodeGenConfiguration();
    }

    public XMLRuntimeCodeGenConfigurarionReader(Class<?> c) {
        super(c);
        configuration = new CodeGenConfiguration();
    }

    public XMLRuntimeCodeGenConfigurarionReader(ClassLoader classLoader) {
        super(classLoader);
        configuration = new CodeGenConfiguration();
    }
}
