package org.jb.stream.reader.runtime.configuration;

import org.jb.common.reader.runtime.configuration.XMLRuntimeConfigurarionReader;
import org.jb.stream.config.StreamConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimeStreamConfigurarionReader extends XMLRuntimeConfigurarionReader {
    public XMLRuntimeStreamConfigurarionReader() {
        super();
        configuration = new StreamConfiguration();
    }

    public XMLRuntimeStreamConfigurarionReader(Field field) {
        super(field);
        configuration = new StreamConfiguration();
    }

    public XMLRuntimeStreamConfigurarionReader(Method method) {
        super(method);
        configuration = new StreamConfiguration();
    }

    public XMLRuntimeStreamConfigurarionReader(Class<?> c) {
        super(c);
        configuration = new StreamConfiguration();
    }

    public XMLRuntimeStreamConfigurarionReader(ClassLoader classLoader) {
        super(classLoader);
        configuration = new StreamConfiguration();
    }
}
