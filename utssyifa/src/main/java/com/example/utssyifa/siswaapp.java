package com.example.utssyifa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.*;

public class siswaapp extends Application {

    private TableView<siswa> table;
    private ObservableList<siswa> siswaData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        siswaData = FXCollections.observableArrayList(siswaservice.readData());

        // TableView
        table = new TableView<>();
        table.setItems(siswaData);
        table.getColumns().add(createColumn("NIM", "nim"));
        table.getColumns().add(createColumn("Nama", "nama"));
        table.getColumns().add(createColumn("Nilai", "nilai"));

        // Form to add new siswa
        TextField namaField = new TextField();
        TextField nimField = new TextField();
        TextField nilaiField = new TextField();
        Button addButton = new Button("Tambah");

        addButton.setOnAction(e -> {
            try {
                String nama = namaField.getText();
                String nim = nimField.getText();
                double nilai = Double.parseDouble(nilaiField.getText());
                siswa siswa = new siswa(nama, nim, nilai);
                siswaservice.addSiswa(siswa);
                siswaData.add(siswa);
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        VBox vbox = new VBox(10, new HBox(10, new Label("Nama:"), namaField),
                new HBox(10, new Label("NIM:"), nimField), new HBox(10, new Label("Nilai:"), nilaiField),
                addButton, table);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Aplikasi Pengelola Nilai Siswa");
        stage.show();
    }

    private TableColumn<siswa, String> createColumn(String title, String property) {
        TableColumn<siswa, String> column = new TableColumn<>(title);
        column.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNama()));
        return column;
    }
}

