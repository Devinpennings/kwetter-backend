package data.memory;

import data.interfaces.IDAO;
import data.PaginationDetails;
import model.Model;
import util.IFunction;

import java.util.*;

public class ModelMemoryDAO<T extends Model> implements IDAO<T> {

    protected Set<T> items;
    private static MemoryUniqueIdentifier id;

    static {
        id = new MemoryUniqueIdentifier();
    }

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
    public void add(T item) {

        if (item.getId() == 0L) {
            item.setId(id.next());
        }

        this.items.add(item);

    }

    @Override
    public void add(Collection<T> items){

        for (T item : items){
            this.add(item);
        }

    }

    @Override
    public void update(T item){

        this.executeForPresent(item, (o) -> this.items.remove(o));
        this.items.add(item);

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