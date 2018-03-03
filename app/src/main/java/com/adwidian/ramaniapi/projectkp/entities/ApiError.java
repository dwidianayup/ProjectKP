package com.adwidian.ramaniapi.projectkp.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/03/04.
 */

public class ApiError {
    String message;
    Map<String, List<String>> errors;

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
