package controller;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;

import dao.CourseDao;
import dao.CourseDetails;
import dao.StudentCourseDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Label;

public class EnrollcourseController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	
	@FXML
	private ListView<String> courseslist;
	@FXML
	private Label selcourselabel;
	@FXML
	private Button selcoursebutton;
	@FXML
    private Button closebutton;
	@FXML
    private Label enrolledstatus;
	ArrayList<String> al = new ArrayList<String>();
	
	private ObservableList<String> names  = FXCollections.observableArrayList();
	
	EnrollcourseController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
    	
    }
	
	public void initialize() {
		HashMap<String, CourseDetails> cdets = new LinkedHashMap<String, CourseDetails>();
		
		CourseDao cd = new CourseDao();
		cdets = cd.getCourseDBinfo();
		
		for (String s : cdets.keySet()) {
			
			names.add(s);
				}
		
		courseslist.getItems().addAll(names);
		
		String course = courseslist.getSelectionModel().getSelectedItem();
		
		StudentCourseDao scd = new StudentCourseDao();
        scd.StudentCourseDB();
		
		courseslist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		        selcourselabel.setText(newValue);
		        
		        
		        selcoursebutton.setOnAction(e->{
					boolean cap = scd.CheckCapacityandmode(newValue);
					al = scd.Enrolledcourses(model, model.getcurrentStudent().getUsername());
					if (cap==true && !al.contains(newValue)) {
						enrolledstatus.setText("Enrolled in "+newValue);
						enrolledstatus.setTextFill(Color.GREEN);
					scd.AddStudentCourse(model,newValue);}
					else {
						enrolledstatus.setText("Couldn't Enroll in "+newValue);
						enrolledstatus.setTextFill(Color.RED);
					}
				});
		    }
		    
			
		});
		
		closebutton.setOnAction(e->{
			stage.close();
			parentStage.show();
		});
		
	}
	public void showStage(Pane root) {
		Scene scene = new Scene(root);
		
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Enroll Courses");
		stage.show();
	}	
}
