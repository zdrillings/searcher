package com.hardToFind.service;

import com.hardToFind.models.Job;
import org.jooq.DSLContext;
import org.jooq.Field;

import java.util.List;

import static org.jooq.impl.DSL.*;

/**
 * Created by zdrillings on 5/4/17.
 */
public class JobService {

    private DSLContext database;
    final String table = "job_configuration.job";

    public JobService(DSLContext database){
        this.database = database;
    }

    public List<Job> getJobs(){

        List<Job> jobs = database.select().from(table(table)).fetch().into(Job.class);

        return jobs;
    }

    public List<Job> getJob(int id){

        Field<Integer> f = field(name( "id"), Integer.class);
        List<Job> jobs = database.select().from(table(table)).where(f.eq(id)).fetch().into(Job.class);

        return jobs;
    }

    public List<Job> createJob(Job job){
        return database.insertInto(table(table))
                .set(field("search_string"),job.searchString)
                .set(field("user_id"),job.userId)
                .set(field("search_type_id"),job.searchTypeId)
                .set(field("report_cadence_id"),job.reportCadenceId)
                .returning(field("id"), field("search_string"), field("search_type_id"), field("report_cadence_id"))
                .fetch()
                .into(Job.class);
    }

    public List<Job> updateJob(Job job){
        return database.update(table(table))
                .set(field("search_string"),job.searchString)
                .set(field("user_id"),job.userId)
                .set(field("search_type_id"),job.searchTypeId)
                .set(field("report_cadence_id"),job.reportCadenceId)
                .returning(field("id"), field("search_string"), field("search_type_id"), field("report_cadence_id"))
                .fetch()
                .into(Job.class);
    }

    public boolean deleteJob(int id){
        database.delete(table(table)).where(field("id").eq(id)).execute();
        return true;
    }
}
