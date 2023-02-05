module testts {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.logging;
	requires junit;
	requires aether.util;
	requires org.junit.platform.commons;
	
	opens application to javafx.graphics, javafx.fxml;
}
