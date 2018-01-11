package org.jb.common.dictionary.project.enums;

import org.jb.common.config.Configuration;
import org.jb.common.dictionary.RuntimeDictionary;

/**
 * Created by fabiano on 16/10/17.
 */

public enum ProjectLayer {
    CODEGEN {
        public Configuration getConfiguration(RuntimeDictionary dictionary) {
            return dictionary.getProjectConfiguration().getCodeGenConfiguration();
        }
    },
    PERSISTENCE {
        public Configuration getConfiguration(RuntimeDictionary dictionary) {
            return dictionary.getProjectConfiguration().getPersistenceConfiguration();
        }
    },
    STREAM {
        public Configuration getConfiguration(RuntimeDictionary dictionary) {
            return dictionary.getProjectConfiguration().getStreamConfiguration();
        }
    };

    public abstract Configuration getConfiguration(RuntimeDictionary dictionary);
}
