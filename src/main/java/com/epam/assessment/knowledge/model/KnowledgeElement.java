package com.epam.assessment.knowledge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnowledgeElement {
    private int id;
    private String topic;
    private String category;
    private String question;
    private String answer;
    private LocalDateTime lastAsked;
    private List<Boolean> history = new ArrayList<Boolean>();
    private Long weight = 0L;

    public KnowledgeElement(int id, String topic, String question, String answer) {
        this.id = id;
        this.topic = topic;
        this.question = question;
        this.answer = answer;
    }

    public void calculateTimeWeight() {
        this.weight += this.getLastAsked() == null ? 1L : 0L;
    }

    public void calcultateHistoryWeight() {
        Long knowledgeElementHistoryWeight = 0L;
        List<Boolean> history = this.getHistory();
        Integer historySize = history.size();

        if (historySize == 1) {
            Optional<Boolean> lastItem = Optional.of(history.get(historySize - 1));

            knowledgeElementHistoryWeight = lastItem.get() == false ? 2l : -1L;

        } else if (historySize >= 2) {
            Optional<Boolean> lastItem = Optional.of(history.get(historySize - 1));
            Optional<Boolean> penultimateItem = Optional.of(history.get(historySize - 2));

            knowledgeElementHistoryWeight += lastItem.get() == false ? 2l : -1L;
            knowledgeElementHistoryWeight += penultimateItem.get() == false ? 2l : -1L;

        }

        this.weight += knowledgeElementHistoryWeight;

    }

    public void calculateTotalWeight(){
        this.calculateTimeWeight();
        this.calcultateHistoryWeight();
    }
}
