package org.jb.codegen.generator.auxiliar;

/**
 * Created by fabiano on 12/10/16.
 */

public enum MenuAction {
    NEW {
        public String desc() {
            return "New";
        }
        public String _desc() {
            return "new";
        }
    },
    DETAIL {
        public String desc() {
            return "Detail";
        }
        public String _desc() {
            return "detail";
        }
    },
    EDIT {
        public String desc() {
            return "Edit";
        }
        public String _desc() {
            return "edit";
        }
    },
    DELETE {
        public String desc() {
            return "Delete";
        }
        public String _desc() {
            return "delete";
        }
    },
    FIND {
        public String desc() {
            return "Find";
        }
        public String _desc() {
            return "find";
        }
    },
    SELECT {
        public String desc() {
            return "Select";
        }
        public String _desc() {
            return "select";
        }
    },
    SAVE {
        public String desc() {
            return "Save";
        }
        public String _desc() {
            return "save";
        }
    },
    ADD {
        public String desc() {
            return "Add";
        }
        public String _desc() {
            return "add";
        }
    };

    public abstract String desc();
    public abstract String _desc();
}
