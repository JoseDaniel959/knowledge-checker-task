package com.epam.assessment.knowledge.logic.strategies;

import java.util.List;

import com.epam.assessment.knowledge.model.KnowledgeElement;

import lombok.Data;

@Data
public class AdaptativeStrategy implements QuestionSelectionStrategy {

    

    @Override
    public KnowledgeElement selectQuestion(List<KnowledgeElement> sortedElements) {
        List<KnowledgeElement> last5KnowledgeElements = sortedElements.subList(0, 5);
        Integer randomNumber = (int)Math.floor(Math.random()*5);
        KnowledgeElement randomKnowledgeElement = last5KnowledgeElements.get(randomNumber);
        return randomKnowledgeElement;
    }

    
    
}
