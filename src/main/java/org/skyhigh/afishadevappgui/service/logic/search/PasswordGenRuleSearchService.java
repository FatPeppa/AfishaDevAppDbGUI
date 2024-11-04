package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.time.LocalDateTime;
import java.util.List;

@FunctionalInterface
public interface PasswordGenRuleSearchService {
    /**
     * Метод поиска правил генерации (заполнения) паролей. Допустимые комбинации: passwordGenRuleId / actualizationDate / all null
     * @param passwordGenRuleId ID правила
     * @param actualizationDate Дата актуальности, на которую осуществляется поиск. Если null, то считается как текущая дата на момент получения запроса сервером СУБД
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<PasswordGenRule> searchPasswordGenRule(Integer passwordGenRuleId, LocalDateTime actualizationDate) throws CommonFlkException;
}
