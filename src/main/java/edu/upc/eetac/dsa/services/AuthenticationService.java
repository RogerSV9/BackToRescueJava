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


@Api(value = "/authentication", description = "Endpoint to Authentication Service")
@Path("/authentication")
public class AuthenticationService {
    final static Logger log = Logger.getLogger(AuthenticationService.class.getName());
    private BTRManager bm;

    public AuthenticationService(){
        this.bm = BTRManagerImpl.getInstance();
        User user1 = new User("Laia", "munoz");
        bm.UserRegistration(user1);
        bm.AddCharacter("Laia",100,100,50,50,2,999);
    }

    @POST
    @ApiOperation(value = "Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Character.class),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(User user) {
        try {
            Character charact = this.bm.UserLogin(user);
            GenericEntity<Character> entity = new GenericEntity<Character>(charact){};
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

    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signin(User user) {
            bm.UserRegistration(user);
            return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a User", notes = "asdasd")
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
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/update")
    public Response updateUser(Character character) {
        bm.LogOut(character);
        return Response.status(201).build();
    }


}
