package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dao.CourseDao;
import dao.CourseDetails;
import dao.StudentCourseDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.TableColumn;

public class ShowenrolledController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	HashMap<String, CourseDetails> cdets = new LinkedHashMap<String, CourseDetails>();
	@FXML
	private TableView<CourseDetails> tableenrolledcourses;
	@FXML
	private TableColumn<CourseDetails,String> coursecolumn;
	@FXML
	private TableColumn<CourseDetails,String> modecolumn;
	@FXML
	private TableColumn<CourseDetails,String> daycolumn;
	@FXML
	private TableColumn<CourseDetails,String> timecolumn;
	@FXML
	private MenuBar viewmenu;
	@FXML
	private MenuItem menulistview;
	@FXML
	private MenuItem menuttview;
	@FXML
    private Button closebutton;
	  @FXML
	    private Label enrollcoursessize;
	ObservableList<CourseDetails> list = FXCollections.observableArrayList(
			);
    
    
    ShowenrolledController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
    	
    }
    
    public ArrayList populate() {
    	StudentCourseDao scd = new StudentCourseDao();
		ArrayList<String> array = new ArrayList<String>();
		array = scd.Enrolledcourses(model, model.getcurrentStudent().getUsername());
    	CourseDao cd = new CourseDao();
		cdets = cd.getCourseDBinfo();
		int i=0;
		
		for (String s : cdets.keySet()) {
			
			i=i+1;
			if(array.contains(s)) {
			CourseDetails b = cdets.get(s);
			list.add(new CourseDetails(
			        b.getcoursename(),
			        b.getcapacity(),
			        b.getyear(),
			        b.getdelmode(),
			        b.getday(),
			        b.gettimes(),
			        b.getduration()
			    ));
			}
		}
		return array;
    }
    public void initialize() {
    	
    	ArrayList<String> array = new ArrayList<String>();
    	array=populate();
		if(array.isEmpty()) {
			enrollcoursessize.setText("You haven't enrolled in any course");
			enrollcoursessize.setTextFill(Color.RED);
			tableenrolledcourses.setVisible(false);
		}
		else {
			enrollcoursessize.setText("You are Enrolled in "+ array.size()+" courses");
			enrollcoursessize.setTextFill(Color.GREEN);
		}
		
		coursecolumn.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getcoursename()));
		
		
		modecolumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getdelmode()));
		daycolumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getday()));
		timecolumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().gettimes()));
		tableenrolledcourses.setItems(list);
		
		menuttview.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Calendar.fxml"));
			CalendarController cc =  new CalendarController(stage, model);
			loader.setController(cc);
			Pane root;
			try {
				root = loader.load();
				cc.showStage(root);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		stage.setTitle("Show Enrolled Courses");
		stage.show();
	}
}