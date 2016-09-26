/**
 * Jeff Morin
 * CISC3120-TR
 * 2/26/16
 * */

package edu.cuny.cisc3120.homework3;

public abstract class Animal
{
    protected enum Diet {
        CARNIVORE, HERBIVORE, OMNIVORE, INSECTIVORE
    }

    protected int size;
    protected Diet diet;
    protected boolean isCannibal;
    protected boolean isAlive;

    public Animal(int size) {
        this.size = size;
        isAlive = true;
    }

    // All animals speak.
    abstract public String speak();
    // Animals can move
    public abstract void move();
    // All animals eat.
    public abstract boolean eat(Animal food);
    public abstract boolean eat(Plant food);

    // This method checks if foods are compatible with the animal's diet.
    protected boolean canEat(Object object)
    {
        Animal food = (Animal) object;

        boolean isCarnivoreMeal = (food instanceof Mammal
                || food instanceof Bird || food instanceof Reptile);
        boolean isHerbivoreMeal = (object instanceof Plant);
        boolean isInsectivoreMeal = (food instanceof Insect);
        boolean isOmnivoreMeal = (isCarnivoreMeal || isHerbivoreMeal || isInsectivoreMeal);

        if(food == this) {
            System.out.printf("\n%s cannot eat itself", getName());
            return false;
        }

        if (size >= food.getSize() || object instanceof Plant) {
            if (food.getName().equals(this.getName())) {
                if (isCannibal) {
                    return true;
                } else {
                    System.out.printf("\nA %s cannot eat another %s." +
                            " It is non-cannibalistic.",
                            this.getName(), food.getName());
                    return false;
                }
            }
            switch (diet) {
                case CARNIVORE : return isCarnivoreMeal;
                case HERBIVORE : return isHerbivoreMeal;
                case OMNIVORE  : return isOmnivoreMeal;
                case INSECTIVORE : return isInsectivoreMeal;
            }
        } else {
            System.out.printf("\nThe %s is too small to eat the %s. ",
                    getName(), food.getName());
        }

        return false;
    }

    // I implemented this for you.  It returns the name of the class without the package.
    protected String getName() {
        // This implementation gives us the name of the class.
        String className = getClass().getName();
        // The class name has the package in it, so for example it is:
        // edu.cuny/cisc3120.homework2.model.Iguana
        //
        // This code "splits" the string on the "."
        // character into an array containing the segments that were separated by the "."
        String[] segments = className.split("\\.");
        // Take the last segment, which is the name of this class.
        String name = segments[segments.length - 1];
        // Convert to lower case.
        name = name.toLowerCase();

        return name;
    }


    public String getDiet() {
        return diet.toString().toLowerCase();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getSize() {
        return size;
    }
}