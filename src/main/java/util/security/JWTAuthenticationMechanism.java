package util.security;

import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.identitystore.CredentialValidationResult;

import static util.security.Constants.AUTHORIZATION_HEADER;
import static util.security.Constants.BEARER;

public class JWTAuthenticationMechanism {

    @Inject
    private TokenProvider tokenProvider;

    /**
     * To validate the JWT token e.g Signature check, JWT claims
     * check(expiration) etc
     *
     * @param token   The JWT access tokens
     * @return the AuthenticationStatus to notify the container
     */
    public JWTCredential validateToken(String token) {
        try {
            if (tokenProvider.validateToken(token)) {
                return tokenProvider.getCredential(token);
            }
            return null;
        } catch (Exception eje) {
            return null;
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result  the result from validation of UsernamePasswordCredential
     * @return the AuthenticationStatus to notify the container
     */
    public String createToken(CredentialValidationResult result) {
        return tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups());
    }

}
