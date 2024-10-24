package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка длины пароля при регистрации и/или аутентификации пользователя
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030004 implements CommonFlk {
    private final String code = "10030004";
    private final String message = "При регистрации и/или аутентификации пользователя допустима длина пароля от 8 до 20 символов";
    private final List<String> attributesNames = List.of("password");
    private final boolean isCritical = true;

    private String password;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for password: {}", password);

        if (password != null && (password.length() > 20 || password.length() < 8)) {
            log.debug("Flk " + code + " for password: {} finished with error", password);

            throw new CommonFlkException(
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for password: {}  finished successfully", password);
    }
}
