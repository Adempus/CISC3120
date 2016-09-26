package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public class Pigeon extends Bird {
    public Pigeon(int size) {
        super(size);
        diet = Diet.HERBIVORE;
        isCannibal = false;
    }

    public String speak() {
        return "Hodododooooodoo...";
    }

    public boolean eat(Plant food) {
        if (canEat(food)) {
            System.out.printf("\nThe %s eats the %s. plant", getName());
            return true;
        } else {
            System.out.printf("It is a %s", getDiet());
            return false;
        }
    }

    @Override
    public void move() {
        System.out.printf("\nThe %s soars in the sky.",
                getName());
    }

    public void layEgg() {
        System.out.println("The " + getName() + " lays an egg.");
    }
}
