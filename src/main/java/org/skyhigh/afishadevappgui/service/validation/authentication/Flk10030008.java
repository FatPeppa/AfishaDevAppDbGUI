package org.skyhigh.afishadevappgui.service.validation.authentication;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.util.List;

// Проверка, что пароль найденного пользователя совпадает с введенным при аутентификации
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030008 implements CommonFlk {
    private final String code = "10030008";
    private final String message = "Указан некорректный логин/пароль";
    private final List<String> attributesNames = List.of("login", "password");
    private final boolean isCritical = true;

    private String login;
    private String password;
    private DbUser foundDbUser;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: '{}' and password: '{}'", login, password);

        if (password != null && foundDbUser != null) {
            BCrypt.Result hashVerificationResult = BCrypt.verifyer().verify(password.toCharArray(), foundDbUser.getUserPass());
            if (!hashVerificationResult.verified) {
                log.debug("Flk " + code + " for login: '{}' and password: '{}' finished with error: existing user's (id: '{}') password does not match the input", login, password, foundDbUser.getUserId());

                throw new CommonFlkException(
                        code,
                        message,
                        attributesNames,
                        isCritical
                );
            }
        }

        log.debug("Flk " + code + " for login: '{}' and password: '{}' finished successfully", login, password);
    }
}
