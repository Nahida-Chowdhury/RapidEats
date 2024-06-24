package com.example.rapideats.Domain;

import androidx.annotation.NonNull;

public class Price {
    private int Id;
    private String Value;

    public Price() {
    }

    @NonNull
    @Override
    public String toString() {
        return Value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
