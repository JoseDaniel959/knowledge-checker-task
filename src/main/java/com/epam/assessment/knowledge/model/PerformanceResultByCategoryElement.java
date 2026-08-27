package com.epam.assessment.knowledge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerformanceResultByCategoryElement {
    private long asked;
    private long correct;   
}
