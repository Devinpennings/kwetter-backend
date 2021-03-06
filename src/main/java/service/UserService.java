package service;

import util.PaginationDetails;
import data.interfaces.IUserDAO;
import model.User;
import util.exceptions.NotFoundException;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Devin
 */
public class UserService {

    @Inject
    private IUserDAO userDAO;

    public void setDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User get(String id) throws NotFoundException {
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

    public void follow(String followerId, String toFollowId) throws NotFoundException {

        User follower = this.get(followerId);
        User toFollow = this.get(toFollowId);

        toFollow.follow(follower);

        userDAO.update(follower);
        userDAO.update(toFollow);

    }

    public void unfollow(String followerId, String toUnfollowId) throws NotFoundException {

        User follower = this.get(followerId);
        User toUnfollow = this.get(toUnfollowId);

        toUnfollow.unfollow(follower);

        userDAO.update(follower);
        userDAO.update(toUnfollow);

    }
}
