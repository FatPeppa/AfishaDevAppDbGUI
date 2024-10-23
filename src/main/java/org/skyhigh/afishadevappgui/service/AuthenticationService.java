package org.skyhigh.afishadevappgui.service;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public interface AuthenticationService {
    public DbUser authenticate(String username, String password) throws CommonFlkException;
    public DbUser register(String username, String password) throws CommonFlkException;
}
