package org.skyhigh.afishadevappgui.common.db.validation;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

public class CommonSystemException extends CommonFlkException {
    private static final String code = "99999999";
    private static final boolean isCritical = true;

    public CommonSystemException(
            String message,
            boolean onlyMessageProxy
    ) {
        super(
                onlyMessageProxy ? message : String.format(
                        "Flk %s exception occurred: %s; criticality: %b",
                        code,
                        message,
                        isCritical
                )
        );
    }

    public CommonSystemException(
            String message,
            Throwable cause,
            boolean onlyMessageAndCauseProxy
    ) {
        super(
                onlyMessageAndCauseProxy ? message : String.format(
                        "Flk %s exception occurred: %s; criticality: %b",
                        code,
                        message,
                        isCritical
                ),
                cause
        );
    }
}
