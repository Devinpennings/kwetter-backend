package service;

import model.Kweet;
import model.Model;
import util.PaginationDetails;
import data.interfaces.IUserDAO;
import model.User;
import util.exceptions.NotFoundException;

import javax.inject.Inject;
import java.util.*;

import static util.security.Constants.USER;

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

        if (user.getRoles().size() == 0) {
            user.getRoles().add(USER);
        }

        if (user.getPicture() == null || user.getPicture().trim().length() == 0) {
            user.setPicture("http://i.pravatar.cc/300");
        }

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

    public Collection<Kweet> getTimeline(String userId) throws NotFoundException {

        SortedSet<Kweet> kweets = new TreeSet<>();
        User user = this.userDAO.get(userId).orElseThrow(NotFoundException::new);
        user.getFollowing().forEach(u -> kweets.addAll(u.getPostedKweets()));
        kweets.addAll(user.getPostedKweets());
        return ((TreeSet<Kweet>) kweets).descendingSet();

    }
}
