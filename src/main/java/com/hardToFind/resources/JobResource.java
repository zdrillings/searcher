package com.hardToFind.resources;

import com.codahale.metrics.annotation.Timed;
import com.hardToFind.models.Job;
import com.hardToFind.service.JobService;
import org.jooq.DSLContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zdrillings on 5/4/17.
 */
@Path("/job")
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

    private final AtomicLong counter;
    // private AppConfiguration configuration;

    public JobResource() {
        // this.configuration = configuration;
        this.counter = new AtomicLong();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<Job> getJobs(@QueryParam("id") String id, @Context DSLContext database) {
        JobService jobService = new JobService(database);
        List<Job> jobs;

        if (id == null) {
            jobs = jobService.getJobs();
        } else {
            jobs = jobService.getJob(Integer.parseInt(id));
        }
        //List<String> value = jobs.stream().map(job -> job.toString()).collect(Collectors.toList());
        return jobs;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public List<Job> postJob(Job.JobEnvelope job, @Context DSLContext database) {
        JobService jobService = new JobService(database);
        return jobService.createJob(job.getJob());

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public List<Job> putJob(Job.JobEnvelope job, @QueryParam("id") String id, @Context DSLContext database) {
        if (id == null) throw new WebApplicationException("No valid id provided");
        JobService jobService = new JobService(database);
        return jobService.updateJob(job.getJob());

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Boolean deleteJob(@QueryParam("id") String id, @Context DSLContext database) {
        if (id == null) throw new WebApplicationException("No valid id provided");
        JobService jobService = new JobService(database);
        return jobService.deleteJob(Integer.parseInt(id));

    }

}