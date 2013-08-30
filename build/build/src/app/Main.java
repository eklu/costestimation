package app;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		//load properties
		Properties properties = new Properties();    
        properties.load(Main.class.getResourceAsStream("/app.properties"));  
        DataUtil.reFactor = Double.parseDouble(properties.getProperty("reFactor"));
        DataUtil.costPerM3 = Integer.parseInt(properties.getProperty("costPerM3"));
        DataUtil.costPerKg = Integer.parseInt(properties.getProperty("costPerKg"));
        DataUtil.costPerM2 = Integer.parseInt(properties.getProperty("costPerM2"));
        
		AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("/AppLayout.fxml"));
		Scene scene = new Scene(page);

		stage.setScene(scene);
		stage.setTitle("Cost Estimation by Emmanuel Klu");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
