import java.util.*;

public class Search {

	public boolean arcCheck(Queue<Node> nodeQueue){
		int i = 0;
		while(nodeQueue.size() > 0){
			Node x = nodeQueue.poll();
			Node y = nodeQueue.poll();
			if(arcRevise(x, y)){
				if(x.getDomain().size() == 0){
					return false;
				}
				for(Node k :x.getConnected()){
					nodeQueue.add(k);
				}
			}
			i++;
		}
		return true;
	}

	public boolean arcRevise(Node x, Node y){
		boolean revised = false;
		for(String color : x.getDomain()){
			/*WRONG*/
			if(!y.canSatisfy(color)){
				x.getDomain().remove(color);
				revised = true;
			}
		}
		return revised;
	}
}

/*
 * input all nodes, make queue with each node of all connections
 * while not empty{
 * 		take element out of queue
 * 		if(revise(current node, connected node) then{
 * 			if size of domain of current node is = to 0 after the revision, 
 * 			return false, not consistent
 * 
 * 			for each node connected to current, minus the element checked{
 * 				add node connected?
 * 			}
 * 		}
 * 
 * }
 * 
 */
