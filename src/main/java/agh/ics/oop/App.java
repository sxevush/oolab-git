package agh.ics.oop;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    private AbstractWorldMap map;
    @Override
    public void init() {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {

        Vector2d lowerLeft = map.countLowerLeft();
        Vector2d upperRight = map.countUpperRight();
        int height = 50;
        int width = 50;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));

        Label xy = new Label("y/x");
        GridPane.setHalignment(xy, HPos.CENTER);
        grid.add(xy, 0, 0);

        for (int i = 1; i <= upperRight.x - lowerLeft.x + 1; i++){
            Label label = new Label("" + ((lowerLeft.x + i) - 1));
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i, 0);
        }

        for (int i =1; i <= upperRight.y - lowerLeft.y + 1; i++){
            Label label = new Label("" + (upperRight.y - i + 1));
            grid.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i);
        }

        for (int x = lowerLeft.x; x <= upperRight.x; x++){
            for (int y = lowerLeft.y; y <= upperRight.y; y++){
                Vector2d position = new Vector2d(x, y);
                if (map.objectAt(position) != null) {
                    Object object = map.objectAt(position);
                    Label label = new Label(object.toString());
                    grid.add(label, position.x - lowerLeft.x + 1, upperRight.y - position.y + 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        primaryStage.setTitle("zwierzaki by sxevush");
        Scene scene = new Scene(grid, (upperRight.x - lowerLeft.x + 2) * width, (upperRight.y - lowerLeft.y + 2) * height);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}