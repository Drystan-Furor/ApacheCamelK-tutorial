package camel.k.tutorial;

public class MyBean {
    private Integer id;
    private String name;

    // toString method for logging purposes
    @Override
    public String toString() {
        return "MyBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
