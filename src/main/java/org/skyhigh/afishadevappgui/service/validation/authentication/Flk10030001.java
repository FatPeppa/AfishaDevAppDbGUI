package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка длины логина при регистрации и/или аутентификации пользователя
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030001 implements CommonFlk {
    private final String code = "10030001";
    private final String message = "При регистрации и/или аутентификации пользователя допустима длина логина от 8 до 20 символов";
    private final List<String> attributesNames = List.of("login");
    private final boolean isCritical = true;

    private String login;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {}", login);

        if (login != null && (login.length() > 20 || login.length() < 8)) {
            log.debug("Flk " + code + " for login: {} finished with error", login);

            throw new CommonFlkException(
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for login: {}  finished successfully", login);
    }
}
