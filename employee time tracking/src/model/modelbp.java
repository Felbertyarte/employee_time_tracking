package model;




public class modelbp {
    public passcode_model PASSCODE_MODEL;
    public employee_model EMPLOYEE_MODEL;
    public history_model HISTORY_MODEL;
    public salary_model SALARY_MODEL;
    public user userbp;
    public modelbp(
    employee_model EMPLOYEE_MODEL,
    passcode_model PASSCODE_MODEL,
    history_model HISTORY_MODEL,
    salary_model SALARY_MODEL){
        this.PASSCODE_MODEL = PASSCODE_MODEL;
        this.EMPLOYEE_MODEL = EMPLOYEE_MODEL;
        this.HISTORY_MODEL = HISTORY_MODEL;
        this.SALARY_MODEL = SALARY_MODEL;
    }
}
