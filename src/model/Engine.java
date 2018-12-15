package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Engine
{

	private static Engine instance = null;
	private StudentDAO dao;

	private Engine()
	{
	}

	public synchronized static Engine getInstance()
	{
		if (instance == null)
			instance = new Engine();
		return instance;
	}

	public String doPrime(String min, String max) throws Exception
	{
		// try {
		// int minInteger = Integer.parseInt(min);
		// int maxInteger = Integer.parseInt(max);
		//
		// if (minInteger > maxInteger)
		// throw new Exception("No more primes in range.");
		// else if (maxInteger < 0)
		// throw new Exception("end < 0: " + maxInteger);
		// else if (minInteger < 0)
		// throw new Exception("start < 0: " + minInteger);
		//
		// List<Integer> primes = new ArrayList<>();
		// // loop through the numbers one by one
		// for (int i = minInteger + 1; i <= maxInteger; i++) {
		// boolean isPrimeNumber = true; // check to see if the number is prime
		//
		// for (int j = 2; j < i; j++) {
		// if (i % j == 0) {
		// isPrimeNumber = false;
		// break;
		// }// exit the inner for loop
		// }
		// // add it to list the number if prime
		// if (isPrimeNumber) {
		// primes.add(i);
		// }
		// }
		// return primes.remove(0) + "";
		// } catch (NumberFormatException e) {
		// throw new Exception("Invalid Entries!");
		// } catch (IndexOutOfBoundsException e) {
		// throw new Exception("No more primes in range.");
		// }
		//
		long lowerLong = 0l;
		long upperLong = 0l;

		try
		{
			lowerLong = Long.parseLong(min);
			upperLong = Long.parseLong(max);
		} catch (NumberFormatException e)
		{
			throw new Exception("Invalid entires.");
		}

		if (upperLong < 0)
			throw new Exception("end < 0: " + upperLong);
		else if (lowerLong < 0)
			throw new Exception("start < 0: " + lowerLong);
		else if (lowerLong > upperLong)
			throw new Exception("No more primes in range");

		BigInteger primeInt;

		primeInt = new BigInteger(min);
		primeInt = primeInt.nextProbablePrime();

		if (primeInt.longValue() > Long.parseLong(max))
			throw new Exception("No more primes in range.");

		return primeInt.toString();

	}

	public List<StudentBean> doSis(String prefix, String minGpa, String sortBy) throws Exception
	{
		try
		{
			dao = new StudentDAO();
			List<StudentBean> students = dao.retrieve(prefix, minGpa, sortBy);
			return students;
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}
