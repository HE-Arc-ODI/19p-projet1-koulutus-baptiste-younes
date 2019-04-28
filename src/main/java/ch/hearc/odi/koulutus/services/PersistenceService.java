/*
 * Copyright (c) 2019. Cours outils de développement intégré, HEG-Arc
 */

package ch.hearc.odi.koulutus.services;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.business.Pojo;
import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.business.Session;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceService {

  private EntityManagerFactory entityManagerFactory;


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
  }

  public ArrayList<Program> getPrograms() {
  }

  public Program createAndPersistProgram() {
  }

  public Program getProgramById(Integer programId) {
  }

  public void deleteProgram(Integer programId) {
  }

  public void updateProgram(Integer programId, Program newProgram) {
  }

  public Session createAndPersistSession(Long aLong, Long aLong1) {
  }

  public Session getDetailsOfSessionByIds(Long aLong, Long aLong1, Long aLong2) {
  }

  public void unregisterSessionToCourse(Integer programId, Integer courseId,
      Integer sessionId) {
  }

  public void editSession(Integer programId, Integer courseId, Integer sessionId) {
  }

  public void registerParticipant(Integer programId, Integer courseId,
      Integer participantId) {
  }

  public List<Participant> getParticipants() {
  }

  public void addParticipant() {
  }

  public Participant getParticipantById(Integer participantId) {
  }

  public void deleteParticipant(Integer participantId) {
  }

  public void editParticipant(Integer participantId) {
  }

  public List<Course> getCourseByParticipant(Integer participantId) {
  }

  public Course createAndPersistCourse(Integer programId) {
  }

  public ArrayList<Course> getCoursesByProgramId(Integer programId) {
  }

  public Course getDetailsOfCourseByProgram(Integer programId, Integer courseId) {
  }

  public void deleteCourseOfProgram(Integer programId, Integer courseId) {
  }

  public Course updateCourse(Integer programId, Integer courseId) {
  }

  public List<Participant> getParticipantsByCoiurseId(Integer programId, Integer courseId) {
  }
}





