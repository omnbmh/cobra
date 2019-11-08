package org.github.omnbmh.cobra.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseBody implements Serializable {
    public Integer code;
    public String message;
    public Object body;


    private ResponseBody() {
    }

    public ResponseBody(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseBody(Integer code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public static ResponseBody ok() {
        return new ResponseBody(0, "成功");
    }

    public static ResponseBody ok(String message) {
        return new ResponseBody(0, message);
    }
}

