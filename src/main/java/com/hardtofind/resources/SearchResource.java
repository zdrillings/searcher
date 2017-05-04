package com.hardToFind.resources;

import com.codahale.metrics.annotation.Timed;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

        Result<Record> results = database.selectFrom(table("job_configuration.users")).fetch();
        List<String> value = new ArrayList<>();
        for(Record record:results){
            String email = record.get("email").toString();
            int id = Integer.parseInt(record.get("id").toString());
            value.add(email + "--" + id);
        }

        return value;
    }


}
