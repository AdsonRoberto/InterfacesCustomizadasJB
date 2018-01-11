package org.jb.common.dictionary;

import org.jb.common.dictionary.elements.RuntimeClassDescription;
import org.jb.common.dictionary.elements.RuntimeEnumDescription;
import org.jb.common.dictionary.printer.XMLRuntimeDescriptionPrinter;
import org.jb.common.dictionary.project.ProjectConfiguration;
import org.jb.persistence.config.PersistenceConfiguration;
import org.jb.persistence.reader.runtime.configuration.XMLRuntimePersistenceConfigurarionReader;
import org.jb.persistence.reader.runtime.mapping.XMLRuntimePersistenceMappingReader;
import org.jb.stream.config.StreamConfiguration;
import org.jb.stream.reader.runtime.configuration.XMLRuntimeStreamConfigurarionReader;
import org.jb.stream.reader.runtime.mapping.XMLRuntimeStreamMappingReader;
import org.jb.ui.config.CodeGenConfiguration;
import org.jb.ui.reader.runtime.configuration.XMLRuntimeCodeGenConfigurarionReader;
import org.jb.ui.reader.runtime.mapping.XMLRuntimeCodeGenMappingReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;

/**
 * Created by fabiano on 15/10/17.
 */

public class RuntimeDictionary {
    ProjectConfiguration projectConfiguration = null;
    ClassLoader classLoader;

    HashMap<String,RuntimeClassDescription> classes = null;
    HashMap<String,RuntimeEnumDescription> enums = null;

    public RuntimeDictionary() {
        super();
        projectConfiguration = new ProjectConfiguration();
        classLoader = getClass().getClassLoader();

        classes = new HashMap<String,RuntimeClassDescription>();
        enums = new HashMap<String,RuntimeEnumDescription>();
    }

    public Integer getTotalClasses() {
        if(classes != null)
            return classes.size();
        return 0;
    }

    public Integer getTotalEnums() {
        if(enums != null)
            return enums.size();
        return 0;
    }

    private boolean isEmpty() {
        if((classes != null && classes.size() > 0)
            || (enums != null && enums.size() > 0)) {
            return false;
        }
        return true;
    }

    public ProjectConfiguration getProjectConfiguration() {
        return projectConfiguration;
    }

    public void setProjectConfiguration(ProjectConfiguration projectConfiguration) {
        this.projectConfiguration = projectConfiguration;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public HashMap<String, RuntimeClassDescription> getClasses() {
        return classes;
    }

    public HashMap<String, RuntimeEnumDescription> getEnums() {
        return enums;
    }

    public boolean containsClassWithKey(String key) {
        return classes.containsKey(key);
    }

    public boolean containsEnumWithKey(String key) {
        return enums.containsKey(key);
    }

    public RuntimeClassDescription getClass(String key) {
        return classes.get(key);
    }

    public RuntimeEnumDescription getEnum(String key) {
        return enums.get(key);
    }

    public void putClass(RuntimeClassDescription c) {
        if(!containsClassWithKey(c.getCanonicalName())) {
            classes.put(c.getCanonicalName(), c);
            c.setDictionary(this);
        }
    }

    public void putEnum(RuntimeEnumDescription e) {
        if(!containsEnumWithKey(e.getCanonicalName())) {
            enums.put(e.getCanonicalName(), e);
            e.setDictionary(this);
        }
    }

    public void putAllClasses(Collection<RuntimeClassDescription> collection) {
        Iterator<RuntimeClassDescription> it = collection.iterator();
        while(it.hasNext()) {
            putClass(it.next());
        }
    }

    public void putAllEnums(Collection<RuntimeEnumDescription> collection) {
        Iterator<RuntimeEnumDescription> it = collection.iterator();
        while(it.hasNext()) {
            putEnum(it.next());
        }
    }

    public Collection<RuntimeClassDescription> getClassValues() {
        return classes.values();
    }

    public Collection<RuntimeEnumDescription> getEnumValues() {
        return enums.values();
    }

    public List<RuntimeClassDescription> getSubClasses(RuntimeClassDescription c) {
        List<RuntimeClassDescription> list = new ArrayList<RuntimeClassDescription>();
        for (RuntimeClassDescription cd : classes.values()) {
            if(cd.getSuperClass().equals(c.getCanonicalName())) {
                list.add(cd);
            }
        }
        return list;
    }

    public void load(ClassLoader classLoader) {
        if(!isEmpty())
            return;

        this.classLoader = classLoader;

        XMLRuntimeCodeGenConfigurarionReader readerCodeGen = new XMLRuntimeCodeGenConfigurarionReader(classLoader);
        XMLRuntimePersistenceConfigurarionReader readerPersistence = new XMLRuntimePersistenceConfigurarionReader(classLoader);
        XMLRuntimeStreamConfigurarionReader readerStream = new XMLRuntimeStreamConfigurarionReader(classLoader);

        readerCodeGen.parse("jb-codegen-configuration.xml");
        readerPersistence.parse("jb-persistence-configuration.xml");
        readerStream.parse("jb-stream-configuration.xml");

        readerCodeGen.getConfiguration().print();
        readerPersistence.getConfiguration().print();
        readerStream.getConfiguration().print();

        projectConfiguration.setCodeGenConfiguration((CodeGenConfiguration) readerCodeGen.getConfiguration());
        projectConfiguration.setPersistenceConfiguration((PersistenceConfiguration) readerPersistence.getConfiguration());
        projectConfiguration.setStreamConfiguration((StreamConfiguration) readerStream.getConfiguration());

        XMLRuntimeCodeGenMappingReader mappingCodeGen = new XMLRuntimeCodeGenMappingReader(this);
        XMLRuntimePersistenceMappingReader mappingPersistence = new XMLRuntimePersistenceMappingReader(this);
        XMLRuntimeStreamMappingReader mappingStream = new XMLRuntimeStreamMappingReader(this);

        mappingCodeGen.setClassLoader(classLoader);
        mappingPersistence.setClassLoader(classLoader);
        mappingStream.setClassLoader(classLoader);

        mappingCodeGen.parse();
        mappingPersistence.parse();
        mappingStream.parse();

        System.out.println("Classes: " + getTotalClasses() + " - Enums: " + getTotalEnums());
        XMLRuntimeDescriptionPrinter printer = new XMLRuntimeDescriptionPrinter(this);
        printer.print();
    }
}
