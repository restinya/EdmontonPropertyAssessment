module com.groupproject.edmontonpropertyassessment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.groupproject.edmontonpropertyassessment to javafx.fxml;
    exports com.groupproject.edmontonpropertyassessment;
}
