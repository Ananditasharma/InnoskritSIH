package com.example.mckinley;

public class List_data {
    private int id;
    private String name;
    private int year;
    private String color;
    private int pantone_value;

    public List_data(int id, String name, String color, int year, int pantone_value) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.year = year;
        this.pantone_value = pantone_value;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getyear() {
        return year;
    }

    public int getPantone_value() {
        return pantone_value;
    }


}
