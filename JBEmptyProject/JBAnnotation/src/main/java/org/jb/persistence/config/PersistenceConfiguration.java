package org.jb.persistence.config;

import org.jb.common.config.Configuration;
import org.jb.common.config.enums.PersistenceType;

/**
 * Created by fabiano on 11/07/17.
 */

public class PersistenceConfiguration extends Configuration {
    public PersistenceType getPersistenceType() {
        if(getProperty("persistence-type") != null)
            return PersistenceType.valueOf(getProperty("persistence-type").toUpperCase());
        return PersistenceType.UNKNOWN;
    }
}
