package org.skyhigh.afishadevappgui.service.logic.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

public interface AuthenticationService {

    /**
     *  Метод аутентификации пользователя в Системе. Выполняет проверки введенных логина и пароля пользователя.
     *      В случае успешного прохождения указанных проверок, приступает к поиску записи пользователя по логину. Затем, если
     *      запись была найдена, осуществляет проверку совпадения хэшей паролей посредством алгоритма Bcrypt.
     *      Если запись пользователя не была найдена, то выдает соответствующую ошибку.
     *      Если все проверки пройдены успешно, выдает запись найденного пользователя как результат выполнения
     * @param login Логин пользователя для аутентификации в Системе (длина: от 8 до 20 символов)
     * @param password Пароль пользователя для аутентификации в Системе (длина: от 8 до 20 символов)
     * @return Запись аутентифицированного пользователя или null
     * @throws CommonFlkException Ошибки при проверках ФЛК
     */
    DbUser authenticate(String login, String password) throws CommonFlkException;

    /**
     * Метод регистрации пользователя в Системе. Выполняет проверки целевых логина и пароля пользователя, а
     *      также наличие пользователя с указанным логином в Системе. В случае успешного прохождения всех проверок
     *      создает запись пользователя, связанную с ней запись автора, и возвращает первую из указанных записей (в случае, если создать запись не получилось, однако ошибок получено не было, метод возвращает null).
     *      При этом, введенный пользователем пароль хэшируется посредством алгоритма BCrypt
     * @param login Целевой логин пользователя (длина: от 8 до 20 символов; не должен начинаться с цифры; должен содержать как буквы, так и цифры; не может быть null)
     * @param password Пароль пользователя (длина: от 8 до 20 символов; не должен начинаться с цифры; должен содержать как буквы, так и цифры; не может быть null)
     * @param systemRole Роль пользователя (не может быть null)
     * @return Созданная запись пользователя (объект DbUser) или null
     * @throws CommonFlkException Ошибки при проверках ФЛК
     */
    DbUser register(String login, String password, SystemRole systemRole) throws CommonFlkException;
}
