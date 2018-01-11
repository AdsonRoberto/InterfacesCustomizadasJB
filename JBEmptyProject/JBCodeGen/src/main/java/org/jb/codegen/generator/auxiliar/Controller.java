package org.jb.codegen.generator.auxiliar;

/**
 * Created by fabiano on 12/10/16.
 */

public enum Controller {
    ACTIVITY {
        public String desc() {
            return "Activity";
        }
        public String _desc() {
            return "activity";
        }
    },
    FRAGMENT {
        public String desc() {
            return "Fragment";
        }
        public String _desc() {
            return "fragment";
        }
    };

    public abstract String desc();
    public abstract String _desc();
}
