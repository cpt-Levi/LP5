import java.util.*;

class Process
{
	public int id;
	public boolean active;

	public Process(int id) {
		this.id = id;
		active = true;
	}
}

public class Ring {

	int numberOfProcesses;
	Process[] processes;
	Scanner sc;
	
	public Ring()
	{
		sc = new Scanner(System.in);
	}
	
	public void initialiseRing()
	{
		System.out.println("Enter the number of processes ");
		numberOfProcesses = sc.nextInt();	
		
		processes = new Process[numberOfProcesses];
		for(int i = 0; i < processes.length; i++)
			processes[i] = new Process(i);
	}
	
	public int getMax()
	{
		int maxID = -99;
		int maxIDIndex = 0;
		
		for(int i = 0; i < processes.length; i++)
		{
			if(processes[i].active && processes[i].id > maxID)
			{
				maxID = processes[i].id;
				maxIDIndex = i;
			}
		}
		
		return maxIDIndex;
	}
	
	public void performElection()
	{
		System.out.println("Process no. " + processes[getMax()].id + " fails");
		processes[getMax()].active = false;
		System.out.println("Election initiated by: ");
		int initiatorProcess = sc.nextInt();
		
		int prev = initiatorProcess;
		int next = prev + 1;
		
		while(true)
		{
			if(processes[next].active)
			{
				System.out.println("Process " + processes[prev].id + 
						" pass Election(" + processes[prev].id + ") to " + processes[next].id);
				prev = next;
			}
			
			next = (next + 1) % numberOfProcesses;
			if(next == initiatorProcess)
				break;
		}
		
		System.out.println("Process " + processes[getMax()].id + " becomes coordinator");
		int coordinator = processes[getMax()].id;
		
		prev = coordinator;
		next = (prev + 1) % numberOfProcesses;
		
		while(true)
		{
			if(processes[next].active)
			{
				System.out.println("Process " + processes[prev].id + " pass Coordinator(" 
						+ coordinator + ") message to process " + processes[next].id);
				prev = next;
			}
			
			next = (next + 1) % numberOfProcesses;
			
			if(next == coordinator)
			{
				System.out.println("End of Election");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Ring r = new Ring();
		r.initialiseRing();
		r.performElection();
	}

}

/*
 * Enter the number of processes 
10
Process no. 9 fails
Election initiated by: 
5
Process 5 pass Election(5) to 6
Process 6 pass Election(6) to 7
Process 7 pass Election(7) to 8
Process 8 pass Election(8) to 0
Process 0 pass Election(0) to 1
Process 1 pass Election(1) to 2
Process 2 pass Election(2) to 3
Process 3 pass Election(3) to 4
Process 8 becomes coordinator
Process 8 pass Coordinator(8) message to process 0
Process 0 pass Coordinator(8) message to process 1
Process 1 pass Coordinator(8) message to process 2
Process 2 pass Coordinator(8) message to process 3
Process 3 pass Coordinator(8) message to process 4
Process 4 pass Coordinator(8) message to process 5
Process 5 pass Coordinator(8) message to process 6
Process 6 pass Coordinator(8) message to process 7
End of Election
 * 
 * 
 * */