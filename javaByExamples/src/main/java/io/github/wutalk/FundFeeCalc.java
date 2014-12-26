/*
 * @(#)	2014-12-26
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package io.github.wutalk;

/**
 * <p>
 * monthly amount(a), buyFee(bf%), for years(y), adminFee%(af), serviceFee%(sf).
 * </p>
 * <p>
 * total fee = 12a*bf%*y + 12a(af%+sf%) + 2*12a(af%+sf%) + ... + y*12a(af%+sf%).
 * </p>
 * <p>
 * = 12a*bf%*y + (y/2)*(y+1)*12a(af%+sf%).
 * </p>
 * <p>=
 * 12*a*y(bf% + ((y+1)/2)*(af%+sf%))
 * 
 * @author wutalk
 */
public class FundFeeCalc {

	public static void main(String[] args) {
		if (args.length != 5) {
			System.out
					.println("Usage: \njava -jar FundFeeCalc.jar amount buyFee years adminFee serviceFee");
			System.exit(0);
		}
		double a = Double.parseDouble(args[0]);
		double bf = Double.parseDouble(args[1]);
		int y = Integer.parseInt(args[2]);
		double af = Double.parseDouble(args[3]);
		double sf = Double.parseDouble(args[4]);

		System.out.println("input: amount=" + a + ", buyFee=" + bf + "%, years=" + y
				+ ", adminFee=" + af + "%, serviceFee=" + sf + "%");

		bf = bf / 100;
		af = af / 100;
		sf = sf / 100;

		double totalFee = 12 * a * y * (bf + ((y + 1) / 2) * (af + sf));

		System.out.printf("total fee: %.2f", totalFee);
	}

}
