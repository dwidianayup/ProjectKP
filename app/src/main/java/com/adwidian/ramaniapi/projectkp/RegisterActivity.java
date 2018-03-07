package com.adwidian.ramaniapi.projectkp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.adwidian.ramaniapi.projectkp.entities.AccessToken;
import com.adwidian.ramaniapi.projectkp.entities.ApiError;
import com.adwidian.ramaniapi.projectkp.network.ApiService;
import com.adwidian.ramaniapi.projectkp.network.RetrofitBuilder;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.til_name) TextInputLayout tilName;
    @BindView(R.id.til_email) TextInputLayout tilEmail;
    @BindView(R.id.til_password) TextInputLayout tilPassword;
    ApiService service;
    Call<AccessToken> call;
    AwesomeValidation validator;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        setupRules();
    }

    @OnClick(R.id.btn_register)
    void register(){
        final String name = tilName.getEditText().getText().toString();
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();

        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);

        validator.clear();

        if(validator.validate()) {
            call = service.register(name, email, password);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    Log.w(TAG, "onResponse: " + response);

                    if (response.isSuccessful()) {
                        Log.w(TAG,"onResponse: "+response.body());
                        tokenManager.saveToken(response.body());
                    } else {
                        handleErrors(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t);
//                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
//                startActivity(intent);
                }

//            TODO lanjut streaming 34:25
            });
        }
    }

    private void handleErrors(ResponseBody response){
        ApiError apiError = Utils.converErrors(response);

        for (Map.Entry<String , List<String>> error : apiError.getErrors().entrySet()){
            if(error.getKey().equals("name")){
                tilName.setError(error.getValue().get(0));
            }

            if(error.getKey().equals("email")){
                tilEmail.setError(error.getValue().get(0));
            }

            if(error.getKey().equals("password")){
                tilPassword.setError(error.getValue().get(0));
            }
        }
    }

    public void setupRules(){
        validator.addValidation(this,R.id.til_name, RegexTemplate.NOT_EMPTY,R.string.err_name);
        validator.addValidation(this,R.id.til_email, Patterns.EMAIL_ADDRESS,R.string.err_email);
        validator.addValidation(this,R.id.til_password, "[a-zA-Z0-9]{6,}",R.string.err_password);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null) {
            call.cancel();
            call = null;
        }
    }

    public void LoginControll(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
