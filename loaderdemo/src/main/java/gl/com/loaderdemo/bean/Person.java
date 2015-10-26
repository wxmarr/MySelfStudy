package gl.com.loaderdemo.bean;

/**
 * Created by mac on 15-10-26.
 */
public class Person {

    private int id;
    private String name;
    private String age;

    /**
     * 对应的数据库biao名 以及列名
     */
    public static final String TABLE_NAME="tb_person";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
