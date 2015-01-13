package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int instances;
  
  public Person() {
    this("", 0, 0.0d);
    instances++;
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    instances++;
  }

  public int getAge() {
    return age;
  }
  
  public void setAge(int i) {
	  if (i < 0) {
		  throw new IllegalArgumentException();
	  } else {
		  age = i;
	  }
  }
  
  public String getName() {
    return name;
  }
  public void setName(String n) {
	  if (n == null) {
		  throw new IllegalArgumentException();
	  } else {
		  name = n;
	  }
  }
  public double getSalary() {
    return salary;
  }
  public void setSalary(double s) {
	  salary = s;
  }
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public int count(){
	  return instances;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
 
  public boolean equals(Object other) {
    if(other instanceof Person){
    		Person p = (Person)other;
    		return p.getAge() == this.getAge() && p.getName() == this.getName();
    }
    return false;
  }
  
  public static class AgeComparator implements Comparator<Person>{
	  public int compare(Person now, Person other){
		  return now.getAge() - other.getAge();
	  }
  }
  @Override
  public int compareTo(Person other){
	  return -((int)(this.salary - other.getSalary()));
  }

  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }
  public static ArrayList<Person> getNewardFamily(){
	  Person ted = new Person("Ted",41,250000);
	  Person charlotte = new Person("Charlotte",43,150000);
	  Person michael = new Person("Michael",22,10000);
	  Person matthew = new Person("Matthew",15,0);
	  ArrayList<Person> newardFam = new ArrayList<Person>();
	  newardFam.add(ted);
	  newardFam.add(charlotte);
	  newardFam.add(michael);
	  newardFam.add(matthew);
	  
	  return newardFam;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
