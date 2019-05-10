package ch.hearc.odi.koulutus.rest;

import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.exception.ParticipantException;
import ch.hearc.odi.koulutus.exception.ProgramException;
import ch.hearc.odi.koulutus.services.PersistenceService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("program")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramResource {

    @Inject
    private PersistenceService persistenceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Program> getAllPrograms() {
        return persistenceService.getPrograms();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Program createAndPersistProgram(
        @FormParam("name") String name,
        @FormParam("richDescription") String richDescription,
        @FormParam("field") String field,
        @FormParam("price") Integer price){ // formparam ne correspond pas à la spec
        return persistenceService.createAndPersistProgram(name,richDescription,field,price);
    }

    @GET
    @Path("{programId}")
    public Program getProgramById(@PathParam("programId") Integer programId)
        throws ProgramException {
        return persistenceService.getProgramById(programId);
    }

    @DELETE
    @Path("{programId}")
    public void deleteProgramById(@PathParam("programId") Integer programId){
        persistenceService.deleteProgramById(programId);
    }

    @PUT
    @Path("{programId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgram(@PathParam("programId") Integer programId,
                              Program program) throws ProgramException {
        persistenceService.updateProgram(programId,program);
    }

    @POST
    @Path("{programId}/course/{courseId}/participant/{participantId}")
    public void registerParticipant(
        @PathParam("programId") Integer programId,
        @PathParam("courseId") Integer courseId,
        @PathParam("participantId") Integer participantId) throws ParticipantException {
        persistenceService.registerParticipant(programId,courseId,participantId);
    }
}
