import java.util.*;

public class BullyTest {

	static int numProc;
	static int priorities[] = new int[100];
	static int status[] = new int[100];
	static int cord;
	
	static void electProcess(int ele)
	{
		cord = ele;
		
		for(int i = 0; i < numProc; i++)
		{
			if(priorities[ele] < priorities[i])
			{
				System.out.println("Election message sent from " + ele + " to " + i);
				
				if(status[i] == 0)
					System.out.println("But process " + i + " is inactive");
				else
					electProcess(i);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter total number of processes: ");
		Scanner sc = new Scanner(System.in);
		numProc = sc.nextInt();
		
		for(int i = 0; i < numProc; i++)
		{
			System.out.println("Status of process " + i + ":");
			status[i] = sc.nextInt();
			
			System.out.println("Priority of process " + i + ":");
			priorities[i] = sc.nextInt();
		}
		
		System.out.println("Process " + (numProc - 1) + " fails");
		status[numProc - 1] = 0;
		
		boolean run = true;
		while(run)
		{
			System.out.println("Enter the initiator process: ");
			int ele = sc.nextInt();
			
			if(status[ele] == 0)
				System.out.println("Process " + ele + " is inactive");
			else
			{
				sc.close();
				electProcess(ele);
				System.out.println("New coordinator is " + cord);
				
				for(int i = 0; i < numProc; i++)
				{
					if(status[i] == 1 && i != cord)
						System.out.println("Coordinator " + cord + " sends COORDINATOR message to " + i);
				}
				run = false;
			}
		}
	}

}
