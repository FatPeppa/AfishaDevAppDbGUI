package org.skyhigh.afishadevappgui.common.db.validation;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

public class CommonEntityException extends CommonFlkException {
    public CommonEntityException(String message) {
        super(message);
    }

    public CommonEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonEntityException(
            String entityName,
            String code,
            String message,
            List<String> attributesNames,
            boolean isCritical
    ) {
        super(
                String.format(
                        "entityName: %s; Flk %s exception occurred: %s; attributes: %s; criticality: %b",
                        entityName,
                        code,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                )
        );
    }

    public CommonEntityException(
            String entityName,
            String code,
            String message,
            List<String> attributesNames,
            boolean isCritical,
            Throwable cause
    ) {
        super(
                String.format(
                        "entityName: %s; Flk %s exception occurred: %s; attributes: %s; criticality: %b",
                        entityName,
                        code,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                ),
                cause
        );
    }
}
