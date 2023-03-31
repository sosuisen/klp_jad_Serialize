package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import com.google.gson.GsonBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyAppController {
	private String path = "data.json";

	@FXML
	private TextField inputField;

	@FXML
	private Label txtLabel;

	@FXML
	private Button saveBtn;

	@FXML
	private Button loadBtn;

	public void initialize() {
		var memo = new Memo();

		// var gson = new Gson();
		var gson = new GsonBuilder().setPrettyPrinting().create();

		saveBtn.setOnAction(e -> {
			var saveData = inputField.getText();
			memo.setText(saveData);
			memo.setDate(LocalDate.now().toString());
			var json = gson.toJson(memo);
			try {
				Files.writeString(Path.of(path), json);
			} catch (Exception err) {
				err.printStackTrace();
			}

		});

		loadBtn.setOnAction(e -> {
			String loadData = "";
			try {
				loadData = Files.readString(Path.of(path));

				Memo memoObj = gson.fromJson(loadData, Memo.class);
				
				// txtLabel.setText(memoObj.getText() + "(" + memoObj.getDate() + ")");
				txtLabel.setText("%s(%s)".formatted(memoObj.getText(), memoObj.getDate()));
			} catch (Exception err) {
				err.printStackTrace();
			}
		});

	}
}
