package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

public class Flk10000006Validator {
    public static void validate(DeploymentStatus deploymentStatus) throws CommonFlkException {
        Flk10000006 flk10000006 = new Flk10000006(deploymentStatus);
        flk10000006.validate();
    }
}
