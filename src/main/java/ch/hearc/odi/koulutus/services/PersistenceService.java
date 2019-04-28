/*
 * Copyright (c) 2019. Cours outils de développement intégré, HEG-Arc
 */

package ch.hearc.odi.koulutus.services;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Pojo;
import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.business.Session;
import ch.hearc.odi.koulutus.exception.ProgramException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PersistenceService {

  private EntityManagerFactory entityManagerFactory;


  public PersistenceService() {
    //  an EntityManagerFactory is set up once for an application
    //  IMPORTANT: the name here matches the name of persistence-unit in persistence.xml
    entityManagerFactory = Persistence.createEntityManagerFactory("ch.hearc.odi.koulutus.jpa");
  }

  //----------------------------------------Program--------------------------------------------------------------------

  /**
   * Return all existing program
   */
  public ArrayList<Program> getPrograms() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    List<Program> programs = entityManager.createQuery("from Program", Program.class)
            .getResultList();
    /*entityManager.getTransaction().commit();
    entityManager.close();*/
    return (ArrayList<Program>) programs;
  }

//program by ID
  public Program getProgramById(Integer programId) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = entityManager.find(Program.class, programId);
    entityManager.getTransaction().commit();
    entityManager.close();
    return program;
  }

//creat program and persist
  public Program createAndPersistProgram(String name, String richDescription, String field,
                                         Integer price) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = new Program(name, richDescription, field, price);
    entityManager.persist(program);
    entityManager.getTransaction().commit();
    entityManager.close();
    return program;
  }

 //Delete a program
  public void deleteProgram(Integer programId) throws ProgramException{
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = entityManager.find(Program.class, programId);

    if (program == null){
      throw  new ProgramException("Program with id "+ programId +" not found");
    }
    entityManager.remove(program);
    entityManager.getTransaction().commit();
    entityManager.close();
  }

  //Update a Program

  public Program updateProgram(Integer programId, Program newProgram) throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Program program = entityManager.find(Program.class, programId);
    if (program == null){
      throw new ProgramException("Program with id "+ programId +" not found");
    }
    program.update(newProgram);
    entityManager.getTransaction().commit();
    return program;
  }

  //----------------------------------------Course----------------------------------------------------------------------

  /**
   * Return all existing courses for a given program id *
   */
  public ArrayList<Course> getCoursesByProgramId(Integer programId) throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    TypedQuery<Course> query = entityManager
            .createQuery("SELECT c from Course c where c.program.id = :programId", Course.class);

    List<Course> courses = query.setParameter("programId", programId).getResultList();

    if (courses == null) {
      throw new ProgramException("Program " + programId + " was not found");
    }
    entityManager.getTransaction().commit();
    entityManager.close();

    return (ArrayList<Course>) courses;
  }

  /**
   * Return course by ID and program id
   */
  public Course getCourseByIdProgramId(Integer programId, Integer courseId)
          throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    TypedQuery<Course> query = entityManager.createQuery(
            "SELECT c from Course c where c.program.id = :programId and c.id = :courseId",
            Course.class);

    Course courses = query.setParameter("programId", programId)
            .setParameter("programId", courseId)
            .getSingleResult();

    if (courses == null) {
      throw new ProgramException("Program or course not found");
    }

    entityManager.getTransaction().commit();
    entityManager.close();

    return courses;
  }

  /**
   * Create a new Course and persist
   */
  public Course createAndPersistCourse(Integer programId, Integer quarter, Integer year,
                                       Integer maxNumberOfParticipants) throws ProgramException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Course courses = new Course(quarter, year, maxNumberOfParticipants);
    Program program = getProgramById(programId);

    if (program != null) {
      program.addCourse(courses);
      entityManager.persist(courses);
      entityManager.getTransaction().commit();
      entityManager.close();
    } else {
      throw new ProgramException("Program " + programId + " was not found");
    }

    return courses;
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

  public Session createAndPersistSession(Long aLong, Long aLong1) {
  }

  public Session getDetailsOfSessionByIds(Long aLong, Long aLong1, Long aLong2) {
  }

  public void unregisterSessionToCourse() {
  }

  public void editSession() {
  }
}





