package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookState {
    AVAILABLE("AVAILABLE"),
    BORROWED("BORROWED"),
    EXTEND("EXTEND"),
    OVERDUE("OVERDUE");

    private final String value;
}
