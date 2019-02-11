package data.memory;

import data.IDAO;
import model.Model;
import util.IFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class ModelMemoryDAO<T extends Model> implements IDAO<T> {

    private Collection<T> items;
    private static MemoryUniqueIdentifier id;

    static {
        id = new MemoryUniqueIdentifier();
    }

    public ModelMemoryDAO(){

        this.items = new ArrayList<>();

    }

    @Override
    public Collection<T> get(){

        return this.items;

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
