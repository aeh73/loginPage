module testts {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.logging;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
