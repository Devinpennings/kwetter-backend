package data.memory;

/**
 * Created by Devin
 */
public class MemoryUniqueIdentifier {

    private long id = 1L;

    public long next(){
        long returnId = this.id;
        this.id++;
        return returnId;
    }

}
