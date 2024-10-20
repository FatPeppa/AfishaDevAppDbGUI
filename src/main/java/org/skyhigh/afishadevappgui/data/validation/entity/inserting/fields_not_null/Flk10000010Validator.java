package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

public class Flk10000010Validator {
    public static void validate(RequirementAuthor requirementAuthor) throws CommonFlkException {
        Flk10000010 flk10000010 = new Flk10000010(requirementAuthor);
        flk10000010.validate();
    }
}
