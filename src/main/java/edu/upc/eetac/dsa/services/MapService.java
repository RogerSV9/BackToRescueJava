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


@Api(value = "/map", description = "Endpoint to Map Service")
@Path("/map")
public class MapService {
    private BTRManager bm;

    public MapService() {
        this.bm = BTRManagerImpl.getInstance();
    }
    @POST
    @ApiOperation(value = "GetLevelData", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response= String.class)
    })

    @Path("/level/{level}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLevelData(@PathParam("level") int level) {
        String game = bm.GetLevelData(level);
        GenericEntity<String> entity = new GenericEntity<String>(game){};
        return Response.status(200).entity(entity).build();
    }

}