package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public class Rat extends Mammal {
    public Rat(int size) {
        super(size);
        diet = Diet.OMNIVORE;
        isCannibal = false;
    }

    public String speak() {
        return "squeak, squeak";
    }

    public void move() {
            System.out.printf("\nThe %s scurries across the plain.",
                    getName());
    }

    public boolean eat(Animal food) {
        if (canEat(food)) {
            System.out.printf("\nThe %s eats the %s.", getName(), food.getName());
            food.isAlive = false;
            return true;
        }
        return false;
    }
}
