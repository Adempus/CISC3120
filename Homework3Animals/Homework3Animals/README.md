Assignment
==========

* I have implemented an abstract `Animal` class.  You may add methods to it, but it comes with:
	- `void speak()`
	- `int getSize()`
	- `void eat(Animal target)`
* You can add parameters to the constructor, but you cannot remove the size parameter!
* Create (in new files, but in the same package) at least two abstract subclasses of `Animal`, for example `Mammal` and `Reptile`.
* Create (in new files, but in the same package) at least two non-abstract (concrete) classes for each abstract class.  For example, `Rat` and `Cat`. 

* There are example `Insect` and `Mantis` classes in the project. 

* When `speak` is invoked on your most-derived classes, it should print out appropriate messages.
* When `eat` is invoked on your most-derived classes it should print out a message indicating if the eater successfully ate the eated. 
	- Make it interesting!  You can use the Types, size, and anything else you want to add to determine what to print out.  For example:
		- The giant dog refuses to eat the stinky skunk.
		- The giant dog chows down on the adorable kitten :`(
* Add your new animals in `main` where it says `// TODO` and make sure they behave correctly.
* Add a rule in `Animal` that prevents an `Animal` from eating itself, and that displays a message specific to this situation (something like: "The white grub is not hungry enough to eat itself.")

Zip up everything and e-mail to connor@sci.brooklyn.cuny.edu
