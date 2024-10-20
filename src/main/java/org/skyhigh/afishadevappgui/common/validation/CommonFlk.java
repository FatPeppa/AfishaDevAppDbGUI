package org.skyhigh.afishadevappgui.common.validation;

/**
 * Интерфейс класса стандартной проверки ФЛК. Наследуется в классах реализации по предметной области
 */
@FunctionalInterface
public interface CommonFlk {
    void validate() throws CommonFlkException;
}
