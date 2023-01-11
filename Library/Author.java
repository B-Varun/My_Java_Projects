package com.learn.school;

public class Author {
	public String name;
	public BookList books = new BookList();

	public boolean equals(Object o) {
		return name.equals(((Author)o).name);
	}

}
