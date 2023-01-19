package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;
import pl.edu.agh.debtexecutor.services.utils.PaginationService;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class Pagination extends HBox implements Initializable {
    private static final String FXML_PATH = "/fxml/controls/Pagination.fxml";

    @FXML private Button firstPageButton;
    @FXML private Button prevPageButton;
    @FXML private Button nextPageButton;
    @FXML private Button lastPageButton;
    @FXML private Label currentPageNumber;
    @FXML private Label totalPagesNumber;

    private PaginationService paginationService;

    public Pagination() {
        ResourceLoader.loadControlFXML(FXML_PATH, this);
    }

    public void setPaginationService(PaginationService paginationService) {
        this.paginationService = paginationService;
        setBindings();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstPageButton.setOnAction((v) -> paginationService.firstPage());
        prevPageButton.setOnAction((v) -> paginationService.prevPage());
        nextPageButton.setOnAction((v) -> paginationService.nextPage());
        lastPageButton.setOnAction((v) -> paginationService.lastPage());
    }

    private void setBindings() {
        PaginationOptions options = paginationService.getPaginationOptions();
        currentPageNumber.textProperty().bind(options.pageNumberProperty().asString());
        totalPagesNumber.textProperty().bind(options.totalPagesProperty().asString());
    }
}
