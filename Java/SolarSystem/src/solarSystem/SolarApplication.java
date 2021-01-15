package solarSystem;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class SolarApplication extends Application {
	
    private Image getImageFromResource(String resourcePath) {
        return new Image(this.getClass().getResourceAsStream(resourcePath));
    }
    
    private Scene makeScene(Group planets) {
    	 ImagePattern background = new ImagePattern(getImageFromResource("space.png"));
         return new Scene(planets, 1200, 900, background);
    }

	public void start(Stage stage) throws Exception {
		Sphere sun = new Sphere(75);
		sun.setTranslateX(600);
		sun.setTranslateY(450);
		PhongMaterial material_sun = new PhongMaterial();
        material_sun.setDiffuseMap(getImageFromResource("sunmap.jpg"));
        material_sun.setSpecularColor(Color.WHITE);
        material_sun.setSpecularPower(0.1);
        sun.setMaterial(material_sun);
		
		Sphere mercury = new Sphere(4.56);
		mercury.setTranslateX(725);
		mercury.setTranslateY(450);
		PhongMaterial material_mercury = new PhongMaterial();
        material_mercury.setDiffuseMap(getImageFromResource("mercurymap.jpg"));
        mercury.setMaterial(material_mercury);
		
		Sphere venus = new Sphere(11.4);
		venus.setTranslateX(775);
		venus.setTranslateY(450);
		PhongMaterial material_venus = new PhongMaterial();
        material_venus.setDiffuseMap(getImageFromResource("venusmap.jpg"));
        venus.setMaterial(material_venus);
		
		Sphere earth = new Sphere(12);
		earth.setTranslateX(825);
		earth.setTranslateY(450);
		PhongMaterial material_earth = new PhongMaterial();
        material_earth.setDiffuseMap(getImageFromResource("earthmap1k.jpg"));
        earth.setMaterial(material_earth);
		
		Sphere mars = new Sphere(6.36);
		mars.setTranslateX(900);
		mars.setTranslateY(450);
		PhongMaterial material_mars = new PhongMaterial();
        material_mars.setDiffuseMap(getImageFromResource("mars_1k_color.jpg"));
        mars.setMaterial(material_mars);
		
		Group planets = new Group();
		planets.getChildren().add(sun);
		planets.getChildren().add(mercury);
		planets.getChildren().add(venus);
		planets.getChildren().add(earth);
		planets.getChildren().add(mars);
		
		PerspectiveCamera camera = new PerspectiveCamera();
		
		Scene scene = makeScene(planets);
		
		scene.setCamera(camera);
		stage.setTitle("Solar System");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
