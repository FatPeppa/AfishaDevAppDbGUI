package org.skyhigh.afishadevappgui.data.validation.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonArgsException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Универсальная проверка аргументов метода чтения данных из таблицы с сортировкой, что поле, по которому сортируются записи,
 *  содержится в списке полей сущности
 */
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10010000 implements CommonFlk {
    private final String code = "10010000";
    private final String message = "При выполнении сортировки записей таблицы" +
            ", соответствующей сущности %s, поле sortBy должно быть заполнено. Также оно должно соответствовать списку " +
            "параметров сущности: %s";
    private final List<String> attributesNames = List.of("sortBy");
    private final boolean isCritical = true;

    private String entityClassName;
    private String daoClassName;
    private String daoMethodName;
    private SortDirection sortDirection;
    private String sortBy;

    @Override
    public void validate() throws CommonArgsException, CommonSystemException {
        log.debug("Flk " + code + " started for method {} in dao {} with sortDirection {} and sortBy {}",
                daoMethodName,
                daoClassName,
                sortDirection,
                sortBy
        );
        if (sortDirection != SortDirection.NONE) {
            try {
                if (sortBy == null || Arrays.stream(Class.forName(entityClassName).getDeclaredFields())
                        .noneMatch(field -> field.getName().equals(sortBy))) {

                    log.debug("Flk " + code + " for method {} in dao {} with sortDirection {} and sortBy {} finished with error",
                            daoMethodName,
                            daoClassName,
                            sortDirection,
                            sortBy
                    );

                    throw new CommonArgsException(
                            entityClassName,
                            daoClassName,
                            daoMethodName,
                            code,
                            String.format(
                                    message,
                                    entityClassName,
                                    Arrays.stream(Class.forName(entityClassName).getDeclaredFields())
                                            .map(Field::getName).collect(Collectors.joining(", "))
                            ),
                            attributesNames,
                            isCritical
                    );
                }
            } catch (ClassNotFoundException e) {
                log.debug("Flk " + code + " for method {} in dao {} with sortDirection {} and sortBy {} finished with ClassNotFoundException",
                        daoMethodName,
                        daoClassName,
                        sortDirection,
                        sortBy
                );
                throw new CommonSystemException(
                        "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                        e.getCause(),
                        false
                );
            }
        }
        log.debug("Flk " + code + " for method {} in dao {} with sortDirection {} and sortBy {} finished successfully",
                daoMethodName,
                daoClassName,
                sortDirection,
                sortBy
        );//orm
    }
}
