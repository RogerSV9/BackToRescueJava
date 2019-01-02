package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import edu.upc.eetac.dsa.Character;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.Object;
import java.util.List;


@Api(value = "/objects", description = "Endpoint to Objects Service")
@Path("/objects")
public class ObjectsService {
    private BTRManager bm;

    public ObjectsService() {
        this.bm = BTRManagerImpl.getInstance();
        bm.AddObject("Laia","Sword","sword.jpg");
        bm.AddObject("Laia","Gold","gold.jpg");
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
    @POST
    @ApiOperation(value = "Add an object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/addobject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signin(Objeto objeto) {
        bm.AddObject(objeto.getUsername(),objeto.getName(),objeto.getImage());
        return Response.status(201).build();
    }

}
