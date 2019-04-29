package ch.hearc.odi.koulutus.business;

import ch.hearc.odi.koulutus.exception.ProgramException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Course")
@XmlRootElement(name = "Course")
public class Course {

  private Integer id;
  public enum quarter {1,2,3,4};
  private Integer year;
  private Integer maxNumberOfParticipants;
  public enum status {OPEN, CONFIRMED, CANCELLED};
  private List<Session> sessions;

  public Course(Integer quarter, Integer year, Integer maxNumberOfParticipants,
                Enum status) {
    sessions = new ArrayList<>();
    status  = Course.status.OPEN;
  }


  public Course(Integer quarter, Integer year, Integer maxNumberOfParticipants) {
    this();
    this.year = year;
    this.maxNumberOfParticipants = maxNumberOfParticipants;
  }
  public Course(Integer id, Integer quarter, Integer year, Integer maxNumberOfParticipants) {

    this(quarter, year, maxNumberOfParticipants);
    this.id = id;
  }

  @OneToMany(targetEntity = Session.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "session")
  @OrderColumn(name = "order_session")
  public void setSession() {
    setSession();
  }

  @OneToMany(targetEntity = Session.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "session")
  @OrderColumn(name = "order_session")
  public List<Session> getSessions() {
    return this.getSessions();
  }

  public void setSession(List<Session> sessions) {
    this.sessions = sessions;
  }

  public void addSessions(Session session) throws ProgramException {
    sessions.add(session);
  }

  public void removeSession(Integer idSession) throws ProgramException {
    this.sessions.remove(this.getIndex(idSession));
  }


  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  public Long getId() {
    return Long.valueOf(id);
  }

  public int getIndex(Integer id) throws ProgramException {
    int i;
    for (i = 0; i < sessions.size(); i++) {
      Session session = sessions.get(i);
      if (session.getId() == (id.longValue())) {
        return i;
      }
    }
    throw new ProgramException("Index not found");
  }

  public void setId(Integer id) {
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
