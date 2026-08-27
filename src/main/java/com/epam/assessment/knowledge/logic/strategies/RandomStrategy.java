package com.epam.assessment.knowledge.logic.strategies;

import java.util.List;

import com.epam.assessment.knowledge.model.KnowledgeElement;

public class RandomStrategy implements QuestionSelectionStrategy {

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) {
        Integer randomNumber = (int) Math.floor(Math.random() * 5);
        KnowledgeElement randomKnowledgeElement = elements.get(randomNumber);

        return randomKnowledgeElement;

    }

}
