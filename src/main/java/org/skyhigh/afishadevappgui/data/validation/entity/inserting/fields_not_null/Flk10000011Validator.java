package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

public class Flk10000011Validator {
    public static void validate(RequirementType requirementType) throws CommonFlkException {
        Flk10000011 flk10000011 = new Flk10000011(requirementType);
        flk10000011.validate();
    }
}
