package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000010Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class RequirementAuthorDAOImpl extends BaseTable implements RequirementAuthorDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("requirement_author")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementAuthorDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("requirement_author", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementAuthorDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public void saveRequirementAuthor(@NonNull RequirementAuthor requirementAuthor) throws SQLException, CommonFlkException {
        Flk10000010Validator.validate(requirementAuthor);
        //UUID deploymentStatusId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, requirementAuthor.getRequirementId());
        ps.setObject(2, requirementAuthor.getAuthorId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
    }

    @Override
    public void deleteRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.requirement_id = ? AND t.author_id = ?"
        );
        ps.setObject(1, requirementId);
        ps.setObject(2, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void deleteRequirementAuthorsByRequirementId(@NonNull UUID requirementId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.requirement_id = ?"
        );
        ps.setObject(1, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void deleteRequirementAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.author_id = ?"
        );
        ps.setObject(1, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void updateRequirementAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID requirementId, @NonNull UUID newAuthorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET author_id = ? WHERE author_id = ? AND requirement_id = ?"
        );
        ps.setObject(1, newAuthorId);
        ps.setObject(2, oldAuthorId);
        ps.setObject(3, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateRequirementAuthorRequirementId(@NonNull UUID authorId, @NonNull UUID oldRequirementId, @NonNull UUID newRequirementId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET requirement_id = ? WHERE requirement_id = ? AND author_id = ?"
        );
        ps.setObject(1, newRequirementId);
        ps.setObject(2, oldRequirementId);
        ps.setObject(3, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public RequirementAuthor getRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.author_id FROM " + super.getTableName() + " t WHERE t.requirement_id = ? AND t.author_id = ?"
        );
        ps.setObject(1, requirementId);
        ps.setObject(2, authorId);
        return getSingleRequirementAuthor(ps);
    }

    @Override
    public List<RequirementAuthor> getAllRequirementAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                RequirementAuthor.class.getName(),
                RequirementAuthorDAO.class.getName(),
                "getAllRequirementAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id, t.author_id FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );

        return getRequirementAuthors(ps);
    }

    @Override
    public List<RequirementAuthor> getRequirementAuthorsByRequirementId(@NonNull UUID requirementId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                RequirementAuthor.class.getName(),
                RequirementAuthorDAO.class.getName(),
                "getRequirementAuthorsByRequirementId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id, t.author_id FROM " + super.getTableName() + " t WHERE t.requirement_id = ?",
                sortDirection,
                sortBy
        );
        ps.setObject(1, requirementId);

        return getRequirementAuthors(ps);
    }

    @Override
    public List<RequirementAuthor> getRequirementAuthorsByAuthorId(@NonNull UUID authorId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                RequirementAuthor.class.getName(),
                RequirementAuthorDAO.class.getName(),
                "getRequirementAuthorsByAuthorId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id, t.author_id FROM " + super.getTableName() + " t WHERE t.author_id = ?",
                sortDirection,
                sortBy
        );
        ps.setObject(1, authorId);

        return getRequirementAuthors(ps);
    }

    /**
     * Внутренний метод для получения одного автора требования по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект RequirementAuthor - автор требования (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private RequirementAuthor getSingleRequirementAuthor(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        RequirementAuthor requirementAuthor = null;
        if (rs.next()) {
            requirementAuthor = new RequirementAuthor(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return requirementAuthor;
    }

    /**
     * Внутренний метод для получения списка авторов требований по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов RequirementAuthor (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<RequirementAuthor> getRequirementAuthors(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<RequirementAuthor> requirementAuthors = new ArrayList<>();
        while (rs.next()) {
            requirementAuthors.add(new RequirementAuthor(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return requirementAuthors;
    }
}
