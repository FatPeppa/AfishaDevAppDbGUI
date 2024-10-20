package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью ProjectAuthor
 */
public interface PasswordGenRuleDAO {
    /**
     * Метод сохранения правила генерации паролей
     * @param passwordGenRule Сущность правила генерации паролей
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    UUID savePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws SQLException, CommonFlkException;

    /**
     * Метод обновления правила генерации паролей в Системе
     * @param passwordGenRule Целевая сущность правила генерации паролей. Поле ruleId должно быть заполнено,
     *                иначе будет сформировано исключение NullFieldsInDBEntityException/
     *                Поиск обновляемой записи осуществляется именно по ruleId
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    void updatePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws SQLException, CommonFlkException;

    /**
     * Метод удаления правила генерации паролей
     * @param ruleId Идентификатор правила генерации паролей
     * @throws SQLException Ошибка при работе с БД
     */
    void deletePasswordGenRuleById(@NonNull UUID ruleId) throws SQLException;

    /**
     * Метод получения правила генерации паролей по идентификатору
     * @param ruleId Идентификатор правила генерации паролей
     * @throws SQLException Ошибка при работе с БД
     */
    PasswordGenRule getPasswordGenRuleById(@NonNull UUID ruleId) throws SQLException;

    /**
     * Метод получения всех правил генерации паролей в Системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список с сущностями PasswordGenRule или пустой список в случае, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    List<PasswordGenRule> getAllPasswordGenRules(
            @NonNull SortDirection sortDirection,
             String sortBy
    ) throws SQLException, CommonFlkException;
}
