package com.predize.ecommerce.service.dto.response;

import lombok.Getter;

@Getter
public class Response<D> {
    private Integer status = 400;
    D body;

    public void setOk() {
        this.status = 200;
    }

    public void setData(D data) {
        this.body = data;
    }
}
