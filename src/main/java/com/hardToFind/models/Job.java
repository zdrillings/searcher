package com.hardToFind.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

/**
 * Created by zdrillings on 5/4/17.
 */
public class Job {

    @Column(name = "ID")
    @JsonProperty("id")
    public int id;

    @Column(name = "USER_ID")
    @JsonProperty("user_id")
    public int userId;

    @Column(name = "SEARCH_TYPE_ID")
    @JsonProperty("search_type_id")
    public int searchTypeId;

    @Column(name = "REPORT_CADENCE_ID")
    @JsonProperty("report_cadence_id")
    public int reportCadenceId;

    @Column(name = "SEARCH_STRING")
    @JsonProperty("search_string")
    public String searchString;

    public String toString(){
        return "id: " + id + " search: " + searchString + " user id: " + userId;
    }
    
    public static class JobEnvelope {

        @JsonProperty("job")
        private Job job;

        public JobEnvelope() {
        }

        public Job getJob() {
            return this.job;
        }

        public void setJob(Job job) {
            this.job = job;
        }
    }
}
