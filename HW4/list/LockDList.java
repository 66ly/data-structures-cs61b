package list;

public class LockDList extends DList{

	//protected LockDListNode head;
	
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
	    //DListNode newnode = super.newNode(item,prev,next);
	    //LockDListNode newnode = new LockDListNode
		
		return new LockDListNode(item, prev, next);
	}
	
	//public LockDList(){
	//	super();
	//	head.lock = true;
	//	size = 0;
	//}
	
	
	public void lockNode(DListNode node) {
		//node = new LockDListNode(node.item,node.prev,node.next);
		//node = (LockDListNode) node;
		((LockDListNode)node).lock = true;
		
	}
	
	public void remove(DListNode node) {	
		node = (LockDListNode) node;
		if (!((LockDListNode)node).lock){
			super.remove(node);
		}
	}
}
