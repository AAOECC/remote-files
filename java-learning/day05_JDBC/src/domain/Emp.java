package domain;

import java.util.Date;

/**
 * 数据库emp表实体类
 */
public class Emp {
    private int id;
    private String eName;
    private int job_id;
    private int mgr;
    private Date joinDate;
    private double salary;
    private double bonus;
    private int dept_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public Emp(int id, String eName, int job_id, int mgr, Date joinDate, double salary, double bonus, int dept_id) {
        this.id = id;
        this.eName = eName;
        this.job_id = job_id;
        this.mgr = mgr;
        this.joinDate = joinDate;
        this.salary = salary;
        this.bonus = bonus;
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", eName='" + eName + '\'' +
                ", job_id=" + job_id +
                ", mgr=" + mgr +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", dept_id=" + dept_id +
                '}';
    }
}
