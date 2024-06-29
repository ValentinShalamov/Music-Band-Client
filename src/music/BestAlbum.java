package music;

public class BestAlbum {

    private final String name; //Поле не может быть null, Строка не может быть пустой
    private long sales; //Значение поля должно быть больше 0

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
