package com.epam.assessment.knowledge.service;

import java.time.LocalDateTime;
import java.util.Map;

import com.epam.assessment.knowledge.logic.strategies.QuestionSelectionStrategy;
import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.model.PerformanceResultBank;
import com.epam.assessment.knowledge.model.PerformanceResultByCategoryElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionServiceImp implements IquestionService {

    private QuestionSelectionStrategy questionSelectionStrategy;
    private KnowledgeBank knowledgeBank;
    private PerformanceResultBank performanceResultBank;

    @Override
    public boolean checkAnswer(String userAnswer, KnowledgeElement knowledgeElement) {
        String knowledgeElementAnswer = knowledgeElement.getAnswer();
        if (knowledgeElementAnswer.equals(userAnswer))
            return true;
        return false;
    }

    @Override
    public KnowledgeElement pickKnowledgeElement() {
        KnowledgeElement pickedKnowledgeElement = questionSelectionStrategy.selectQuestion(knowledgeBank.getElements());
        pickedKnowledgeElement.setLastAsked(LocalDateTime.now());
        return pickedKnowledgeElement;
    }

    @Override
    public void updatePerfomanceResult(boolean isAnswerCorrect, KnowledgeElement pickedKowledgeElement) {
        String categoryOfPickedKowledgeElement = pickedKowledgeElement.getCategory();
        Map<String, PerformanceResultByCategoryElement> newPerformanceResultBankMap = performanceResultBank
                .getPerformaceResultMap();
        if (!newPerformanceResultBankMap.containsKey(categoryOfPickedKowledgeElement)) {
            newPerformanceResultBankMap.put(categoryOfPickedKowledgeElement,
                    new PerformanceResultByCategoryElement(0, 0));
        }
        if (isAnswerCorrect) {
            PerformanceResultByCategoryElement newPerformanceResultByCategoryElement = newPerformanceResultBankMap
                    .get(categoryOfPickedKowledgeElement);
            newPerformanceResultByCategoryElement.setAsked(newPerformanceResultByCategoryElement.getAsked() + 1);
            newPerformanceResultByCategoryElement.setCorrect(newPerformanceResultByCategoryElement.getCorrect() + 1);
            newPerformanceResultBankMap.put(categoryOfPickedKowledgeElement, newPerformanceResultByCategoryElement);
        } else {
            PerformanceResultByCategoryElement newPerformanceResultByCategoryElement = newPerformanceResultBankMap
                    .get(categoryOfPickedKowledgeElement);
            newPerformanceResultByCategoryElement.setAsked(newPerformanceResultByCategoryElement.getAsked() + 1);
            newPerformanceResultBankMap.put(categoryOfPickedKowledgeElement, newPerformanceResultByCategoryElement);
        }
        performanceResultBank.setPerformaceResultMap(newPerformanceResultBankMap);
    }

    @Override
    public void saveHistoryInKnowledgeElement(Boolean isAnswerCorrect, KnowledgeElement pickedKowledgeElement) {
            knowledgeBank.getElements()
            .forEach((knowledgeElement) -> {
                if (knowledgeElement.getQuestion().equals(pickedKowledgeElement.getQuestion())) {
                    if (isAnswerCorrect)
                        knowledgeElement.getHistory().add(true);
                    else
                        knowledgeElement.getHistory().add(false);

                }
            });
    }

    public void calculateKnowledgeElemetsWeight(){
        knowledgeBank.getElements()
        .forEach((knowledgeElement)->{
            knowledgeElement.calculateTotalWeight();
        });
    }

    public void sortKnowledBankElemets(){
        knowledgeBank.getElements().sort((a,b) -> b.getWeight().compareTo(a.getWeight()));
    }
}
