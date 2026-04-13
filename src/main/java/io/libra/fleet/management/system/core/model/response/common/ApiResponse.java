package io.libra.fleet.management.system.core.model.response.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T, E> {

    private T data;
    private E error;

    public static <T, E> ApiResponse<T, E> fromData(T data) {
        return new ApiResponse<>(data, null);
    }

    public static <T, E> ApiResponse<T, E> fromError(E error) {
        return new ApiResponse<>(null, error);
    }
}
