package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dao.StudentCourseDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class DashboardController {
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	 @FXML
	    private Label usernamelabel;
@FXML
private Label lnamedash;
@FXML
private Label fnamedash;
@FXML
private Label siddash;
@FXML
private Button showcourses;
@FXML 
private Button enrollcourse;
@FXML
private Button showenrolled;

@FXML
private Button exportcourse;
@FXML
private Button withdrawcourse;
@FXML
private Button logout;

@FXML
private Button editdetails;

@FXML
private ImageView scimg;

@FXML
private ImageView secimg;
@FXML
private ImageView wcimg;
@FXML
private ImageView eudimg = new ImageView(new Image("editdetails.png"));;

@FXML
private ImageView eximg;
@FXML
private ImageView ecimg;

	DashboardController(Stage parentstage, Model model){
		this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
		
	}
	
	public void initialize() {
		
		
		editdetails.setGraphic(eudimg);
		
		usernamelabel.setText(model.getcurrentStudent().getUsername());

		fnamedash.setText(model.getcurrentStudent().getfname());
		lnamedash.setText(model.getcurrentStudent().getlname());
		siddash.setText(model.getcurrentStudent().getsid());
		showcourses.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Allcourses.fxml"));
			
			AllcoursesController regController =  new AllcoursesController(stage, model);
			loader.setController(regController);
			Pane root;
			try {
				root = loader.load();
				regController.showStage(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		enrollcourse.setOnAction(se->{
FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Enrollcourse.fxml"));
			
			EnrollcourseController ecc =  new EnrollcourseController(stage, model);
			loader.setController(ecc);
			Pane root;
			try {
				root = loader.load();
				ecc.showStage(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		editdetails.setOnAction(ed->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Editdetails.fxml"));
			EditdetailsController edc =  new EditdetailsController(stage, model);
			loader.setController(edc);
			Pane root;
			try {
				root = loader.load();
				edc.showStage(root);
				FXMLLoader fxmlLoader = new FXMLLoader();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fnamedash.setText(model.getcurrentStudent().getfname());
			lnamedash.setText(model.getcurrentStudent().getlname());
			siddash.setText(model.getcurrentStudent().getsid());
		});
		showenrolled.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Showenrolled.fxml"));
			ShowenrolledController se =  new ShowenrolledController(stage, model);
			loader.setController(se);
			Pane root;
			try {
				root = loader.load();
				se.showStage(root);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		withdrawcourse.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Withdrawcourse.fxml"));
			WithdrawcourseController se =  new WithdrawcourseController(stage, model);
			loader.setController(se);
			Pane root;
			try {
				root = loader.load();
				se.showStage(root);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		exportcourse.setOnAction(e->{
			ArrayList<String> array = new ArrayList<String>();
			StudentCourseDao scd = new StudentCourseDao();
			array=scd.Export(model);
			String name = model.getcurrentStudent().getUsername();
			String textfilename = name+"-Courses.txt";
			try {
			      FileWriter myWriter = new FileWriter(textfilename);
			      BufferedWriter bw = new BufferedWriter(myWriter);
			      
			      bw.write(model.getcurrentStudent().getUsername());
			      bw.write(" Courses");
			      bw.newLine();
			      int i=1;
			      for (String s : array){
			    	 
			    	  bw.write(i+")");
			    	  bw.write(s);
			    	  bw.newLine();
			    	  i=i+1;
		}
			      bw.close();
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException ee) {
			      System.out.println("An error occurred.");
			      ee.printStackTrace();
			    }
		});
		logout.setOnAction(e->{
			stage.close();
			parentStage.show();
		});
		
	}
	
	public void showStage(Pane root) {

		Scene scene = new Scene(root);
		

	//	scene.getStylesheets().add("/view/styles.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Dashboard");
		stage.show();
	}
}
