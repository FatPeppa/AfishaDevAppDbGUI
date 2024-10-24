package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.util.List;
import java.util.UUID;

public interface DbUserRepository {
    /**
     * Метод сохранения пользователя DbUser
     * @param dbUser Сохраняемая сущность DbUser
     * @return Уникальный идентификатор, присвоенный созданной записи
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    int saveDbUser(@NonNull DbUser dbUser) throws CommonFlkException;

    /**
     * Метод обновления пароля пользователя
     * @param userId Идентификатор пользователя
     * @param pass Новый пароль пользователя
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateDbUserPassById(int userId, @NonNull String pass) throws CommonFlkException;

    /**
     * Метод удаления пользователя по его идентификатору
     * @param userId Идентификатор удаляемого пользователя
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteDbUserById(int userId) throws CommonFlkException;

    /**
     * Метод получения пользователя по идентификатору пользователя
     * @param userId Идентификатор пользователя
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    DbUser getDbUserById(int userId) throws CommonFlkException;

    /**
     * Метод получения пользователя по логину пользователя
     * @param login Логин пользователя
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    DbUser getDbUserByLogin(@NonNull String login) throws CommonFlkException;

    /**
     * Метод получения пользователя по идентификатору автора
     * @param authorId Идентификатор автора
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    DbUser getDbUserByAuthorId(UUID authorId) throws CommonFlkException;

    /**
     * Метод получения всех пользователей в проекте
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий пользователей, или пустой список, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<DbUser> getAllDbUsers(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
