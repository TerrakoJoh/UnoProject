module TemplateJavaFX {
	requires javafx.controls;
	requires java.logging;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
