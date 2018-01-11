package org.jb.codegen.util;

/**
 * Created by fabiano on 12/07/17.
 */

public class ArrayUtil {
    public static boolean contains(Object[] array, Object item) {
        for(Object object : array) {
            if(object.equals(item))
                return true;
        }
        return false;
    }
}
