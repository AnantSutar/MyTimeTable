package controller;

import java.util.ArrayList;

import dao.StudentCourseDao;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class CalendarController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	@FXML
	private Label ps;
	@FXML
	private Label ac;
	@FXML
	private Label m;
	@FXML
	private Label jp;
	@FXML
	private Label dm;
	@FXML
	private Label kt;
	@FXML
	private Label app;
	CalendarController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
		
    	
    }
	public void initialize() {
		jp.setVisible(false);
		kt.setVisible(false);
		app.setVisible(false);
		dm.setVisible(false);
		m.setVisible(false);
		ac.setVisible(false);
		ps.setVisible(false);
		ArrayList<String> array = new ArrayList<String>();
		StudentCourseDao scd = new StudentCourseDao();
		array = scd.Enrolledcourses(model, model.getcurrentStudent().getUsername());
		
		for(String s:array) {
			System.out.println(s);
			if(s.equals(ps.getText())) {
				ps.setVisible(true);
				
			}
			else if(s.equals(ac.getText())) {
				ac.setVisible(true);
			}
			else if(s.equals(m.getText())) {
				m.setVisible(true);
			}
			else if(s.equals(jp.getText())) {
				jp.setVisible(true);
				//break;
			}
			else if(s.equals(dm.getText())) {
				dm.setVisible(true);
			}
			else if(s.equals(kt.getText())) {
				kt.setVisible(true);
			}
			else if(s.equals(app.getText())) {
				app.setVisible(true);
			}
		}
		
	}
	public void showStage(Pane root) {
		Scene scene = new Scene(root);
		
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Calendar");
		stage.show();
	}
}
