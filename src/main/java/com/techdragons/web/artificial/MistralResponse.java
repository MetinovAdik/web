package com.techdragons.web.artificial;

import java.util.List;

public class MistralResponse {
    private final String text;
    private final List<String> tokens;

    public MistralResponse(String text, List<String> tokens) {
        this.text = text;
        this.tokens = tokens;
    }

    // Getters
    public String getText() {
        return text;
    }

    public List<String> getTokens() {
        return tokens;
    }
}