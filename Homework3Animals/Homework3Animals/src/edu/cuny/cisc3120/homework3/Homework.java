/**
 * Jeff Morin
 * CISC3120-TR
 * 3/8/16
 * */

package edu.cuny.cisc3120.homework3;

class Homework {
    public static void main(String[] args) {
        Animal[] animals = {
                new Mantis(4),
                new Lion(30),
                new Pigeon(8),
                new Rat(6),
                new Capuchin(18),
                new Crocodile(32)
                // TODO:  Add your new mammals here.
        };

        // Check every combination of animals eating each other (And themselves!)
        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < animals.length; j++) {
                animals[i].move();
                animals[i].eat(animals[j]);
                System.out.printf("\n%s : %s\n", animals[i].getName(), animals[i].speak());
                System.out.println();
            }
        }
    }
}
