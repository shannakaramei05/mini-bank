package com.example.coresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class  Response<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private T data ;

    public static <T> Response<T> empty() {
        return success(204,null); // no content
    }

    public static <T> Response<T> success(int statusCode ,T data) {
        return Response.<T>builder()
                .message("SUCCESS!")
                .data(data)
                .success(true)
                .build();
    }

    public static <T> Response<T> error (int statusCode , Error ex) {
        return Response.<T>builder()
                .success(false)
                .statusCode(statusCode)
                .message(ex.getMessage())
                .build();
    }
}
