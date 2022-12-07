package agh.ics.oop;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {

    int POSITIVE_INFINITY = Integer.MAX_VALUE;
    int NEGATIVE_INFINITY = Integer.MIN_VALUE;
    public GrassField(int fieldsOfGrass) {
        putGrassFields(fieldsOfGrass);
    }

    protected Map<Vector2d, Grass> grass = new HashMap<>();

    @Override
    public Vector2d countLowerLeft () {
        Vector2d lowerLeftResult = new Vector2d(POSITIVE_INFINITY, POSITIVE_INFINITY);

        for (Animal animal : animals.values()) {
            lowerLeftResult = animal.getAnimalPosition().lowerLeft(lowerLeftResult);
        }
        for (Grass grass1 : grass.values()) {
            lowerLeftResult = grass1.getGrassPosition().lowerLeft(lowerLeftResult);
        }
        return lowerLeftResult;
    }

    @Override
    public Vector2d countUpperRight () {
        Vector2d upperRightResult = new Vector2d(NEGATIVE_INFINITY, NEGATIVE_INFINITY);
        for (Animal animal : animals.values()) {
            upperRightResult = animal.getAnimalPosition().upperRight(upperRightResult);
        }
        for (Grass grass1 : grass.values()) {
            upperRightResult = grass1.getGrassPosition().upperRight(upperRightResult);
        }

        return upperRightResult;
    }

    public void putGrassFields (int fieldsOfGrass) {
        for (int i = 0; i < fieldsOfGrass; i++) {
            int grassX = new Random().nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
            int grassY = new Random().nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
            Vector2d positionOfGrass = new Vector2d (grassX, grassY);

            //collections.shuffle()
            //random generator - new random. nextInt(a, b);
            while (isOccupiedGrass(positionOfGrass)) {
                grassX = new Random().nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
                grassY = new Random().nextInt((int) Math.sqrt(fieldsOfGrass*10)+1);
                positionOfGrass = new Vector2d (grassX, grassY);
            }
            Grass newGrass = new Grass(positionOfGrass);
            grass.put(positionOfGrass, newGrass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!this.isOccupiedAnimal(position));

        // objectAt
        // obj instanceof grass
    }



    public boolean isOccupiedGrass(Vector2d position) {
        return grass.containsKey(position);
    }


    public Animal objectAtAnimal(Vector2d position) {
        return animals.getOrDefault(position, null);
    }

    public Grass objectAtGrass(Vector2d position) {
        return grass.getOrDefault(position, null);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (objectAtAnimal(position) != null) {
            return objectAtAnimal(position);
        }
        return objectAtGrass(position);
    }


}
