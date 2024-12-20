package controller;

import java.util.List;

import dao.StudentDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;

public class EditdetailsController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	String newname;
	String surname;
	String pwd;
String status = "Updated ";
List<Boolean> st;
    @FXML
    private Label labelstatus;
	@FXML
    private TextField newfname;

    @FXML
    private TextField newlname;

    @FXML
    private TextField newpassword;
    
    @FXML
    private Button editbutton;
    @FXML
    private Button close;
    
    EditdetailsController(Stage parentstage,Model model){
    	this.stage = new Stage();
		
		this.parentStage = parentstage;
		this.model = model;
    	
    }
    
    public void initialize() {
    	StudentDaoImpl sdi = new StudentDaoImpl();
    	
    	editbutton.setOnAction(e->{
    		newname = newfname.getText();
        	surname = newlname.getText();
        	pwd = newpassword.getText();
        	if (newname.equals("")) {
        		
        		newname = model.getcurrentStudent().getfname();
        		
        	}
        	else {
        		status = status.concat("First Name");
        	}
        	if (surname.equals("")) {
        		
        		surname = model.getcurrentStudent().getlname();
        		
        	}
        	else {
        		status = status.concat("Last Name ");
        	}
        	if (pwd.equals("")) {
        		
        		pwd = model.getcurrentStudent().getPassword();
        		
        	}else {
        		status = status.concat("Password");
        	}
        	
        	if(status.length()==8) {
        		status = status.concat("Nothing");
        		labelstatus.setText(status);
        		labelstatus.setTextFill(Color.RED);
        	}
        	else {
        		labelstatus.setText(status);
        		labelstatus.setTextFill(Color.GREEN);
        	}
    	 	sdi.updateStudent(model.getcurrentStudent().getUsername(), newname, surname,pwd);
    	    model.getcurrentStudent().setfname(newname);
    	    model.getcurrentStudent().setlname(surname);
    	    model.getcurrentStudent().setPassword(pwd);
    	});
    	close.setOnAction(c->{
    		stage.close();
    		
			parentStage.show();
    	});
    }
    public void showStage(Pane root) {
		Scene scene = new Scene(root);
		
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Edit Details");
		stage.showAndWait();
	}

}
