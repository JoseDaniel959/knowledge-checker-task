package com.epam.assessment.knowledge.logic.strategies;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import lombok.Data;

import java.util.List;

@Data
public class SequentialStrategy implements QuestionSelectionStrategy {

    int currentIndex = 0;

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> elements) throws IllegalArgumentException{
        if(elements.size() == 0) throw new IllegalArgumentException();
        if(currentIndex == elements.size()) this.setCurrentIndex(0);
        KnowledgeElement selectedQuestion =  elements.get(currentIndex);
        this.setCurrentIndex(++currentIndex);
        return selectedQuestion;
    }
}
