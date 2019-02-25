package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import core.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "employee")
public class Employee  extends BaseEntity {
    @NotNull
    @Column(name="name")
    private String name;
    @NotNull
    @Column(name="department")
    private String department;
    @NotNull
    @Column(name="job_title")
    private String jobTitle;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="employment_date")
    private Date employmentDate;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String _department) {
        this.department = _department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String _jobTitle) {
        this.jobTitle = _jobTitle;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString(){
        String output = String.format(
                "%1 | %2 | %3 | %4 | %5", getId(), name, department, jobTitle, employmentDate);
        return output;
    }
}
