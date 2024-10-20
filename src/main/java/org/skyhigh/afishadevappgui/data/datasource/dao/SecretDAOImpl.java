package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000012Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.updating.Flk10000015Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class SecretDAOImpl extends BaseTable implements SecretDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("secret")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public SecretDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("secret", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public SecretDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveSecret(@NonNull Secret secret) throws SQLException, CommonFlkException {
        Flk10000012Validator.validate(secret);
        UUID secretId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?, ?, ?, ?)");
        ps.setObject(1, secretId);
        ps.setObject(2, secret.getDeploymentId());
        ps.setString(3, secret.getAddress());
        ps.setString(4, secret.getLogin());

        if (secret.getPassword() != null)
            ps.setString(5, secret.getPassword());
        else {
            String generatedPassword = generatePassword();
            ps.setString(5, generatedPassword);
        }

        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return secretId;
    }

    @Override
    public void updateSecret(@NonNull Secret secret) throws SQLException, CommonFlkException {
        Flk10000015Validator.validate(secret);
        Flk10000012Validator.validate(secret);
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.deployment_id = ?1, t.address = ?2" +
                        " t.login = ?3, t.password = ?4 " +
                        "WHERE t.secret_id = ?5"
        );
        ps.setObject(5, secret.getSecretId());
        ps.setObject(1, secret.getDeploymentId());
        ps.setString(2, secret.getAddress());
        ps.setString(3, secret.getLogin());
        if (secret.getPassword() != null)
            ps.setString(4, secret.getPassword());
        else {
            String generatedPassword = generatePassword();
            ps.setString(4, generatedPassword);
        }

        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateSecretDeploymentIdById(@NonNull UUID secretId, @NonNull UUID deploymentId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.deployment_id = ?1 WHERE t.secret_id = ?2"
        );
        ps.setObject(1, deploymentId);
        ps.setObject(2, secretId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateSecretAddressById(@NonNull UUID secretId, @NonNull String address) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.address = ?1 WHERE t.secret_id = ?2"
        );
        ps.setString(1, address);
        ps.setObject(2, secretId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateSecretLogin(@NonNull UUID secretId, @NonNull String login) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.login = ?1 WHERE t.secret_id = ?2"
        );
        ps.setString(1, login);
        ps.setObject(2, secretId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateSecretPassword(@NonNull UUID secretId, String password) throws SQLException, CommonFlkException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.password = ?1 WHERE t.secret_id = ?2"
        );
        if (password != null)
            ps.setString(1, password);
        else {
            String generatedPassword = generatePassword();
            ps.setString(4, generatedPassword);
        }
        ps.setObject(2, secretId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteSecretById(@NonNull UUID secretId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.secret_id = ?1"
        );
        ps.setObject(1, secretId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public Secret getSecretById(UUID secretId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.secret_id, t.deployment_id, t.address, t.login, t.password " +
                        " FROM " + super.getTableName() + " t WHERE t.secret_id=?1",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, secretId);
        return getSingleSecret(ps);
    }

    @Override
    public List<Secret> getSecretsByDeploymentId(@NonNull UUID deploymentId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Secret.class.getName(),
                SecretDAO.class.getName(),
                "getSecretsByDeploymentId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.secret_id, t.deployment_id, t.address, t.login, t.password " +
                        " FROM " + super.getTableName() + " t WHERE t.deployment_id=?1",
                sortDirection,
                sortBy
        );
        ps.setObject(1, deploymentId);
        return getSecrets(ps);
    }

    @Override
    public List<Secret> getSecretsByAddress(@NonNull String address, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Secret.class.getName(),
                SecretDAO.class.getName(),
                "getSecretsByAddress",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.secret_id, t.deployment_id, t.address, t.login, t.password " +
                        " FROM " + super.getTableName() + " t WHERE t.address=?1",
                sortDirection,
                sortBy
        );
        ps.setString(1, address);
        return getSecrets(ps);
    }

    @Override
    public List<Secret> getSecretsByLogin(@NonNull String login, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Secret.class.getName(),
                SecretDAO.class.getName(),
                "getSecretsByLogin",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.secret_id, t.deployment_id, t.address, t.login, t.password " +
                        " FROM " + super.getTableName() + " t WHERE t.login=?1",
                sortDirection,
                sortBy
        );
        ps.setString(1, login);
        return getSecrets(ps);
    }

    @Override
    public List<Secret> getAllAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Secret.class.getName(),
                SecretDAO.class.getName(),
                "getAllAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.secret_id, t.deployment_id, t.address, t.login, t.password " +
                        " FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );
        return getSecrets(ps);
    }

    /**
     * Внутренний метод для получения одного доступа к развертыванию по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект Secret - доступ к развертыванию (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private Secret getSingleSecret(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        Secret secret = null;
        if (rs.next()) {
            secret = new Secret(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return secret;
    }

    /**
     * Внутренний метод для получения списка доступов к развертываниям по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов Secret (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<Secret> getSecrets(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<Secret> secrets = new ArrayList<>();
        while (rs.next()) {
            secrets.add(new Secret(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return secrets;
    }

    /**
     * Внутренний метод для генерации пароля с помощью функции БД
     * @return Сгенерированный пароль
     * @throws SQLException - Ошибка при работе с БД
     * @throws CommonFlkException Ошибка при проверке ФЛК
     */
    private String generatePassword() throws SQLException, CommonFlkException {
        String generatedPassword = null;
        PreparedStatement psForPassGen = super.prepareStatement(
                "SELECT gen_password_by_rule(NULL);"
        );
        ResultSet rsForPassGen = psForPassGen.executeQuery();
        while (rsForPassGen.next()) {
            generatedPassword = rsForPassGen.getString(1);
        }
        rsForPassGen.close();
        psForPassGen.close();
        if (generatedPassword == null)
            throw new CommonSystemException(
                    "Произошла ошибка при обновлении доступа к развертыванию. Пароль не сгенерирован. Пожалуйста, проверьте наличие правил генерации паролей",
                    false
            );
        return generatedPassword;
    }
}
