package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.lang.Math;

import static java.lang.System.out;

public class GrassField extends AbstractWorldMap {


    private static final Vector2d LOWER_LEFT_MAP = new Vector2d(0, 0);
    private final int fieldsOfGrass;
    public GrassField(int fieldsOfGrass) {
        this.fieldsOfGrass = fieldsOfGrass;
        putGrassFields();
    }

    public int getFieldsOfGrass() {
        return fieldsOfGrass;
    }

    protected List<Grass> grass = new ArrayList<>();
    protected List<Animal> animals = new ArrayList<>();

    Random rand = new Random();

    int MAX_VALUE = (int)Double.POSITIVE_INFINITY;
    protected Vector2d sizeOfMap = new Vector2d(0, 0);

    public void putGrassFields () {
        for (int i = 0; i < fieldsOfGrass; i++) {
            int grassX = rand.nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
            int grassY = rand.nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
            Vector2d positionOfGrass = new Vector2d (grassX, grassY);
            while (isOccupiedGrass(positionOfGrass)) {
                grassX = rand.nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
                grassY = rand.nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
                positionOfGrass = new Vector2d (grassX, grassY);
            }
            Grass newGrass = new Grass(positionOfGrass);
            out.print(newGrass.getGrassPosition());
            sizeOfMap = newGrass.getGrassPosition().upperRight(sizeOfMap);
            grass.add(newGrass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(LOWER_LEFT_MAP)
                && !this.isOccupiedAnimal(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getAnimalPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupiedAnimal(Vector2d position) {
        return animals.stream()
                .anyMatch(animal -> Objects.equals(position, animal.getAnimalPosition()));
    }

    public boolean isOccupiedGrass(Vector2d position) {
        return grass.stream()
                .anyMatch(grass1 -> Objects.equals(position, grass1.getGrassPosition()));
    }


    public Animal objectAtAnimal(Vector2d position) {
        return animals.stream()
                .filter(animal -> Objects.equals(position, animal.getAnimalPosition()))
                .findFirst()
                .orElse(null);
    }

    public Grass objectAtGrass(Vector2d position) {
        return grass.stream()
                .filter(grass -> Objects.equals(position, grass.getGrassPosition()))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Object objectAt(Vector2d position) {
        if (objectAtAnimal(position) != null) {
            return objectAtAnimal(position);
        }
        return objectAtGrass(position);
    }

    @Override
    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        out.print(sizeOfMap);
        return drawing.draw(LOWER_LEFT_MAP, sizeOfMap);
    }
}
