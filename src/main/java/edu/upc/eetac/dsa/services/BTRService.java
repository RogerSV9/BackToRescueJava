package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import edu.upc.eetac.dsa.Character;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

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
public class BTRService {
    final static Logger log = Logger.getLogger(BTRService.class.getName());
    private BTRManager bm;

    public BTRService(){
        this.bm = BTRManagerImpl.getInstance();
        bm.UserRegistration("Laia","munoz");
        bm.AddObject("Laia","Sword","sword.jpg");
        bm.AddObject("Laia","Gold","gold.jpg");
        bm.AddCharacter("Laia",100,100,50,50,2,999);
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
    @ApiOperation(value = "Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Character.class),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/login/{user}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(@PathParam("user") String user, @PathParam("password") String password) {
        try {
            Character charact = this.bm.UserLogin(user, password);
            GenericEntity<Character> entity = new GenericEntity<Character>(charact){};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "GetLevelData", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= String.class)
    })

    @Path("/level/{level}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLevelData(@PathParam("level") int level) {
        String game = bm.getLevelData(level);
        //game = "Game";
        GenericEntity<String> entity = new GenericEntity<String>(game){};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Sign-in", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/signin/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signin(@PathParam("username") String username, @PathParam("password") String password) {
            bm.UserRegistration(username,password);
            return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/delete/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteUser(@PathParam("username") String username, @PathParam("password") String password) {
        try {
            bm.UserDelete(username, password);
            return Response.status(201).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/update")
    public Response updateUser(Character character) {
        bm.LogOut(character);
        return Response.status(201).build();
    }
    @GET
    @ApiOperation(value = "get Stats from a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Character.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/statsbyusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatsByUsername(@PathParam("username") String username) throws UserNotFoundException {
        try {
            Character character = this.bm.GetStats(username);
            GenericEntity<Character> entity = new GenericEntity<Character>(character){};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @POST
    @ApiOperation(value = "Sign-in", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/addobject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signin(Objeto objeto) {
        bm.AddObject(objeto.getUsername(),objeto.getName(),objeto.getImage());
        return Response.status(201).build();
    }
}
