import java.util.*;

// Базовий клас для цукерок
abstract class Candy {
    private String name;
    private double weight; // Вага в грамах

    public Candy(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    // Абстрактний метод для отримання характеристик
    public abstract double getChocolateContent(); 

    @Override
    public String toString() {
        return "Candy{name='" + name + "', weight=" + weight + "}";
    }
}

// Клас для шоколадних цукерок
class ChocolateCandy extends Candy {
    private double chocolateContent; // Вміст шоколаду (від 0 до 100%)

    public ChocolateCandy(String name, double weight, double chocolateContent) {
        super(name, weight);
        this.chocolateContent = chocolateContent;
    }

    @Override
    public double getChocolateContent() {
        return chocolateContent;
    }

    @Override
    public String toString() {
        return "ChocolateCandy{name='" + getName() + "', weight=" + getWeight() + ", chocolateContent=" + chocolateContent + "%}";
    }
}

// Клас для карамельних цукерок
class CaramelCandy extends Candy {
    private double caramelContent; // Вміст карамелі

    public CaramelCandy(String name, double weight, double caramelContent) {
        super(name, weight);
        this.caramelContent = caramelContent;
    }

    @Override
    public double getChocolateContent() {
        return 0; // Карамель не містить шоколаду
    }

    @Override
    public String toString() {
        return "CaramelCandy{name='" + getName() + "', weight=" + getWeight() + ", caramelContent=" + caramelContent + "%}";
    }
}

// Клас для дитячого подарунка
class Gift {
    private List<Candy> candies;

    public Gift() {
        this.candies = new ArrayList<>();
    }

    // Додавання цукерки до подарунка
    public void addCandy(Candy candy) {
        candies.add(candy);
    }

    // Загальна вага подарунка
    public double getTotalWeight() {
        double totalWeight = 0;
        for (Candy candy : candies) {
            totalWeight += candy.getWeight();
        }
        return totalWeight;
    }

    // Сортування цукерок за вагою
    public void sortCandiesByWeight() {
        candies.sort(Comparator.comparingDouble(Candy::getWeight));
    }

    // Пошук цукерок, що відповідають заданому діапазону вмісту шоколаду
    public List<Candy> findCandiesByChocolateContent(double min, double max) {
        List<Candy> result = new ArrayList<>();
        for (Candy candy : candies) {
            if (candy instanceof ChocolateCandy) {
                double chocolateContent = candy.getChocolateContent();
                if (chocolateContent >= min && chocolateContent <= max) {
                    result.add(candy);
                }
            }
        }
        return result;
    }

    // Виведення цукерок у подарунку
    public void printCandies() {
        for (Candy candy : candies) {
            System.out.println(candy);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // Створення цукерок
            ChocolateCandy chocolateCandy1 = new ChocolateCandy("Milk Chocolate", 50, 60); // 60% шоколаду
            ChocolateCandy chocolateCandy2 = new ChocolateCandy("Dark Chocolate", 70, 80); // 80% шоколаду
            CaramelCandy caramelCandy1 = new CaramelCandy("Caramel Delight", 30, 90); // 90% карамелі
            CaramelCandy caramelCandy2 = new CaramelCandy("Caramel Dream", 40, 85); // 85% карамелі

            // Створення подарунка
            Gift gift = new Gift();
            gift.addCandy(chocolateCandy1);
            gift.addCandy(chocolateCandy2);
            gift.addCandy(caramelCandy1);
            gift.addCandy(caramelCandy2);

            // Виведення цукерок у подарунку
            System.out.println("Candies in gift:");
            gift.printCandies();

            // Виведення загальної ваги подарунка
            System.out.println("Total weight of gift: " + gift.getTotalWeight() + " grams");

            // Сортування цукерок за вагою
            System.out.println("\nCandies sorted by weight:");
            gift.sortCandiesByWeight();
            gift.printCandies();

            // Пошук шоколадних цукерок з вмістом шоколаду від 70 до 80%
            System.out.println("\nCandies with chocolate content between 70% and 80%:");
            List<Candy> foundCandies = gift.findCandiesByChocolateContent(70, 80);
            for (Candy candy : foundCandies) {
                System.out.println(candy);
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
