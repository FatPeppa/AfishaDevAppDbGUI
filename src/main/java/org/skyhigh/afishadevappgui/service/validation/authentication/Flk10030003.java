package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка, что логин содержит как цифры, так и буквы
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030003 implements CommonFlk {
    private final String code = "10030003";
    private final String message = "При регистрации пользователя логин должен содержать как цифры, так и буквы";
    private final List<String> attributesNames = List.of("login");
    private final boolean isCritical = true;

    private String login;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {}", login);

        if (login != null && !login.matches("^[A-Za-z0-9]*$")) {
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
