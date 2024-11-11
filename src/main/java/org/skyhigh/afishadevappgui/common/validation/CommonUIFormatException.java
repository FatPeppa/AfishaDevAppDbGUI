package org.skyhigh.afishadevappgui.common.validation;

import java.util.List;

public class CommonUIFormatException extends CommonFlkException {
    public CommonUIFormatException(String message) {
        super(message);
    }

    public CommonUIFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonUIFormatException(String code, String message, List<String> attributesNames) {
        super(String.format("Ошибка %s: %s; атрибуты, вызвавшие ошибку: %s", code, message, String.join(", ", attributesNames)));
        //super(code, message, attributesNames, isCritical);
    }

    public CommonUIFormatException(String code, String message, List<String> attributesNames, Throwable cause) {
        super(String.format("Ошибка %s: %s; атрибуты, вызвавшие ошибку: %s", code, message, String.join(", ", attributesNames)), cause);
    }

    public CommonUIFormatException(String code, String message) {
        super(String.format("Ошибка %s: %s", code, message));
        //super(code, message, attributesNames, isCritical);
    }

    public CommonUIFormatException(String code, String message, Throwable cause) {
        super(String.format("Ошибка %s: %s", code, message), cause);
    }
}
