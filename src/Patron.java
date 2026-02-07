import java.util.*;
import java.io.*;

public class Patron {
    private String id;
    private String name;
    private String address;
    private double fine;

    public Patron(String id, String name, String address, double fine) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fine = fine;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getFine() { return fine; }

    @Override
    public String toString() {
        return String.format("%s-%s-%s-%.2f", id, name, address, fine); // format for the ID
    }
} 