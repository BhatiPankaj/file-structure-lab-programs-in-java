import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Program obj = new Program();
		obj.create();
		obj.merge();
		obj.display();
	}

	private void display() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("mergedList.txt")));
		String l;
		System.out.println("The merged list is: ");
		while ((l = br.readLine()) != null)
			System.out.println(l);
		br.close();
	}

	private void merge() throws IOException {
		ArrayList<String> mList = new ArrayList<String>();
		for (int i = 1; i <= 8; i++) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(i + ".txt")));
			String line;
			while ((line = br.readLine()) != null)
				mList.add(line);
			br.close();
		}
		Collections.sort(mList);
		PrintWriter pw = new PrintWriter("mergedList.txt");
		for (String line : mList)
			pw.println(line);
		pw.flush();
	}

	private void create() throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 8; i++) {
			PrintWriter pw = new PrintWriter(i + ".txt");
			System.out.println("Enter the names in list" + i + ". Enter # to terminate list");
			String s1 = sc.nextLine();
			while (!(s1.equals("#"))) {
				pw.println(s1);
				s1 = sc.nextLine();
			}
			pw.flush();
			pw.close();
		}
	}
}
