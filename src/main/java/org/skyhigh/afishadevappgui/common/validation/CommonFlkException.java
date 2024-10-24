package org.skyhigh.afishadevappgui.common.validation;

import java.util.List;

public class CommonFlkException extends Exception {
    public CommonFlkException(String message) {
        super(message);
    }

    public CommonFlkException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonFlkException(String code, String message, List<String> attributesNames, boolean isCritical) {
        super(
                String.format(
                        "Flk %s exception occurred: %s; attributes: %s; criticality: %b",
                        code,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                )
        );
    }

    public CommonFlkException(String code, String message, List<String> attributesNames, boolean isCritical, Throwable cause) {
        super(
                String.format(
                        "Flk %s exception occurred: %s; attributes: %s; criticality: %b",
                        code,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                ),
                cause
        );
    }
}
