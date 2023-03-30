package com.example;

import java.nio.file.Files;
import java.nio.file.Path;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyAppController {
	private String path = "data.txt";

	@FXML
	private TextField inputField;

	@FXML
	private Label txtLabel;

	@FXML
	private Button saveBtn;

	@FXML
	private Button loadBtn;

	public void initialize() {
		saveBtn.setOnAction(e -> {
			var saveData = inputField.getText();
			try {
				Files.writeString(Path.of(path), saveData);
			} catch (Exception err) {
				err.printStackTrace();
			}

		});

		loadBtn.setOnAction(e -> {
			String loadData = "";
			try {
				loadData = Files.readString(Path.of(path));
				txtLabel.setText(loadData);
			} catch (Exception err) {
				err.printStackTrace();
			}
		});

	}
}
