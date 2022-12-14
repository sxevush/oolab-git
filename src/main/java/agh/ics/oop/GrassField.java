package agh.ics.oop;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {

    public GrassField(int fieldsOfGrass) {
        putGrassFields(fieldsOfGrass);
    }

    protected Map<Vector2d, Grass> grass = new HashMap<>();

    public boolean place(Animal animal) throws IllegalArgumentException{
        if (super.place(animal)){
            mapBoundary.addPosition(animal.getAnimalPosition());
            return true;
        }
        throw new IllegalArgumentException("cannot place animal on" + animal.getAnimalPosition());
    }

    @Override
    public Vector2d countLowerLeft () {
        return mapBoundary.countLowerLeft();
    }

    @Override
    public Vector2d countUpperRight () {
        return mapBoundary.countUpperRight();
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
            mapBoundary.addPosition(positionOfGrass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) { return (!this.isOccupiedAnimal(position)); }

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
    public IMapElement objectAt(Vector2d position) {
        if (objectAtAnimal(position) != null) {
            return objectAtAnimal(position);
        }
        return objectAtGrass(position);
    }


}
