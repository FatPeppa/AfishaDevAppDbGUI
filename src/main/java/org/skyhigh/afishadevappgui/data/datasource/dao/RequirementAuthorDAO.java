package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью RequirementAuthor
 */
public interface RequirementAuthorDAO {
    /**
     * Метод сохранения сущности связи автор-требование
     * @param requirementAuthor Сохраняемая сущность связи автор-требование
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void saveRequirementAuthor(@NonNull RequirementAuthor requirementAuthor) throws SQLException, CommonFlkException;

    /**
     * Метод удаления связи автор-требования по идентификаторам
     * @param requirementId Идентификатор требования
     * @param authorId Идентификатор автора
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException;

    /**
     * Метод удаления связей автор-требования в пределах требования
     * @param requirementId Идентификатор требования, связи с которым необходимо удалить
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorsByRequirementId(@NonNull UUID requirementId) throws SQLException;

    /**
     * Метод удаления связей автор-требования в пределах автора
     * @param authorId Идентификатор автора, связи с которым необходимо удалить
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException;

    /**
     * Метод обновления идентификатора автора в связи автор-требование
     * @param oldAuthorId Старый идентификатор автора (используется для определения обновляемой записи)
     * @param requirementId Идентификатор требования
     * @param newAuthorId Целевой идентификатор автора
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementAuthorAuthorId(
            @NonNull UUID oldAuthorId,
            @NonNull UUID requirementId,
            @NonNull UUID newAuthorId
    ) throws SQLException;

    /**
     * Метод обновления идентификатора требования в связи автор-требование
     * @param authorId Идентификатор автора
     * @param oldRequirementId Старый идентификатор требования (используется для определения обновляемой записи)
     * @param newRequirementId Целевой идентификатор требования
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementAuthorRequirementId(
            @NonNull UUID authorId,
            @NonNull UUID oldRequirementId,
            @NonNull UUID newRequirementId
    ) throws SQLException;

    /**
     * Метод получения связи автор-требование по идентификаторам
     * @param requirementId Идентификатор требования
     * @param authorId Идентификатор автора
     * @return Сущность связи автор-требование или null в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    RequirementAuthor getRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException;

    /**
     * Метод получения всех связей автор-требование в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getAllRequirementAuthors(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения всех связей автор-требование в пределах требования
     * @param requirementId Идентификатор требования
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getRequirementAuthorsByRequirementId(
            @NonNull UUID requirementId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения всех связей автор-требование в пределах автора
     * @param authorId Идентификатор автора
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список, содержащий связи автор-требование или пустой в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<RequirementAuthor> getRequirementAuthorsByAuthorId(
            @NonNull UUID authorId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException;
}
