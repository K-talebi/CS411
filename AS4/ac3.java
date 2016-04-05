import java.util.*;
public class ac3 {

	public static void main(String [] args){
		ArrayList<String> nodeNames = new ArrayList<String>(Arrays.asList("WA","NT","SA","Q","NSW","W","T"));
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for(int i = 0;i < args.length; i++){
			String name = "";
			String color = "";
			String [] parts = args[i].split("=");
			name = parts[0];
			color = parts[1];
			if(!nodeNames.contains(name)){
				System.out.println("INVALID NAME ENTERED IN ARGS, USE: WA, NT, SA, Q, NSW, W, T");
			}
			nodeNames.remove(name);
			nodeList.add(new Node(name, color.toUpperCase()));
		}
		for(int i = 0; i < nodeNames.size(); i++){
			nodeList.add(new Node(nodeNames.get(0)));
			nodeList.remove(0);
		}
		
	}
}
