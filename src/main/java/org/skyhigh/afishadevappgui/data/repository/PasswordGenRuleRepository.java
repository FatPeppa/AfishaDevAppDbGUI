package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.time.LocalDateTime;
import java.util.List;

public interface PasswordGenRuleRepository {
    /**
     * Метод сохранения правила генерации паролей
     * @param passwordGenRule Сущность правила генерации паролей
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    Integer savePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws CommonFlkException;

    /**
     * Метод обновления правила генерации паролей в Системе
     * @param passwordGenRule Целевая сущность правила генерации паролей. Поле ruleId должно быть заполнено,
     *                иначе будет сформировано исключение NullFieldsInDBEntityException/
     *                Поиск обновляемой записи осуществляется именно по ruleId
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updatePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws CommonFlkException;

    /**
     * Метод удаления правила генерации паролей
     * @param ruleId Идентификатор правила генерации паролей
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void deletePasswordGenRuleById(@NonNull Integer ruleId) throws CommonFlkException;

    /**
     * Метод получения правила генерации паролей по идентификатору
     * @param ruleId Идентификатор правила генерации паролей
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    PasswordGenRule getPasswordGenRuleById(@NonNull Integer ruleId) throws CommonFlkException;

    /**
     * Метод получения актуального правила генерации паролей по дате актуальности (если не заполнена, то определяется как текущая дата на момент получения сервером СУБД запроса)
     * @param actualizationDate Дата актуальности, на которую осуществляется поиск правила
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    PasswordGenRule getActualPasswordGenRuleByDate(LocalDateTime actualizationDate) throws CommonFlkException;

    /**
     * Метод получения всех правил генерации паролей в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями PasswordGenRule или пустой список в случае, если данные не были найдены
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<PasswordGenRule> getAllPasswordGenRules(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws CommonFlkException;
}
