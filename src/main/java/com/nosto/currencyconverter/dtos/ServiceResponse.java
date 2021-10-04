package com.nosto.currencyconverter.dtos;


import com.nosto.currencyconverter.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    public int code;
    public String message;
    public T data;

    public ServiceResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", object=" + data +
                '}';
    }
}
