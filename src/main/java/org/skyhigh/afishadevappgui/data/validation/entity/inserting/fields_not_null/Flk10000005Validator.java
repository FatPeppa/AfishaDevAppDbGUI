package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

public class Flk10000005Validator {
    public static void validate(Deployment deployment) throws CommonFlkException {
        Flk10000005 flk10000005 = new Flk10000005(deployment);
        flk10000005.validate();
    }
}
