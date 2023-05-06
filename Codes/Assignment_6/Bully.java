import java.io.*;
import java.util.*;

class Bully
{
	static int numberOfProcesses;
	static int priorities[] = new int[100];
	static int status[] = new int[100];
	static int cord;
	
	static void electProcess(int ele)
	{
		ele = ele - 1;
		cord = ele + 1;
	
		for(int i = 0; i < numberOfProcesses; i++)
		{
			if(priorities[ele] < priorities[i])
			{
				System.out.println("Election message is sent from " + (ele + 1) 
						+ " to " + (i + 1));
				
				if(status[i] == 1)
					electProcess(i+1);
			}
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		System.out.println("Enter the total number of processes: ");
		Scanner sc = new Scanner(System.in);
		numberOfProcesses = sc.nextInt();
		
		int i;
		for(i = 0; i < numberOfProcesses; i++)
		{
			System.out.println("Status of process " + (i+1) + ":");
			status[i] = sc.nextInt();
			System.out.println("Priority of process " + (i+1) + ":");
			priorities[i] = sc.nextInt();
		}
		
		System.out.println("Enter process which will initiate election");
		int ele = sc.nextInt();
		
		sc.close();
		
		electProcess(ele);
		System.out.println("After electing process the final coordinator is " + cord);
	}
}


/*
 * Enter the total number of processes: 
5
Status of process 1:
1
Priority of process 1:
4
Status of process 2:
0
Priority of process 2:
3
Status of process 3:
1
Priority of process 3:
1
Status of process 4:
1
Priority of process 4:
2
Status of process 5:
0
Priority of process 5:
5
Enter process which will initiate election
3
Election message is sent from 3 to 1
Election message is sent from 1 to 5
Election message is sent from 3 to 2
Election message is sent from 3 to 4
Election message is sent from 4 to 1
Election message is sent from 1 to 5
Election message is sent from 4 to 2
Election message is sent from 4 to 5
Election message is sent from 3 to 5
After electing process the final coordinator is 1

 * 
 */