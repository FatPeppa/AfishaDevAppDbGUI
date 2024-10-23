package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public class Flk10000015Validator {
    public static void validate(DbUser dbUser) throws CommonFlkException {
        Flk10000015 flk10000015 = new Flk10000015(dbUser);
        flk10000015.validate();
    }
}
