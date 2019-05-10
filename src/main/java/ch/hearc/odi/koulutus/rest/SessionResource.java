package ch.hearc.odi.koulutus.rest;

import ch.hearc.odi.koulutus.business.Session;
import ch.hearc.odi.koulutus.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("program/{programId}/course/{courseId}/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // ne correspond pas à la spec
public class SessionResource {
  @Inject
  private PersistenceService persistenceService;

  @GET
  public List<Session> getSessionByCourseAndProgram(
      @PathParam("programId") Integer programId,
      @PathParam("courseId") Integer courseId){
    return persistenceService.getSessionByCourseAndProgram(
        new Long(programId),
        new Long(courseId));
  }

  @POST
  public Session createAndPersistSession(
      @PathParam("programId") Integer programId,
      @PathParam("courseId") Integer courseId,
      @FormParam("startDateTime") String startDateTime,
      @FormParam("endDateTime") String endDateTime,
      @FormParam("price") Integer price,
      @FormParam("room") String room){ // formparam ne correspond pas à la spec
    return persistenceService.createAndPersistSession(
        new Long(programId),
        new Long(courseId),
        startDateTime,
        endDateTime,
        price,
        room);
  }

  @GET
  @Path("{sessionId}")
  public Session getDetailsOfSessionByIds(
      @PathParam("programId") Integer programId,
      @PathParam("courseId") Integer courseId,
      @PathParam("sessionId")Integer sessionId) {
    return persistenceService.getDetailsOfSessionByIds(
        new Long(programId),
        new Long(courseId),
        new Long(sessionId));
  }

  @DELETE
  @Path("{sessionId}")
  public void unregisterSessionToCourse(
      @PathParam("programId") Integer programId,
      @PathParam("courseId") Integer courseId,
      @PathParam("sessionId")Integer sessionId) {
    persistenceService.unregisterSessionToCourse(programId,courseId,sessionId);
  }

  @PUT
  @Path("{sessionId}")
  public void editSession(
      @PathParam("programId") Integer programId,
      @PathParam("courseId") Integer courseId,
      @PathParam("sessionId")Integer sessionId,
      Session session) {
    persistenceService.editSession(programId,courseId,sessionId,session);
  }

}
