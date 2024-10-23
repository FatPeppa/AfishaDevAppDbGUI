package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.util.List;
import java.util.UUID;

public interface RequirementAuthorRepository {
    /**
     * Метод сохранения сущности связи автор-требование
     * @param requirementAuthor Сохраняемая сущность связи автор-требование
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void saveRequirementAuthor(@NonNull RequirementAuthor requirementAuthor) throws CommonFlkException;

    /**
     * Метод удаления связи автор-требования по идентификаторам
     * @param requirementId Идентификатор требования
     * @param authorId Идентификатор автора
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод удаления связей автор-требования в пределах требования
     * @param requirementId Идентификатор требования, связи с которым необходимо удалить
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteRequirementAuthorsByRequirementId(@NonNull UUID requirementId) throws CommonFlkException;

    /**
     * Метод удаления связей автор-требования в пределах автора
     * @param authorId Идентификатор автора, связи с которым необходимо удалить
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deleteRequirementAuthorsByAuthorId(@NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод обновления идентификатора автора в связи автор-требование
     * @param oldAuthorId Старый идентификатор автора (используется для определения обновляемой записи)
     * @param requirementId Идентификатор требования
     * @param newAuthorId Целевой идентификатор автора
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateRequirementAuthorAuthorId(
            @NonNull UUID oldAuthorId,
            @NonNull UUID requirementId,
            @NonNull UUID newAuthorId
    ) throws CommonFlkException;

    /**
     * Метод обновления идентификатора требования в связи автор-требование
     * @param authorId Идентификатор автора
     * @param oldRequirementId Старый идентификатор требования (используется для определения обновляемой записи)
     * @param newRequirementId Целевой идентификатор требования
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updateRequirementAuthorRequirementId(
            @NonNull UUID authorId,
            @NonNull UUID oldRequirementId,
            @NonNull UUID newRequirementId
    ) throws CommonFlkException;

    /**
     * Метод получения связи автор-требование по идентификаторам
     * @param requirementId Идентификатор требования
     * @param authorId Идентификатор автора
     * @return Сущность связи автор-требование или null в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    RequirementAuthor getRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-требование в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getAllRequirementAuthors(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-требование в пределах требования
     * @param requirementId Идентификатор требования
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getRequirementAuthorsByRequirementId(
            @NonNull UUID requirementId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;

    /**
     * Метод получения всех связей автор-требование в пределах автора
     * @param authorId Идентификатор автора
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getRequirementAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
