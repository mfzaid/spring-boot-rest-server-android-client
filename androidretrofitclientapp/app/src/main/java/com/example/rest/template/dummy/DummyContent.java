package com.example.rest.template.dummy;

import android.util.Log;

import com.example.rest.template.core.Employee;
import com.example.rest.template.core.SpringBootAppApi;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Employee> ITEMS = new ArrayList<Employee>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Employee> ITEM_MAP = new HashMap<String, Employee>();

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.13:9999/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpringBootAppApi springAppApi = retrofit.create(SpringBootAppApi.class);

        updateEmployee_Async(springAppApi);

        updateEmployeeJobTitle_Async(springAppApi);

        getEmployeesFromServer_Async(springAppApi);
    }

    private static void updateEmployee_Async(SpringBootAppApi springAppApi) {
        Employee employee5 = new Employee();
        employee5.setId(5);
        employee5.setName("Ahmad Ibrahim");
        employee5.setDepartment("Android Quality Assurance");
        employee5.setJobTitle("Quality Engineer Full 3");
        employee5.setEmploymentDate("2020-12-1");

        Call<Employee> api_updateEmployee_Async = springAppApi.
                updateEmployee(employee5);

        api_updateEmployee_Async.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()){
                    Log.e("Employee-U","Something went wrong: " + response.code() );
                    return;
                }
                Log.d("Employee-U", response.body().getJobTitle());
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("Employee-U","Something went wrong.", t);
            }
        });
    }

    private static void updateEmployeeJobTitle_Async(SpringBootAppApi springAppApi) {
        Call<Employee> api_updateEmployeeJobTitle_Async = springAppApi.
                updateEmployeeJobTitle(1, "Android Apps Tester 3");

        api_updateEmployeeJobTitle_Async.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()){
                    Log.e("Employee-JobTitle","Something went wrong: " + response.code() );
                    return;
                }
                Log.d("Employee-JobTitle", response.body().getJobTitle());
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e("Employee-JobTitle","Something went wrong.", t);
            }
        });
    }

    private static void getEmployeesFromServer_Async(SpringBootAppApi springAppApi) {
        Call<List<Employee>> api_getEmployees = springAppApi.getEmployees();

        //Asynchronous response
        api_getEmployees.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Log.e("Employees","Something went wrong: " + response.code() );
                    return;
                }
                List<Employee> employees = response.body();
                for (Employee employee : employees) {
                    Log.d("Employees", employee.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("Employees","Something went wrong.", t);
            }
        });
    }

    private static void addItem(Employee employee) {
        ITEMS.add(employee);
        ITEM_MAP.put(String.valueOf(employee.getId()), employee);
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
