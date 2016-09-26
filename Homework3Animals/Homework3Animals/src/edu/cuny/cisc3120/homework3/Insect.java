package edu.cuny.cisc3120.homework3;

public abstract class Insect extends Animal {
	public Insect(int size)
    {
        // Insects are all very small!
        super(size);
    }

    public boolean eat(Animal food)
    {
        return canEat(food);
    }

    public boolean eat(Plant food)
    {
        return canEat(food);
    }
}
