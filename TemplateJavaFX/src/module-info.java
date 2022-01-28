module TemplateJavaFX {
	requires javafx.controls;
	requires java.logging;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
