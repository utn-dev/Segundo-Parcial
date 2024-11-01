package Model;

public class RegisterTester {

    private String id;
    private Double temperature;

    public RegisterTester() {
    }

    public RegisterTester(String id, Double temperature) {
        this.id = id;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "RegisterTester{" + "id=" + id + ", temperature=" + temperature + '}';
    }



}
