package data.memory;

import data.interfaces.IDAO;
import data.PaginationDetails;
import model.Model;
import model.User;
import util.IFunction;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.*;

public abstract class ModelMemoryDAO<T extends Model> implements IDAO<T> {

    @Inject
    private MemoryUniqueIdentifier id;
    Set<T> items;

    public ModelMemoryDAO(){

        this.id = new MemoryUniqueIdentifier();
        this.items = new TreeSet<>();

    }

    @Override
    public Collection<T> get(){

        return this.items;

    }

    @Override
    public Collection<T> get(PaginationDetails paginationDetails){

        int from = paginationDetails.getIndexFrom();
        int to = paginationDetails.getIndexTo();

        ArrayList<T> list = new ArrayList<>(this.items);
        return list.subList(from, to);

    }

    @Override
    public Optional<T> get(long id){

        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst();

    }

    @Override
    public T add(T item) {

        if (item.getId() == 0L) {
            item.setId(id.next());
        }

        item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        item.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.items.add(item);

        return item;

    }

    @Override
    public Collection<T> add(Collection<T> items){

        for (T item : items){
            this.add(item);
        }

        return items;

    }

    @Override
    public Optional<T> update(T item){

        boolean wasPresent = this.items.contains(item);

        this.executeForPresent(item, (o) -> this.items.remove(o));

        item.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        this.items.add(item);

        if(wasPresent) return Optional.of(item);
        else return Optional.empty();

    }

    @Override
    public void delete(T item) {

        this.executeForPresent(item, (o) -> this.items.remove(o));

    }

    private void executeForPresent(T model, IFunction<T> function){

        if (model.getId() == 0L) return;

        Optional<T> item = this.items.stream()
            .filter(i -> i.getId() == model.getId())
            .findAny();

        item.ifPresent(function::call);

    }
}
