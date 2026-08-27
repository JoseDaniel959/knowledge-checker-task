package com.epam.assessment.knowledge.service;

import com.epam.assessment.knowledge.model.PerformanceResultBank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerformanceResultImp implements IPerformanceResult {

    private PerformanceResultBank performanceResultBank;

    
}
