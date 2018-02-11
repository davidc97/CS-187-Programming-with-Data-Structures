package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.lang.Math;


public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;
	protected BSTNode<T> justAdded;
	
	public BinarySearchTree(){
		root = null;
		justAdded = null;
	}
	public BinarySearchTree(BSTNode<T> root){
		this.root = root;
		justAdded = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) throws NullPointerException{
		// TODO
		if (t == null){
			throw new NullPointerException();
		}
		Iterator<T> itr = inorderIterator();
		while(itr.hasNext()){
			if (itr.next().equals(t));
			return true;
		}
		return false;
	}

	public boolean remove(T t) throws NullPointerException {
		if(t == null){
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) throws NullPointerException{
		// TODO
		if(t == null){
			throw new NullPointerException();
		}
		Iterator<T> itr = inorderIterator();
		while(itr.hasNext()){
			T element = itr.next();
			if (element.equals(t));{
			return element;
			}
		}
		return null;
	}

	public void add(T t) throws NullPointerException{
		if(t == null){
			throw new NullPointerException();
		}
		root = addToSubtree(t, root);
	}

	protected BSTNode<T> addToSubtree(T t, BSTNode<T> node) {
		if (node == null) {
			justAdded = new BSTNode<T>(t,null, null);
			return justAdded;
		}
		if (t.compareTo(node.getData()) <= 0) {
			node.setLeft(addToSubtree(t, node.getLeft()));
		} else {
			node.setRight(addToSubtree(t, node.getRight()));
		}
		return node;
	}
	
	public BSTNode<T> getJustAdded(){
		return justAdded;
	}

	@Override
	public T getMinimum() {
		// TODO
		if(root == null){
			return null;
		}
		
		BSTNode<T> itr = root;
		while(itr.getLeft() != null){
			itr=itr.getLeft();
		}
		return itr.getData();
	}

	@Override
	public T getMaximum() {
		// TODO
		if(root == null){
			return null;
		}
		BSTNode<T> itr = root;
		while(itr.getRight() != null){
			itr=itr.getRight();
		}
		return itr.getData();
	}


	@Override
	public int height() {
		// TODO
		if(this.isEmpty()){
			return -1;
		}
		int leftHeight = 0;
		int rightHeight = 0;
		return heightHelper(root,leftHeight,rightHeight);
	}
	
	private int heightHelper(BSTNode<T> node,int leftHeight, int rightHeight) {
		if(node == null){
			return -1;
		}
		leftHeight = heightHelper(node.getLeft(),leftHeight,rightHeight);
		rightHeight= heightHelper(node.getRight(),leftHeight, rightHeight);
		return Math.max(leftHeight, rightHeight)+1;
	}
	

	@Override
	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node){
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if(node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue,node.getRight());
			queue.add(node.getData());
			
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) throws NullPointerException {
		// TODO
		if(other == null){
			throw new NullPointerException();
		}
		if(!sameValues(other)){
			return false;
		}
		Iterator<T> itr = this.preorderIterator();
		Iterator<T> itr2 = other.preorderIterator();
		while(itr.hasNext()){
			if(!itr.next().equals(itr2.next())){
				return false;
			}
		}
		return true;
	}
	
		

	@Override
	public boolean sameValues(BSTInterface<T> other) throws NullPointerException {
		// TODO
		if(other == null){
			throw new NullPointerException();
		}
		Iterator<T> itr = this.inorderIterator();
		Iterator<T> itr2 = other.inorderIterator();
		if(this.size() != other.size()){
			return false;
		}
		while(itr.hasNext()){
			if(!itr.next().equals(itr2.next())){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		if(isEmpty()){
			return true;
		}
		return Math.pow(2, this.height()) <= this.size() && this.size() < Math.pow(2, this.height()+1);
	}

	@Override
	public void balance() {
		// TODO
		Iterator<T> itr = this.inorderIterator();
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[this.size()];
		for(int i = 0;i< this.size();i++){
			temp[i] =itr.next();
		}
		balanceHelper(temp,root);
	}
	protected void balanceHelper(T[] array, BSTNode<T> node){
		if(array.length >0){
		node.setData((array[array.length/2]));
		node.setLeft(null);
		node.setRight(null);
		}
		if(array.length > 1){
//		if(node == null){
//			node = new BSTNode<T>(array[array.length/2],null,null);
//		}
		
		T[] left = (T[]) Arrays.copyOfRange(array, 0, array.length/2);
		T[] right = (T[]) Arrays.copyOfRange(array, (array.length/2)+1, array.length);
		if(node.getLeft() == null){
			node.setLeft(new BSTNode<T>(null, null, null));
		}
		if(node.getRight() == null){
			node.setRight(new BSTNode<T>(null, null, null));
		}
		balanceHelper(left, node.getLeft());
		balanceHelper(right,node.getRight());
		
	}
	}

	@Override
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// DO NOT MODIFY
		// see project description for explanation

		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}
}