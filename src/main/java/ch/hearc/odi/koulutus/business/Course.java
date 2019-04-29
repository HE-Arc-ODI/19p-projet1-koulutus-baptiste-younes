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
  public enum CEnum {OPEN ("open"), CONFIRMED ("confirmed"), CANCELED ("canceled");
    private String cEnum;
    CEnum(String cEnum){this.cEnum = cEnum;}
    public String toString() {
      return super.toString().toLowerCase();}
  }
  private CEnum status;
  private Integer year;
  private Integer maxNumberOfParticipants;

  public enum QEnum {N1(Integer.valueOf(1)), N2(Integer.valueOf(2)), N3(Integer.valueOf(3)), N4(Integer.valueOf(4));
    private Integer qEnum;
    QEnum(Integer qEnum){this.qEnum = qEnum;}
    public String toString() {
      return super.toString().toLowerCase();}
  }
  private QEnum quarter;
  private List<Session> sessions;

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
