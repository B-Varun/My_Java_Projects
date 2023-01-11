package com.learn.school;

public class Book {
public String name;
public int count_of_books;

public Book(String name, int count) {
	this.name = name;
	this.count_of_books = count;
}

public boolean equals(Object o) {
	return name.equals(((Book)o).name);
}

}
