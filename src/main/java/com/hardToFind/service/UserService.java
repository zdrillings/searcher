package com.hardToFind.service;

import com.hardToFind.models.User;
import org.jooq.DSLContext;
import org.jooq.Field;

import java.util.List;

import static org.jooq.impl.DSL.*;

/**
 * Created by zdrillings on 5/4/17.
 */
public class UserService {

    private DSLContext database;
    final String table = "job_configuration.users";

    public UserService(DSLContext database){
        this.database = database;
    }

    public List<User> getUsers(){

        List<User> users = database.select().from(table(table)).fetch().into(User.class);

        return users;
    }

    public List<User> getUser(int id){

        Field<Integer> f = field(name( "id"), Integer.class);
        List<User> users = database.select().from(table(table)).where(f.eq(id)).fetch().into(User.class);

        return users;
    }

    public List<User> createUser(User user){
        return database.insertInto(table(table))
                .set(field("email"),user.email)
                .returning(field("id"), field("email"))
                .fetch()
                .into(User.class);
    }

    public boolean deleteUser(int id){
        database.delete(table(table)).where(field("id").eq(id)).execute();
        return true;
    }

    public boolean setUserValidated(int id){
        int rowsUpdated = database.update(table(table))
                .set(field("validated"),true)
                .where(field("id").eq(id))
                .execute();
        if(rowsUpdated==1) return true;
        return false;
    }
}
