package edu.cuny.cisc3120.homework3;

/**
 * Created by Jeff Morin on 2/26/16.
 */
public abstract class Bird extends Animal {
    public Bird(int size) {
        super(size);
    }

    public boolean eat(Animal food) {
        return canEat(food);
    }
    public boolean eat(Plant food) {
        return canEat(food);
    }
}
