package org.skyhigh.afishadevappgui.common.properties;

import org.skyhigh.afishadevappgui.common.properties.enums.DbmsType;

/**
 * Запись с основными параметрами приложения
 * @param DB_URL URL для подключения к БД
 * @param DB_USER Логин для подключения к БД
 * @param DB_PASSWORD Пароль для подключения к БД
 * @param DB_TYPE СУБД, в которой размещена БД. На данный момент доступно одно значение - POSTGRESQL
 * @param DB_DRIVER Драйвер, посредством которого будет осуществляться подключение к БД
 * @param DB_INIT Признак необходимости инициализации БД: true - необходима инициализация, false - инициализация не требуется.
 */
public record ApplicationProperties(
        String DB_URL,
        String DB_USER,
        String DB_PASSWORD,
        DbmsType DB_TYPE,
        String DB_DRIVER,
        boolean DB_INIT
) {}
