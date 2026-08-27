package com.epam.assessment.knowledge.service;

import com.epam.assessment.knowledge.model.KnowledgeBank;
import com.epam.assessment.knowledge.model.KnowledgeElement;

interface IquestionService {
    boolean checkAnswer(String answer, KnowledgeElement knowledgeElement);
    KnowledgeElement pickKnowledgeElement();
    void saveHistoryInKnowledgeElement(Boolean isAnswerCorrect, KnowledgeElement pickedKowledgeElement);
    void updatePerfomanceResult(boolean isAnswerCorrect, KnowledgeElement pickedKowledgeElement);
}
