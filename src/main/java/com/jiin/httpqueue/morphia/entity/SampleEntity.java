package com.jiin.httpqueue.morphia.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

@Entity("SampleEntity")
public class SampleEntity {
    @Id
    private String isbn;
    private String title;
    private String author;
    @Property("price")
    private double cost;
    // constructors, getters, setters and hashCode, equals, toString implementations
}