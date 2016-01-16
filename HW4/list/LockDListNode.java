package list;

public class LockDListNode extends DListNode {
	
	/* constructor which is the same as DListNode;
	 * @param i the item to store in the node.
     * @param p the node previous to this node.
     * @param n the node following this node.
     * @param lock whether the node is lock or not.
	 */
	protected boolean lock;
	
	LockDListNode(){
		super();
		lock = false;
	}
	
	LockDListNode(Object i, DListNode p, DListNode n){
		super(i,p,n);
		lock = false;
	}
}
