package com.epam.assessment.knowledge.UI;

import java.util.Map;
import java.util.Scanner;

import com.epam.assessment.knowledge.model.KnowledgeElement;
import com.epam.assessment.knowledge.model.PerformanceResultByCategoryElement;
import com.epam.assessment.knowledge.service.PerformanceResultImp;
import com.epam.assessment.knowledge.service.QuestionServiceImp;

public class Interface {
    private String strategy;
    private Scanner scanner = new Scanner(System.in);
    private QuestionServiceImp questionServiceImp;
    private PerformanceResultImp performanceResultImp;

    public Interface(String strategy, QuestionServiceImp questionServiceImp,
            PerformanceResultImp performanceResultImp) {
        this.strategy = strategy;
        this.questionServiceImp = questionServiceImp;
        this.performanceResultImp = performanceResultImp;

    };

    public void loadInterface() {
        if (strategy.equals("ADAPTIVE")) {
            this.questionServiceImp.calculateKnowledgeElemetsWeight();
            this.questionServiceImp.sortKnowledBankElemets();
        }
        this.printWelcomingMessage();
        questionsLoop();

    }

    public void questionsLoop() {
        while (true) {
            KnowledgeElement pickedKnowledgeElement = this.questionServiceImp.pickKnowledgeElement();
            System.out.println("Question:" + pickedKnowledgeElement.getQuestion());
            System.out.println("Your answer:");
            String answer = scanner.nextLine();

            boolean isExitApp = this.checkExitApplication(answer);
            if (isExitApp) {
                this.performaceMessage();
                break;
            }

            this.printAnswerFeedback(answer, pickedKnowledgeElement);
            this.updateHistoryInKnowledgeElemet(answer, pickedKnowledgeElement);

        }

    }

    public void printWelcomingMessage() {
        System.out.println(
            "Welcome to the Knowledge Bank Application!\r\n" + 
            "This application will test your knowledge on the following topics: Geography, History, Culture, Science, Food & Cooking, Art, Nature\r\n"+ 
            "Total questions in the knowledge bank: 25\r\n" + 
            "You will be asked questions from various categories.\r\n" + 
            "If you want to exit, type '/exit' as your answer.");
    }

    public void printAnswerFeedback(String userAnswer, KnowledgeElement pickedKnowledgeElement) {
        boolean isAnswerCorrect = this.questionServiceImp.checkAnswer(userAnswer, pickedKnowledgeElement);
        this.updatePerformance(isAnswerCorrect, pickedKnowledgeElement);

        if (isAnswerCorrect)
            System.out.println("Correct! Well done.");
        else
            System.out.println("Incorrect. The correct answer is: " + pickedKnowledgeElement.getAnswer());
    }

    public void updatePerformance(boolean isAnswerCorrect, KnowledgeElement pickedKnowledgeElement) {
        this.questionServiceImp.updatePerfomanceResult(isAnswerCorrect, pickedKnowledgeElement);

    }

    public boolean checkExitApplication(String userAnswer) {
        if (userAnswer.equals("/exit"))
            return true;
        return false;
    }

    public void performaceMessage() {

        for (Map.Entry<String, PerformanceResultByCategoryElement> entryPerformanceResultImp : performanceResultImp
                .getPerformanceResultBank().getPerformaceResultMap().entrySet()) {
            String category = entryPerformanceResultImp.getKey();
            Long asked = entryPerformanceResultImp.getValue().getAsked();
            Long correct = entryPerformanceResultImp.getValue().getCorrect();
            Integer percentage = Math.round(correct / asked) * 100;

            System.out.format("%s - Asked: %d, Correct: %d, Percentage 100%%\n",
                    category,
                    asked,
                    correct,
                    percentage);
        }

    }

    public void updateHistoryInKnowledgeElemet(String userAnswer, KnowledgeElement pickedKnowledgeElement) {
        boolean isAnswerCorrect = this.questionServiceImp.checkAnswer(userAnswer, pickedKnowledgeElement);
        this.questionServiceImp.saveHistoryInKnowledgeElement(isAnswerCorrect, pickedKnowledgeElement);
    }

}
