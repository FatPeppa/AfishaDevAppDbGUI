package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

public class Flk10000003Validator {
    public static void validate(Author author) throws CommonFlkException {
        Flk10000003 flk10000003 = new Flk10000003(author);
        flk10000003.validate();
    }
}
