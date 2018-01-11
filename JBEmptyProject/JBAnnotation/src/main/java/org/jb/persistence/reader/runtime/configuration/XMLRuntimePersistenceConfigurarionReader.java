package org.jb.persistence.reader.runtime.configuration;

import org.jb.common.reader.runtime.configuration.XMLRuntimeConfigurarionReader;
import org.jb.persistence.config.PersistenceConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fabiano on 09/10/17.
 */

public class XMLRuntimePersistenceConfigurarionReader extends XMLRuntimeConfigurarionReader {
    public XMLRuntimePersistenceConfigurarionReader() {
        super();
        configuration = new PersistenceConfiguration();
    }

    public XMLRuntimePersistenceConfigurarionReader(Field field) {
        super(field);
        configuration = new PersistenceConfiguration();
    }

    public XMLRuntimePersistenceConfigurarionReader(Method method) {
        super(method);
        configuration = new PersistenceConfiguration();
    }

    public XMLRuntimePersistenceConfigurarionReader(Class<?> c) {
        super(c);
        configuration = new PersistenceConfiguration();
    }

    public XMLRuntimePersistenceConfigurarionReader(ClassLoader classLoader) {
        super(classLoader);
        configuration = new PersistenceConfiguration();
    }

}
