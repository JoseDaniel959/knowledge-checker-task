package com.epam.assessment.knowledge.modelmapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.epam.assessment.knowledge.DTO.KnowledgeElementDTO;
import com.epam.assessment.knowledge.model.KnowledgeElement;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Mapper {

    public static KnowledgeElementDTO toKnowledgeElementDTO(KnowledgeElement knowledgeElement){
        if(knowledgeElement == null) return null;
          return KnowledgeElementDTO.builder()
                .id(knowledgeElement.getId())
                .category(knowledgeElement.getCategory())
                .question(knowledgeElement.getQuestion())
                .answer(knowledgeElement.getAnswer())
                .lastAsked(knowledgeElement.getLastAsked() != null ? knowledgeElement.getLastAsked().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null)
                .history(knowledgeElement.getHistory())
                .build();

    }

    public static KnowledgeElement toKnowledgeElement(KnowledgeElementDTO knowledgeElementDTO){
        if(knowledgeElementDTO == null) return null;
          return KnowledgeElement.builder()
                .id(knowledgeElementDTO.getId())
                .topic(knowledgeElementDTO.getCategory())
                .category(knowledgeElementDTO.getCategory())
                .question(knowledgeElementDTO.getQuestion())
                .answer(knowledgeElementDTO.getAnswer())
                .lastAsked(knowledgeElementDTO.getLastAsked() != null ? LocalDateTime.parse(knowledgeElementDTO.getLastAsked()): null)
                .history(knowledgeElementDTO.getHistory())
                .weight(0L)
                .build();

    }
}
