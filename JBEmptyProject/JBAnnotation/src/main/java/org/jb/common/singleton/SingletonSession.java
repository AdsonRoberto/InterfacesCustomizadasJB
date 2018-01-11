package org.jb.common.singleton;

import org.jb.common.dictionary.RuntimeDictionary;

/**
 * Created by fabiano on 15/10/17.
 */

public class SingletonSession {
    private static SingletonSession session = null;
    RuntimeDictionary dictionary = null;

    private SingletonSession() {
        dictionary = new RuntimeDictionary();
    }

    public static SingletonSession instance() {
        if(session == null) {
            session = new SingletonSession();
        }
        return session;
    }

    public RuntimeDictionary getDictionary() {
        return dictionary;
    }
}
