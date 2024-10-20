package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

public class Flk10000000Validator {
    public static void validate(Project project) throws CommonFlkException {
        Flk10000000 flk10000000 = new Flk10000000(project);
        flk10000000.validate();
    }
}
