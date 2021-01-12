package solarSystem;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarApplication extends Application {
	
    private Image getImageFromResource(String resourcePath) {
        return new Image(this.getClass().getResourceAsStream(resourcePath));
    }

	public void start(Stage stage) throws Exception {
		HBox hBox = new HBox();
		Scene scene = new Scene(hBox, 1200, 900);
		hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		Sphere sphere = new Sphere(200);
		PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(getImageFromResource("sunmap.jpg"));
        material.setSpecularPower(1);
	    sphere.setMaterial(material);
	    hBox.getChildren().add(sphere);
	    sphere.setTranslateX(400);
	    sphere.setTranslateY(250);
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(sphere);
        rotateTransition.setDuration(Duration.seconds(15));
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
    	PerspectiveCamera camera = new PerspectiveCamera(); 
	    camera.setTranslateX(0);
	    camera.setTranslateY(0);
	    camera.setTranslateZ(0);
	    camera.setRotate(40);
	    scene.setCamera(camera);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}