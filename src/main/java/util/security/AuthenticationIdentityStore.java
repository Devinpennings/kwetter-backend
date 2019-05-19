package util.security;

import data.interfaces.IUserDAO;
import model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@RequestScoped
public class AuthenticationIdentityStore implements IdentityStore {

    @Inject
    private IUserDAO userDAO;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        CredentialValidationResult result;

        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePassword = (UsernamePasswordCredential) credential;
            Optional<User> user = userDAO.getByUsername(usernamePassword.getCaller());

            if (!user.isPresent()) {
                result = NOT_VALIDATED_RESULT;
                return result;
            }

            String expectedPW = user.get().getPassword();
            if (expectedPW != null && expectedPW.equals(usernamePassword.getPasswordAsString())) {
                result = new CredentialValidationResult(user.get().getId());
            } else {
                result = INVALID_RESULT;
            }
        } else {
            result = NOT_VALIDATED_RESULT;
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(VALIDATE);
    }
}