package edu.synergy.practice;

import edu.synergy.practice.examples.Calculator;
import edu.synergy.practice.examples.ConsoleExamples;
import edu.synergy.practice.tests.PracticeSelfTest;
import edu.synergy.practice.ui.InfoFrame;
import javax.swing.SwingUtilities;

/** Entry point for the PM.02 training project. */
public final class Main {
    private Main() {}
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "console" -> ConsoleExamples.main(new String[0]);
                case "calc" -> Calculator.main(new String[0]);
                case "test" -> PracticeSelfTest.main(new String[0]);
                default -> {
                    System.err.println("Unknown mode: " + args[0]);
                    System.err.println("Use: console | calc | test | gui (or no arguments)");
                    System.exit(2);
                }
            }
            return;
        }
        SwingUtilities.invokeLater(() -> new InfoFrame().setVisible(true));
    }
}