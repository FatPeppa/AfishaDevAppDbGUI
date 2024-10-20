package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

public class Flk10000001Validator {
    public static void validate(Requirement requirement) throws CommonFlkException {
        Flk10000001 flk10000001 = new Flk10000001(requirement);
        flk10000001.validate();
    }
}
