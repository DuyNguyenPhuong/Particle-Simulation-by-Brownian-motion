# Fruit

## Overview

The Fruit program contains a definition for the class Fruit and instantiates three fruits: apples, oranges, and bananas. Each of the fruits has a fruit name, which reflects the object reference, and a price. This program can:
- take a discount percentage from the command line
- prompt user input for the age of the customer. If the customer is less than 18 years of age, they receive an additional discount on each item.
- display the old prices of the fruits and then the discounted prices

## Usage
To use the Fruit program, run the following commands after downloading the code:
```
$ javac Fruit.java
$ java Fruit discount
```
where `discount` is the number specifying the class wide discount amount. For example, if all items are 50% off, enter 50. 
Then the program will print "You have 50% off".
```
$ java Fruit 50
$ You have 50% off
```
The program will then ask for the `age` of the customer: "Enter customer's age", take user's input, and print, for example: "Customer's age is: 15"

```
$ Enter customer's age
$ 15
$ Customer's age is: 15
```

After, the program will print to the terminal the total number of fruit types, their original prices, and the final prices after the discount:
```
$ Number of fruits: 3
$ Here are the original prices:
$ apple: $50.00
$ orange: $15.00
$ banana: $25.00
$ Here are the final prices: 
$ apple: $20.00
$ orange: $2.50
$ banana: $7.50
```
## Note on usage
If the `finalPrice` were to be less than 1 due to the discount and age discount, then the `finalPrice` is set to 1.

## Rubric

Here we discuss how our project meets the rubric requirements of HW1 as well as our response to the HW1 prompt.

### Compiles without warnings and runs

When our code compiles, it does not produce any warnings and it runs for all discount values. This can be seen by running the following:
``` java
$ javac Fruit.java
$ java Fruit 50
```
and
``` java
$ javac Fruit.java
$ java Fruit 50.5
```
and
``` java
$ javac Fruit.java
$ java Fruit -50
```

### New class created correctly

The class we created is called `Fruit` and is defined in the file `Fruit.java`.

### Private variable used correctly

One of the private variables for each instance of the class is its price, `finalPrice`. `finalPrice` is defined on line 23 of `Fruit.java`. Usage of this variable can be seen in the following code snippet of the `setFinalPrice` method:
``` java
    public void setFinalPrice(){
        this.finalPrice = this.fruitPrice * (1- floDiscount/100) - ageCompare(ageInt)*5;
        if (this.finalPrice<1){
            this.finalPrice = 1;
        }
    }
```

The `finalPrice` variable was made private so that it could not be accessed of our class. This is to make sure that the vegetables do not change the fruit prices and spoil the market (this is both a joke and hypothetical programming class interferance example).
### Class variable used correctly

The class variable we used `numberOfFruit`, which is defined on line 12 of `Fruit.java`. It is used to keep track of how many instances of the class have been made. Incrimenting of the class variable can be seen in the following code snippet from the constructor:
``` java
    numberOfFruit = numberOfFruit + 1;
```
In addition `numberOfFruit` is printed out to the console in the main function. This is done with the following code (line 100):
``` java
System.out.println("Number of fruits: " + numberOfFruit);
```
The `numberOfFruit` class variable was designed as such so that the total number of `Fruit` instances could be kept track of so that each fruit can access info on the total number of fruits. This is done because the `numberOfFruit` belongs to the class rather than each instance of the class.

### Instance of class created correctly

One of the instances we created of the `Fruit` class is `apple`. This instance was created with the following line of code (line 91):
``` java
Fruit apple = new Fruit("apple", 50);
```
Here we set the `fruitName` to "apple" and `fruitPrice` to 50.

### User input used correctly

We take user input age and use that to determine the 'finalPrice' of each object.

```java
    Scanner myObj = new Scanner(System.in);
    System.out.println("Enter customer's age");
    String userName = myObj.nextLine();
    myObj.close();
    System.out.println("Customer's age is: " + userName); 
```

We first chane user input into integer `ageInt`:
```java
    ageInt = Integer.parseInt(userName);
```
Then we use `ageInt` in the `ageCompare` method to evaluate the age of the customer. This is done determine if they qualify for an extra discount. `ageCompare` returns 1 if the customer is 18 years or younger, and 0 if they are older than 18. This returned value is used in the `setFinalPrice` method as follows:
``` java
    public void setFinalPrice(){
        this.finalPrice = this.fruitPrice * (1- floDiscount/100) - ageCompare(ageInt)*5;
        if (this.finalPrice<1){
            this.finalPrice = 1;
        }
    }
```

### Command line argument used correctly

This program requires that the user enter an discount value as a command line argument. 
```
$ java Fruit 50
```
After this is ran, the `discount` variable references the `String` "50"
``` java
    String discount = args[0];
```
which is then converted to the type `float` and is stored in the variable `floDiscount`:
``` java
    floDiscount = Float.parseFloat(discount);
```
Later `floDiscount` is used in the `setFinalPrice` method:
``` java
    public void setFinalPrice(){
        this.finalPrice = this.fruitPrice * (1- floDiscount/100) - ageCompare(ageInt)*5;
        if (this.finalPrice<1){
            this.finalPrice = 1;
        }
    }
```

### Useful output

Our program outputs to the console the total number of fruit types, the original prices of the fruits and their post-discount prices. This is done with the following code:

``` java
    System.out.println("Number of fruits: " + numberOfFruit);
    
    System.out.printf("Here are the original prices:\n");
    System.out.printf("%s: $%.2f\n", apple.fruitName, apple.fruitPrice);
    System.out.printf("%s: $%.2f\n", orange.fruitName, orange.fruitPrice);
    System.out.printf("%s: $%.2f\n", banana.fruitName, banana.fruitPrice);

    System.out.println("Here are the final prices: ");
    System.out.printf("%s: $%.2f\n", apple.fruitName, apple.getFinalPrice());
    System.out.printf("%s: $%.2f\n", orange.fruitName, orange.getFinalPrice());
    System.out.printf("%s: $%.2f\n", banana.fruitName, banana.getFinalPrice());
```
When ran with a discount of 50 and an age of 15, the console output is: 
```
$ Number of fruits: 3
$ Here are the original prices:
$ apple: $50.00
$ orange: $15.00
$ banana: $25.00
$ Here are the final prices: 
$ apple: $20.00
$ orange: $2.50
$ banana: $7.50
```

### Sufficient JavaDocs-style documentation

Here is the JavaDocs-style documentation for the `ageCompare` method as an example:
``` java
    /**
     * The ageCompare method determines if the customer is young enough to
     * receive the young persons' discount.
     * @param ageInt this is the age of the customer (type int).
     * @return 1 if the customer is 18 years old or younger, else 0.
     */
 ```
 
 ### Follows style guide
 
 Evidince for completion of this rubric item can be seen in the `Fruit.java` file. 
 
 




