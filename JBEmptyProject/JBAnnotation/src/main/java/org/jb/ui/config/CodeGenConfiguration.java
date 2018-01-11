package org.jb.ui.config;

import org.jb.common.config.Configuration;

/**
 * Created by fabiano on 11/07/17.
 */

public class CodeGenConfiguration extends Configuration {
    public boolean isCodeGenerationEnabled() {
        if(getProperty("code-generation-enabled") != null) {
            return Boolean.valueOf(getProperty("code-generation-enabled"))
                    .booleanValue();
        }
        return false;
    }
}
