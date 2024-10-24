package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.ArrayList;
import java.util.List;

// Проверка заполнения логина и пароля при регистрации и/или аутентификации пользователя
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030000 implements CommonFlk {
    private final String code = "10030000";
    private final String message = "При регистрации и/или аутентификации пользователя обязательные поля";
    private final List<String> attributesNames = List.of("login", "password");
    private final boolean isCritical = true;

    private String login;
    private String password;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {} and password: {}", login, password);

        List<String> tempAttributesNames = new ArrayList<>();
        if (login == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("login")).toList());
        }
        if (password == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("password")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for login: {} and password: {} finished with error", login, password);

            throw new CommonFlkException(
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }
        log.debug("Flk " + code + " for login: {} and password: {} finished successfully", login, password);
    }
}
