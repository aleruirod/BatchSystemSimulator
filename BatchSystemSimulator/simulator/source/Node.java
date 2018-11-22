package simulator.source;

import simulator.enumerators.NodeType;
import simulator.exceptions.InvalidParametersException;

public class Node {

	private NodeType type;
	private Integer numCores;
	private Boolean available;

	public Node(NodeType type, Integer cores) {
		if (cores < 16)
			throw new InvalidParametersException("The minimum number of cores is 16.");

		this.type = type;
		numCores = cores;
		available = true;
	}

	public NodeType getType() {
		return type;
	}

	public Integer getNumcore() {
		return numCores;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void changeAvailability(Boolean availability) {
		available = availability;
	}

}
