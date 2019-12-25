package com.machamasisuraj.sqlitesapplication.api;

import com.machamasisuraj.sqlitesapplication.model.Employee;
import com.machamasisuraj.sqlitesapplication.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call <List<Employee>> getAllEmployees();

    @POST("create")
    Call<Void> Create(@Body EmployeeCUD emp);

    @DELETE("delete/{id}")
    Call<Void> Delete(@Path("id") int id);

}
