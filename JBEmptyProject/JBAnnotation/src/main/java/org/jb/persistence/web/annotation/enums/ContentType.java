package org.jb.persistence.web.annotation.enums;

/**
 * Created by fabiano on 05/04/17.
 */

public enum ContentType {
    TEXT_PLAIN,
    TEXT_XML,
    TEXT_JSON,
    APPLICATION_XML,
    APPLICATION_JSON;

    public String description() {
        return name().toLowerCase().replaceAll("_", "/");
    }
}
