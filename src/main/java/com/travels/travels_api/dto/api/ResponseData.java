package com.travels.travels_api.dto.api;

public class ResponseData<T> extends Response {
    private T data;

    public ResponseData(
        T data
    ) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
