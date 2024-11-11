package org.skyhigh.afishadevappgui.common.validation;

import java.util.List;

public class CommonUIException extends CommonFlkException {
    public CommonUIException(String message) {
        super(message);
    }

    public CommonUIException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonUIException(String code, String message, List<String> attributesNames) {
        super(String.format("Ошибка %s: %s; атрибуты, вызвавшие ошибку: %s", code, message, String.join(", ", attributesNames)));
        //super(code, message, attributesNames, isCritical);
    }

    public CommonUIException(String code, String message, List<String> attributesNames, Throwable cause) {
        super(String.format("Ошибка %s: %s; атрибуты, вызвавшие ошибку: %s", code, message, String.join(", ", attributesNames)), cause);
    }

    public CommonUIException(String code, String message) {
        super(String.format("Ошибка %s: %s", code, message));
        //super(code, message, attributesNames, isCritical);
    }

    public CommonUIException(String code, String message, Throwable cause) {
        super(String.format("Ошибка %s: %s", code, message), cause);
    }
}
