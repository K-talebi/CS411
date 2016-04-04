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
		for(String color : y.getDomain()){
			if(!x.canSatisfy(color)){
				y.getDomain().remove(color);
				revised = true;
			}
		}
		return revised;
	}
}

