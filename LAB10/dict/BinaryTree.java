/* BinaryTree.java */

package dict; 

/**
 *  BinaryTree implements a Dictionary as a binary tree (unbalanced).
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/
public class BinaryTree implements Dictionary {

  /** 
   *  size is the number of items stored in the dictionary.
   *  root is the BinaryTreeNode that serves as root of the tree.  If there
   *    are no items (size is zero), root is null.
   **/
  protected int size;
  protected BinaryTreeNode root;

  public BinaryTree() {
    makeEmpty();
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;
    if (root == null) {
      root = new BinaryTreeNode(entry);
    } else {
      insertHelper(entry, (Comparable) key, root);
    }
    size++;
    return entry;
  }

  private void insertHelper(Entry entry, Comparable key, BinaryTreeNode node) {
    if (key.compareTo(node.entry.key()) <= 0) {
      if (node.leftChild == null) {
	node.leftChild = new BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.leftChild);
      }
    } else {
      if (node.rightChild == null) {
	node.rightChild = new BinaryTreeNode(entry, node);
      } else {
	insertHelper(entry, key, node.rightChild);
      }
    }
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    BinaryTreeNode node = findHelper((Comparable) key, root);
    if (node == null) {
      return null;
    } else {
      return node.entry;
    }
  }

  /**
   *  Search for a node with the specified key, starting from "node".  If a
   *  matching key is found (meaning that key1.compareTo(key2) == 0), return
   *  a node containing that key.  Otherwise, return null.
   *
   *  Be sure this method returns null if node == null.
   **/

  private BinaryTreeNode findHelper(Comparable key, BinaryTreeNode node) {
    // Replace the following line with your solution.
	if (key.compareTo(node.entry.key()) == 0){
		return node;
	}
	else if (key.compareTo(node.entry.key()) < 0){
		if (node.leftChild == null){
			return null;
		}
		else{
			BinaryTreeNode result = findHelper(key , node.leftChild);
			return result;
		}
	}
	else {
		if (node.rightChild == null){
			return null;
		}
		else{
			BinaryTreeNode result = findHelper(key, node.rightChild);
			return result;
		}
	}
 
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */
  public BinaryTreeNode first(BinaryTreeNode node){
	  if  (size == 0){
		  return null;
	  }
	  else{
		  if (node.leftChild == null){
			  return node;
		  }
		  else{
			  BinaryTreeNode newnode = first(node.leftChild);
			  return newnode;
		  }
  
	  }
  }
  
  
  public Entry remove(Object key) {
    // Replace the following line with your solution.
	BinaryTreeNode node = findHelper((Comparable) key, root);
	if (node == null){
		return null;
	}
	else if (node == root){
		if (root.leftChild==null && root.rightChild==null){
			root = null;
			size--;
			return node.entry;
		}
		else if (root.leftChild != null && root.rightChild != null){
			BinaryTreeNode smallnode = first(node.rightChild);
			Entry newentry = remove(smallnode.entry.key());
			smallnode.leftChild = root.leftChild;
			smallnode.rightChild = root.rightChild;
			root.leftChild.parent = smallnode;
			root.rightChild.parent = smallnode;
			root = smallnode;
			root.parent = null;
			return node.entry;
		}
		else if (root.leftChild != null && root.rightChild==null){
			root = root.leftChild;
			root.parent = null;
			size--;
			return node.entry;
		}
		else{
			root = root.rightChild;
			root.parent = null;
			size--;
			return node.entry;
		}
	}
	else {
		if (node.leftChild==null && node.rightChild==null){
			if (((Comparable)node.entry.key).compareTo(node.parent.entry.key)<=0 ){
				
				node.parent.leftChild = null;
				node.parent = null;
				size--;
				return node.entry;
			}
			else {
				node.parent.rightChild = null;
				node.parent = null;
				size--;
				return node.entry;
			}

		}
		else if (node.leftChild != null && node.rightChild != null){
			BinaryTreeNode smallnode = first(node.rightChild);
			Entry newentry = remove(smallnode.entry.key());
			
			if (((Comparable)node.entry.key()).compareTo(node.parent.entry.key())<=0){
				
				//node.parent.leftChild = new BinaryTreeNode(newentry,node.parent,node.leftChild,node.rightChild);
				node.parent.leftChild = new BinaryTreeNode(newentry, node.parent, node.leftChild, node.rightChild);
				return node.entry;
			}
			else {
				//node.parent.rightChild = new BinaryTreeNode(newentry,node.parent,node.leftChild,node.rightChild);
				node.parent.rightChild = new BinaryTreeNode(newentry, node.parent, node.leftChild, node.rightChild);
				return node.entry;
			}
		}
		else{
			if (((Comparable)node.entry.key()).compareTo(node.parent.entry.key())<=0){
				if (node.leftChild != null){
					node.parent.leftChild = node.leftChild;
					node.leftChild.parent = node.parent;
					size--;
					return node.entry;
				}
				else{
					node.parent.leftChild = node.rightChild;
					node.rightChild.parent = node.parent;
					size--;
					return node.entry;
				}
				
			}
			else {
				if (node.leftChild != null){
					node.parent.rightChild = node.leftChild;
					node.leftChild.parent = node.parent;
					size--;
					return node.entry;
				}
				else{
					node.parent.rightChild = node.rightChild;
					node.rightChild.parent = node.parent;
					size--;
					return node.entry;
				}
			}

		}

	}
  }

  /**
   *  Remove all entries from the dictionary.
   */

  public void makeEmpty() {
    size = 0;
    root = null;
  }

  /**
   *  Convert the tree into a string.
   **/

  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /* Tests the binary tree. */
  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    System.out.println("Inserting 1A, 6V, 3K, 2Z, 5L, 9L:");
    tree.insert(new Integer(1), "A");
    tree.insert(new Integer(6), "V");
    tree.insert(new Integer(3), "K");
    tree.insert(new Integer(2), "Z");
    tree.insert(new Integer(5), "L");
    tree.insert(new Integer(9), "L");
    System.out.println("The tree is:  " + tree);
    System.out.println("Size:  " + tree.size());

    System.out.println("\nTesting find() ...");
    testFind(tree, 1, "A");
    testFind(tree, 9, "L");
    testFind(tree, 5, "L");
    testFind(tree, 4, null);
    testFind(tree, 6, "V");
    testFind(tree, 3, "K");

    System.out.println("\nTesting remove() (for nodes with < 2 children) ...");
    testRemove(tree, 5, "1A(((2Z)3K)6V(9L))");
    testRemove(tree, 3, "1A((2Z)6V(9L))");
    testRemove(tree, 1, "(2Z)6V(9L)");
    tree.insert(new Integer(7), "S");
    tree.insert(new Integer(8), "X");
    tree.insert(new Integer(10), "B");
    System.out.println("After inserting 7S, 8X, 10B:  " + tree);
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 6) {
      System.out.println("  SHOULD BE 6.");
    }

    System.out.println("\nTesting remove() (for nodes with 2 children) ...");
    testRemove(tree, 6, "(2Z)7S((8X)9L(10B))");
    testRemove(tree, 9, "(2Z)7S((8X)10B)");
    System.out.println("Size:  " + tree.size());
    if (tree.size() != 4) {
      System.out.println("  SHOULD BE 4.");
    }
  }

  private static void testRemove(BinaryTree tree, int n, String shouldBe) {
    Integer key = new Integer(n);
    System.out.print("After remove(" + n + "):  ");
    tree.remove(key);
    System.out.println(tree);
    if (!tree.toString().equals(shouldBe)) {
      System.out.println("  SHOULD BE " + shouldBe);
    }
  }

  private static void testFind(BinaryTree tree, int n, Object truth) {
    Integer key = new Integer(n);
    Entry entry = tree.find(key);
    System.out.println("Calling find() on " + n);
    if (entry == null) {
      System.out.println("  returned null.");
      if (truth != null) {
        System.out.println("  SHOULD BE " + truth + ".");
      }
    } else {
      System.out.println("  returned " + entry.value() + ".");
      if (!entry.value().equals(truth)) {
        if (truth == null) {
          System.out.println("  SHOULD BE null.");
        } else {
          System.out.println("  SHOULD BE " + truth + ".");
        }
      }
    }
  }
  
}
