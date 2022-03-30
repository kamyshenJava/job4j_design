package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("create table if not exists %s();", tableName);
        this.getStatement().execute(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table if exists %s;", tableName);
        this.getStatement().execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
        this.getStatement().execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        this.getStatement().execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName);
        this.getStatement().execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        Statement statement = this.getStatement();
        buffer.add(header);
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private static Properties setProperties(String path) throws IOException {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(path)) {
            properties.load(in);
        }
        return properties;
    }

    private Statement getStatement() throws Exception {
        return connection.createStatement();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = setProperties("db_connect.properties");
        try (TableEditor tableEditor = new TableEditor(properties)) {
            String tableName = "Temp_table";
            String tempColumnName = "Temp_column";
            String newColumnName = "New_column";
            tableEditor.createTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.addColumn(tableName, tempColumnName, "text");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.renameColumn(tableName, tempColumnName, newColumnName);
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropColumn(tableName, newColumnName);
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));
        }
    }
}
