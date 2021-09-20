module com.lyndexter.computer_graphics {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lyndexter.computer_graphics to javafx.fxml;
    exports com.lyndexter.computer_graphics;
    exports com.lyndexter.computer_graphics.lab3;
    exports com.lyndexter.computer_graphics.lab4;
    opens com.lyndexter.computer_graphics.lab3 to javafx.fxml;
    opens com.lyndexter.computer_graphics.lab4 to javafx.fxml;
}