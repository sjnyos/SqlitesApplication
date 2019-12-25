package com.machamasisuraj.sqlitesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.machamasisuraj.sqlitesapplication.api.EmployeeAPI;
import com.machamasisuraj.sqlitesapplication.model.Employee;
import com.machamasisuraj.sqlitesapplication.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeCreateActivity extends AppCompatActivity {

    private EditText et_name,et_salary,et_age;
    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_create);
        et_name= findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        et_salary=findViewById(R.id.et_salary);
        btnCreate=findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeCUD employeeCUD= new EmployeeCUD(new Employee(0
                        ,et_name.getText().toString()
                        ,Float.parseFloat(et_salary.getText().toString())
                        ,Integer.parseInt(et_age.getText().toString()),
                        ""));

                Retrofit retrofit= new Retrofit.Builder()
                        .baseUrl("http://dummy.restapiexample.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                Call<Void> voidCall = employeeAPI.Create(employeeCUD);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(EmployeeCreateActivity.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EmployeeCreateActivity.this,JsonActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EmployeeCreateActivity.this, "SOme Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
