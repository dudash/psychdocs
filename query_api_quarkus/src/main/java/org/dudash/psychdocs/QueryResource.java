package org.dudash.psychdocs;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;

// import io.vertx.axle.core.eventbus.EventBus;

@Path("/queries")

public class QueryResource {
    // FUTURE USE
    @ConfigProperty(name = "quickauthenforcing", defaultValue = "true")
    protected boolean quickAuthEnforcing;
    @ConfigProperty(name = "quickauthuser", defaultValue = "true")
    protected String quickAuthUser;
    @ConfigProperty(name = "quickauthpassword", defaultValue = "true")
    protected String quickAuthPassword;
    @ConfigProperty(name = "checksumenforcing", defaultValue = "true")
    protected boolean checksumEnforcing;
    @ConfigProperty(name = "checksumsecret", defaultValue = "123456")
    protected String checksumSecret;

    // @Inject EventBus bus;

    @GET
    @Produces("application/json")
    public List<Query> list() {
        return Query.listAll();
    }

    @GET
    @Path("/count")
    @Produces("text/plain")
    public Long count() {
        return Query.count();
    }

    @POST
    @Consumes("application/json")
    public Response create(@HeaderParam("Authorization") String authorization, Query query) {
        if (quickAuthEnforcing) {
            if (authorization == null) return Response.status(401).build();
            if (!authorization.toLowerCase().startsWith("basic")) return Response.status(401).build();
            String base64string = authorization.substring("Basic".length()).trim();
            byte[] bytes = Base64.getDecoder().decode(base64string);
            String credentials = new String(bytes, StandardCharsets.UTF_8);
            final String[] keyValueCredentials = credentials.split(":", 2);
            if (keyValueCredentials[0].compareTo(quickAuthUser)!=0) return Response.status(401).build();
            if (keyValueCredentials[1].compareTo(quickAuthPassword)!=0) return Response.status(401).build();
        }
        else {
            System.out.println("POST to /query: ignoring basic auth");
        }
        if (checksumEnforcing) {
            // System.out.println("POST to /query: checksum="+query.checksum);
            // if (query.checksum == null || query.checksum.length()<=0) return Response.status(401).build();
            // if (!validateChecksum(query.name, query.query.toString(), query.checksum)) return Response.status(401).build();
        }
        else {
            System.out.println("POST to /query: ignoring checksums");
        }
        query.persist();
        return Response.status(201).build();
    }
}
