package org.jb.common.dictionary.project;

import org.jb.common.config.enums.MappingType;
import org.jb.common.dictionary.RuntimeDictionary;
import org.jb.common.dictionary.project.enums.ProjectLayer;
import org.jb.common.singleton.SingletonSession;

/**
 * Created by fabiano on 16/10/17.
 */

public class ProjectMapping {
    public static MappingType getMappingType(ClassLoader classLoader, ProjectLayer layer) {
        SingletonSession session = SingletonSession.instance();
        RuntimeDictionary dictionary = session.getDictionary();
        dictionary.load(classLoader);

        return layer.getConfiguration(dictionary).getMappingType();
    }
}
