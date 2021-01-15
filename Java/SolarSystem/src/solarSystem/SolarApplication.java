package solarSystem;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarApplication extends Application {
	
	private final static double SUN_X = 600;
	private final static double SUN_Y = 450;
	private double anchorX, anchorY;
	private double angleX = 0, angleY = 0;
	private final DoubleProperty AngleX = new SimpleDoubleProperty(0);
	private final DoubleProperty AngleY = new SimpleDoubleProperty(0);
	
    private Image getImageFromResource(String resourcePath) {
        return new Image(this.getClass().getResourceAsStream(resourcePath));
    }
    
    private Scene makeScene(RotateGroup planets) {
    	 ImagePattern background = new ImagePattern(getImageFromResource("space.png"));
         return new Scene(planets, 1200, 900, background);
    }
    
    private void mouseControl(RotateGroup group, Scene scene, Stage stage) {
    	Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
    	Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
    	group.getTransforms().add(xRotate);
    	group.getTransforms().add(yRotate);
    	xRotate.angleProperty().bind(AngleX);
    	yRotate.angleProperty().bind(AngleY);
    	
    	scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				anchorX = arg0.getSceneX();
				anchorY = arg0.getSceneY();
				angleX = AngleX.get();
				angleY = AngleY.get();
			}
    	});
    	
    	scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				AngleX.set(angleX - (anchorY - arg0.getSceneY()));
				AngleY.set(angleY + anchorX - arg0.getSceneX());
			}	
    	});
    	
    	stage.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent arg0) {
				double scroll_delta = arg0.getDeltaY();
				group.setTranslateZ(group.getTranslateZ() + scroll_delta);
			}
    	});
			
    }
    
    public void keyboardControl(RotateGroup group, Scene scene, Stage stage) {
    	stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent arg0) {
				switch (arg0.getCode()) {
					case Q:
						group.rotate_X();
						break;
					case E:
						group.rotateX();
						break;
					case W:
						group.rotateY();
						break;
					case S:
						group.rotate_Y();
						break;
					default:
						break;
				}
			}
    		
    	});
    }
    
    private Sphere setup(double radius, double x, double y, String image) {
    	Sphere obj = new Sphere(radius);
    	obj.setTranslateX(x);
    	obj.setTranslateY(y);
    	PhongMaterial material = new PhongMaterial();
    	material.setDiffuseMap(getImageFromResource(image));
    	obj.setMaterial(material);
    	return obj;
    }
    
    private Ellipse planet_Ellipse(double value1, double value2) {
    	Ellipse orbit = new Ellipse();
    	orbit.setCenterX(SUN_X);
    	orbit.setCenterY(SUN_Y);
    	orbit.setRadiusX(150 / 2.0 + value1 * 170);
    	orbit.setRadiusY(150 / 2.0 + value2 * 170);
        orbit.setStroke(Color.WHITE);
        orbit.setStrokeWidth(1);
        orbit.setFill(Color.TRANSPARENT);
        return orbit;
    }
    
    private void planet_Transition(Ellipse planet_ellipse, Sphere planet, double duration) {
		PathTransition transitionPlanet = new PathTransition();
        transitionPlanet.setPath(planet_ellipse);
        transitionPlanet.setNode(planet);
        transitionPlanet.setInterpolator(Interpolator.LINEAR);
        transitionPlanet.setDuration(Duration.seconds(duration));
        transitionPlanet.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionPlanet.setCycleCount(Timeline.INDEFINITE);
        transitionPlanet.play();
    }

	public void start(Stage stage) throws Exception {
		
		Sphere sun = setup(75, 600, 450, "sunmap.jpg");
		
		Sphere mercury = setup(4.56, 714, 450, "mercurymap.jpg");
		Ellipse mercury_ellipse = planet_Ellipse(0.466697, 0.307499);
		planet_Transition(mercury_ellipse, mercury, 2.40846);
		
		Sphere venus = setup(11.4, 747.3, 450, "venusmap.jpg");
		Ellipse venus_ellipse = planet_Ellipse(0.728213, 0.718440);
		planet_Transition(venus_ellipse, venus, 6.15198);
		
		Sphere earth = setup(12, 775, 450, "earthmap1k.jpg");
		Ellipse earth_ellipse = planet_Ellipse(1.01671388, 0.98329134);
		planet_Transition(earth_ellipse, earth, 10.000017421);
		
		Sphere mars = setup(6.36, 827.4, 450, "mars_1k_color.jpg");
		Ellipse mars_ellipse = planet_Ellipse(1.6660, 1.3814);
		planet_Transition(mars_ellipse, mars, 18.808);
		
		RotateGroup planets = new RotateGroup();
		planets.getChildren().addAll(mercury_ellipse, venus_ellipse, earth_ellipse, mars_ellipse);
		planets.getChildren().addAll(sun, mercury, venus, earth, mars);
		
		PerspectiveCamera camera = new PerspectiveCamera();
		
		Scene scene = makeScene(planets);
		scene.setCamera(camera);
		
		mouseControl(planets, scene, stage);
		keyboardControl(planets, scene, stage);
		
		stage.setTitle("Solar System");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class RotateGroup extends Group {
	Rotate r;
	Transform t = new Rotate();
	
	void rotateY() {
		r = new Rotate(1, Rotate.Y_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}
	
	void rotateX() {
		r = new Rotate(1, Rotate.X_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}
	
	void rotate_X() {
		r = new Rotate(-1, Rotate.X_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}
	
	void rotate_Y() {
		r = new Rotate(-1, Rotate.Y_AXIS);
		t = t.createConcatenation(r);
		this.getTransforms().clear();
		this.getTransforms().addAll(t);
	}
}
