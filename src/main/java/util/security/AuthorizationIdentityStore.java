package util.security;

import data.interfaces.IUserDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

/**
 * Created by Devin
 */
@RequestScoped
public class AuthorizationIdentityStore implements IdentityStore {

    @Inject
    private IUserDAO userDAO;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        Set<String> result = userDAO.get(validationResult.getCallerPrincipal().getName()).get().getRoles();
        if (result == null) {
            result = emptySet();
        }
        return result;
    }

    @Override
    public Set<IdentityStore.ValidationType> validationTypes() {
        return singleton(PROVIDE_GROUPS);
    }

}
