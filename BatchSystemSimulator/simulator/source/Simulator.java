package simulator.source;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import simulator.enumerators.JobType;
import simulator.enumerators.NodeType;
import simulator.enumerators.QueueType;

public class Simulator {
	
	private Double organizationBudget = 10000.0;
	private List<Node> nodeList = new LinkedList<>();
	private Integer numAvailableNodes = nodeList.size();
	private Integer numStorageDevices;// There are no functions associated to this attribute, however, it will be
										// declared for possible future utilization.
	private List<Job> jobsInExecution = new LinkedList<>();
	private List<User> userList = new LinkedList<>();
	private List<Queue> jobQueues = new LinkedList<>();
	private Integer[] waitingTimesPerQueue = { 0, 0, 0, 0 };
	private Double totalTurnAroundTime = 0.0;
	private Integer minNodes = 128;// this can be modified as the number of nodes may vary depending of the system
									// capabilities.
	
	public Simulator() {
	}

	public Simulator(List<Node> nodes, List<User> users) {
		
		nodeList = nodes;
		userList = users;

	}

	public Double getEconomicBalance() {
		return organizationBudget;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public Integer getNumAvailableNodes() {
		return numAvailableNodes;
	}

	public Integer getNumStorageDevices() {
		return numStorageDevices;
	}

	public List<User> getUserList() {
		return userList;
	}

	public List<Queue> getQueueList() {
		return jobQueues;
	}

	public Integer[] getWaitingTimes() {
		return waitingTimesPerQueue;
	}

	public List<Job> getJobsInExecution() {
		return jobsInExecution;
	}

	public Integer getNodesUsedPerType(JobType type) {

		Integer sum = 0;

		for (int i = 0; i < jobsInExecution.size(); i++) {
			if (jobsInExecution.get(i).getType() == type)
				sum += jobsInExecution.get(i).getNodesNeeded();

		}

		return sum;
	}

	public Double getAverageTurnAroundTime() {
		if (jobsInExecution.size() > 0)
			return totalTurnAroundTime / jobsInExecution.size();
		else
			return 0.0;
	}

	public void addToExecution(List<Job> list) {
		jobsInExecution.addAll(list);
	}

	public void charge(Double cost) {
		organizationBudget -= cost;
	}

	public void receivePayment(Double cost) {
		organizationBudget += cost;
	}

	public void addUser(User u) {
		userList.add(u);
	}

	public void increaseWaitingTimeForQueue(Integer queue, Integer time) {
		waitingTimesPerQueue[queue] += time;
	}

	public void generateNodes() {
		Random randomGen = new Random();

		for (int i = 0; i < minNodes; i++) {
			Integer nodeType = randomGen.nextInt(3);
			Node n;
			
			switch(nodeType) {

			case 0:
				n = new Node(NodeType.Accelerated, 16);
				nodeList.add(n);
				break;
				
			case 1:
				n = new Node(NodeType.Specialized, 16);
				nodeList.add(n);
				break;
				
			default:
				n = new Node(NodeType.Traditional, 16);
				nodeList.add(n);
				break;

			}
		}
	}

	public void increaseTurnAroundtime(Double time) {
		totalTurnAroundTime += time;
	}

	public void generateQueues() {

		Queue q1 = new Queue(QueueType.Short, 0.2, 0.01);
		Queue q2 = new Queue(QueueType.Medium, 0.4, 0.01);
		Queue q3 = new Queue(QueueType.Large, 0.8, 0.01);
		Queue q4 = new Queue(QueueType.Huge, 1.6, 0.01);

		jobQueues.add(0, q1);
		jobQueues.add(1, q2);
		jobQueues.add(2, q3);
		jobQueues.add(3, q4);

	}

	public void initialize() {
		this.generateNodes();
		this.generateQueues();
	}

	public void applyOperationalCosts() {
		for (int i = 0; i < getQueueList().size(); i++) {
			charge(getQueueList().get(i).getTotalMachineHours() * getQueueList().get(i).getOperationalCostPerHour());
		}
	}

}
