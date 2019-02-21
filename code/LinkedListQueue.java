public class LinkedListQueue<E> implements Queue<E>{
	private SinglyLinkedList<E> linkedList;
	
  public LinkedListQueue(){
	  linkedList = new SinglyLinkedList<E>();	  
	 
  }
  public int size(){
	 return linkedList.size();
  }
  public boolean isEmpty(){
	  return linkedList.isEmpty();
  }
  public E first(){
	  return linkedList.first();
  }
  public void enqueue(E node){
	  linkedList.addLast(node);
  }
  public E dequeue(){
	  return linkedList.removeFirst();
  }
}
