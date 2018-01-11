package org.jb.codegen.generator.auxiliar;

/**
 * Created by fabiano on 13/10/16.
 */

public enum TypedElementAction {
    SELECT {
        public String desc() {
            return "Select";
        }
        public String _desc() {
            return "select";
        }
        public String action() {
            return "openSelectionObject";
        }
    },
    DETAIL {
        public String desc() {
            return "Detail";
        }
        public String _desc() {
            return "detail";
        }
        public String action() {
            return "openDetailObject";
        }
    },
    COLLECTION {
        public String desc() {
            return "Collection";
        }
        public String _desc() {
            return "collection";
        }
        public String action() {
            return "showCollection";
        }
    };

    public abstract String desc();
    public abstract String _desc();
    public abstract String action();
}
