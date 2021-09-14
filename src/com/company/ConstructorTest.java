package com.company;
import java.util.*;
public class ConstructorTest {
    public static void main(String[] args){
        Employee [] staff = new Employee[3];// define a object reference array
        staff[0] = new Employee("JB",100000);
        staff[1] = new Employee(100000);
        staff[2] = new Employee();
        for(Employee e:staff){
            System.out.println("name="+e.getName()+" "+"id="+e.getId()+" "+"salary="+e.getSalary());
        }

    }
}
class  Employee{
    private static int nextId;
    private int id;
    private String name = "";
    private double salary;
    static{
        Random generator = new Random();
        nextId = generator.nextInt(10000);

    }
    {
        id = nextId;
        nextId++;
    }
    public Employee(String n,double s){
        name = n;
        salary = s;
    }
    public Employee(double s){
        this("Employee#"+nextId,s);
    }
    public Employee(){
        //name initialized to "" see above
        //salary not explicitly set
        // id initialized in initialization block
    }
    public String getName(){
        return this.name;
    }
    public double getSalary(){
        return this.salary;
    }
    public int getId(){
        return id;
    }
}
