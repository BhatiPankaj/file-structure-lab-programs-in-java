import java.io.*;
import java.util.*; 
import java.lang.*;
  
class Program {
    Scanner scan = new Scanner(System.in);
    public static int count; 
    public static final int[] rrn = new int[20];
    public static void main(String[] args) throws IOException 
    {
        Program obj = new Program();
        while(true){
            System.out.println("1. Pack");
            System.out.println("2. Unpack");
            System.out.println("3. Search");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice : ");
            int choice = obj.scan.nextInt();
            obj.scan.nextLine();
            System.out.println();
            switch(choice)
            {
                case 1: obj.pack();
                        break;
                case 2: obj.unpack();
                        break;
                case 3: System.out.println("Enter the RRN number to be searched");
                        int x = obj.scan.nextInt();
                        obj.scan.nextLine();
                        obj.search(x);
                        break;
                case 4: System.out.println("Exit!");
                        System.exit(0);
                        break;
                default: System.out.println("Invalid entry!");
            }
        }
    }
    public void createRRN() throws FileNotFoundException, IOException 
    {
        count = -1;
        long pos;
        RandomAccessFile file = new RandomAccessFile("student.txt", "r");
        pos = file.getFilePointer();
        String s = file.readLine();
        while(s != null){
            count++;
            rrn[count] = (int)pos;
            pos = file.getFilePointer();
            String[] result = s.split("\\|");
            String name = result[0];
            System.out.println("The rrn for " + name + " is " + count);
            s = file.readLine();
        }
        file.close(); 
    } 
    public void pack() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Name, USN, SEM, Branch");
        String name = scan.nextLine();
        String usn = scan.nextLine();
        String sem = scan.nextLine();
        String branch = scan.nextLine();
        String  student = name + "|" + usn + "|" + sem + "|" + branch + "|";
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("student.txt"),true));
        pw.println(student);
        pw.flush();
        pw.close();
        createRRN();
    }
    public void unpack() throws IOException
    {
        FileReader file = new FileReader("student.txt");
        BufferedReader brr = new BufferedReader(file);
        String student = "";
        String name = "", usn = "", sem = "", branch = "";
        while((student = brr.readLine()) != null)
        {
            String[] s = student.split("\\|");
            name = s[0];
            usn = s[1];
            sem = s[2];
            branch = s[3];
            System.out.println("Student details are : ");
            System.out.println("Name : " + name);
            System.out.println("USN : " + usn);
            System.out.println("Semester : " + sem);
            System.out.println("Branch : " + branch);
        }
        brr.close();
    }
    public void search(int x) throws FileNotFoundException, IOException 
    {
        RandomAccessFile file = new RandomAccessFile("student.txt", "rw");
        if(x > count){
            System.out.println("Record not found");
            System.exit(0);
        }
        else{
            int pos = rrn[x];
            file.seek(pos);
            String a = file.readLine();
            System.out.println("The Record is : " + a);
        }
        file.close();
    }
} 
