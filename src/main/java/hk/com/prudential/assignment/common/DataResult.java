package hk.com.prudential.assignment.common;

import lombok.Data;

@Data
public class DataResult <T> {

    private Integer code;

    private String msg;

    private T data;

    public static DataResult error(String msg) {
        DataResult dr = new DataResult<>();
        dr.code = 500;
        dr.msg = msg;
        return dr;
    }

    public static <T> DataResult<T> success(T data) {
        DataResult<T> dr = new DataResult<>();
        dr.code = 200;
        dr.msg = "ok";
        dr.data = data;
        return dr;
    }

}
