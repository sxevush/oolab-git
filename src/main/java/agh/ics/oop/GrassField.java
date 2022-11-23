package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {

    public GrassField(int fieldsOfGrass) {
        putGrassFields(fieldsOfGrass);
    }

    protected List<Grass> grass = new ArrayList<>();

    @Override
    public Vector2d countLowerLeft () {
        Vector2d lowerLeftResult = animals.get(0).getAnimalPosition();
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
        Vector2d upperRightResult = animals.get(0).getAnimalPosition();
        for (Animal animal : animals) {
            upperRightResult = animal.getAnimalPosition().upperRight(upperRightResult);
        }
        for (Grass grass1 : grass) {
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
            grass.add(newGrass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (!this.isOccupiedAnimal(position));

        // objectAt
        // obj instanceof grass
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
