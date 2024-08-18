public class Animal {
    public String name, noise;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.noise = "Huh?";
    }

    public void greet() {
        System.out.println("Animal " + name + " says: " + this.noise);
    }

    public void play() {
        System.out.println("Woo it is so much fun being an animal!");
    }
}

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
        this.noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + this.noise);
    }

    public void play(String expr) {
        System.out.println("Woo it is so much fun being a cat!" + expr);
    }
}

public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
        noise = "Woof!";
    }

    @Override
    public void greet() {System.out.println("Dog " + name + " says: " + this.noise);}

    public void play(int happiness) {
        if (happiness > 10) {
            System.out.println("Woo it is so much fun being a dog!")
        }
    }
}

public class TestAnimal {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);
        a.greet();                                  //Animal Pluto says: Huh?
        c.greet();                                  //Cat Garfield says: Meow!
        d.greet();                                  //Dog Fido says: Woof!
        c.play();                                   //Woo it is so much fun being an animal!
        c.play(":)");                          //Woo it is so much fun being a cat!" :)
        a = c;
        ((Cat) a).greet();                          //Cat Garfield says: Meow!
        ((Cat) a).play(":D");                   //Woo it is so much fun being a cat!" :D
        a.play(14);                                     // compile time error
        ((Dog) a).play(12);                    //runtime error
        a.greet();                                          //Cat Pluto says: Meow!
        c = (Cat)a;                                          //compile time error
    }
}
