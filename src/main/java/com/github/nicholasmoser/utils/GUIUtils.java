package com.github.nicholasmoser.utils;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * GUI utilities.
 */
public class GUIUtils {
  public static final String FONT_SIZE_CSS = "-fx-font-size: 26px;";

  public static final String BORDER = "-fx-effect: innershadow(gaussian, #039ed3, 2, 1.0, 0, 0);";

  private static final Image NARU_16 = new Image(GUIUtils.class.getResourceAsStream("naru16.gif"));

  private static final Image NARU_32 = new Image(GUIUtils.class.getResourceAsStream("naru32.gif"));

  private static final Image NARU_64 = new Image(GUIUtils.class.getResourceAsStream("naru64.gif"));

  private static final Image NARU_128 =
      new Image(GUIUtils.class.getResourceAsStream("naru128.gif"));

  /**
   * Creates a new loading window for a specified task.
   * 
   * @param title The title of the window.
   * @param task The task to perform.
   * @return The loading window.
   */
  public static Stage createLoadingWindow(String title, Task<?> task) {
    Stage loadingWindow = new Stage();
    loadingWindow.initModality(Modality.APPLICATION_MODAL);
    loadingWindow.initStyle(StageStyle.UNDECORATED);
    loadingWindow.setTitle(title);
    GUIUtils.setIcons(loadingWindow);

    GridPane flow = new GridPane();
    flow.setAlignment(Pos.CENTER);
    flow.setVgap(20);

    Text text = new Text();
    text.setStyle(FONT_SIZE_CSS);

    ProgressIndicator progressIndicator = new ProgressIndicator(-1.0f);

    GridPane.setHalignment(text, HPos.CENTER);
    GridPane.setHalignment(progressIndicator, HPos.CENTER);
    flow.add(text, 0, 0);
    flow.add(progressIndicator, 0, 1);
    flow.setStyle(BORDER);

    Scene dialogScene = new Scene(flow, 300, 200);
    loadingWindow.setScene(dialogScene);
    loadingWindow.show();

    progressIndicator.progressProperty().bind(task.progressProperty());
    text.textProperty().bind(task.messageProperty());

    return loadingWindow;
  }

  /**
   * Sets the application icons on the stage.
   * 
   * @param primaryStage The primary stage to set the icons for.
   */
  public static void setIcons(Stage primaryStage) {
    ObservableList<Image> icons = primaryStage.getIcons();
    icons.add(NARU_16);
    icons.add(NARU_32);
    icons.add(NARU_64);
    icons.add(NARU_128);
  }

  /**
   * Sets dark theme on the scene.
   *
   * @param scene The scene to make dark themed.
   */
  public static void toggleDarkMode(Scene scene) {
    List<String> stylesheets = scene.getStylesheets();
    if (stylesheets.isEmpty()) {
      scene.getStylesheets().add(GUIUtils.class.getResource("stylesheet.css").toExternalForm());
    } else {
      stylesheets.clear();
    }
  }
}
