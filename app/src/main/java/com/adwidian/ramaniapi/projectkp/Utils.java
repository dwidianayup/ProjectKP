package com.adwidian.ramaniapi.projectkp;

import com.adwidian.ramaniapi.projectkp.entities.ApiError;
import com.adwidian.ramaniapi.projectkp.network.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by apple on 2018/03/04.
 */

public class Utils {

    public static ApiError converErrors(ResponseBody response){
        Converter<ResponseBody, ApiError> converter = RetrofitBuilder.getRetrofit()
                .responseBodyConverter(ApiError.class,new Annotation[0]);

        ApiError apiError = null;

        try {
            apiError = converter.convert(response);
        }catch (IOException e){
            e.printStackTrace();
        }
        return apiError;
    }
}
