package org.skyhigh.afishadevappgui.common.properties;

import java.util.UUID;

/**
 * Запись с параметрами текущей сессии взаимодействия пользователя с Системой
 * @param CURRENT_USER_ID Идентификатор текущего активного пользователя
 * @param CURRENT_AUTHOR_ID Идентификатор автора, связанного с текущим активным пользователем
 * @param CURRENT_USER_LOGIN Логин текущего активного пользователя
 */
public record CurrentUserProperties(
        UUID CURRENT_USER_ID,
        UUID CURRENT_AUTHOR_ID,
        String CURRENT_USER_LOGIN
) {}
