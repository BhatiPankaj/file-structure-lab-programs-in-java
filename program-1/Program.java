import java.util.*; 
import java.lang.*; 
import java.io.*;
class Program {
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException
    {
        Program obj = new Program();
        int choice;

        while(true)
        {
            System.out.println("************");
            System.out.println("1. Accept input from Standard Input Devices");
            System.out.println("2. Accept Input from File");
            System.out.println("3. Exit");
            System.out.println("****************");
            choice = obj.scan.nextInt();
            switch(choice)
            {
                case 1: obj.StdInput();
                        break;
                case 2: obj.FileInput();
                        break;
                case 3: System.out.println("You choose exit!");
                        System.exit(0);
                        break;
                default: System.out.println("Invalid option");
            }
        }
    }
    void StdInput()
    {
        String org, rev = "";
        System.out.println("Whenever you want to stop just write 'stop'");
        scan.nextLine();
        String name  = "";

        while(!name.equals("stop"))
        {
            System.out.println("Enter the name to be Reversed!");
            name = scan.nextLine();
            rev = Rev_String(name);
            System.out.println("Reversed of entered string is: " + rev);
        }
    }

    void FileInput() throws IOException 
    {
        String org, rev = "";
        System.out.println("Enter the input file name(with extension)");
        String infile  = scan.next();
        System.out.println("Enter the output file name(with extension)");
        String outfile = scan.next();

        BufferedReader br = new BufferedReader(new FileReader(infile));
        PrintWriter pw = new PrintWriter(outfile);

        while((org = br.readLine()) != null)
        {
            rev = Rev_String(org);
            pw.println(rev);
        }
        pw.flush();
        System.out.println("All Reverse Names written to " + outfile);
        br.close();
        pw.close();
    }
     
    String Rev_String(String org)
    {
        StringBuilder sb = new StringBuilder(org);
        sb.reverse();
        return sb.toString();
    }
} 
