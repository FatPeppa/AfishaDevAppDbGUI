package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.List;
import java.util.UUID;

public interface ProjectAuthorRepository {
    /**
     * Метод сохранения связи автор-проект
     * @param projectAuthor Сущность связи автор-проект
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void saveProjectAuthor(@NonNull ProjectAuthor projectAuthor) throws CommonFlkException;

    /**
     * Метод удаления связи автор-проект
     * @param projectId Идентификатор проекта
     * @param authorId Идентификатор автора
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод удаления связей автор-проект по идентификатору проекта (всех связей по проекту)
     * @param projectId Идентификатор проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteProjectAuthorsByProjectId(@NonNull UUID projectId) throws CommonFlkException;

    /**
     * Метод удаления связи автор-проект по идентификатору автора (всех связей по автору)
     * @param authorId Идентификатор автора
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteProjectAuthorsByAuthorId(@NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод обновления связи автор-проект по старому идентификатору автора и идентификатору проекта
     * @param oldAuthorId Старый идентификатор автора (используется для определения обновляемой записи)
     * @param projectId Идентификатор проекта
     * @param newAuthorId Целевой идентификатор автора
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID projectId, @NonNull UUID newAuthorId) throws CommonFlkException;

    /**
     * Метод обновления связи автор-проект по старому идентификатору проекта и идентификатору автора
     * @param authorId Идентификатор автора
     * @param oldProjectId Старый идентификатор проекта (используется для определения обновляемой записи)
     * @param newProjectId Целевой идентификатор проекта
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateProjectAuthorProjectId(@NonNull UUID authorId, @NonNull UUID oldProjectId, @NonNull UUID newProjectId) throws CommonFlkException;

    /**
     * Метод получения связи автор-проект по идентификаторам
     * @param projectId Идентификатор проекта
     * @param authorId Идентификатор автора
     * @return Объект сущности ProjectAuthor или null в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    ProjectAuthor getProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-проект в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<ProjectAuthor> getAllProjectAuthors(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-проект, относящихся к проекту
     * @param projectId Идентификатор проекта, связи с которым необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<ProjectAuthor> getProjectAuthorsByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-проект, относящихся к автору
     * @param authorId Идентификатор автора, связи с которым необходимо получить
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями ProjectAuthor или пустой список в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<ProjectAuthor> getProjectAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
