package service;

import data.interfaces.IDAO;
import data.PaginationDetails;
import model.Kweet;
import model.User;
import util.exceptions.NotFoundException;

import java.util.Collection;

/**
 * Created by Devin
 */
public class KweetService {

    private IDAO<Kweet> kweetDAO;
    private IDAO<User> userDAO;

    public Kweet get(long id) throws NotFoundException {
        return this.kweetDAO.get(id).orElseThrow(NotFoundException::new);
    }

    public Collection<Kweet> get(){
        return kweetDAO.get();
    }

    public Collection<Kweet> get(PaginationDetails paginationDetails){
        return kweetDAO.get(paginationDetails);
    }

    public void add(Kweet kweet, long userId) throws NotFoundException {

        User user = this.userDAO.get(userId).orElseThrow(NotFoundException::new);
        user.postKweet(kweet);

        kweetDAO.add(kweet);
        userDAO.update(user);

    }

    public void like(long kweetId, long userId) throws NotFoundException {

        Kweet kweet = this.get(kweetId);
        User user = this.userDAO.get(userId).orElseThrow(NotFoundException::new);

        kweet.like(user);

        kweetDAO.update(kweet);
        userDAO.update(user);

    }

}
