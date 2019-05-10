package ch.hearc.odi.koulutus.rest;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Participant;
import ch.hearc.odi.koulutus.exception.ProgramException;
import ch.hearc.odi.koulutus.services.PersistenceService;
import java.util.ArrayList;
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

@Path("program")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    @Inject
    private PersistenceService persistenceService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{programId}/course}")
    public Course createCourseByProgramId(
        @PathParam("programId") Integer programId,
        @FormParam("quarter") String quarter,
        @FormParam("year") Integer year,
        @FormParam("maxNumberOfParticipants") Integer maxNumberOfParticipants) // formparam ne correspond pas à la spec
        throws ProgramException {
        return persistenceService.createAndPersistCourse(programId, quarter, year, maxNumberOfParticipants);
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
    @Path("{programId}/course/{courseId}") // consumes manquant
    public Course updateCourse(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId,
        Course course) {
        return persistenceService.updateCourse(programId, courseId, course);
    }

    @GET
    @Path("{programId}/course/{courseId}/participant") // consumes manquant
    public List<Participant> getParticipantsByCourseId(
        @PathParam("programId")Integer programId,
        @PathParam("courseId") Integer courseId,
        @FormParam("quarter") String quarter,
        @FormParam("year") Integer year,
        @FormParam("maxNumberOfParticipants") Integer maxNumberOfParticipants){
        return persistenceService.getParticipantsByCourseId(programId, courseId, quarter, year, maxNumberOfParticipants);
    }

}
