package org.skyhigh.afishadevappgui.common.controller;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Обработчик ошибок ФЛК. Предназначен для выполнения методов, которые могут вернуть ошибку ФЛК, и
 *      формирования сообщения с ошибкой
 */
public class FlkExceptionHandlerController {
    public class MethodResult {
        public Object singleResult;
        public Object[] multiResult;
    }

    public static MethodResult executeMethod(Method method){//, Object[] args) {
        if (method.getReturnType().equals(Void.TYPE)) {
            int a = 0;
        }
        if (method.getReturnType().equals(List.class)) {
            int b = 0;
        }
        return null;
    }
}
