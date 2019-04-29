/*
 * Copyright (c) 2019. Cours outils de développement intégré, HEG-Arc
 */

package ch.hearc.odi.koulutus.services;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.business.Pojo;
import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.business.Session;
import ch.hearc.odi.koulutus.exception.ParticipantException;
import ch.hearc.odi.koulutus.exception.ProgramException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistenceService {

  private EntityManagerFactory entityManagerFactory;
  private static final Logger logger = LogManager.getLogger(PersistenceService.class)


  public PersistenceService() {
    //  an EntityManagerFactory is set up once for an application
    //  IMPORTANT: the name here matches the name of persistence-unit in persistence.xml
    entityManagerFactory = Persistence.createEntityManagerFactory("ch.hearc.odi.koulutus.jpa");
  }

  @Override
  public void finalize() throws Throwable {
    entityManagerFactory.close();
    super.finalize();
  }

  public Pojo createAndPersistAPojo(String myProperty){
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Pojo pojo = new Pojo();
    pojo.setSomeProperty(myProperty);
    entityManager.persist(pojo);
    entityManager.getTransaction().commit();
    entityManager.close();
    return pojo;
  }

  public List<Session> getSessionByCourseAndProgram(Long aLong, Long aLong1) {
    return null;
  }

  public ArrayList<Program> getPrograms() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Program> programs = entityManager.createQuery("from Program", Program.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return (ArrayList<Program>) programs;
  }

  public Program createAndPersistProgram() {
  return null;
  }

  public Program getProgramById(Integer programId) throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = entityManager.find(Program.class, programId);

    if (program == null) {
      throw new ProgramException("Program " + programId + "not found");}

    entityManager.getTransaction().commit();
    entityManager.close();
    return program;
  }

  public void deleteProgram(Integer programId) throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = entityManager.find(Program.class, new Long(programId));
    entityManager.remove(program);
    if (program == null) {
      throw new ProgramException("Program not found");
    }
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void updateProgram(Integer programId, Program newProgram) {
  }

  public Session createAndPersistSession(Long aLong, Long aLong1, String startDateTime,
      String endDateTime, Integer price, String room) {
    return null;
  }

  public Session getDetailsOfSessionByIds(Long aLong, Long aLong1, Long aLong2) {
    return null;
  }

  public void unregisterSessionToCourse(Integer programId, Integer courseId, Integer sessionId) {

  }

  public void editSession(Integer programId, Integer courseId, Integer sessionId,
      Session session) {
  }

  public void registerParticipant(Integer programId, Integer courseId, Integer participantId)
      throws ParticipantException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Participant participant = entityManager.find(Participant.class, participantId);
    Course course = entityManager.find(Course.class, courseId);
    Program program = entityManager.find(Program.class, programId);
    entityManager.remove(participant);
    if (participant == null || course == null || program == null) {
      throw new ParticipantException("Data not found");
    }
    participant.addCourse(course);
    entityManager.merge(participant);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public List<Participant> getParticipants() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Participant> participant = entityManager.createQuery("from Participant", Participant.class).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return (List<Participant>) participant;
  }

  public void addParticipant(String firstname, String lastname, String birthdate)
      throws ParseException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Participant participant = new Participant(firstname, lastname, new SimpleDateFormat("dd/MM/yyyy").parse(birthdate));
    entityManager.persist(participant);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public Participant getParticipantById(Integer participantId) throws ParticipantException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Participant participant = entityManager.find(Participant.class, participantId);

    if (participant == null) {
      throw new ParticipantException("Participant " + participantId + " was not found");
    }

    entityManager.getTransaction().commit();
    entityManager.close();
    return participant;
  }

  public void deleteParticipant(Integer participantId) throws ParticipantException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Participant participant = entityManager.find(Participant.class, participantId);
    entityManager.remove(participant);
    if (participant == null) {
      throw new ParticipantException("Participant " + participantId + " was not found for deletion");
    }

    entityManager.getTransaction().commit();
    entityManager.close();
  }

  public void editParticipant(Integer participantId,
      Participant participant) {
  }

  public List<Course> getCourseByParticipant(Integer participantId) {
  return null;
  }

  public Course createAndPersistCourse(Integer id, String quarter, Integer year,
      Integer programId) {
    return null;
  }

  public ArrayList<Course> getCoursesByProgramId(Integer programId) {
    return null;
  }

  public Course getDetailsOfCourseByProgram(Integer programId, Integer courseId) {
    return null;
  }

  public void deleteCourseOfProgram(Integer programId, Integer courseId) {

  }

  public Course updateCourse(Integer programId, Integer courseId,
      Course course) {
    return null;
  }

  public List<Participant> getParticipantsByCourseId(Integer integer, Integer id,
      String quarter, Integer programId,
      Integer courseId) {
    return null;
  }

  public void deleteProgramById(Integer programId) {
  }
}





