package ch.hearc.odi.koulutus.rest;

import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.business.Session;
import ch.hearc.odi.koulutus.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("participant")
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantResource {

  @Inject
  private PersistenceService persistenceService;

  @GET
  public List<Participant> getParticipants() {
    return persistenceService.getParticipants();
  }

  @POST
  public void addParticipant() {
    return persistenceService.addParticipant();
  }

  @GET
  @Path("{participantId}")
  public Participant getParticpantById(
      @PathParam("participantId") Integer participantId) {
    return persistenceService.getParticipantById();
  }

  @DELETE
  @Path("{participantId}")
  public void deleteParticipant(
      @PathParam("participantId") Integer participantId) {
    persistenceService.deleteParticipant();
  }

  @PUT
  @Path("{participantId}")
  public void editParticipant(
      @PathParam("participantId") Integer participantId) {
    persistenceService.editParticipant();
  }

  @GET
  @Path("{participantId}/summary")
  public List<Course> getCourseByParticipant(
      @PathParam("participantId") Integer participantId) {
    return persistenceService.getCourseByParticipant();
  }
}


