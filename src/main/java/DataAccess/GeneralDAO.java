package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import Model.Bill;
import Model.Client;

public abstract class GeneralDAO<T> {

    /**
     * GeneralDAO attributes
     */
    protected final Logger LOGGER = Logger.getLogger(getClass().getName());
    private final Class<T> type;
    private final String idFieldName;  // The name of the id field

    /**
     * GeneralDAO constructor
     * @param idFieldName
     */
    @SuppressWarnings("unchecked")
    public GeneralDAO(String idFieldName) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.idFieldName = idFieldName;
    }

    /**
     * Method that create the Select query string
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();

    }

    /**
     * Method that creates the Select all query string
     * @return
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Method that creates INSERT query string
     * @return
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Method that creates UPDATE query string
     * @return
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getName().equals(idFieldName)) {
                sb.append(fields[i].getName());
                sb.append(" = ?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" WHERE " + idFieldName + " = ?");

        return sb.toString();
    }


    /**
     * Method that creates DELETE query string
     * @return
     */
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + idFieldName + "= ?");
        return sb.toString();
    }


    /**
     * Method that executes the Insert query
     * @param object
     * @return
     */
    public T insert(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        String query = createInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            for (Field field : object.getClass().getDeclaredFields()) {  // For each field of the object
                field.setAccessible(true);  // Set the field accessible
                Object value = field.get(object);  // Get the value of the field
                statement.setObject(i, value);      // Set the value of the field
                i++;                        // Increment the index
            }
            int affectedRows = statement.executeUpdate();       // Execute the query

            if (affectedRows == 0) {        // If no rows were affected
                throw new SQLException("Creating object failed, no rows affected.");    // Throw exception
            }

            generatedKeys = statement.getGeneratedKeys();       // Get the generated keys
            if (generatedKeys.next()) {    // If there are generated keys
                int idField = generatedKeys.getInt(1);      // Get the first generated key
                Field field = object.getClass().getDeclaredField(idFieldName);  // Get the id field
                field.setAccessible(true);    // Set the field accessible
                field.set(object, idField);  // Set the id field

            } else {
                throw new SQLException("Creating object failed, no ID obtained.");
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(generatedKeys);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return object;
    }


    /**
     * Method that executes the Update query
     * @param object
     * @return
     */
    public T update(T object) {
        Connection connection = null;
        PreparedStatement statement = null;

        String query = createUpdateQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int i = 1;
            for (Field field : object.getClass().getDeclaredFields()) {  // For each field of the object
                if (!field.getName().equals(idFieldName)) {     // If the field is not the id field
                    field.setAccessible(true);      // Set the field accessible
                    Object value = field.get(object);       // Get the value of the field
                    statement.setObject(i, value);      // Set the value of the field
                    i++;
                }
            }

            Field idField = object.getClass().getDeclaredField(idFieldName);  // Get the id field
            idField.setAccessible(true);        // Set the field accessible
            statement.setObject(i, idField.get(object));        // Set the value of the id field

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return object;
    }


    /**
     * Method that executes the Delete query
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);        // Set the id of the object to be deleted

            statement.executeUpdate();      // Execute the query
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    /**
     * Method that finds all elements from the database
     * @param objectType
     * @return list of elements
     */
    public List<T> findAll(Class<T> objectType) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        List<T> list = new ArrayList<>();  // Initialize the list here

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {      // While there are elements in the result set
                Constructor<T> constructor = objectType.getDeclaredConstructor();       // Get the constructor
                constructor.setAccessible(true);        // Set the constructor accessible
                T object = constructor.newInstance();       // Create a new instance of the object

                for (Field field : objectType.getDeclaredFields()) {        // For each field of the object
                    field.setAccessible(true);      // Set the field accessible
                    Object value = resultSet.getObject(field.getName());        // Get the value of the field
                    field.set(object, value);       // Set the value of the field
                }

                list.add(object);
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return list;  // Return the list, even if it's empty
    }

    /**
     * Method that finds an element by ID
     * @param id
     * @return
     */
    public int findByIdForDeletion(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(idFieldName);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(idFieldName); // Retrieve the ID from the result set
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;

    }


    /**
     * Method that finds an element by ID
     * @param id
     * @param objectType
     * @return object
     */
    public <T> T findWhatYouNeedById(int id, Class<T> objectType) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(idFieldName);

        T object = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            Constructor<T> constructor = objectType.getDeclaredConstructor();       // Get the constructor
            constructor.setAccessible(true);        // Set the constructor accessible
            object = constructor.newInstance();     // Create a new instance of the object

            while (resultSet.next()) {
                for (Field field : objectType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    field.set(object, value);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return object;
    }


}