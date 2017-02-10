package application;

import backbone.SSH_Connection;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private TextField userTextField;
	private PasswordField pwBox;
	private Stage workWindow;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("GooeyStudent Login");
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));

			Scene scene = new Scene(grid, 600, 400);
			
			Text scenetitle = new Text("Log in to GooeyStudent");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Label userName = new Label("Username:");
			grid.add(userName, 0, 1);

			userTextField = new TextField();
			grid.add(userTextField, 1, 1);

			Label pw = new Label("Password:");
			grid.add(pw, 0, 2);

			pwBox = new PasswordField();
			grid.add(pwBox, 1, 2);
			
			Button btn = new Button("Sign in");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn);
			grid.add(hbBtn, 1, 4);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
			{

				@Override
				public void handle(WindowEvent arg0) {
					SSH_Connection.closeUp();
				}
				
			});
			
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	                onLogin();
	                primaryStage.close();
	            }
	        });
	        
	        pwBox.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	                onLogin();
	                primaryStage.close();
	            }
	        });

	        
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openWorkWindow()
	{
		workWindow = new Stage();
		
		workWindow.setTitle("GooeyStudent");
		
		BorderPane base = new BorderPane();
		TilePane surface = new TilePane();
		
		Button file = new Button();
		file.setStyle("-fx-background-image: url('/media/fileImage.png');" + "-fx-background-size: cover;");
		file.setMinSize(64, 64);
		Button folder = new Button();
		folder.setStyle("-fx-background-image: url('/media/folderImage.png');" + "-fx-background-size: cover;");
		folder.setMinSize(64, 64);
		
		surface.setTileAlignment(Pos.TOP_LEFT);
		surface.setHgap(10);
		surface.setVgap(10);
		surface.setPadding(new Insets(15, 15, 15, 15));
		
		surface.getChildren().add(file);
		surface.getChildren().add(folder);
		
		
		HBox topBox = new HBox();
		VBox leftBox = new VBox();
		TextField terminal = new TextField();
		
		topBox.setPadding(new Insets(15, 12, 15, 12));
		topBox.setSpacing(10);
		topBox.setStyle("-fx-background-color: #336699;");
		Text path = new Text(SSH_Connection.getPath());
		path.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
		topBox.getChildren().add(path);
		
		leftBox.setPadding(new Insets(15, 12, 15, 12));
		leftBox.setSpacing(10);
		leftBox.setStyle("-fx-background-color: #224466;");
		
		terminal.setPromptText("Input UNIX commands here.");
		terminal.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
                //take text typed in box and send to Student as command. Return Student's response.
            }
        });
		
		base.setLeft(leftBox);
		base.setTop(topBox);
		base.setCenter(surface);
		base.setBottom(terminal);
		
		Scene scene = new Scene(base, 600, 400);
		
		workWindow.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0) {
				SSH_Connection.closeUp();
			}
			
		});
		
		workWindow.setScene(scene);
		workWindow.show();
		
	}
	
	
	
	public void onLogin()
	{
		SSH_Connection.setUsername("smallcj");
		SSH_Connection.setPassword("900649073");		
		
//		SSH_Connection.setUsername(userTextField.getText());
//		SSH_Connection.setPassword(pwBox.getText());
		SSH_Connection.connect();
		openWorkWindow();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
