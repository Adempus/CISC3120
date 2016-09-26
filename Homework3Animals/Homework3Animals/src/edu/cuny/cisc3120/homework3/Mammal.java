package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public abstract class Mammal extends Animal {
    public Mammal (int size) {
        super(size);
    }

    public boolean eat(Animal food) {
        return canEat(food);
    }

    public boolean eat(Plant food) {
        return canEat(food);
    }
}
