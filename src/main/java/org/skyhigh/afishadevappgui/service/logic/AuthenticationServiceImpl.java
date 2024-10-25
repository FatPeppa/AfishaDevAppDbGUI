package org.skyhigh.afishadevappgui.service.logic;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;
import org.skyhigh.afishadevappgui.service.validation.authentication.*;

@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final DbUserRepository dbUserRepository;

    @Override
    public DbUser authenticate(String login, String password) throws CommonFlkException {
        Flk10030000Validator.validate(login, password);
        Flk10030001Validator.validate(login);
        Flk10030004Validator.validate(password);
        DbUser foundDbuser = dbUserRepository.getDbUserByLogin(login);
        if (foundDbuser == null) return null;
        Flk10030008Validator.validate(login, password, foundDbuser);
        return foundDbuser;
    }

    @Override
    public DbUser register(String login, String password) throws CommonFlkException {
        Flk10030000Validator.validate(login, password);
        Flk10030001Validator.validate(login);
        Flk10030002Validator.validate(login);
        Flk10030003Validator.validate(login);
        Flk10030004Validator.validate(password);
        Flk10030005Validator.validate(password);
        Flk10030006Validator.validate(password);
        Flk10030007Validator.validate(login, dbUserRepository);
        String hashedPassword = BCrypt.withDefaults().hashToString(6, password.toCharArray());
        int userId = dbUserRepository.saveDbUser(
                new DbUser(
                        0,
                        null,
                        login,
                        hashedPassword
                )
        );
        return dbUserRepository.getDbUserById(userId);
    }
}
