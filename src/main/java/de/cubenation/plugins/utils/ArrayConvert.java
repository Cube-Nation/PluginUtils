package de.cubenation.plugins.utils;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ArrayConvert<T> {
    public final String[] toArray(Collection<T> objArray) {
        if (objArray == null) {
            return null;
        }

        Collection<String> collection = toCollection(objArray);

        return collection.toArray(new String[] {});
    };

    public final Collection<String> toCollection(Collection<T> objArray) {
        if (objArray == null) {
            return null;
        }

        ArrayList<String> newList = new ArrayList<String>();

        for (T obj : objArray) {
            newList.add(convertToString(obj));
        }

        return newList;
    };

    protected abstract String convertToString(T obj);
}
