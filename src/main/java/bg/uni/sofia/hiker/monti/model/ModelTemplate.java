package bg.uni.sofia.hiker.monti.model;

import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;
public class ModelTemplate {

    private String id;
    private String name;
    private String type;
    private double elevation;
    private Pair<Double, Double> coordinates;

    public ModelTemplate() {}

    public ModelTemplate(String id, String name, String type, double elevation, Pair<Double, Double> coordinates) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.elevation = elevation;
        this.coordinates = coordinates;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getElevation() {
        return this.elevation;
    }

    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

//    @Override
//    public boolean equals(Object o) {
//
//        if (this == o)
//            return true;
//        if (!(o instanceof Employee))
//            return false;
//        Employee employee = (Employee) o;
//        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
//                && Objects.equals(this.role, employee.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.id, this.name, this.role);
//    }
//
//    @Override
//    public String toString() {
//        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
//    }
}