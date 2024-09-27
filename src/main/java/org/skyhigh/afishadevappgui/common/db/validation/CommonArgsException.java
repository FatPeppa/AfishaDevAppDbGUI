package org.skyhigh.afishadevappgui.common.db.validation;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

public class CommonArgsException extends CommonFlkException {
    public CommonArgsException(String message) {
        super(message);
    }

    public CommonArgsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonArgsException(
            String entityName,
            String daoClassName,
            String daoMethodName,
            String code,
            String message,
            List<String> attributesNames,
            boolean isCritical
    ) {
        super(
                String.format(
                        "Flk %s exception occurred in class %s method %s for entity %s: %s; attributes: %s; criticality: %b",
                        code,
                        daoClassName,
                        daoMethodName,
                        entityName,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                )
        );
    }

    public CommonArgsException(
            String entityName,
            String daoClassName,
            String daoMethodName,
            String code,
            String message,
            List<String> attributesNames,
            boolean isCritical,
            Throwable cause
    ) {
        super(
                String.format(
                        "Flk %s exception occurred in class %s method %s for entity %s: %s; attributes: %s; criticality: %b",
                        code,
                        daoClassName,
                        daoMethodName,
                        entityName,
                        message,
                        String.join(", ", attributesNames),
                        isCritical
                ),
                cause
        );
    }
}
