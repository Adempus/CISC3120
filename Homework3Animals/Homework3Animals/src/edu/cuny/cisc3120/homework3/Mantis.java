package edu.cuny.cisc3120.homework3;

/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */
public class Mantis extends Insect
{

    public Mantis(int size) {
        super(size);
        diet = Diet.INSECTIVORE;
        isCannibal = true;
    }

    public boolean eat(Animal food) {
        if (super.canEat(food)) {
            System.out.printf("\nThe %s eats the %s.", getName(), food.getName());
            return true;
        } else {
            System.out.printf("It is an %s.", getDiet());
        }
        return false;
    }

    public void move() {
        System.out.printf("\nThe %s creeps along some branches.",
                getName());
    }

    public void layEgg() {

    }

    public String speak() {
        return "Hissssssssss.";
    }

    public int getSize() {
        return super.getSize();
    }
}
