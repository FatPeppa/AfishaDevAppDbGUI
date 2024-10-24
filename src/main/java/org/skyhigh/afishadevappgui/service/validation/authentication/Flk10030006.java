package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка, что пароль содержит как цифры, так и буквы
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030006 implements CommonFlk {
    private final String code = "10030006";
    private final String message = "При регистрации пользователя пароль должен содержать как цифры, так и буквы";
    private final List<String> attributesNames = List.of("password");
    private final boolean isCritical = true;

    private String password;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for password: {}", password);

        if (password != null && (!password.matches(".*\\d.*") || !password.matches("[a-zA-Z]+"))) {
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
