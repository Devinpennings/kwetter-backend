package data.memory;

import data.interfaces.IDAO;
import util.PaginationDetails;
import model.Model;
import util.IFunction;

import java.util.*;

public abstract class ModelMemoryDAO<T extends Model> implements IDAO<T> {

    protected final Set<T> items;

    public ModelMemoryDAO(){
        this.items = new TreeSet<>();
    }

    @Override
    public Collection<T> get(){

        return this.items;

    }

    @Override
    public Collection<T> get(PaginationDetails paginationDetails){

        int from = paginationDetails.getIndexFrom();
        int to = paginationDetails.getIndexTo() > this.items.size() ? this.items.size() : paginationDetails.getIndexTo();

        ArrayList<T> list = new ArrayList<>(this.items);
        return list.subList(from, to);

    }

    @Override
    public Optional<T> get(String id){

        return items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();

    }

    @Override
    public T add(T item) {

        item.onCreate();

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

        this.executeForPresent(item, this.items::remove);

        item.onUpdate();

        this.items.add(item);

        if(!wasPresent) return Optional.of(item);
        else return Optional.empty();

    }

    @Override
    public void delete(T item) {

        this.executeForPresent(item, (o) -> this.items.remove(o));

    }

    @Override
    public abstract Collection<T> search(String term);

    private void executeForPresent(T model, IFunction<T> function){

        if (model.getId() == null) return;

        Optional<T> item = this.items.stream()
            .filter(i -> i.getId().equals(model.getId()))
            .findAny();

        item.ifPresent(function::call);

    }
}
