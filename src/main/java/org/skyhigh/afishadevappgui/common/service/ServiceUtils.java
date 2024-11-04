package org.skyhigh.afishadevappgui.common.service;

import java.util.List;

public class ServiceUtils {
    public static int countNull(List<Object> nullableObjects) {
        return (int) nullableObjects.stream().filter(x -> {
            if (x == null) return true;
            return x instanceof String && ((String) x).isEmpty();
        }).count();
    }

    public static int countNotNull(List<Object> nullableObjects) {
        return (int) nullableObjects.stream().filter(x -> {
            if (x != null)
                return !(x instanceof String) || !((String) x).isEmpty();
            return false;
        }).count();
    }
}
