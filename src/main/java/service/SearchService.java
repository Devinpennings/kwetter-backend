package service;

import data.interfaces.IKweetDAO;
import data.interfaces.ITrendDAO;
import data.interfaces.IUserDAO;
import model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Devin
 */
@Stateless
public class SearchService {

    @Inject
    private IUserDAO userDAO;

    @Inject
    private IKweetDAO kweetDAO;

    @Inject
    private ITrendDAO trendDAO;

    public Collection<Model> search(String term) {

        Collection<Model> models = new ArrayList<>();

        models.addAll(userDAO.search(term));
        models.addAll(kweetDAO.search(term));
        models.addAll(trendDAO.search(term));

        return models;

    }

}
