package com.epam.assessment.knowledge.logic;

import com.epam.assessment.knowledge.logic.strategies.AdaptativeStrategy;
import com.epam.assessment.knowledge.logic.strategies.FixedStrategy;
import com.epam.assessment.knowledge.logic.strategies.QuestionSelectionStrategy;
import com.epam.assessment.knowledge.logic.strategies.RandomStrategy;
import com.epam.assessment.knowledge.logic.strategies.SequentialStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StrategyFactory {
    String strategy;

    public QuestionSelectionStrategy createStrategy() {
        return switch (this.strategy) {
            case "FIXED" -> new FixedStrategy();
            case "SEQUENTIAL" -> new SequentialStrategy();
            case "ADAPTIVE" -> new AdaptativeStrategy();
            case "RANDOM" -> new RandomStrategy();
            default -> throw new IllegalArgumentException();
        };

    }

}