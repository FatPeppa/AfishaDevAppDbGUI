package org.skyhigh.afishadevappgui.data.datasource;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.data.datasource.dao.*;

import java.sql.SQLException;

@AllArgsConstructor
public class AfishaDevAppDb {
    private ApplicationProperties properties;

    public AccessedRoleDAO getAccessedRoleDAO() throws SQLException {
        return new AccessedRoleDAOImpl(
                new DbConnector(properties)
        );
    }

    public AuthorDAO getAuthorDAO() throws SQLException {
        return new AuthorDAOImpl(
                new DbConnector(properties)
        );
    }

    public CodeFileDAO getCodeFileDAO() throws SQLException {
        return new CodeFileDAOImpl(
                new DbConnector(properties)
        );
    }

    public DbUserDAO getDbUserDAO() throws SQLException {
        return new DbUserDAOImpl(
                new DbConnector(properties)
        );
    }

    public DeploymentDAO getDeploymentDAO() throws SQLException {
        return new DeploymentDAOImpl(
                new DbConnector(properties)
        );
    }

    public DeploymentStatusDAO getDeploymentStatusDAO() throws SQLException {
        return new DeploymentStatusDAOImpl(
                new DbConnector(properties)
        );
    }

    public PasswordGenRuleDAO getPasswordGenRuleDAO() throws SQLException {
        return new PasswordGenRuleDAOImpl(
                new DbConnector(properties)
        );
    }

    public ProjectAuthorDAO getProjectAuthorDAO() throws SQLException {
        return new ProjectAuthorDAOImpl(
                new DbConnector(properties)
        );
    }

    public ProjectDAO getProjectDAO() throws SQLException {
        return new ProjectDAOImpl(
                new DbConnector(properties)
        );
    }

    public RequirementAuthorDAO getRequirementAuthorDAO() throws SQLException {
        return new RequirementAuthorDAOImpl(
                new DbConnector(properties)
        );
    }

    public RequirementDAO getRequirementDAO() throws SQLException {
        return new RequirementDAOImpl(
                new DbConnector(properties)
        );
    }

    public RequirementTypeDAO getRequirementTypeDAO() throws SQLException {
        return new RequirementTypeDAOImpl(
                new DbConnector(properties)
        );
    }

    public SecretDAO getSecretDAO() throws SQLException {
        return new SecretDAOImpl(
                new DbConnector(properties)
        );
    }
}
