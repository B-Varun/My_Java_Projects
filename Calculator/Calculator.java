/**
 * The Calculator class works with 2 numbers and performs the following operations:
 * Addition
 * Subtraction
 * Division
 * Multiplication
 *
 * Note : This program only works on integers.
 */

import java.util.Scanner;

public class Calculator
{

	public static void main(String... arguments)
	{
		boolean while_condition = true;
		int num1, num2;
		String operation;

		Scanner scan = new Scanner(System.in);
		while(while_condition)
		{

			System.out.println("Enter the required operation: \n1) Addition\t\t2) Subtraction\n3) Division\t\t4) Multiplication"		  +"\n\t\t5) Exit");
			operation=optionEntered(scan.nextLine());
			isExit(operation);
			if(isInvalid(operation))
				continue;

			System.out.println("Enter 2 numbers for performing "+operation+" operation");
			num1 = scan.nextInt();
			num2 = scan.nextInt();
			executeOperation(operation, num1, num2);

			scan.nextLine();
		}
		scan.close();
	}

	public static String optionEntered(String option)
	{
		if(option.equals("1"))
			return "Addition";
		else if(option.equals("2"))
			return "Subtraction";
		else if(option.equals("3"))
			return "Division";
		else if(option.equals("4"))
			return "Multiplication";
		else if(option.equals("5"))
			return "Exit";
		else
			return "Invalid Option";
	}

	public static int addition(int num1, int num2)
	{
		return num1+num2;
	}

	public static int subtraction(int num1, int num2)
	{
		return num1-num2;
	}

	public static int multiplication(int num1,int num2)
	{
		return num1*num2;
	}

	public static int division(int num1, int num2)
	{
		return num1/num2;
	}

	public static void print(String option, int num1, int num2, int result)
	{
		System.out.println("After "+option+" of "+num1+" ,"+num2+",the result is :"+result);
		printEnd();
	}

	public static void executeOperation(String option, int num1, int num2)
	{
		int result=0;
		if(option.equals("Addition"))
			result = addition(num1, num2);
		else if(option.equals("Subtraction"))
			result = subtraction(num1, num2);
		else if(option.equals("Division"))
			result = division(num1, num2);
		else if(option.equals("Multiplication"))
			result = multiplication(num1, num2);
		else
		System.out.println("the program has an error.");

		print(option, num1, num2, result);
	}

	public static void isExit(String option)
	{
		if(option.contains("Exit"))
				System.exit(0);
		else
				return;
	}

	public static boolean isInvalid(String option)
	{
		if(option.contains("Invalid Option"))
			{
				System.out.println("entered : "+option+"|||||");
				System.out.println("Please enter a valid option. Try Again. Good Luck!!!!");
				printEnd();
				return true;
			}
		else 
				return false;
	}

	public static void printEnd(){
		System.out.println("========================================================================================================================");
	}
}
