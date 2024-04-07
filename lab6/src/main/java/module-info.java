module org {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.Compulsory to javafx.fxml;
    exports org.Compulsory;
    opens org.Homework to javafx.fxml;
    exports org.Homework;
}
