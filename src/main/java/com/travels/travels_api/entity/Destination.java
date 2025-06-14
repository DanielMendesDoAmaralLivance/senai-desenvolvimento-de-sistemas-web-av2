package com.travels.travels_api.entity;

public class Destination {
    private Long id;

    private String name;
    private String description;
    private String address;
    
    private double avgRating;
    private int totalRatings;

    // Getters / Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }
    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }
}
