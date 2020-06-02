import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Program {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Program obj = new Program();
		System.out.println("Enter the names in list-1 (Enter # to terminate the list)");
		obj.readNames("list-1.txt");
		System.out.println("Enter the names in list-2 (Enter # to terminate the list)");
		obj.readNames("list-2.txt");
		obj.combineLists();
		obj.display();
	}

	private void display() throws IOException {
		BufferedReader b = new BufferedReader(new FileReader("list-3.txt"));
		String s = b.readLine();
		if (s == null)
			System.out.println("No matching string");
		else {
			System.out.println("Common names in both lists are: ");
			do {
				System.out.println(s);
			} while ((s = b.readLine()) != null);
		}
		b.close();
	}

	private void sort(String[] s, int count) {
		String temp;
		for (int i = 0; i < count; i++)
			for (int j = i + 1; j < count; j++)
				if (s[i].compareTo(s[j]) > 0) {
					temp = s[i];
					s[i] = s[j];
					s[j] = temp;
				}
	}

	private void combineLists() throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader("list-1.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("list-2.txt"));
		PrintWriter pw = new PrintWriter("list-3.txt");
		String name1 = br1.readLine();
		String name2 = br2.readLine();
		while (name1 != null && name2 != null) {
			if (name1.equals(name2)) {
				pw.println(name1);
				name1 = br1.readLine();
				name2 = br2.readLine();
			} else if (name1.compareTo(name2) < 0)
				name1 = br1.readLine();
			else
				name2 = br2.readLine();
		}
		pw.close();
		br2.close();
		br1.close();
	}

	private void readNames(String fname) throws FileNotFoundException {
		String s[] = new String[50];
		PrintWriter pw = new PrintWriter(fname);
		int i = 0, j;
		for (i = 0;; i++) {
			s[i] = sc.nextLine();
			if (s[i].equals("#"))
				break;
		}
		sort(s, i);
		for (j = 0; j < i; j++)
			pw.println(s[j]);
		pw.close();

	}
}
