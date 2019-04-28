package ch.hearc.odi.koulutus.rest;


import ch.hearc.odi.koulutus.business.Course;
import ch.hearc.odi.koulutus.business.Program;
import ch.hearc.odi.koulutus.exception.ProgramException;
import ch.hearc.odi.koulutus.services.PersistenceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


public class CoursResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{programId/course}")
    public Course createCourseByProgramId(@PathParam("programId") Integer programId,
                                          @PathParam("quarter") Integer quarter,
                                          @PathParam("year") Integer year,
                                          @PathParam("maxNumberOfParticipants") Integer maxNumberOfParticipants){
        try {
            return persistenceService.createAndPersistCourse(programId,quarter,year,maxNumberOfParticipants);
        } catch (ProgramException ex) {
            ex.printStackTrace();
            throw new WebApplicationException("Program "+ programId +" not found");
        }
    }

    //A TESTER Mr Baptist ;)
    @GET
    @Path("{programId/course}")
    public ArrayList<Course> getCourseByProgramId(@PathParam("programId") Integer programId){
        try {
            return persistenceService.getCoursesByProgramId(programId);
        } catch (ProgramException ex) {
            ex.printStackTrace();
            throw new WebApplicationException("Program "+ programId +" not found");
        }
    }
}
