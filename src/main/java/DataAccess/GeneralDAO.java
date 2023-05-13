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
import Model.Client;

public abstract class GeneralDAO <T> {

    protected final Logger LOGGER = Logger.getLogger(getClass().getName());


    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public GeneralDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();

    }

    private String createSelectAllQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

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


    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getName().equals("id")) {
                sb.append(fields[i].getName());
                sb.append(" = ?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" WHERE id = ?");

        return sb.toString();
    }


    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }


    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }


    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;

    }


    public T insert(T object)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        String query = createInsertQuery();

        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            for (Field field : object.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(object);
                statement.setObject(i, value);
                i++;
            }
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object failed, no rows affected.");
            }

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idField = generatedKeys.getInt(1);
                Field field = object.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(object, idField);
            } else {
                throw new SQLException("Creating object failed, no ID obtained.");
            }
        }catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(generatedKeys);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return object;
    }


    public T update(T object) {
        Connection connection = null;
        PreparedStatement statement = null;

        String query = createUpdateQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int i = 1;
            for (Field field : object.getClass().getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    Object value = field.get(object);
                    statement.setObject(i, value);
                    i++;
                }
            }

            Field idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(i, idField.get(object));

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return object;
    }


    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        List<T> list = new ArrayList<>();  // Initialize the list here

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if(type.equals(Client.class))
                {
                    Client client = new Client();
                    client.setId(resultSet.getInt("id"));
                    client.setName(resultSet.getString("name"));
                    client.setSurname(resultSet.getString("surname"));
                    client.setPhoneNumber(resultSet.getString("phoneNumber"));

                    list.add((T) client);
                }
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return list;  // Return the list, even if it's empty
    }

    public int findByIdForDeletion(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id"); // Retrieve the ID from the result set
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


    public Client findClientByIdForDeletion(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        Client client = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            client = new Client();
            while (resultSet.next()) {
                if (type.equals(Client.class)) {

                    client.setId(resultSet.getInt("id"));
                    client.setName(resultSet.getString("name"));
                    client.setSurname(resultSet.getString("surname"));
                    client.setPhoneNumber(resultSet.getString("phoneNumber"));
                }
            }


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return client;

    }



}