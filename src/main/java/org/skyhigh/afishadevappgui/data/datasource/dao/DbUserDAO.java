package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью DbUser
 */
public interface DbUserDAO {
    /**
     * Метод сохранения пользователя DbUser
     * @param dbUser Сохраняемая сущность DbUser
     * @return Уникальный идентификатор, присвоенный созданной записи
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    int saveDbUser(@NonNull DbUser dbUser) throws SQLException, CommonFlkException;

    /**
     * Метод обновления пароля пользователя
     * @param userId Идентификатор пользователя
     * @param pass Новый пароль пользователя
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateDbUserPassById(int userId, @NonNull String pass) throws SQLException, CommonFlkException;

    /**
     * Метод удаления пользователя по его идентификатору
     * @param userId Идентификатор удаляемого пользователя
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteDbUserById(int userId) throws SQLException;

    /**
     * Метод получения пользователя по идентификатору пользователя
     * @param userId Идентификатор пользователя
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    DbUser getDbUserById(int userId) throws SQLException;

    /**
     * Метод получения пользователя по логину пользователя
     * @param login Логин пользователя
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    DbUser getDbUserByLogin(@NonNull String login) throws SQLException;

    /**
     * Метод получения пользователя по идентификатору автора
     * @param authorId Идентификатор автора
     * @return Сущность типа DbUser или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    DbUser getDbUserByAuthorId(UUID authorId) throws SQLException;

    /**
     * Метод получения всех пользователей в проекте
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий пользователей, или пустой список, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<DbUser> getAllDbUsers(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;
}
