package org.skyhigh.afishadevappgui.data.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordGenRule {
    /**
     * Идентификатор правила заполнения паролей
     */
    private Integer ruleId;
    /**
     * Максимальное допустимое количество идущих подряд повторяющихся символов
     */
    private Integer repeatableCharactersAmount;
    /**
     * Минимальное допустимое количество заглавных букв (ASCII 65-90 uppercase, 97-122 lowercase)
     */
    private Integer capitalLettersAmount;
    /**
     * Минимальное допустимое количество специальных символов (ASCII 33-47 58-64 91-96 123-126)
     */
    private Integer specSymbolsAmount;
    /**
     * Дата начала действия правила
     */
    private LocalDateTime beginDate;
    /**
     * Дата окончания действия правила
     */
    private LocalDateTime endDate;
    /**
     * Дата создания правила
     */
    private LocalDateTime createDate;
    /**
     * Минимальное допустимое количество цифр (ASCII 48-57)
     */
    private Integer numericSymbolsAmount;
}
