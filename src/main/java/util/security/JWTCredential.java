package util.security;

import javax.security.enterprise.credential.Credential;
import java.util.Set;

/**
 * Created by Devin
 */
public class JWTCredential implements Credential {

    private final String principal;
    private final Set<String> authorities;

    public JWTCredential(String principal, Set<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

}