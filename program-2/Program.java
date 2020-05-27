import java.io.*;
import java.util.*; 
import java.lang.*;
  
class Program {
    // BufferedReader br = new BufferedReader(new InputStream(System.in));
    Scanner scan = new Scanner(System.in);
    final int size  = 100;
    public static void main(String[] args) throws IOException {
        Program obj = new Program();
        System.out.println("1. Pack");
        System.out.println("2. Unpack");
        System.out.println("3. Search");
        System.out.println("4. Modify");
        System.out.println("5. Exit");
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
            case 3: obj.search();
                    break;
            case 4: obj.modify();
                    break;
            case 5: System.out.println("Exit!");
                    System.exit(0);
                    break;
            default: System.out.println("Invalid entry!");
        }
    } 
    public void pack() throws FileNotFoundException
    {
        System.out.println("Enter Name, USN, SEM, Branch");
        String name = scan.nextLine();
        String usn = scan.nextLine();
        String sem = scan.nextLine();
        String branch = scan.nextLine();
        String  student = name + "|" + usn + "|" + sem + "|" + branch + "|";
//        File file = new File("student.txt"); 
//        if (!file.exists()) { 
//            // Create a new file if not exists. 
//            file.createNewFile(); 
//        }
        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("student.txt"),true));
        String s = "-";
        int len = student.length();
        if(len < 50)
        {
            for(int i = len; i < 50; i++)
            student = student.concat(s);
        }
        pw.println(student);
        pw.flush();
        pw.close();
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
    public void search() throws FileNotFoundException, IOException 
    {
        FileReader file = new FileReader("student.txt");
        BufferedReader brr = new BufferedReader(file);
        System.out.println("Enter the usn of student to be searched");
        String susn = scan.nextLine();
        String student = "";
        String name = "", usn = "", sem = "", branch = "";
        while((student = brr.readLine()) != null)
        {
            String[] s = student.split("\\|");
            name = s[0];
            usn = s[1];
            sem = s[2];
            branch = s[3];
            if(usn.equals(susn)){
               System.out.println("Student details are : ");
                System.out.println("Name : " + name);
                System.out.println("USN : " + usn);
                System.out.println("Semester : " + sem);
                System.out.println("Branch : " + branch);
               brr.close();
               return;
            }
        }
        System.out.println("Record not found");
        brr.close();
    }
    public void modify() throws FileNotFoundException, IOException, NullPointerException
    {
        String name = "", usn = "", sem = "", branch = "";
        File file = new File("student.txt");
        BufferedReader brr = new BufferedReader(new FileReader(file));
        File temp = new File("temp.txt");
        PrintWriter pw = new PrintWriter(temp);
        System.out.println("Enter the usn of student to be modified");
        String usnM = scan.nextLine();
        String string = "";
        while((string = brr.readLine()) != null)
        {
            String[] s = string.split("\\|");
            name = s[0];
            usn = s[1];
            sem = s[2];
            branch = s[3];
            
            if(usn.equals(usnM))
            {
                System.out.println("The deatials are: " + name + " " + usn + " " + sem + " " + branch);
                System.out.println("Enter name, usn, sem and branch");
                String name11 = scan.nextLine();
                String usn11 = scan.nextLine();
                String sem11 = scan.nextLine();
                String branch11 = scan.nextLine();
                String b = name11 + "|" + usn11 + "|" + sem11 + "|" + branch11 + "|";
                int len = b.length();
                String s1 = "-";
                if(len < 50)
                {
                    for(int j = len; j <= 50; j++)
                    {
                        b = b.concat(s1);
                    }
                    pw.println(b);
                }
            }
            else{
                pw.println(string);
            }
        }
        pw.flush();
        pw.close();
        brr.close();
        
        if(file.delete())
        {
            System.out.println("file deleted");
        }
        else    
            System.out.println("file not deleted");
        if(!(temp.renameTo(file)))
            System.out.println("Renaming is not done");
    }
} 
