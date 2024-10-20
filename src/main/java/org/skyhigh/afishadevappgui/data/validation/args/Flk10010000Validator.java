package org.skyhigh.afishadevappgui.data.validation.args;

import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10010000Validator {
    public static void validate(
            String entityClassName,
            String daoClassName,
            String daoMethodName,
            SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException {
        Flk10010000 flk10010000 = new Flk10010000(
                entityClassName,
                daoClassName,
                daoMethodName,
                sortDirection,
                sortBy
        );
        flk10010000.validate();
    }
}
