package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

public class Flk10000007Validator {
    public static void validate(Project project) throws CommonFlkException {
        Flk10000007 flk10000007 = new Flk10000007(project);
        flk10000007.validate();
    }
}
