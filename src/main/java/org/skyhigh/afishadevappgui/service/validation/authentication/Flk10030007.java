package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;

import java.util.List;

// Проверка, что пользователя с указанным логином нет в Системе
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030007 implements CommonFlk {
    private final String code = "10030007";
    private final String message = "Пользователь с указанным логином уже существует";
    private final List<String> attributesNames = List.of("login");
    private final boolean isCritical = true;

    private String login;
    private @NonNull DbUserRepository dbUserRepository;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {}", login);

        if (login != null) {
            DbUser foundDbUser = dbUserRepository.getDbUserByLogin(login);
            if (foundDbUser != null) {
                log.debug("Flk " + code + " for login: {} finished with error: found existing user with id: '{}'", login, foundDbUser.getUserId());

                throw new CommonFlkException(
                        code,
                        message,
                        attributesNames,
                        isCritical
                );
            }
        }

        log.debug("Flk " + code + " for login: {} finished successfully", login);
    }
}
