package solarSystem;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
	private static PerspectiveCamera camera;
	
	private Image getImageFromResource(String resourcePath) {
		return new Image(this.getClass().getResourceAsStream(resourcePath));
	}

	public void start(Stage stage) throws Exception {
		stage.setTitle("Solar System");
		
		
		// makes the sun
		Sphere sun = new Sphere(75);
		
		// makes the mercury
		Sphere mercury = new Sphere(5);
		
		Sphere earth = new Sphere(5);
		
		// center the sun
		sun.setTranslateX(600);
		sun.setTranslateY(450);

		PhongMaterial material = new PhongMaterial();
		material.setDiffuseMap(getImageFromResource("sunmap.jpg"));
		material.setSpecularPower(0);
		sun.setMaterial(material);
	    
	    sun.setMaterial(material);
        
		Group Sun = new Group();
		Sun.getChildren().add(sun);
		Sun.getChildren().add(mercury);
		Sun.getChildren().add(earth);
		
        Ellipse ellipseMercury = getMercuryEllipse(sun);
        
        mercuryTransition(ellipseMercury, mercury);
        
        Ellipse ellipseEarth = getEarthEllipse(sun);
        
        earthTransition(ellipseEarth, earth);
        
		Sun.getChildren().add(ellipseEarth);
		Sun.getChildren().add(ellipseMercury);

		Scene scene = new Scene(Sun, 1200, 900);
        
		scene.setFill(Color.BLACK);
		
		stage.setScene(scene);
		stage.show();

	}
	
	public Ellipse getMercuryEllipse(Sphere sun) {
		Ellipse ellipseMercury = new Ellipse();
        ellipseMercury.setCenterX(sun.getTranslateX());
        ellipseMercury.setCenterY(sun.getTranslateY());
        ellipseMercury.setRadiusX(sun.getBoundsInLocal().getWidth() / 2.0 + 0.466697  * 170);
        ellipseMercury.setRadiusY(sun.getBoundsInLocal().getHeight() / 2.0 + 0.307499 * 170);
        ellipseMercury.setStroke(Color.WHITE);
        ellipseMercury.setStrokeWidth(1);
        ellipseMercury.setFill(Color.TRANSPARENT);
        return ellipseMercury;
	}
	
	public Ellipse getEarthEllipse(Sphere sun) {
		Ellipse ellipseEarth = new Ellipse();
        ellipseEarth.setCenterX(sun.getTranslateX());
        ellipseEarth.setCenterY(sun.getTranslateY());
        ellipseEarth.setRadiusX(sun.getBoundsInLocal().getWidth() / 2.0 + 1.01671388  * 170);
        ellipseEarth.setRadiusY(sun.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
        ellipseEarth.setStroke(Color.WHITE);
        ellipseEarth.setStrokeWidth(1);
        ellipseEarth.setFill(Color.TRANSPARENT);
        return ellipseEarth;
	}
	
	public void mercuryTransition(Ellipse ellipseMercury, Sphere mercury) {
		PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseMercury);
        transitionEarth.setNode(mercury);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(2.40846));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
        transitionEarth.play();
	}
	
	public void earthTransition(Ellipse ellipseEarth, Sphere earth) {
		PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(earth);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(10.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
        transitionEarth.play();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}