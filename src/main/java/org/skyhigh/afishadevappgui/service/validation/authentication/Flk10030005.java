package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.List;

// Проверка, что пароль не начинается с цифры
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030005 implements CommonFlk {
    private final String code = "10030005";
    private final String message = "При регистрации пользователя пароль не должен начинаться с цифры";
    private final List<String> attributesNames = List.of("password");
    private final boolean isCritical = true;

    private String password;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for password: {}", password);

        if (password != null) {
            try {
                Integer.parseInt(password.substring(0, 1));
            } catch (NumberFormatException e) {
                log.debug("Flk " + code + " for password: {}  finished successfully", password);
                return;
            }
            log.debug("Flk " + code + " for password: {} finished with error", password);

            throw new CommonFlkException(
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
        }

    }
}
