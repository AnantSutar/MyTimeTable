package controller;

import java.util.ArrayList;

import dao.StudentCourseDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Label;

public class WithdrawcourseController {
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private ListView<String> withdrawcourseslist;
	@FXML
	private Label selcourselabel;
	@FXML
	private Button selcoursebutton;
	@FXML
	private Label withdrawstatus;
	@FXML
    private Button closebutton;
	
	ArrayList<String> array = new ArrayList<String>();

private ObservableList<String> names  = FXCollections.observableArrayList();;
	
	WithdrawcourseController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
    	
    }
	
	public void initialize() {
		StudentCourseDao scd = new StudentCourseDao();
		array = scd.Enrolledcourses(model, model.getcurrentStudent().getUsername());
		if (array.isEmpty()) {
			selcourselabel.setText("You haven't enrolled in any course");
			selcourselabel.setTextFill(Color.RED);
			withdrawcourseslist.setVisible(false);
			selcoursebutton.setVisible(false);
			withdrawstatus.setVisible(false);
		}
//		else {
//			selcourselabel.setText("You can withdraw from "+ array.size()+" courses!!");
//			selcourselabel.setTextFill(Color.RED);
//		}
		withdrawcourseslist.getItems().addAll(array);
		String course = withdrawcourseslist.getSelectionModel().getSelectedItem();
		withdrawcourseslist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		        selcourselabel.setText(newValue);
		        
		        selcoursebutton.setOnAction(e->{
					withdrawstatus.setText("Withdrew from "+newValue);
					withdrawstatus.setTextFill(Color.GREEN);
					scd.Withdrawcourses(model, newValue);
					
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
			stage.setTitle("Withdraw Course");
			stage.show();
		}
}
