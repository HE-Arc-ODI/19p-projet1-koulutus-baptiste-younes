package ch.hearc.odi.koulutus.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Course")
@XmlRootElement(name = "Course")
public class Course {

  private Long id;
  public enum quarter {1,2,3,4};
  private Integer year;
  private Integer maxNumberOfParticipants;
  public enum status {OPEN, CONFIRMED, CANCELLED};
  private List<Session> sessions;

  public Course() {
    sessions = new ArrayList<>();
  }

  public Course(Integer year, Integer maxNumberOfParticipants) {
    this();
    this.year = year;
    this.maxNumberOfParticipants = maxNumberOfParticipants;
  }

  public void setSession(List<Session> sessions) {
    this.sessions = sessions;
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

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMaxNumberOfParticipants() {
    return maxNumberOfParticipants;
  }

  public void setMaxNumberOfParticipants(Integer maxNumberOfParticipants) {
    this.maxNumberOfParticipants = maxNumberOfParticipants;
  }

}
