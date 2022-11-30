package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {

    private final int fieldsOfGrass;
    public GrassField(int fieldsOfGrass) {
        this.fieldsOfGrass = fieldsOfGrass;
        putGrassFields();
    }

    protected List<Grass> grass = new ArrayList<>();

    Random rand = new Random();

    @Override
    public Vector2d countLowerLeft () {
        Vector2d lowerLeftResult = new Vector2d(0, 0);
        for (Animal animal : animals) {
            lowerLeftResult = animal.getAnimalPosition().lowerLeft(lowerLeftResult);
        }
        for (Grass grass1 : grass) {
            lowerLeftResult = grass1.getGrassPosition().lowerLeft(lowerLeftResult);
        }
        return lowerLeftResult;
    }

    @Override
    public Vector2d countUpperRight () {
        Vector2d upperRightResult = new Vector2d(0, 0);
        for (Animal animal : animals) {
            upperRightResult = animal.getAnimalPosition().upperRight(upperRightResult);
        }
        for (Grass grass1 : grass) {
            upperRightResult = grass1.getGrassPosition().upperRight(upperRightResult);
        }

        return upperRightResult;
    }

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
//            upperRightMap = newGrass.getGrassPosition().upperRight(upperRightMap);
            grass.add(newGrass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!this.isOccupiedAnimal(position));
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


}
