package com.javamentor.qa.platform.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.temporal.TemporalAmount;

@AllArgsConstructor
@Getter
public enum Period {
    DAY(java.time.Period.ofDays(1)),
    WEEK(java.time.Period.ofDays(7)),
    MONTH(java.time.Period.ofMonths(1)),
    YEAR(java.time.Period.ofYears(1)),
    ALL(null);

    private final TemporalAmount value;
}
