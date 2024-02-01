package model;

import java.util.Objects;

public class employee_bp {
    public int ID;
    public String name;
    public double rate_per_hour;
    public String role;
    public double hour_balance;
    public employee_bp(int ID, String name,double rate_per_hour,String role,double hour_balance){
        this.ID = ID;
        this.name = name;
        this.rate_per_hour = rate_per_hour;
        this.role = role;
        this.hour_balance = hour_balance;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        employee_bp otherEmployee = (employee_bp) obj;
        return ID == otherEmployee.ID && Objects.equals(name, otherEmployee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }

}
