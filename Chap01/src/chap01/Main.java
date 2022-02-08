package chap01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = N;
		while(K >= 0) {
			if(K > 1) {
				System.out.println(K + " bottles of beer on the wall, " + K + " bottles of beer.\r\n" + 
					"Take one down and pass it around, " + (K-1) + " bottles of beer on the wall.\n");
				K--;
			} else if(K == 1) {
				System.out.println(K + " bottle of beer on the wall, " + K + " bottle of beer.\r\n" + 
						"Take one down and pass it around, no more bottles of beer on the wall.\n");
				K--;
			} else {
				System.out.println("No more bottles of beer on the wall, no more bottles of beer.\r\n" + 
						"Go to the store and buy some more, " + N +" bottles of beer on the wall.\n");
				K--;
			}
		}		
	}
}
