package controller.rest;

import util.security.JWTAuthenticationMechanism;
import util.security.JWTCredential;
import util.security.TokenProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.security.Constants.*;

/**
 * Created by Devin
 */
@RequestScoped
public class JWTHttpAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private JWTAuthenticationMechanism authenticationMechanism;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {

        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String token = extractToken(context);

        context.getResponse().setHeader("Access-Control-Allow-Origin", "*");

        if (name != null && password != null) {
            // validation of the credential using the identity store
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(name, password));
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                // Communicate the details of the authenticated user to the container and return SUCCESS.
                String jwt = this.authenticationMechanism.createToken(result);
                context.getResponse().setHeader(AUTHORIZATION_HEADER, BEARER + jwt);
                return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
            }
            // if the authentication failed, we return the unauthorized status in the http response
            return context.responseUnauthorized();
        } else if (token != null) {
            // validation of the jwt credential
            JWTCredential credential = this.authenticationMechanism.validateToken(token);
            if (credential != null) {
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            } else {
                return context.responseUnauthorized();
            }

        } else if (context.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return context.responseUnauthorized();
        }
        // there are no credentials AND the resource is not protected,
        // SO Instructs the container to "do nothing"
        return context.doNothing();
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.substring(BEARER.length());
        }
        return null;
    }

}