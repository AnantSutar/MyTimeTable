package controller;




import java.io.IOException;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Student;

public class LoginsController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button loginbutton;
	@FXML
	private Button registerbutton;

	private Model model;
	private Stage stage;
	
	public LoginsController(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
	}
	@FXML
	public void initialize() {

		
		loginbutton.setOnAction(e->{
			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
				Student user;
				
				try {
					user = model.getStudentDao().getStudent(username.getText(),password.getText());
					if (user != null) {
						model.setcurrentStudent(user);
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
							DashboardController dashController = new DashboardController(stage, model);
							
							loader.setController(dashController);
							Pane root = loader.load();
							message.setText(null);
							dashController.showStage(root);
							stage.close();
						}catch (IOException ec) {
							message.setText(ec.getMessage());
						}
						
					} else {
						message.setText("Wrong username or password");
						message.setTextFill(Color.RED);
					}
				} catch (SQLException ex) {
					message.setText(ex.getMessage());
					message.setTextFill(Color.RED);
				}
				
			} else {
				message.setText("Empty username or password");
				message.setTextFill(Color.RED);
			}
			username.clear();
			password.clear();
		});
		
		registerbutton.setOnAction(eh->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));
				
				// Customize controller instance
				RegisterController regController =  new RegisterController(stage, model);

				loader.setController(regController);
				GridPane root = loader.load();
				
				regController.showStage(root);
				
				message.setText("");
				username.clear();
				password.clear();
				
				stage.close();
			} catch (IOException e) {
				message.setText(e.getMessage());
			}
		});
		
	}
	public void showStage(Pane root) {
		Scene scene = new Scene(root);
	//scene.getStylesheets().add("/view/styles.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome");
		stage.show();
	}
}
