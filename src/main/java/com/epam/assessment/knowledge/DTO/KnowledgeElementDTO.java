package com.epam.assessment.knowledge.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class KnowledgeElementDTO{

    private int id;
    private String category;
    private String question;
    private String answer;
    private String lastAsked;
    private List<Boolean> history = new ArrayList<Boolean>();
}