package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью Secret
 */
public interface SecretDAO {
    /**
     *
     * @param secret
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveSecret(@NonNull Secret secret) throws SQLException;

    /**
     *
     * @param secret
     * @throws SQLException Ошибка при работе с БД
     */
    void updateSecretById(@NonNull Secret secret) throws SQLException;

    /**
     *
     * @param secretId
     * @param deploymentId
     * @throws SQLException Ошибка при работе с БД
     */
    void updateSecretDeploymentIdById(@NonNull UUID secretId, @NonNull UUID deploymentId) throws SQLException;

    /**
     *
     * @param secretId
     * @param address
     * @throws SQLException Ошибка при работе с БД
     */
    void updateSecretAddressById(@NonNull UUID secretId, @NonNull String address) throws SQLException;

    /**
     *
     * @param secretId
     * @param login
     * @throws SQLException Ошибка при работе с БД
     */
    void updateSecretLogin(@NonNull UUID secretId, @NonNull String login) throws SQLException;

    /**
     *
     * @param secretId
     * @param password
     * @throws SQLException Ошибка при работе с БД
     */
    void updateSecretPassword(@NonNull UUID secretId, @NonNull String password) throws SQLException;

    /**
     *
     * @param secretId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteSecretById(@NonNull UUID secretId) throws SQLException;

    /**
     *
     * @param secretId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    Secret getSecretById(UUID secretId) throws SQLException;

    /**
     *
     * @param deploymentId
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Secret> getSecretsByDeploymentId(
            @NonNull UUID deploymentId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param address
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Secret> getSecretsByAddress(
            @NonNull String address,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;

    /**
     *
     * @param login
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, заполнено значением null
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    List<Secret> getSecretsByLogin(
            @NonNull String login,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException;
}
