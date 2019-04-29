package ch.hearc.odi.koulutus.rest;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.exception.ProgramException;
import ch.hearc.odi.koulutus.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import net.bytebuddy.description.field.FieldDescription.InGenericShape;

@Path("program")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    @Inject
    private PersistenceService persistenceService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{programId}/course}")
    public Course createCourseByProgramId(@PathParam("programId") Integer programId)
        throws ProgramException {
        return persistenceService.createAndPersistCourse(programId);
    }

    @GET
    @Path("{programId}/course")
    public ArrayList<Course> getCourseByProgramId(@PathParam("programId") Integer programId)
        throws ProgramException {
        return persistenceService.getCoursesByProgramId(programId);
    }

    @GET
    @Path("{programId}/course/{courseId}")
    public Course getDetailsOfCourseByProgram(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId){
        return persistenceService.getDetailsOfCourseByProgram(programId,courseId);
    }

    @DELETE
    @Path("{programId}/course/{courseId}")
    public void deleteCourseOfProgram(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId) {
        persistenceService.deleteCourseOfProgram(programId, courseId);
    }

    @PUT
    @Path("{programId}/course/{courseId}")
    public Course updateCourse(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId) {
        return persistenceService.updateCourse(programId, courseId);
    }

    @GET
    @Path("{programId}/course/{courseId}/participant")
    public List<Participant> getParticipantsByCourseId(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId) {
        return persistenceService.getParticipantsByCoiurseId(programId, courseId);
    }

}
