package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью ProjectAuthor
 */
public interface ProjectAuthorDAO {
    /**
     * Метод сохранения связи автор-проект
     * @param projectAuthor Сущность связи автор-проект
     * @throws SQLException Ошибка при работе с БД
     */
    void saveProjectAuthor(@NonNull ProjectAuthor projectAuthor) throws SQLException;

    /**
     * Метод удаления связи автор-проект
     * @param projectId Идентификатор проекта
     * @param authorId Идентификатор автора
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException;

    /**
     * Метод удаления связей автор-проект по идентификатору проекта (всех связей по проекту)
     * @param projectId Идентификатор проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorsByProjectId(@NonNull UUID projectId) throws SQLException;

    /**
     * Метод удаления связи автор-проект по идентификатору автора (всех связей по автору)
     * @param authorId Идентификатор автора
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteProjectAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод обновления связи автор-проект по старому идентификатору автора и идентификатору проекта
     * @param oldAuthorId Старый идентификатор автора (используется для определения обновляемой записи)
     * @param projectId Идентификатор проекта
     * @param newAuthorId Целевой идентификатор автора
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID projectId, @NonNull UUID newAuthorId) throws SQLException;

    /**
     * Метод обновления связи автор-проект по старому идентификатору проекта и идентификатору автора
     * @param authorId Идентификатор автора
     * @param oldProjectId Старый идентификатор проекта (используется для определения обновляемой записи)
     * @param newProjectId Целевой идентификатор проекта
     * @throws SQLException Ошибка при работе с БД
     */
    void updateProjectAuthorProjectId(@NonNull UUID authorId, @NonNull UUID oldProjectId, @NonNull UUID newProjectId) throws SQLException;

    /**
     * Метод получения связи автор-проект по идентификаторам
     * @param projectId Идентификатор проекта
     * @param authorId Идентификатор автора
     * @return Объект сущности ProjectAuthor или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    ProjectAuthor getProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения всех связей автор-проект в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<ProjectAuthor> getAllProjectAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     * Метод получения всех связей автор-проект, относящихся к проекту
     * @param projectId Идентификатор проекта, связи с которым необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<ProjectAuthor> getProjectAuthorsByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     * Метод получения всех связей автор-проект, относящихся к автору
     * @param authorId Идентификатор автора, связи с которым необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    List<ProjectAuthor> getProjectAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
