package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;
import org.skyhigh.afishadevappgui.data.repository.PasswordGenRuleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PasswordGenRuleSearchServiceImpl implements PasswordGenRuleSearchService {
    private final PasswordGenRuleRepository passwordGenRuleRepository;

    //2024-10-01T23:35:01
    @Override
    public List<PasswordGenRule> searchPasswordGenRule(
            Integer passwordGenRuleId,
            LocalDateTime actualizationDate
    ) throws CommonFlkException {
        if (passwordGenRuleId != null && actualizationDate != null)
            return null;
        if (passwordGenRuleId != null) {
            ArrayList<PasswordGenRule> passwordGenRules = new ArrayList<>();
            passwordGenRules.add(passwordGenRuleRepository.getPasswordGenRuleById(passwordGenRuleId));
            return passwordGenRules;
        }
        if (actualizationDate != null) {
            ArrayList<PasswordGenRule> passwordGenRules = new ArrayList<>();
            passwordGenRules.add(passwordGenRuleRepository.getActualPasswordGenRuleByDate(actualizationDate));
            return passwordGenRules;
        }
        return passwordGenRuleRepository.getAllPasswordGenRules(
                SortDirection.NONE,
                null
        );
    }
}
