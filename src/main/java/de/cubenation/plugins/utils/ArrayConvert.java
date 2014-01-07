package de.cubenation.plugins.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class ArrayConvert<T> {
    public final String[] toArray(T[] objArray) {
        ArrayList<T> arrayList = new ArrayList<T>();
        Collections.addAll(arrayList, objArray);
        return toArray(arrayList);
    }

    public final String[] toArray(Collection<T> objArray) {
        if (objArray == null) {
            return null;
        }

        Collection<String> collection = toCollection(objArray);

        return collection.toArray(new String[] {});
    };

    public final Collection<String> toCollection(T[] objArray) {
        ArrayList<T> arrayList = new ArrayList<T>();
        Collections.addAll(arrayList, objArray);
        return toCollection(arrayList);
    }

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
