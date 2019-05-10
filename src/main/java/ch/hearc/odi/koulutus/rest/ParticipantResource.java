package ch.hearc.odi.koulutus.rest;

import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.exception.ParticipantException;
import ch.hearc.odi.koulutus.services.PersistenceService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
  public void addParticipant(
      @FormParam("firstname") String firstname, // formparam ne correspond pas à la spec
      @FormParam("lastname") String lastname,
      @FormParam("birthdate") String birthdate) throws ParseException {
    persistenceService.addParticipant(firstname,lastname,new SimpleDateFormat("dd/MM/yyyy").parse(birthdate));
  }

  @GET
  @Path("{participantId}")
  public Participant getParticpantById(
      @PathParam("participantId") Integer participantId) throws ParticipantException {
    return persistenceService.getParticipantById(participantId);
  }

  @DELETE
  @Path("{participantId}")
  public void deleteParticipant(
      @PathParam("participantId") Integer participantId) throws ParticipantException {
    persistenceService.deleteParticipant(participantId);
  }

  @PUT
  @Path("{participantId}") // consumes manquant
  public void editParticipant(
      @PathParam("participantId") Integer participantId, Participant participant) {
    persistenceService.editParticipant(participantId, participant);
  }

  @GET
  @Path("{participantId}/summary")
  public List<Course> getCourseByParticipant(
      @PathParam("participantId") Integer participantId) {
    return persistenceService.getCourseByParticipant(participantId);
  }
}


