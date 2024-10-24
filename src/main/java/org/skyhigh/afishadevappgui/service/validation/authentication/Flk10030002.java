package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка, что логин не начинается с цифры
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030002 implements CommonFlk {
    private final String code = "10030002";
    private final String message = "При регистрации пользователя логин не должен начинаться с цифры";
    private final List<String> attributesNames = List.of("login");
    private final boolean isCritical = true;

    private String login;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {}", login);

        if (login != null) {
            try {
                Integer.parseInt(login.substring(0, 1));
            } catch (NumberFormatException e) {
                log.debug("Flk " + code + " for login: {} finished with error", login);

                throw new CommonFlkException(
                        code,
                        message,
                        attributesNames,
                        isCritical
                );
            }
        }

        log.debug("Flk " + code + " for login: {}  finished successfully", login);
    }
}
