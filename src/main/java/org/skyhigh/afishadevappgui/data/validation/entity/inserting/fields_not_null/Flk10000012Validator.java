package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

public class Flk10000012Validator {
    public static void validate(Secret secret) throws CommonFlkException {
        Flk10000012 flk10000012 = new Flk10000012(secret);
        flk10000012.validate();
    }
}
