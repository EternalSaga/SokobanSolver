package uk.ac.ncl.csc8005.team3.algorithm;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Test {
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException, NoSuchElementException {
		SokobanSolver SS = new SokobanSolver();
		SS.loadFile("examples/t9.txt", 'm');
		System.out.println(SS.solve('b'));
	}
}
