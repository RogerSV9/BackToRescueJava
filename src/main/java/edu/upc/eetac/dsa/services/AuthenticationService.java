package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import edu.upc.eetac.dsa.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api(value = "/authentication", description = "Endpoint to Authentication Service")
@Path("/authentication")
public class AuthenticationService {
    final static Logger log = Logger.getLogger(AuthenticationService.class.getName());
    private BTRManager bm;

    public AuthenticationService(){
        this.bm = BTRManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Player.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })

    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(User user) {
        try {
            Player charact = this.bm.UserLogin(user);
            GenericEntity<Player> entity = new GenericEntity<Player>(charact){};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).entity(e).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e).build();
        }
    }


    @POST
    @ApiOperation(value = "Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "User already in the system")
    })

    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signin(User user) {
            Boolean bool;
            bool = bm.UserRegistration(user);
            if(bool) {
                return Response.status(201).build();
            }
            else{
                return Response.status(500).build();
            }
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
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Path("/update")
    public Response updateUser(Player player) {
        try {
            bm.UpdateStats(player);
            return Response.status(200).build();
        } catch (UserNotFoundException e){
            return  Response.status(404).build();
        } catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @ApiOperation(value = "Log-out", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })

    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Logout(Player player) {
        try {
            bm.UpdateStats(player);
            return Response.status(200).build();
        } catch (UserNotFoundException e){
            return Response.status(404).build();
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

}
