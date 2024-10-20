package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

public class Flk10000015Validator {
    public static void validate(Secret secret) throws CommonFlkException {
        Flk10000015 flk10000015 = new Flk10000015(secret);
        flk10000015.validate();
    }
}
