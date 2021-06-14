package com.allianz.exercise.poc.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Employee.
 */
@Entity
public class Employee {

  /** The id. */
  private @Id @GeneratedValue Long id;
  
  /** The name. */
  private String name;
  
  /** The role. */
  private String role;

  /**
   * Instantiates a new employee.
   */
  Employee() {}

  /**
   * Instantiates a new employee.
   *
   * @param name the name
   * @param role the role
   */
  public Employee(String name, String role) {

    this.name = name;
    this.role = role;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the role.
   *
   * @return the role
   */
  public String getRole() {
    return this.role;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the role.
   *
   * @param role the new role
   */
  public void setRole(String role) {
    this.role = role;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Employee))
      return false;
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
        && Objects.equals(this.role, employee.role);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
  }
}