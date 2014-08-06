/*
 * @(#)	2014-7-21
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

import java.util.Scanner;

/**
 * 
 * @author wutalk
 */
public class TriangleGame {
	public static void main(String[] args) {
		if (args.length > 0 && "-h".equals(args[0])) {
			usage();
		}
		String[] slides = new String[3];
		if (args.length > 0 && "-p".equals(args[0])) {
			if (args.length != 4) {
				System.out.println("Invalid argument list, you must input 3 numbers");
				usage();
			}
			slides[0] = args[1];
			slides[1] = args[2];
			slides[2] = args[3];
		} else {
			slides = readSlides();
		}
		if (slides.length != 3) {
			System.out.println("Invalid argument list, you must input 3 numbers");
			usage();
		} else {
			try {
				int a = Integer.parseInt(slides[0]);
				int b = Integer.parseInt(slides[1]);
				int c = Integer.parseInt(slides[2]);
				System.out.println(tell(a, b, c));

			} catch (NumberFormatException e) {
				System.out.println("sides of a triangle should be "
						+ "integer numbers(-2147483648 ~ 2147483647)");
				usage();
			}
		}
	}

	private static String[] readSlides() {
		System.out.println("Please input three integer values, delimited by space, e.g: 3 4 5");
		Scanner scanIn = new Scanner(System.in);
		String numbers = scanIn.nextLine();
		scanIn.close();
		String[] slides = numbers.split("\\s+");
		return slides;
	}

	static void usage() {
		System.out.println("Usage:\tjava -jar triangle-teller.jar [-p slide1 slide2 slide3]");
		System.out.println("Example: check if slide 3 4 5 can be a triangle\n\t"
				+ "java -jar triangle-teller.jar -p 3 4 5");
		System.exit(0);
	}

	public static String tell(int i, int j, int k) {
		long a = i;
		long b = j;
		long c = k;
		if (a <= 0 || b <= 0 || c <= 0) {
			return "Sides[" + a + ", " + b + ", " + c
					+ "] cannot be a Triangle, all sides should be greater than 0.";
		}
		if (a + b <= c || a + c <= b || b + c <= a) {
			String res = "Sides[" + a + ", " + b + ", " + c + "] cannot be a Triangle, "
					+ "the sum of any two sides should be greater than the rest side.";
			return res;
		}
		if (a == b && b == c) {
			return ("Sides[" + a + ", " + b + ", " + c + "] is a Equilateral Triangle.");
		} else if (a == b || b == c || a == c) {
			return ("Sides[" + a + ", " + b + ", " + c + "] is a Isosceles Triangle.");
		} else {
			return ("Sides[" + a + ", " + b + ", " + c + "] is a Scalene Triangle.");
		}
	}

}
