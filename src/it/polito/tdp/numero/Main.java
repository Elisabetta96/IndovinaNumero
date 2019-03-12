package it.polito.tdp.numero;
	
import it.polito.tdp.numero.model.NumeroModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			//controller.setModel(model);
			// non so dove prendere il controller. FXMLLoader mi deve ridare anche il controller 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Numero.fxml"));
			
			
			
			BorderPane root = (BorderPane)loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			NumeroModel model = new NumeroModel ();
			// ora che ho creato l'oggetto loader, posso chiedere direttamente a lui qual è il controller che ha creato per questo caso. 
			NumeroController controller= loader.getController();
			// ora si 
			controller.setModel(model);
			
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
