package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью Author
 */
public interface AuthorDAO {
    /**
     * Метод сохранения автора в БД
     * @param author Сохраняемая в БД сущность автора
     * @return Идентификатор сохраненной записи
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID saveAuthor(@NonNull Author author) throws SQLException, CommonFlkException;

    /**
     * Метод обновления имени автора по идентификатору
     * @param authorId Идентификатор автора
     * @param authorLogin Новое имя автора
     */
    void updateAuthorLoginById(@NonNull UUID authorId, @NonNull String authorLogin) throws SQLException;

    /**
     * Метод удаления записи автора по идентификатору
     * @param authorId Идентификатор удаляемого автора
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteAuthorById(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения записи автора по идентификатору
     * @param authorId Идентификатор искомого автора
     * @return Объект Author или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Author getAuthorById(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения записи автора по имени
     * @param authorLogin Имя искомого автора
     * @return Объект Author или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Author getAuthorByName(@NonNull String authorLogin) throws SQLException;

    /**
     * Метод получения всех авторов в системе
     * @return Список, содержащий объекты Author, или пустой список, если данные не были найдены
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<Author> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException;
}
