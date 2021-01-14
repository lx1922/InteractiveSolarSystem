package solarSystem;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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

	public void start(Stage stage) throws Exception {
		stage.setTitle("Solar System");
		
		// makes the sun
		Sphere sun = new Sphere(75);
		
		// makes the mercury
		Sphere mercury = new Sphere(5);
		
		// center the sun
		sun.setTranslateX(600);
		sun.setTranslateY(450);
		
		Group Sun = new Group();
		Sun.getChildren().add(sun);
		Sun.getChildren().add(mercury);
		
        Ellipse ellipseMercury = getMercuryEllipse(sun);
        
        mercuryTransition(ellipseMercury, mercury);
        
		Sun.getChildren().add(ellipseMercury);
		
		camera = new PerspectiveCamera( true );
        camera.setTranslateX( 600 );
        camera.setTranslateY( 450 );
        camera.setTranslateZ( 0 );
        camera.setFieldOfView(90.0);
        camera.setFarClip(1500.0);
        camera.setNearClip(0.01);
		

		Scene scene = new Scene(Sun, 1200, 900);
		
		scene.setCamera( camera );
        scene.setOnMouseMoved( new MouseLook() );
        
		scene.setFill(Color.TRANSPARENT);
		
		stage.setScene(scene);
		stage.show();

	}
	
	public Ellipse getMercuryEllipse(Sphere sun) {
		Ellipse ellipseMercury = new Ellipse();
        ellipseMercury.setCenterX(sun.getTranslateX());
        ellipseMercury.setCenterY(sun.getTranslateY());

        ellipseMercury.setRadiusX(sun.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
        ellipseMercury.setRadiusY(sun.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);
        ellipseMercury.setStroke(Color.WHITE);
        ellipseMercury.setStrokeWidth(1);
        ellipseMercury.setFill(Color.TRANSPARENT);
        return ellipseMercury;
	}
	
	public void mercuryTransition(Ellipse ellipseMercury, Sphere mercury) {
		PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseMercury);
        transitionEarth.setNode(mercury);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(10.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);
        transitionEarth.play();
	}
	
	static class MouseLook implements EventHandler <MouseEvent> {
	    private static Rotate rotation;
	    private static int oldX, newX;
	    private static boolean alreadyMoved = false;

	    @Override
	    public void handle(MouseEvent event) {
	        if ( alreadyMoved ) {
	            newX = (int) event.getScreenX();

	            if ( oldX < newX ) { // if mouse moved to right
	                rotation = new Rotate( 10.0,
	                        // camera rotates around its location
	                        camera.getTranslateX(), camera.getTranslateY(), camera.getTranslateZ(),
	                        Rotate.Y_AXIS );


	            } else if ( oldX > newX ) { // if mouse moved to left
	                rotation = new Rotate( -10.0,
	                        // camera rotates around its location
	                        camera.getTranslateX(), camera.getTranslateY(), camera.getTranslateZ(),
	                        Rotate.Y_AXIS );

	            }
	            camera.getTransforms().addAll( rotation );

	            oldX = newX;
	        } else {
	            oldX = (int) event.getScreenX();
	            alreadyMoved = true;
	        }
	    }
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}






//private Image getImageFromResource(String resourcePath) {
//return new Image(this.getClass().getResourceAsStream(resourcePath));
//}













//HBox hBox = new HBox();
//Scene scene = new Scene(hBox, 1200, 900);
//hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//Sphere sphere = new Sphere(200);
//PhongMaterial material = new PhongMaterial();
////material.setDiffuseMap(getImageFromResource("sunmap.jpg"));
////material.setSpecularPower(1);
////sphere.setMaterial(material);
//hBox.getChildren().add(sphere);
//sphere.setTranslateX(400);
//sphere.setTranslateY(250);
//RotateTransition rotateTransition = new RotateTransition();
//rotateTransition.setNode(sphere);
//rotateTransition.setDuration(Duration.seconds(15));
//rotateTransition.setAxis(Rotate.Y_AXIS);
//rotateTransition.setFromAngle(0);
//rotateTransition.setToAngle(360);
//rotateTransition.setInterpolator(Interpolator.LINEAR);
//rotateTransition.setCycleCount(Animation.INDEFINITE);
//rotateTransition.play();
////PerspectiveCamera camera = new PerspectiveCamera();
////camera.setTranslateY(-100);
////scene.setCamera(camera);
//stage.setScene(scene);
//stage.show();