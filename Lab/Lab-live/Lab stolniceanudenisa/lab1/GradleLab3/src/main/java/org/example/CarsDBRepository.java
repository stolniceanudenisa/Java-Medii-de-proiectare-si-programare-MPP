package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository {

    private JdbcUtils dbUtils;


//    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
//        logger.info("Initializing CarsDBRepository with properties: {} ",props);

        dbUtils=new JdbcUtils(props);

    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE manufacturer = ?";
        try (
                Connection connection = dbUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, manufacturerN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String manu = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");

                Car car = new Car(manu, model, year);
                car.setId(id);
                cars.add(car);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE year BETWEEN ? AND ?";
        try (
                Connection connection = dbUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String manu = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");

                Car car = new Car(manu, model, year);
                car.setId(id);
                cars.add(car);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }



    @Override
    public void add(Car elem) {
        String sql = "INSERT INTO cars (manufacturer, model, year) VALUES (?, ?, ?)";
        try (
                Connection connection = dbUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, elem.getManufacturer());
            preparedStatement.setString(2, elem.getModel());
            preparedStatement.setInt(3, elem.getYear());

            preparedStatement.executeUpdate();
//            logger.info("Car added successfully: {}", elem);
        } catch (SQLException e) {
//            logger.error("Error adding car: {}", e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void update(Integer integer, Car elem) {
        String sql = "UPDATE cars SET manufacturer=?, model=?, year=? WHERE id=?";
        try (
                Connection connection = dbUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, elem.getManufacturer());
            preparedStatement.setString(2, elem.getModel());
            preparedStatement.setInt(3, elem.getYear());
            preparedStatement.setInt(4, integer);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
public Iterable<Car> findAll() {
//    logger.traceEntry();
    List<Car> cars = new ArrayList<>();
    String sql = "SELECT * FROM cars";

    try (
            Connection connection = dbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
    ) {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String manu = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int year = resultSet.getInt("year");

            Car car = new Car(manu, model, year);
            car.setId(id);
            cars.add(car);
        }

    } catch (SQLException e) {
//        logger.error("Error retrieving cars from the database: {}", e.getMessage());
        e.printStackTrace();
    }
//    logger.traceExit(cars);
    return cars;
}


//    private int getLowId() {
//        String sql = "SELECT id FROM cars ORDER BY id DESC";
//        try (
//                Connection connection = dbUtils.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("id") + 1;
//            } else {
//                return 1;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }
//

//    private void createCarsTableIfNotExists() {
//        try (Connection con = dbUtils.getConnection();
//             Statement stmt = con.createStatement()) {
//            stmt.execute("CREATE TABLE IF NOT EXISTS cars ("
//                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + "manufacturer VARCHAR(20) NOT NULL,"
//                    + "model VARCHAR(20) NOT NULL,"
//                    + "year INTEGER NOT NULL"
//                    + ")");
//        } catch (SQLException e) {
//            logger.error("Error creating cars table: {}", e.getMessage());
//        }
//    }
//
//
//    public void clear() {
//        String sql = "DELETE FROM cars";
//        try (
//                Connection connection = dbUtils.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//            preparedStatement.executeUpdate();
//            logger.info("All records deleted from the cars table.");
//        } catch (SQLException e) {
//            logger.error("Error clearing the cars table: {}", e.getMessage());
//            e.printStackTrace();
//        }
//    }


}