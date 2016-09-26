package edu.cuny.brooklyn.cisc3120.homework3.core;

public interface IChooser {
    enum ComparisonResult {
        Correct,
        TooHigh,
        TooLow
    }

    ComparisonResult checkGuess(int guess);
}