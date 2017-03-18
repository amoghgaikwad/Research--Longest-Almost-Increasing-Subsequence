import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;


public class RedBlackTree {

    private final int RED = 0;
    private final int BLACK = 1;

    private class Node {

        int key = -1;
        int color = 1;
        Node left = nil;
        Node right = nil;
        Node parent = nil;
        int index =0;

        Node(int key) {
            this.key = key;
        } 
    }

    private final Node nil = new Node(-1); 
    private Node root = nil;
    public int counter = 0;
    public int count_node=0;
    static Node temp;


    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        printTree(node.right);
    }
    
    public void z_count(Node node) {
        if (node == nil) {
            return;
        }
        z_count(node.left);
        count_node++;
        z_count(node.right);
    }
    
    public Node Tail_node(Node node) 
    {	
    	counter++;
    	if (node == nil) {
            return nil;
        }
        Tail_node(node.left);
        temp=node;
        Tail_node(node.right);
        return temp;
    }

    private Node findNode(Node findNode, Node node) {
    	counter++;
        if (root == nil) {
            return null;
        }
        
        if (findNode.key < node.key) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
        	counter++;
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
            	counter++;
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    //Takes as argument the newly inserted node
    private void fixTree(Node node) {
        while (node.parent.color == RED) {
        	counter++;
            Node uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                if (node == node.parent.right) {
                    //Double rotation needed
                    node = node.parent;
                    rotateLeft(node);
                } 
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation 
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                 if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation needed
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    //Deletes whole tree
    void deleteTree(){
        root = nil;
    }
    
    //Deletion Code .

    void transplant(Node target, Node with){ 
          if(target.parent == nil){
              root = with;
          }else if(target == target.parent.left){
              target.parent.left = with;
          }else
              target.parent.right = with;
          with.parent = target.parent;
    }
    
    boolean delete(Node z){
    	counter++;
        if((z = findNode(z, root))==null)return false;
        Node x;
        Node y = z; // temporary reference y
        int y_original_color = y.color;
        
        if(z.left == nil){
            x = z.right;  
            transplant(z, z.right);  
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left); 
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color; 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.color == BLACK){ 
        	counter++;
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK; 
    }
    

    public boolean isEmpty( ) {
        return root.right == nil;
    }
    
    
    Node treeMinimum(Node subTreeRoot){
    	if( isEmpty( ) )
            return nil;
        while(subTreeRoot.left!=nil){
        	counter++;
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
    Node treeMaximum(Node subTreeRoot){
    	if( isEmpty( ) )
            return nil;
        while(subTreeRoot.right!=nil){
        	counter++;
            subTreeRoot = subTreeRoot.right;
        }
        return subTreeRoot;
    }
    
    private Node successor(Node x)
    {
    	Node y = nil;
    	if(x == nil)
    		return nil;
    	
    	if(x!=null)
    	{
    		if(x.right != nil)
    		{
    			return treeMinimum(x.right);
    		}
    		y= x.parent;
    		
    		while(y != nil && x==y.right)
    		{
    			counter++;
    			x=y;
    			y=y.parent;
    		}
    	}
    	return y;
    }
    
    private Node predecessor(Node node)
    {
    	Node x = findNode(node,root);
    	Node y =nil;
    	if(x == nil)
    		return nil;
    	
    	if(x!=null)
    	{
    		if(x.left != nil)
    			return treeMaximum(x.left);
    		y= x.parent;
    		while(y != nil && x==y.left)
    		{
    			counter++;
    			x=y;
    			y=y.parent;
    		}
    	}
    	return y;
    }
    
    private Node predecessor_(Node node)
    {
    	Node x = findNode(node,root);
    	Node y =nil;
    	if(x == nil)
    		return nil;
    	
    	if(x!=null)
    	{
    		if(x.left != nil)
    			return treeMaximum(x.left);
    		y= x.parent;
    		while(y != nil && x==y.left)
    		{
    			counter++;
    			x=y;
    			y=y.parent;
    		}
    	}
    	return y;
    }
    
    public int calculate_LaIS(int n,boolean flag)
    {
        int item[]=new int[n];
        int p[]= new int[n];
        int c=2;
        
        if(flag)
        	System.out.print("\nInput: ");
        Random rand=new Random();
        for(int j=0;j<n;j++)
        {
            item[j]=rand.nextInt(1000) + 1;
            if(flag)
            	System.out.print(" "+item[j]);
        }
        System.out.println("");

        Node node2;
        Node pred1,pred2,succ1;   
        
        for(int i=0;i<n;i++)
        {
            counter++;
                 Node node= new Node(item[i]);
                 node.index=i;
                 insert(node);
                 pred1 = predecessor(node);
                 if(pred1 != nil)
                 {
                    p[i]=pred1.index;
                 }
                 else
                 {
                    p[i]=i;
                 }
                 node2 = new Node(item[i]+c);
                 insert(node2);
                 pred2 = predecessor_(node2);
                 delete(node2);
                 succ1 = successor(pred2);
                 
                 if(succ1 != null)
                    delete(succ1);
         }
        if(flag)
        	System.out.print("\nThe LaIS as per Table 2: ");
		int m=Tail_node(root).index;

        for(int i=item.length-1 ; i >= m+1 ; i--)
        {
            if((item[m]-c < item[i]) && (item[i] <= item[m])){ 
            	if(flag)
            		System.out.print(" "+item[i]);
            }
        }
        if(flag)
        	System.out.print(" "+item[m]);
        int t=m;
        while( p[t] != t)
        {
            for(int i=t-1; i>= p[t]+1;i--)
            {
                if( ((item[p[t]]-c) < item[i]) && (item[i] <= item[p[t]]) )
                {
                	if(flag)
                		System.out.print(" "+item[i]);
                } 
            }
            if(flag)
            	System.out.print(" "+item[p[t]]);
            t=p[t];
        }
        if(flag)
        {
        	System.out.print("\n\np[i] values :");
        	for(int i=0;i<p.length;i++)
        	{
        		System.out.print(" "+p[i]);
        	}
        	System.out.println("\n*******************************************\n");
        }
        z_count(root);
        return count_node;
    }
 
    public static void main(String[] args) throws FileNotFoundException 
    {
    	int n=1000;
    	int avg=0,k=0,constant=10;
          
        //File file = new File("Graph_Output.txt");
        //PrintWriter write_log = new PrintWriter(file);

        RedBlackTree[] rbt = new RedBlackTree[1000];
        for (int i = 0; i < 1000; i++) 
        {
        	rbt[i]=new RedBlackTree();
        	k=rbt[i].calculate_LaIS(n,false);
			avg=(rbt[i].counter/constant);
		    //write_log.print("\n"+avg+"-"+k);
		    System.out.println("\nI:"+(i+1)+"\nk: "+k+" avg:"+avg+"\n");
		}
        //write_log.close();
        
        n=20;
        for(int j=0; j<2; j++)
        {   
            System.out.print("\nFor Sequence"+(j+1)+" :");
        	rbt[j]=new RedBlackTree();
        	rbt[j].calculate_LaIS(n,true);
        }
    }

}
