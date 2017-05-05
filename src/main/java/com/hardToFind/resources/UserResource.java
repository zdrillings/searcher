package com.hardToFind.resources;

import com.codahale.metrics.annotation.Timed;
import com.hardToFind.models.User;
import com.hardToFind.service.UserService;
import org.jooq.DSLContext;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zdrillings on 5/4/17.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final AtomicLong counter;
    // private AppConfiguration configuration;

    public UserResource() {
        // this.configuration = configuration;
        this.counter = new AtomicLong();
    }

    @Path("/validate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<User> getValidate(@QueryParam("id") String id, @Context DSLContext database) {
        UserService userService = new UserService(database);
        if(id==null || !isInteger(id)) throw new WebApplicationException("No valid id provided");
        userService.setUserValidated(Integer.parseInt(id));
        return userService.getUser(Integer.parseInt(id));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<User> getUsers(@QueryParam("id") String id, @Context DSLContext database) {
        UserService userService = new UserService(database);
        List<User> users;

        if(id==null) {
            users = userService.getUsers();
        }else{
            users = userService.getUser(Integer.parseInt(id));
        }
        //List<String> value = users.stream().map(user -> user.toString()).collect(Collectors.toList());
        return users;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public List<User> postUser(User.UserEnvelope user, @Context DSLContext database){
        UserService userService = new UserService(database);
        return userService.createUser(user.getUser());

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Boolean deleteUser(@QueryParam("id") String id, @Context DSLContext database){
        if(id==null || !isInteger(id)) throw new WebApplicationException("No valid id provided");
        UserService userService = new UserService(database);
        return userService.deleteUser(Integer.parseInt(id));

    }

    private boolean isInteger(String s) {
        int radix = 10;
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        // we know it starts with a valid int, now make sure
        // there's nothing left!
        sc.nextInt(radix);
        return !sc.hasNext();
    }

}

