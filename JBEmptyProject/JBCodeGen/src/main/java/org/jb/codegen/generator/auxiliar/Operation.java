package org.jb.codegen.generator.auxiliar;

/**
 * Created by fabiano on 12/10/16.
 */

public enum Operation {
    INSERT {
        public String desc() {
            return "Insert";
        }
        public String _desc() {
            return "insert";
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
    DETAIL {
        public String desc() {
            return "Detail";
        }
        public String _desc() {
            return "detail";
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
    LIST {
        public String desc() {
            return "List";
        }
        public String _desc() {
            return "list";
        }
    },
    FIND_RESULT_LIST {
        public String desc() {
            return "FindResultList";
        }
        public String _desc() {
            return "find_result_list";
        }
    },
    SELECT_LIST {
        public String desc() {
            return "SelectList";
        }
        public String _desc() {
            return "select_list";
        }
    };

    public abstract String desc();
    public abstract String _desc();
}
