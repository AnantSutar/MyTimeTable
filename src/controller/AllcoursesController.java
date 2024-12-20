package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import dao.CourseDetails;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dao.CourseDao;

public class AllcoursesController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	HashMap<String, CourseDetails> cdets = new LinkedHashMap<String, CourseDetails>();
	
	@FXML
	private TableView<CourseDetails> table;
	
	
	@FXML
    private TableColumn<CourseDetails,Integer> cap;

    @FXML
    private TableColumn<CourseDetails,String> cname;

    @FXML
    private TableColumn<CourseDetails,String> day;

    @FXML
    private TableColumn<CourseDetails,String> dm;

    @FXML
    private TableColumn<CourseDetails,Double> dur;

    @FXML
    private TableColumn<CourseDetails,String> time;

    @FXML
    private TableColumn<CourseDetails,String> year;
    
    @FXML
    private TextField searchcourse;
    @FXML
    private Button closebutton;
    
    ObservableList<CourseDetails> list = FXCollections.observableArrayList(
			);
    
    
    AllcoursesController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
    	
    }
    
    public void populate() {
    	CourseDao cd = new CourseDao();
		cdets = cd.getCourseDBinfo();
		int i=0;
		
		for (String s : cdets.keySet()) {
			
			i=i+1;
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

	public void initialize() {
		populate();
		cname.setCellValueFactory(p-> new SimpleStringProperty(p.getValue().getcoursename()));
		cap.setCellValueFactory(p-> new ReadOnlyObjectWrapper<Integer>(p.getValue().getcapacity()));
		year.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getyear()));
		dm.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getdelmode()));
		day.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getday()));
		time.setCellValueFactory(p->new SimpleStringProperty(p.getValue().gettimes()));
		dur.setCellValueFactory(p-> new ReadOnlyObjectWrapper<Double>(p.getValue().getduration()));
		table.setItems(list);
		
		
		FilteredList<CourseDetails> fl = new FilteredList<>(list,b->true);
		
		
		
		searchcourse.textProperty().addListener((observable,oldValue,newValue)->{
			fl.setPredicate(CourseDetails->{
				String keyword = newValue;
				
				if (CourseDetails.getcoursename().toLowerCase().indexOf(keyword.toLowerCase())> -1) {
					return true;
				}
				else {
					return false;
				}
				
				
			});
		});
		SortedList<CourseDetails> sd = new SortedList<>(fl);
		sd.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sd);
		closebutton.setOnAction(e->{
			stage.close();
			parentStage.show();
		});
		
	}
	public void showStage(Pane root) {
		Scene scene = new Scene(root);
		
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Courses");
		stage.show();
	}
}
