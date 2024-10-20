package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.io.File;
import java.util.UUID;

public class Flk10020001Validator {
    public static void validate(UUID deploymentId, File deploymentBuilt) throws CommonFlkException {
        Flk10020001 flk10020001 = new Flk10020001(deploymentBuilt, deploymentId);
        flk10020001.validate();
    }
}
