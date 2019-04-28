package ch.hearc.odi.koulutus.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

public class Participant {

  private Long id;
  private String firstName;
  private String lastName;
  private Date birthdate;
  private List<Course> courses;

  public Participant() {
    courses = new ArrayList<>();
  }

  public Participant(String firstName, String lastName, Date birthdate) {
    this();
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthdate = birthdate;
  }

  public Participant(Long id, String firstName, String lastName, Date birthdate) {
    this(firstName, lastName, birthdate);
    this.id = id;
  }

  public void setCategories(List<Course> courses) {
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  @ManyToMany(targetEntity = Course.class, fetch = FetchType.EAGER, mappedBy = "runners")
  public List<Course> getCourses() {
    return courses;
  }


  public Course getCourse(Long id) {
    for (Course course : this.courses) {
      if (course.getId() == (id.longValue())) {
        return course;
      }
    }
  }

  public void removeFromCourse(Long idCourse){
    this.courses.remove(this.getIndex(idCourse));
  }

  public int getIndex(Long id) {
    int i;
    for (i = 0; i < courses.size(); i++) {
      Course course = courses.get(i);
      if (course.getId() == (id.longValue())) {
        return i;
      }
    }
  }

}
