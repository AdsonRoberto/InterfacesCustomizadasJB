package org.jb.common.config;

import org.jb.common.config.enums.MappingType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by fabiano on 11/07/17.
 */

public abstract class Configuration {
    Map<String, String> properties;
    Set<String> mappings;

    public Configuration() {
        super();
        properties = new HashMap<>();
        mappings = new HashSet<>();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Set<String> getMappings() {
        return mappings;
    }

    public void setMappings(Set<String> mappings) {
        this.mappings = mappings;
    }

    /** Add Actions */

    public void addProperty(String name, String value) {
        properties.put(name, value);
    }

    public void addMapping(String value) {
        mappings.add(value);
    }

    /** Remove Actions */

    public void removeProperty(String name) {
        properties.remove(name);
    }

    public void removeMapping(String value) {
        mappings.remove(value);
    }

    /** Find Actions */

    public String getProperty(String name) {
        return properties.get(name);
    }

    public MappingType getMappingType() {
        if(getProperty("mapping-type") != null)
            return MappingType.valueOf(getProperty("mapping-type").toUpperCase());
        return MappingType.UNKNOWN;
    }

    /** Print **/

    public void print() {
        System.out.println("Properties");
        for(String key : properties.keySet()) {
            System.out.println("Key: " + key + " - " + "Value: " + properties.get(key));
        }
        System.out.println("Mappings");
        for(String value : mappings) {
            System.out.println("Value: " + value);
        }
    }
}
