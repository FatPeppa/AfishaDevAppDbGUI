package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
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
     */
    UUID saveAuthor(@NonNull Author author) throws SQLException;

    /**
     * Метод обновлении имени автора по идентификатору
     * @param authorId Идентификатор автора
     * @param authorName Новое имя автора
     */
    void updateAuthorNameById(@NonNull UUID authorId, @NonNull String authorName) throws SQLException;

    /**
     * Метод удаления записи автора по идентификатору
     * @param authorId Идентификатор удаляемого автора
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteAuthorById(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения записи автора по идентификатору
     * @param authorId Илентификатор искомого автора
     * @return Объект Author или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Author getAuthorById(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения записи автора по имени
     * @param authorName Имя искомого автора
     * @return Объект Author или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    Author getAuthorByName(@NonNull String authorName) throws SQLException;

    /**
     * Метод получения всех авторов в системе
     * @return Список, содержащий объекты Author, или пустой список, если данные не были найдены
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @throws SQLException Ошибка при работе с БД
     */
    List<Author> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;
}
