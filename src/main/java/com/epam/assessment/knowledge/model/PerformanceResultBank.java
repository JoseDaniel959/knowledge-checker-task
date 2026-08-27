package com.epam.assessment.knowledge.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceResultBank {
    Map<String, PerformanceResultByCategoryElement> PerformaceResultMap = new HashMap<>();
}
