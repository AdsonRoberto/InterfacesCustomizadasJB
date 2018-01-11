package org.jb.common.dictionary.project;

import org.jb.persistence.config.PersistenceConfiguration;
import org.jb.stream.config.StreamConfiguration;
import org.jb.ui.config.CodeGenConfiguration;

/**
 * Created by fabiano on 11/07/17.
 */

public class ProjectConfiguration {
    CodeGenConfiguration codeGenConfiguration;
    PersistenceConfiguration persistenceConfiguration;
    StreamConfiguration streamConfiguration;

    public ProjectConfiguration() {
        super();
        codeGenConfiguration = new CodeGenConfiguration();
        persistenceConfiguration = new PersistenceConfiguration();
        streamConfiguration = new StreamConfiguration();
    }

    public CodeGenConfiguration getCodeGenConfiguration() {
        return codeGenConfiguration;
    }

    public void setCodeGenConfiguration(CodeGenConfiguration codeGenConfiguration) {
        this.codeGenConfiguration = codeGenConfiguration;
    }

    public PersistenceConfiguration getPersistenceConfiguration() {
        return persistenceConfiguration;
    }

    public void setPersistenceConfiguration(PersistenceConfiguration persistenceConfiguration) {
        this.persistenceConfiguration = persistenceConfiguration;
    }

    public StreamConfiguration getStreamConfiguration() {
        return streamConfiguration;
    }

    public void setStreamConfiguration(StreamConfiguration streamConfiguration) {
        this.streamConfiguration = streamConfiguration;
    }
}
