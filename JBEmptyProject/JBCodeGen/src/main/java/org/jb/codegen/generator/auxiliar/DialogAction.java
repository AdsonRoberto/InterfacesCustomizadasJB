package org.jb.codegen.generator.auxiliar;

/**
 * Created by fabiano on 12/10/16.
 */

public enum DialogAction {
    YES {
        public String desc() {
            return "Yes";
        }
        public String _desc() {
            return "yes";
        }
    },
    NO {
        public String desc() {
            return "No";
        }
        public String _desc() {
            return "no";
        }
    },
    CANCEL {
        public String desc() {
            return "Cancel";
        }
        public String _desc() {
            return "cancel";
        }
    },
    OK {
        public String desc() {
            return "Ok";
        }

        public String _desc() {
            return "ok";
        }
    };

    public abstract String desc();
    public abstract String _desc();
}
