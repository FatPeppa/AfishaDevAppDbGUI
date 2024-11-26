package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

public class Flk10030009Validator {
    public static void validate(String login, String password, SystemRole systemRole) throws CommonFlkException {
        Flk10030009 flk10030009 = new Flk10030009(login, password, systemRole);
        flk10030009.validate();
    }
}
