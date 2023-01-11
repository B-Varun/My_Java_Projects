package com.learn.school;

import java.util.Scanner;

public class Library {

	private AuthorList[] authorList = new AuthorList['z'+1];
	private PatronList[] patronList = new PatronList['z'+1];
	
	public Library() {
		for(int i=0; i<'z'+1; i++) {
			authorList[i] = new AuthorList();
			patronList[i] = new PatronList();
		}
	}
	
	public void addBook() {
		String bookName, authorName;
		int no_of_books;
		authorName = getData("Enter Author's Name");
		bookName = getData("Enter Book Name");
		no_of_books = Integer.valueOf(getData("Enter the no. of books"));
		
		Book newBook = new Book(bookName, no_of_books);
		Author author = new Author();
		author.name = authorName;
		author.books.add(newBook);

		int indexOfAuthor = authorList[(int)authorName.charAt(0)].indexOf(author);
		if(indexOfAuthor == -1) 
			authorList[authorName.charAt(0)].add(author);
		else 
			((Author)authorList[authorName.charAt(0)].get(indexOfAuthor)).books.add(newBook);
	}

	public void borrowBook() {
		System.out.println("BorrowBook");
		String authorName, bookName="", patronName;
		int author_index, book_index=-1;
		patronName = getData("Enter the patron name");
		do {
		authorName = getData("Enter the author Name");
		//Is the author registered?
		Author author=new Author();
		author.name=authorName;
		author_index = authorList[authorName.charAt(0)].indexOf(author);       //change
		System.out.println("\t\tAuthor present at index : "+author_index);
		if(author_index>-1)
		{
			bookName = getData("Enter Book to borrow");
			// Is the book registered on Author
			Book book = new Book(bookName, 0);
			book_index = ((Author)authorList[authorName.charAt(0)].get(author_index)).books.indexOf(book);       //change
			if(book_index>-1)
				break;
			else {
				System.out.println("Enter registered book name");
				continue;
			}
		}
		else {
			System.out.println("Enter registered Author name");
			continue;
		}
		}while(author_index<=-1 && book_index<=-1);		
		
		System.out.println("Author Index :"+author_index+"\tBook Index : "+book_index);
		
		int available_books = ((Book)((Author)authorList[authorName.charAt(0)].get(author_index)).books.get(book_index)).count_of_books;
		if(available_books > 0)
		{
			// Lend the book to the patron and update the book availability in the library
			Book book = new Book(bookName, --available_books);
			Patron patron = new Patron(patronName, book);
			int patron_index = patronList[patronName.charAt(0)].indexOf(patron);       //change
			if(patron_index<=-1) {
				// Add patron to the list
				patronList[patronName.charAt(0)].add(patron);
			}
			else {
				//Patron already present. Update his list
				((Patron)patronList[patronName.charAt(0)].get(patron_index)).books.add(book);
			}
		}
		else {
			// All books are under loan, please check later.
			System.out.println("All available books are already lent. Please checkin later.");
		}
	}

	public void bookReturn() {
		System.out.println("ReturnBook");
		String patronName, authorName, bookName;
		int patron_index=-1, author_index=-1, book_index=-1;
		
		do {
		patronName = getData("Enter the Patron name");		
		// Validate if patron exists, author exists and then the book exists
		Patron patron = new Patron(patronName);
		patron_index = patronList[patronName.charAt(0)].indexOf(patron);
		if(patron_index>-1) {
			//Patron Present. Check if the author is valid or not.
			authorName = getData("Enter the Author Name");
			Author author = new Author();
			author.name = authorName;
			author_index = authorList[authorName.charAt(0)].indexOf(author);
			if(author_index>-1) {
				// Author Present. Validate Book
				bookName = getData("Enter the book Name");
				Book book = new Book(bookName, 0);
				BookList list = ((Patron)patronList[patronName.charAt(0)].get(patron_index)).books;
				boolean is_book_on_loan = list.contains(book);
				if(is_book_on_loan) {
					((Patron)patronList[patronName.charAt(0)].get(patron_index)).books.remove(book);
					author_index = authorList[authorName.charAt(0)].indexOf(authorName);
					book_index = ((Author)authorList[authorName.charAt(0)].get(author_index)).books.indexOf(bookName);
					book = (Book)((Author)authorList[authorName.charAt(0)].get(author_index)).books.get(book_index);
					book.count_of_books = book.count_of_books+1;
					((Author)authorList[authorName.charAt(0)].get(author_index)).books.set(book_index, book);
				}
			}
			else{
				System.out.println("Enter valid Author Name");
				continue;
			}
		}
		else {
			System.out.println("Enter a valid patron name who already used the library");
		}
	}while(patron_index>-1 && author_index>-1 && book_index>-1);
		
	}

	public void status() {
		System.out.println("Status");
		System.out.println("The Library contains the following books: ");
		// Loops through authors and print books registered under them
		for(AuthorList list : authorList) {
			for(Object item : list) {
				System.out.println(((Author)item).name);
				for(Object books : ((Author)item).books) {
					Book book = (Book)books;
					System.out.println("*\t"+book.name+"\t--\t"+book.count_of_books);
				}
				System.out.println("____________________________________________________________________________________\n");
			}
		}
		System.out.println("The below mentioned Patrons are using the library: ");
		// Loop through available Patrons and print all the books lent by them
		for(PatronList list : patronList) {
			for(Object item : list) {
				System.out.println(((Patron)item).name);
				for(Object books : ((Patron)item).books){
					Book book = (Book)books;
					System.out.println("*\t"+book.name);
				}
			}
		}
		
		
	}

	public String getData(String message) {
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		String data = "";
		do {
			data = scan.nextLine();
			if(data.isEmpty())
				System.out.println("Enter valid data");
		}while(data.isEmpty());
		return data.trim();
	}

	public void start() {
		while(true) {
			String choice = getData("Enter your choice :\n1) Add a book to the library\n2) Borrow a book from Library\n3) Return borrowed Book\n"+
					"4) Status of library\n5) Quit");
			char user_choice = choice.charAt(0);
			switch(user_choice) {
			default : System.out.println("Wrong option. Enter again!"); break;
			case '1' : addBook(); break;
			case '2' : borrowBook(); break;
			case '3' : bookReturn(); break;
			case '4' : status(); break;
			case '5' : return; 
			}	
		}
	}	

	public static void main(String[] args) {
		new Library().start();
	}		
}
