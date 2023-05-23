package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookState {
    AVAILABLE("available"),
    BORROWED("borrowed"),
    EXTEND("extend"),
    OVERDUE("overdue");

    private final String value;
}
