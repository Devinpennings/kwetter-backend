package service;

import data.interfaces.IDAO;
import data.PaginationDetails;
import data.interfaces.IUserDAO;
import model.User;
import util.exceptions.NotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
@Stateless
public class UserService {

    @Inject
    private IUserDAO userDAO;

    public User get(long id) throws NotFoundException {
        return userDAO.get(id).orElseThrow(NotFoundException::new);
    }

    public Collection<User> get(){
        return userDAO.get();
    }

    public Collection<User> get(PaginationDetails paginationDetails){
        return userDAO.get(paginationDetails);
    }

    public Optional<User> update(User user){
        return userDAO.update(user);
    }

    public User add(User user){
        return userDAO.add(user);
    }

    public void follow(long followerId, long toFollowId) throws NotFoundException {

        User follower = this.get(followerId);
        User toFollow = this.get(toFollowId);

        toFollow.follow(follower);

        userDAO.update(follower);
        userDAO.update(toFollow);

    }

    public void unfollow(long followerId, long toUnfollowId) throws NotFoundException {

        User follower = this.get(followerId);
        User toUnfollow = this.get(toUnfollowId);

        toUnfollow.unfollow(follower);

        userDAO.update(follower);
        userDAO.update(toUnfollow);

    }

}
