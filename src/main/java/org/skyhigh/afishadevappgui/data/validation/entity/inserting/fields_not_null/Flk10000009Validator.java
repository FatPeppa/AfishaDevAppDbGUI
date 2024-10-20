package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

public class Flk10000009Validator {
    public static void validate(Requirement requirement) throws CommonFlkException {
        Flk10000009 flk10000009 = new Flk10000009(requirement);
        flk10000009.validate();
    }
}
