package com.example.sanmak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DatabaseController extends InputController implements Initializable {

    @FXML
    private TableView<Runner> runnersTable;
    @FXML
    private TableColumn<Runner, String> nameCol;
    @FXML
    private TableColumn<Runner, Float> speedCol;
    @FXML
    private TableColumn<Runner, Double> radiusCol;
    @FXML
    private TableColumn<Runner, String> startTimeCol;
    @FXML
    private TableColumn<Runner, String> endTimeCol;
    @FXML
    private TableColumn<Runner, Float> durationCol;
    @FXML
    private TableColumn<Runner, Integer> lapCol;

    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanmac_interview",
                    "root", "RICHmond@?12");

            // jdbc:mysql://localhost:3306/?user=root
            // `sanmac_interview` is the database name
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Runner> getRunnersList() {
        ObservableList<Runner> runnerList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM runners";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Runner runner;
            while (resultSet.next()) {
                runner = new Runner(resultSet.getString("name"), 0, resultSet.getDouble("radius"),
                        resultSet.getString("start_time"), resultSet.getString("end_time"),
                        0, resultSet.getInt("lap_count"));

                calculateSpeedAndDuration(runner);

                runnerList.add(runner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return runnerList;
    }

    private void loadData() throws SQLException {

        ObservableList<Runner> list = getRunnersList();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        speedCol.setCellValueFactory(new PropertyValueFactory<>("speed"));
        radiusCol.setCellValueFactory(new PropertyValueFactory<>("radius"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        lapCol.setCellValueFactory(new PropertyValueFactory<>("lap"));

        runnersTable.setItems(list);
    }
}