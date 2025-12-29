package com.ramtinmoradiii.onlineshopjava.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, "عملایت با موفقیت انجام شد", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "عملایت با موفقیت انجام شد", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
