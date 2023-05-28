import java.util.*;

public class RingTest {

	static int numProc;
	static int status[] = new int[100];
	static int cord;
	
	
	static void ringElect(int init)
	{
		int temp = (init + 1) % numProc;
		int maxID = -99;
		
		while(temp != init)
		{
			if(status[temp] == 1 && temp > maxID)
				maxID = temp;
			
			temp = (temp + 1) % numProc;
		}
		
		cord = maxID;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of processes: ");
		numProc = sc.nextInt();
		
		for(int i = 0; i < numProc; i++)
		{
			System.out.println("Enter status of process " + i);
			status[i] = sc.nextInt();
		}
		
		System.out.println("The highest process " + (numProc - 1) + " has failed");
		status[numProc - 1] = 0;
		
		boolean run = true;
		while(run)
		{
			System.out.println("Enter initiator process : ");
			int init = sc.nextInt();
			
			if(status[init] == 0)
				System.out.println("But process " + init + " is inactive");
			else
			{
				sc.close();
				ringElect(init);
				System.out.println("Process " + cord + " is the new coordinator");
				run = false;
			}
		}	
	}
}
