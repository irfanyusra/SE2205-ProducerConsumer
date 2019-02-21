public class SinglyLinkedList<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	private static class Node<E> {

		private E elem;
		private Node <E> next;

		public Node(E e, Node<E> n) {
			elem = e;
			next = n;
		}

		public E getElement() {
			return elem;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	}

	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public E first() {
		return head.getElement();
	}

	public E last() {
		return tail.getElement();
	}

	public void addFirst(E element) {
		Node <E> n = new Node<E>(element, null);
		if (size == 0)
			tail = n;
		else
			n.setNext(head);
		head = n;
		size++;
	}

	public void addLast(E element) {
		if (size == 0) {
			addFirst(element);
		
			}

		else {
			Node<E> n = new Node<E>(element, null);
			tail.setNext(n);
			tail = n;
			size++;
		}
		
	}

	public E removeFirst() {
		if (size == 0)
			return null;
		else {
			E n = head.getElement();
			head = head.getNext();
			size--;
			return n;
		}
	}
}
