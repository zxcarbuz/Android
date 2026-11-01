package edu.synergy.practice.examples;

/** Eclipse exercises: greeting, separate lines, integer age variable. */
public final class ConsoleExamples {
    private ConsoleExamples() {}
    public static String greeting(String fullName) {
        return "Здравствуйте, " + fullName + "!";
    }
    public static String ageLine(String name, int age) {
        if (age < 0 || age > 125) {
            throw new IllegalArgumentException("Invalid age");
        }
        return name + ", возраст: " + age;
    }
    public static void main(String[] args) {
        System.out.println(greeting("студент"));
        System.out.println("Раз");
        System.out.println("Два");
        System.out.println("Три");
        int age = 25;
        System.out.println(ageLine("Даниил", age));
    }
}