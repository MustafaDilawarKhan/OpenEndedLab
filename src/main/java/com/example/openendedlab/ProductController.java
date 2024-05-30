package com.example.openendedlab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {
    @FXML
    private TilePane tilePane;

    public void initialize() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productimg", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String imagePath = resultSet.getString("image_path");

                Product product = new Product(name, description, price, imagePath);
                addProductToTilePane(product);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProductToTilePane(Product product) {
        VBox vbox = new VBox(10);
        ImageView imageView = new ImageView(new Image("file:" + product.getImagePath()));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("$" + product.getPrice());

        vbox.getChildren().addAll(imageView, nameLabel, priceLabel);
        tilePane.getChildren().add(vbox);
    }
}
