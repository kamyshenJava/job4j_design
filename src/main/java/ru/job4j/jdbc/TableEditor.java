package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public void actionWithTable(Statement statement, String action, String tableName) throws Exception {
        String middleWord = "";
        String addInfo = "";
        if ("create".equals(action)) {
            middleWord = "not";
            addInfo = "()";
        }
        String sql = String.format(
                "%s table if %s exists %s%s;",
                action,
                middleWord,
                tableName,
                addInfo
        );
        statement.execute(sql);
    }

    public void actionWithColumn(Statement statement, String action, String tableName,
                          String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s %s COLUMN %s %s;",
                tableName,
                action,
                columnName,
                type
        );
        statement.execute(sql);
    }

    public String getTableScheme(Statement statement, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
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

    private static Properties setProperties(String path) {
        Config config = new Config(path);
        config.load();
        Properties properties = new Properties();
        properties.setProperty("driver", config.value("hibernate.connection.driver_class"));
        properties.setProperty("url", config.value("hibernate.connection.url"));
        properties.setProperty("login", config.value("hibernate.connection.username"));
        properties.setProperty("password", config.value("hibernate.connection.password"));
        return properties;
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        Properties properties = setProperties("app.properties");
        TableEditor tableEditor = new TableEditor(properties);
        Class.forName(tableEditor.properties.getProperty("driver"));
        String tableName = "Temp_table";
        String columnName = "Temp_column";
        String newColumnName = "New_column";
        try (Connection connection = tableEditor.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                tableEditor.actionWithTable(statement, "create", tableName);
                System.out.println(tableEditor.getTableScheme(statement, tableName));
                tableEditor.actionWithColumn(statement, "add", tableName, columnName, "text");
                System.out.println(tableEditor.getTableScheme(statement, tableName));
                tableEditor.actionWithColumn(statement, "rename", tableName, columnName,
                        "to " + newColumnName);
                System.out.println(tableEditor.getTableScheme(statement, tableName));
                tableEditor.actionWithColumn(statement, "drop", tableName, newColumnName, "");
                System.out.println(tableEditor.getTableScheme(statement, tableName));
                tableEditor.actionWithTable(statement, "drop", tableName);
                System.out.println(tableEditor.getTableScheme(statement, tableName));
            }
        }
    }
}
