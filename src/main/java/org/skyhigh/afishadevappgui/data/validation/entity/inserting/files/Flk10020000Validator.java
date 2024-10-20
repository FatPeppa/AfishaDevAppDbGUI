package org.skyhigh.afishadevappgui.data.validation.entity.inserting.files;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

public class Flk10020000Validator {
    public static void validate(Deployment deployment) throws CommonFlkException {
        Flk10020000 flk10020000 = new Flk10020000(deployment);
        flk10020000.validate();
    }
}
