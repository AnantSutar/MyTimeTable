package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Student;

public class RegisterController {
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField lname;
	@FXML
	private TextField fname;
	@FXML
	private TextField sid;
	@FXML
	private Button registerbutton;
	@FXML
	private Button close;
	@FXML
	private Label status;
	
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public RegisterController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		registerbutton.setOnAction(event -> {
			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
				Student user;
				try {
					user = model.getStudentDao().createStudent(username.getText(), password.getText(),fname.getText(),lname.getText(),sid.getText());
					if (user != null) {
						status.setText("Created " + user.getUsername());
						status.setTextFill(Color.GREEN);
					} else {
						status.setText("Cannot create user");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText("User already exists");
					status.setTextFill(Color.RED);
				}
				
			} else {
				status.setText("Empty username or password");
				status.setTextFill(Color.RED);
			}
		});

		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}
}
