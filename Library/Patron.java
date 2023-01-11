package com.learn.school;

public class Patron {
public String name;
public BookList books = new BookList();

public Patron(String name) {
	this.name = name;
}

public Patron(String name, Book book) {
	this.name = name;
	books.add(book);
}
}
