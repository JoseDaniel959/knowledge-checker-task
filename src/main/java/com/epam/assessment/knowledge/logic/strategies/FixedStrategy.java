package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import java.util.List;

public class FixedStrategy implements QuestionSelectionStrategy {

    int questionIndex;
    public FixedStrategy() {
        this.questionIndex = 0;
    }

    public FixedStrategy(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) throws IllegalArgumentException{
        if( questionIndex<0 || questionIndex >= elements.size()) throw new IllegalArgumentException();
        return elements.get(questionIndex);
    }
}
