package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000008Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class ProjectAuthorDAOImpl extends BaseTable implements ProjectAuthorDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("project_author")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public ProjectAuthorDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("project_author", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public ProjectAuthorDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public void saveProjectAuthor(@NonNull ProjectAuthor projectAuthor) throws SQLException, CommonFlkException {
        Flk10000008Validator.validate(projectAuthor);
        //UUID deploymentStatusId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, projectAuthor.getProjectId());
        ps.setObject(2, projectAuthor.getAuthorId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
    }

    @Override
    public void deleteProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.project_id = ? AND t.author_id = ?"
        );
        ps.setObject(1, projectId);
        ps.setObject(2, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void deleteProjectAuthorsByProjectId(@NonNull UUID projectId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.project_id = ?"
        );
        ps.setObject(1, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void deleteProjectAuthorsByAuthorId(@NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.author_id = ?"
        );
        ps.setObject(1, authorId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void updateProjectAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID projectId, @NonNull UUID newAuthorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.author_id = ? WHERE t.author_id = ? AND t.project_id = ?"
        );
        ps.setObject(1, newAuthorId);
        ps.setObject(2, oldAuthorId);
        ps.setObject(3, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectAuthorProjectId(@NonNull UUID authorId, @NonNull UUID oldProjectId, @NonNull UUID newProjectId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.project_id = ? WHERE t.author_id = ? AND t.project_id = ?"
        );
        ps.setObject(1, newProjectId);
        ps.setObject(2, authorId);
        ps.setObject(3, oldProjectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public ProjectAuthor getProjectAuthorByIds(@NonNull UUID projectId, @NonNull UUID authorId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.author_id FROM " + super.getTableName() + " t WHERE t.project_id = ? AND t.author_id = ?"
        );
        ps.setObject(1, projectId);
        ps.setObject(2, authorId);
        return getSingleProjectAuthor(ps);
    }

    @Override
    public List<ProjectAuthor> getAllProjectAuthors(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                ProjectAuthor.class.getName(),
                ProjectAuthorDAO.class.getName(),
                "getAllProjectAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.project_id, t.author_id FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );

        return getProjectAuthors(ps);
    }

    @Override
    public List<ProjectAuthor> getProjectAuthorsByProjectId(@NonNull UUID projectId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                ProjectAuthor.class.getName(),
                ProjectAuthorDAO.class.getName(),
                "getAllProjectAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.project_id, t.author_id FROM " + super.getTableName() + " t WHERE t.project_id = ?",
                sortDirection,
                sortBy
        );
        ps.setObject(1, projectId);

        return getProjectAuthors(ps);
    }

    @Override
    public List<ProjectAuthor> getProjectAuthorsByAuthorId(@NonNull UUID authorId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                ProjectAuthor.class.getName(),
                ProjectAuthorDAO.class.getName(),
                "getAllProjectAuthors",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.project_id, t.author_id FROM " + super.getTableName() + " t WHERE t.author_id = ?",
                sortDirection,
                sortBy
        );
        ps.setObject(1, authorId);

        return getProjectAuthors(ps);
    }

    /**
     * Внутренний метод для получения одного автора проекта по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект ProjectAuthor - автор проекта (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private ProjectAuthor getSingleProjectAuthor(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        ProjectAuthor projectAuthor = null;
        if (rs.next()) {
            projectAuthor = new ProjectAuthor(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return projectAuthor;
    }

    /**
     * Внутренний метод для получения списка авторов проектов по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов ProjectAuthor (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<ProjectAuthor> getProjectAuthors(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<ProjectAuthor> projectAuthors = new ArrayList<>();
        while (rs.next()) {
            projectAuthors.add(new ProjectAuthor(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return projectAuthors;
    }
}
