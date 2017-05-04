package com.hardToFind.resources;

import com.codahale.metrics.annotation.Timed;
import com.hardToFind.models.User;
import org.jooq.DSLContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.table;


@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final AtomicLong counter;
   // private AppConfiguration configuration;

    public SearchResource() {
       // this.configuration = configuration;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public List<String> lookup(@QueryParam("query")  String query, @Context DSLContext database) {

        //EbaySearcher ebaySearcher = new EbaySearcher(configuration.getEbayToken(), configuration.getEbayHost());
        //final List<SearchResultItem> value = ebaySearcher.search("test");
        //final List<SearchResultItem> value = Arrays.asList(new SearchResultItem[]{new SearchResultItem()});

        List<User> users = database.select().from(table("job_configuration.users")).fetch().into(User.class);
        List<String> value = users.stream().map(user -> user.toString()).collect(Collectors.toList());


        return value;
    }


}
