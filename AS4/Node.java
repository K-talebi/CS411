import java.util.*;


public class Node{
	ArrayList<Node> connected = new ArrayList<>();
	ArrayList<String> domain = new ArrayList<>();
	String name = "";
	String color = "";

	public Node (String name){
		this.name = name;
		domain.add("RED");
		domain.add("GREEN");
		domain.add("BLUE");
	}
	
	public Node(String name, String color){
		this.name = name;
		if(color == "RED"){
			domain.add("GREEN");
			domain.add("BLUE");
		}
		else if(color == "GREEN"){
			domain.add("RED");
			domain.add("BLUE");
		}
		else if(color == "BLUE"){
			domain.add("RED");
			domain.add("GREEN");
		}
		this.color = color;
	}

	public void addConnected(Node a){
		connected.add(a);
		a.connected.add(this);
	}

	public ArrayList<Node> getConnected(){
		return connected;
	}

	public ArrayList<String> getDomain(){
		return domain;
	}

	public void setColor(String color){
		this.color = color;
		for(int i = 0; i < connected.size(); i++){
			if(connected.get(i).domain.contains(color)){
				connected.get(i).domain.remove(color);
				System.out.println("Deleted "+color+" from D-"+connected.get(i).getName());
			}
		}
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public String getName(){
		return this.name;
	}
	
}
