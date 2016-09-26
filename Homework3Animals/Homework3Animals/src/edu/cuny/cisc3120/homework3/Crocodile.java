package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public class Crocodile extends Reptile {
    public Crocodile(int size) {
        super(size);
        diet = Diet.CARNIVORE;
        isCannibal = true;
    }

    public boolean eat(Animal food) {
        if(canEat(food)) {
            System.out.printf("\nThe %s chomps down on the %s", getName(), food.getName());
            food.isAlive = false;
            return true;
        } else if(food == this) {
            System.out.println("Cannot eat itself");
            return false;
        } else {
            return false;
        }
    }

    public void move() {
        System.out.printf("\nThe %s crawls sneakily across the plain.", getName());
    }

    public String speak() {
        return "CRRROOOOOOOAAAGGRRR!!!";
    }
}
