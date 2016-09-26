package edu.cuny.cisc3120.homework3;

/**
 * Created by Jeff Morin on 3/7/16.
 */
public class Capuchin extends Mammal {
    public Capuchin(int size) {
        super(size);
        diet = Diet.OMNIVORE;
        isCannibal = false;
    }

    public boolean eat(Object food) {
        if (canEat(food)) {
            System.out.printf("\nThe %s eats the %s.", getName(),
                    ((Animal) food).getName());
            ((Animal) food).isAlive = false;
            return true;
        } else {
            return false;
        }
    }

    public String speak() {
        return "SCREECH, SCREECH! ";
    }

    public void move() {
        System.out.printf("\nThe %s swings from branch to branch.",
                getName());
    }
}
