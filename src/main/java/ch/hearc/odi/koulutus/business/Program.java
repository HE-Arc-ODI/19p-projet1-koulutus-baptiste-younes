package ch.hearc.odi.koulutus.business;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.hearc.odi.koulutus.exception.ProgramException;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Program {

  private Long id;
  private String name;
  private String richDescription;
  private String field;
  private Long price;
  private List<Course> courses;


  public Program() {
    courses = new ArrayList<>();
  }

  public Program(String name, String richDescription, String field, Long price) {
    this();
    this.name = name;
    this.richDescription = richDescription;
    this.field = field;
    this.price = price;
  }

  public Program(Long id, String name, String richDescription, String field, Long price) {
    this(name, richDescription,field,price);
    this.id = id;
  }

  public Program(Program p, Course c) {
    id = p.getId();
    name = p.getName();
    richDescription = p.getRichDescription();
    field = p.getField();
    price = p.getPrice();
    courses = new ArrayList<>();
    courses.add(c);
  }

  @XmlElement
  @Transient
  public List<Course> getCourses() { return courses; }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRichDescription() {
    return richDescription;
  }

  public void setRichDescription(String richDescription) {
    this.richDescription = richDescription;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public void addCourse(Course course) {
    courses.add(course);
  }

  public Integer getIndex(Long id) throws ProgramException {
    for (int i = 0; i < courses.size(); i++){
      Course c = courses.get(i);
      if (c.getId() == id){
        return i;}
    }
    throw new ProgramException("Index not found");
  }

  public void update(Program newProgram) {
    this.setCourses(newProgram.getCourses());
    this.setField(newProgram.getField());
    this.setName(newProgram.getName());
    this.setPrice(newProgram.getPrice());
    this.setRichDescription(newProgram.getRichDescription());
  }

  public void removeCourse(Long id) throws ProgramException {
    this.courses.remove(getIndex(id));
  }

}


