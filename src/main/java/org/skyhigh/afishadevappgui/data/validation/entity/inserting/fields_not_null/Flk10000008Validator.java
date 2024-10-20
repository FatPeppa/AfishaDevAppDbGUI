package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

public class Flk10000008Validator {
    public static void validate(ProjectAuthor projectAuthor) throws CommonFlkException {
        Flk10000008 flk10000008 = new Flk10000008(projectAuthor);
        flk10000008.validate();
    }
}
