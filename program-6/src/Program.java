import java.io.*;
import java.util.Scanner;

public class Program {
	public static int count;
	public static final int[] Address_List = new int[100];
	public static final String[] Name_List = new String[100];
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Program obj = new Program();
		obj.create_index();
		int ch;
		while (true) {
			System.out.println("********Menu********");
			System.out.println("1. Add Record");
			System.out.println("2. Search Record");
			System.out.println("3. Remove Record");
			System.out.println("4. Exit");
			System.out.println("*********************");
			System.out.println("Enter your choice: ");
			ch = sc.nextInt();
			sc.nextLine();
			switch (ch) {
			case 1:
				obj.insert();
				break;
			case 2:
				obj.search();
				break;
			case 3:
				obj.remove();
				break;
			case 4:
				System.out.println("Do you want to exit? (Y/N)");
				if (sc.next().equalsIgnoreCase("y")) {
					System.out.println("Program Ended");
					System.exit(0);
				}
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
	}

	private void remove() throws IOException {
		int pos,t;
		System.out.println("Enter the key to be deleted");
		String key = sc.nextLine();
		pos = search_index(key);
		if (pos != -1) {
			delete_from_file(pos);
			t = pos;
			while((t < count) && (Name_List[++t].equals(key)))
				delete_from_file(t);
			t = pos;
			while((t >= 0) && (Name_List[--t].equals(key)))
				delete_from_file(t);
			create_index();
		} else {
			System.out.println("Record not found");
		}
	}

	private void delete_from_file(int pos) throws IOException {
		display_record(pos);
		RandomAccessFile file = new RandomAccessFile("f1.txt", "rw");
		System.out.println("Are you sure you want to delete? (Y/N)");
		String ch = sc.nextLine();

		if (ch.equalsIgnoreCase("y")) {
			int address = Address_List[pos];
			String del_ch = "*";
			file.seek(address);
			file.writeBytes(del_ch);
			System.out.println("Record is deleted");
		}
		file.close();

	}

	private void search() throws IOException {
		int pos;
		int t = 0;
		System.out.println("Enter the name to be searched");
		String key = sc.nextLine();
		pos = search_index(key);
		if (pos != -1) {
			display_record(pos);
			t = pos;
			while((t < count) && (Name_List[++t].equals(key)))
				display_record(t);
			t = pos;
			while((t >= 0) && (Name_List[--t].equals(key)))
				display_record(t);
		}
		else
			System.out.println("Record not found");
	}

	private void display_record(int pos) throws IOException {
		RandomAccessFile file = new RandomAccessFile("f1.txt", "r");
		int address = Address_List[pos];
		String usn = "", sem = "", branch = "", name = "";

		file.seek(address);
		String s = file.readLine();
		while (s != null) {
			String[] result = s.split("\\|");
			name = result[0];
			usn = result[1];
			sem = result[2];
			branch = result[3];
			System.out.println("Record Details");
			System.out.println("Name: " + name);
			System.out.println("USN: " + usn);
			System.out.println("Sem: " + sem);
			System.out.println("Branch: " + branch);
			break;

		}
		file.close();
	}

	private int search_index(String key) {
		int low = 0, high = count, mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (Name_List[mid].equals(key))
				return mid;
			if (Name_List[mid].compareTo(key) > 0)
				high = mid - 1;
			if (Name_List[mid].compareTo(key) < 0)
				low = mid + 1;
		}
		return -1;
	}

	private void insert() throws FileNotFoundException, IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("f1.txt"), true));
		System.out.println("Enter Name, USN, Sem and Branch");
		String name = sc.nextLine();
		String usn = sc.nextLine();
		String sem = sc.nextLine();
		String branch = sc.nextLine();
		String b = name + "|" + usn + "|" + sem + "|" + branch + "|" + "$";
		pw.println();
		pw.print(b);
		pw.close();
		create_index();
	}

	private void create_index() throws IOException, ArrayIndexOutOfBoundsException {
		count = -1;
		long pos;
		RandomAccessFile file = new RandomAccessFile("f1.txt", "r");
		pos = file.getFilePointer();
		String s;
		while ((s = file.readLine()) != null) {
			String[] result = s.split("\\|");
			count++;
			Name_List[count] = result[0];
			Address_List[count] = (int) pos;
			pos = file.getFilePointer();
		}
		file.close();
		sort_index();
	}

	private void sort_index() throws IOException {
		for (int i = 0; i <= count; i++) {
			for (int j = i + 1; j <= count; j++) {
				if(Name_List[i].compareTo(Name_List[j]) > 0) {
					String temp = Name_List[i];
					Name_List[i] = Name_List[j];
					Name_List[j] = temp;

					int temp1 = Address_List[i];
					Address_List[i] = Address_List[j];
					Address_List[j] = temp1;
				}
			}
		}
	}
}