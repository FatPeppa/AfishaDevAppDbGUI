package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

public class Flk10000002Validator {
    public static void validate(AccessedRole accessedRole) throws CommonFlkException {
        Flk10000002 flk10000002 = new Flk10000002(accessedRole);
        flk10000002.validate();
    }
}
