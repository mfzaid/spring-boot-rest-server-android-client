package com.example.rest.template.core;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SpringBootAppApi {

    @GET("employees")
    Call<List<Employee>> getEmployees();

    @PUT("employees")
    Call<Employee> updateEmployee(@Body Employee employee);

    @PUT("employees/{id}/{jobTitle}")
    Call<Employee> updateEmployeeJobTitle(@Path("id") long id, @Path("jobTitle") String jobTitle);

}
