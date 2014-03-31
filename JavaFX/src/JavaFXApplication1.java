
package javafxapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
/**
 *
 * @author Cody
 */
public class JavaFXApplication1 extends Application {
     
    ImageView imageView_Source, imageView_GaussianBlur, imageView_BoxBlur;
    Slider sliderWidth, sliderHeight, sliderIterations;
     
    @Override
    public void start(Stage primaryStage) {
         
        Image image = new Image("http://goo.gl/kYEQl"); //Test image I'm going to use for class tomorrow. Copyright to Java Buddy
        imageView_Source = new ImageView();
        imageView_Source.setImage(image);
 
        imageView_BoxBlur = new ImageView();
        imageView_BoxBlur.setImage(image);
         
        imageView_GaussianBlur = new ImageView();
        imageView_GaussianBlur.setImage(image);
         
        HBox hBoxImage = new HBox();
        hBoxImage.getChildren().addAll(imageView_Source, 
                imageView_GaussianBlur, imageView_BoxBlur);
         
        sliderWidth = SliderBuilder.create()
                .prefWidth(300)
                .min(0.0)   //min
                .max(255.0) //max
                .value(5.0) //default
                .majorTickUnit(50)
                .showTickMarks(true)
                .showTickLabels(true)
                .tooltip(new Tooltip("Width"))
                .build();
         
        sliderHeight = SliderBuilder.create()
                .prefWidth(300)
                .min(0.0)   //min
                .max(255.0) //max
                .value(5.0) //default
                .majorTickUnit(50)
                .showTickMarks(true)
                .showTickLabels(true)
                .tooltip(new Tooltip("Height"))
                .build();
         
        sliderIterations = SliderBuilder.create()
                .prefWidth(300)
                .min(0.0)   //min
                .max(3.0)   //max
                .value(1.0) //default
                .majorTickUnit(1)
                .showTickMarks(true)
                .showTickLabels(true)
                .tooltip(new Tooltip("Iterations"))
                .build();
 
        Button btnProcess = new Button("Process...");
        btnProcess.setOnAction(btnProcessEventListener);
         
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                sliderWidth, sliderHeight, sliderIterations, 
                hBoxImage, btnProcess);
         
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 350, 330);
        primaryStage.setTitle("Blur the Image.");
        primaryStage.setScene(scene);
        primaryStage.show();
         
        updateEffect();
 
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
    EventHandler<ActionEvent> btnProcessEventListener
    = new EventHandler<ActionEvent>(){
 
        @Override
        public void handle(ActionEvent t) {
            updateEffect();
        }
    };
     
    private void updateEffect(){
        //Apply GaussianBlur
        GaussianBlur gaussianBlur = new GaussianBlur();
        imageView_GaussianBlur.setEffect(gaussianBlur);
         
        //Apply BoxBlur
        Double valueWidth = sliderWidth.valueProperty().doubleValue(); 
        Double valueHeight = sliderHeight.valueProperty().doubleValue();
        int valueIterations = sliderIterations.valueProperty().intValue();
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setWidth(valueWidth);
        boxBlur.setHeight(valueHeight);
        boxBlur.setIterations(valueIterations);
        imageView_BoxBlur.setEffect(boxBlur);
    }
}
