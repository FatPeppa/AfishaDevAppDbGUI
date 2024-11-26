package org.skyhigh.afishadevappgui.service.validation.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

import java.util.ArrayList;
import java.util.List;

//Проверка, что роль пользователя при регистрации заполнена
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10030009 implements CommonFlk {
    private final String code = "10030009";
    private final String message = "При регистрации пользователя должна быть заполнена его роль";
    private final List<String> attributesNames = List.of("systemRole");
    private final boolean isCritical = true;

    private String login;
    private String password;
    private SystemRole systemRole;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for login: {} and password: {}", login, password);

        List<String> tempAttributesNames = new ArrayList<>();
        if (systemRole == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("login")).toList());
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
