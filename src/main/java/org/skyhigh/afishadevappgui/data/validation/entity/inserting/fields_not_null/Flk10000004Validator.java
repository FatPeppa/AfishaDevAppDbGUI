package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

public class Flk10000004Validator {
    public static void validate(CodeFile codeFile) throws CommonFlkException {
        Flk10000004 flk10000004 = new Flk10000004(codeFile);
        flk10000004.validate();
    }
}
