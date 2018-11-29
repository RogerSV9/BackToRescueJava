package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.Object;
import java.util.List;

import static edu.upc.eetac.dsa.BTRManagerImpl.getInstance;


@Api(value = "/service", description = "Endpoint to BTR Service")
@Path("/service")
@Singleton
public class BTRService {
    private BTRManager bm;

    public BTRService(){
        this.bm = BTRManagerImpl.getInstance();
        bm.UserRegistration("Laia","munoz");
        bm.AddObject("Laia","Sword","sword.jpg");
        bm.AddObject("Laia","Gold","gold.jpg");
    }
    @GET
    @Path("/Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @ApiOperation(value = "get Objects from a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Object.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/objectsbyusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjectsByUsername(@PathParam("username") String username) throws UserNotFoundException {
        List<Objeto> objects;
        try {
            objects = this.bm.GetObjects(username);
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objects){};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
