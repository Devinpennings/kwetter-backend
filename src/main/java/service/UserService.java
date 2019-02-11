package service;

import data.interfaces.IDAO;
import data.PaginationDetails;
import model.User;
import util.exceptions.NotFoundException;

import java.util.Collection;

/**
 * Created by Devin
 */
public class UserService {

    private IDAO<User> userDAO;

    public User get(long id) throws NotFoundException {
        return userDAO.get(id).orElseThrow(NotFoundException::new);
    }

    public Collection<User> get(){
        return userDAO.get();
    }

    public Collection<User> get(PaginationDetails paginationDetails){
        return userDAO.get(paginationDetails);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public void add(User user){
        userDAO.add(user);
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
