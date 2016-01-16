/* HashTableChained.java */
package dict;

import list.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	protected int n;  //number of keys stored;
	protected int N;  //number of buckets stored;
	protected List[] entry;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	boolean isPrime;
	
	prime:
	for (int i = (sizeEstimate + 1); i <= 2*sizeEstimate ; i++){
		isPrime = true;
		for (int j = 2; j < i ; j++){
			if (i%j ==0){
				isPrime = false;
				break;
			}
		}
		if (isPrime){
			this.N = i;
			break prime;
		}
	}
	
	this.entry = new DList[N];
	this.n     = 0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	this.N = 101;
	this.n = 0;
	this.entry = new DList[N];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	int a = 127;
	int b = 129;
	int p = 16908799;
    
	return ((Math.abs(a*code+b) % p) % N);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/
  public int bucketsize(){
	return N;
  }
  
  public int size() {
    // Replace the following line with your solution.
    return n;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	 
    return (n==0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	int index = compFunction(key.hashCode());
	//System.out.println("index is :" + index);
	Entry newEntry = new Entry();
	newEntry.value = value;
	newEntry.key   = key;
	entry[index].insertBack(newEntry);
	n++;
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	int index = compFunction(key.hashCode());
	try{
		if (entry[index] != null){
			ListNode curr = entry[index].front();
			while(curr.isValidNode()){
				if (((Entry)curr.item()).key().equals(key)){
					return (Entry)curr.item();
				}
				curr = curr.next();
			}
			return null;
			
		}
		else{
			return null;
		}
	}
	catch(InvalidNodeException e){
		return null;
	}
    
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	int index = compFunction(key.hashCode());
	try{
		if (entry[index] != null){
			ListNode curr = entry[index].front();
			while(curr.isValidNode()){
				if (((Entry)curr.item()).key().equals(key)){
					Entry node = (Entry)curr.item();
					curr.remove();
					n--;
					return node;
				}
				curr = curr.next();
			}
			return null;
			
		}
		else{
			return null;
		}
		
	}
	catch(InvalidNodeException e){
		return null;
	}
	
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	//List nullentry = new DList();
	for (int i=0; i<N; i++){
		entry[i] = new DList();
	}
	n=0;
  }
  
  /**
   * count the collision in hash table.
   */
  public int numberOfcollision(){
	int count = 0;
	for (int i=0; i<N; i++){
		if (entry[i].length()>=2){
			count++;
			//System.out.println("i is "+i);
			//System.out.println("size "+ entry[i].length());
		}
	}
	return count;
  }
  
  public double expectedCollision(){
	  return n-N+N*Math.pow(1-1.0/N, n);
  }
}
