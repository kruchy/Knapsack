package main;


public class NotValidInput extends Exception {

	public NotValidInput() {
		System.out.println("Not valid input");
	}
	public NotValidInput(String msg)
	{
		System.out.println(msg);
	}
}
