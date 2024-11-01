package Model;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    private List<Person> healthy = new ArrayList<>();
    private List<RegisterTester> isolated = new ArrayList<>();

    public TestResult() {
    }

    public List<Person> getHealthy() {
        return healthy;
    }

    public void setHealthy(List<Person> healthy) {
        this.healthy = healthy;
    }

    public List<RegisterTester> getIsolated() {
        return isolated;
    }

    public void setIsolated(List<RegisterTester> isolated) {
        this.isolated = isolated;
    }
}
