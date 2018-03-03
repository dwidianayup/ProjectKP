package com.adwidian.ramaniapi.projectkp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.adwidian.ramaniapi.projectkp.entities.AccessToken;
import com.adwidian.ramaniapi.projectkp.entities.ApiError;
import com.adwidian.ramaniapi.projectkp.network.ApiService;
import com.adwidian.ramaniapi.projectkp.network.RetrofitBuilder;

import java.util.List;
import java.util.Map;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ButterKnife.bind(this);

        service = RetrofitBuilder.createService(ApiService.class);

    }

    @OnClick(R.id.btn_register)
    void register(){
        final String name = tilName.getEditText().getText().toString();
        String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();

        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);

        call = service.register(name,email,password);

        call.enqueue(new Callback<AccessToken>(){
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response){

                Log.w(TAG,"onResponse: "+response);

                if(response.isSuccessful()){

                }else{
                    handleErrors(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t){
//                Log.w(TAG,"onFailure: "+t);
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }

//            TODO lanjut streaming 34:25
        });
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
