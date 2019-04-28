package edu.itm.goalgetter.FirebaseExtra;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String Base_url ="http://studentspredictionmaluapi.herokuapp.com/";

    @GET("/")
    Call<ResponseBody> getAiResponse();

    @GET("/users")
    Call<ResponseBody> getUsers();

    @POST("/")
    Call<ResponseBody> postUserDataandGetAnalysis(@Body RequestBody requestBody);

}
