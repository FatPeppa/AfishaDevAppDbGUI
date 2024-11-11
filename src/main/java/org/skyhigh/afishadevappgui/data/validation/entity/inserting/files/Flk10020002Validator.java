package org.skyhigh.afishadevappgui.data.validation.entity.inserting.files;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10020002Validator {
    public static void validate(String filePath) throws CommonFlkException {
        Flk10020002 flk10020002 = new Flk10020002(filePath);
        flk10020002.validate();
    }
}
