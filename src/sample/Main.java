package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }


    public static void main(String[] args) {
        //Product product=new Product("aa",10f,10f);
        //Addition addition=new Addition();
        //System.out.println(product.getTotal());
        //product.updatePrice(20f);
        //System.out.println(product.getTotal());

        launch(args);
    }
}
