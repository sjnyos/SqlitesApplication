package com.machamasisuraj.sqlitesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.machamasisuraj.sqlitesapplication.api.EmployeeAPI;
import com.machamasisuraj.sqlitesapplication.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonActivity extends AppCompatActivity {
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        textview = findViewById(R.id.textview);


        //asynchronous retrofit calls
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://dummy.restapiexample.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            if(!response.isSuccessful()){
                Toast.makeText(JsonActivity.this, "Code Error:"+ response.code(), Toast.LENGTH_SHORT).show();
                return;
            }
            List<Employee> employeeList = response.body();
            for(Employee employee: employeeList){
                String data="";
                data +="Employee Name: "+ employee.getEmployee_name()+ "\n";
                data +="Employee Salary: "+ employee.getEmployee_salary()+ "\n";
                data +="Employee age : "+ employee.getEmployee_age()+ "\n";
                data+="-----------------------------------------------------";
                textview.append(data);

            }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("err msg","onFailure :"+t.getLocalizedMessage());
                Toast.makeText(JsonActivity.this, "Error "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
