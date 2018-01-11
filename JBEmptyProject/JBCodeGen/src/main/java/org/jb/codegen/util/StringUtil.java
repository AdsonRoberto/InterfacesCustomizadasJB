package org.jb.codegen.util;

/**
 * Created by fabiano on 13/10/16.
 */

public class StringUtil {
    public static String upperOnlyFirst(String name) {
        name = name.toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String upperFirst(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String lowerFirst(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
