package org.skyhigh.afishadevappgui.service;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public DbUser authenticate(String username, String password) throws CommonFlkException {
        return null;
    }

    @Override
    public DbUser register(String username, String password) throws CommonFlkException {
        return null;
    }
}
