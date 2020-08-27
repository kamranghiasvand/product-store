package com.bluebox.productstore.rest;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamran Ghiasvand
 */
@Getter
public class RestErrorResponse {
    private String key;
    private List<Object> messages = new ArrayList<>();

    public RestErrorResponse(String key, List<?> messages) {
        this.key = key;
        this.messages.addAll(messages);
    }
}
