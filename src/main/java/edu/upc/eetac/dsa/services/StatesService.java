package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
        import edu.upc.eetac.dsa.Player;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import io.swagger.annotations.ApiResponse;
        import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
        import javax.ws.rs.core.GenericEntity;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;


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
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/statsbyusername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatsByUsername(@PathParam("username") String username) throws UserNotFoundException {
        try {
            Player player = this.bm.GetStats(username);
            GenericEntity<Player> entity = new GenericEntity<Player>(player){};
            return Response.status(200).entity(entity).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
    @PUT
    @ApiOperation(value = "Update Stats", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })

    @Path("/updatestats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Update_Stats(Player player) {
        try {
            this.bm.UpdateStats(player);
            return Response.status(200).build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

}
