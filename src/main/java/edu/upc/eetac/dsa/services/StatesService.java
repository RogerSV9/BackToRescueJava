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


@Api(value = "/states", description = "Endpoint to States Service")
@Path("/states")
public class StatesService {
    private BTRManager bm;

    public StatesService() {
        this.bm = BTRManagerImpl.getInstance();
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
    @ApiOperation(value = "Update Stats", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Character.class),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/updatestats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Update_Stats(Character character) {
        this.bm.UpdateStats(character);
        return Response.status(200).build();
    }

}
