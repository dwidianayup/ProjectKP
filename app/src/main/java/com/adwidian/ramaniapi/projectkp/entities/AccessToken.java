package com.adwidian.ramaniapi.projectkp.entities;

import com.squareup.moshi.Json;

/**
 * Created by apple on 2018/03/04.
 */

public class AccessToken {
    @Json(name = "token_type") String tokenType;
    @Json(name = "expires_in") int expiresIn;
    @Json(name = "access_token") String accessToken;
    @Json(name = "refresh_token") String refreshToken;
}
