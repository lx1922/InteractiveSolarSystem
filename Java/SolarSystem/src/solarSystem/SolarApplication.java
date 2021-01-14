package solarSystem;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarApplication extends Application {
	
//    private Image getImageFromResource(String resourcePath) {
//        return new Image(this.getClass().getResourceAsStream(resourcePath));
//    }

	public void start(Stage stage) throws Exception {
		stage.setTitle("Solar System");
		
		Sphere sun = new Sphere(75);
		Sphere mercury = new Sphere(5);
		sun.setTranslateX(600);
		sun.setTranslateY(450);
		
		Group Sun = new Group();
		Sun.getChildren().add(sun);
		Sun.getChildren().add(mercury);
		
        Ellipse ellipseEarth = new Ellipse();
        ellipseEarth.setCenterX(sun.getTranslateX());
        ellipseEarth.setCenterY(sun.getTranslateY());

        ellipseEarth.setRadiusX(sun.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
        ellipseEarth.setRadiusY(sun.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
        ellipseEarth.setStroke(Color.WHITE);
        ellipseEarth.setStrokeWidth(1);
        ellipseEarth.setFill(Color.TRANSPARENT);
        
        PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(mercury);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(10.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
 
        transitionEarth.play();
		Sun.getChildren().add(ellipseEarth);

		Scene scene = new Scene(Sun, 1200, 900);
		scene.setFill(Color.BLACK);
		
		
		stage.setScene(scene);
		stage.show();
//		HBox hBox = new HBox();
//		Scene scene = new Scene(hBox, 1200, 900);
//		hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//		Sphere sphere = new Sphere(200);
//		PhongMaterial material = new PhongMaterial();
////        material.setDiffuseMap(getImageFromResource("sunmap.jpg"));
////        material.setSpecularPower(1);
////	    sphere.setMaterial(material);
//	    hBox.getChildren().add(sphere);
//	    sphere.setTranslateX(400);
//	    sphere.setTranslateY(250);
//        RotateTransition rotateTransition = new RotateTransition();
//        rotateTransition.setNode(sphere);
//        rotateTransition.setDuration(Duration.seconds(15));
//        rotateTransition.setAxis(Rotate.Y_AXIS);
//        rotateTransition.setFromAngle(0);
//        rotateTransition.setToAngle(360);
//        rotateTransition.setInterpolator(Interpolator.LINEAR);
//        rotateTransition.setCycleCount(Animation.INDEFINITE);
//        rotateTransition.play();
////    	PerspectiveCamera camera = new PerspectiveCamera();
////    	camera.setTranslateY(-100);
////	    scene.setCamera(camera);
//		stage.setScene(scene);
//		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
