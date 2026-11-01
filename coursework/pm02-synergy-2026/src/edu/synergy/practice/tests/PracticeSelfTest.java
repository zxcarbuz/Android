package edu.synergy.practice.tests;

import edu.synergy.practice.examples.Calculator;
import edu.synergy.practice.examples.ConsoleExamples;
import edu.synergy.practice.ui.SnowPanel;

/** No external dependencies: verifiable command-line test suite. */
public final class PracticeSelfTest {
    private static int passed;
    private static void equal(double actual,double expected,String name) {
        if (Math.abs(actual-expected) > 1e-9) throw new AssertionError(name);
        passed++; System.out.println("PASS: " + name);
    }
    private static void check(boolean condition,String name) {
        if (!condition) throw new AssertionError(name);
        passed++; System.out.println("PASS: " + name);
    }
    private static void throwsIllegal(Runnable test,String name) {
        try {test.run();throw new AssertionError(name+": error expected");}
        catch (IllegalArgumentException expected) {passed++;System.out.println("PASS: "+name);}
    }
    public static void main(String[] args) {
        equal(Calculator.calculate(10,5,'+'),15,"addition");
        equal(Calculator.calculate(10,5,'-'),5,"subtraction");
        equal(Calculator.calculate(10,5,'*'),50,"multiplication");
        equal(Calculator.calculate(10,5,'/'),2,"division");
        equal(Calculator.calculate(-4,2,'/'),-2,"negative division");
        equal(Calculator.calculate(0,5,'*'),0,"zero multiply");
        throwsIllegal(()->Calculator.calculate(5,0,'/'),"division by zero blocked");
        throwsIllegal(()->Calculator.calculate(2,3,'%'),"unknown operator blocked");
        throwsIllegal(()->Calculator.calculate(Double.NaN,2,'+'),"NaN blocked");
        check(ConsoleExamples.greeting("Иван").contains("Иван"),"greeting");
        check(ConsoleExamples.ageLine("Даниил",25).contains("25"),"age string");
        throwsIllegal(()->ConsoleExamples.ageLine("Даниил",-1),"negative age blocked");
        equal(SnowPanel.nextY(5,2,360),7,"snowflake movement");
        equal(SnowPanel.nextY(360,2,360),-23,"snowflake wrap");
        System.out.println("RESULT: " + passed + "/14 PASS");
    }
}