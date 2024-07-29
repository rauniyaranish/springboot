package com.springboot.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseNotFound {
    private String title = "Not Found";
    private String message = "Content Not Found";

    public ResponseNotFound() {}

    public ResponseNotFound(String message) {
        this.message = message;
    }
}
