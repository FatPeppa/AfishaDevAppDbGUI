package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.util.List;
import java.util.UUID;

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
    void deletePasswordGenRuleById(@NonNull UUID ruleId) throws CommonFlkException;

    /**
     * Метод получения правила генерации паролей по идентификатору
     * @param ruleId Идентификатор правила генерации паролей
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    PasswordGenRule getPasswordGenRuleById(@NonNull UUID ruleId) throws CommonFlkException;

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
