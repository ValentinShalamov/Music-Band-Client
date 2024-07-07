package music;

public class BestAlbum {

    private final String name;
    private long sales;

    public BestAlbum(String name, long sales) {
        this.name = name;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public long getSales() {
        return sales;
    }
}
