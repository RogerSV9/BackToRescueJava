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
import java.lang.Object;
import java.util.List;


@Api(value = "/scoreboard", description = "Endpoint to Scoreboard Service")
@Path("/scoreboard")
public class ScoreBoardService {
    private BTRManager bm;

    public ScoreBoardService() {
        this.bm = BTRManagerImpl.getInstance();
    }
    @GET
    @ApiOperation(value = "Get scoreboard", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Object.class)
    })
    @Path("/scoreboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScoreboard() {
        List<Player> players;
        players = this.bm.GetScoreboard();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(players){};
        return Response.status(200).entity(entity).build();
    }
}
