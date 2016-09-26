package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public class Lion extends Mammal {
    public Lion(int size) {
        super(size);
        diet = Diet.CARNIVORE;
        isCannibal = false;
    }

    public boolean eat(Animal food) {
        if (canEat(food)) {
            System.out.printf("\nThe %s eats the %s.", getName(), food.getName());
            food.isAlive = false;
            return true;
        } else {
            return false;
        }
    }

    public String speak() {
        return "ROOOOOOAAARR!!!";
    }

    public void move() {
        System.out.printf("\nThe %s is on the prowl.",
                getName());
    }
}
