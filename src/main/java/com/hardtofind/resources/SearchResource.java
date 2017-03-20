package com.hardtofind.resources;

import com.codahale.metrics.annotation.Timed;
import com.hardtofind.models.SearchResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final AtomicLong counter;

    public SearchResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public SearchResult lookup(@QueryParam("name")  String name) {
        final List<String> value = Arrays.asList(new String[]{name});
        return new SearchResult(counter.incrementAndGet(), value);
    }


}
