package agh.ics.oop;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class App extends Application implements IPositionChangeObserver {
    private AbstractWorldMap map;
    private final GridPane grid = new GridPane();
    private final VBox userArgs = new VBox(grid, addStartButtonAndTextField());
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private SimulationEngine engine;
    private final int height = 50;
    private final int width = 50;


    @Override
    public void init() {
        try {
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            engine = new SimulationEngine(map, positions, this);
        }
        catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    private VBox addStartButtonAndTextField() {
        TextField textField = new TextField();
        Button button = new Button("Let's get this party started!");
        button.setOnAction(actionEvent -> startEngine(textField));
        return new VBox(textField, button);
    }

    private void startEngine(TextField textField) {
        String userArgs = textField.getText();
        String[] helpDirections = userArgs.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(helpDirections);
        engine.setDirections(directions);
        Thread engineThread = new Thread(engine);
        engineThread.start();
    }

    public void start(Stage primaryStage) throws FileNotFoundException {
        restartMap();
        primaryStage.setTitle("zwierzaki by sxevush");
        Scene scene = new Scene(userArgs, 20 * width, 20 * height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void restartMap() throws FileNotFoundException {
        checkTheBoundaries();
        clearTheMap();
        makeTheMap();
        makeLabelXY();
        makeColumns(lowerLeft, upperRight);
        makeRows(lowerLeft, upperRight);
        placeObjects(lowerLeft, upperRight);
    }

    private void checkTheBoundaries() {
        lowerLeft = map.countLowerLeft();
        upperRight = map.countUpperRight();
    }

    private void makeTheMap() {
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));
    }

    private void clearTheMap() {
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
    }

    private void makeLabelXY() {
        Label xy = new Label("y/x");
        GridPane.setHalignment(xy, HPos.CENTER);
        grid.add(xy, 0, 0);
    }

    private void placeObjects(Vector2d lowerLeft, Vector2d upperRight) throws FileNotFoundException {
        for (int x = lowerLeft.x; x <= upperRight.x; x++){
            for (int y = lowerLeft.y; y <= upperRight.y; y++){
                Vector2d position = new Vector2d(x, y);
                if (map.objectAt(position) != null) {
                    IMapElement object = map.objectAt(position);
                    VBox vbox = new GuiElementBox(object).getVerticalBox();
                    grid.add(vbox, position.x - lowerLeft.x + 1, upperRight.y - position.y + 1);
                    GridPane.setHalignment(vbox, HPos.CENTER);
                }
            }
        }
    }

    private void makeRows(Vector2d lowerLeft, Vector2d upperRight) {
        for (int i = 1; i <= upperRight.y - lowerLeft.y + 1; i++){
            Label label = new Label("" + (upperRight.y - i + 1));
            grid.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i);
        }
    }

    private void makeColumns(Vector2d lowerLeft, Vector2d upperRight) {
        for (int i = 1; i <= upperRight.x - lowerLeft.x + 1; i++){
            Label label = new Label("" + ((lowerLeft.x + i) - 1));
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i, 0);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {
            try {
                restartMap();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


}