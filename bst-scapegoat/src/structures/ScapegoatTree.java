package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {

	private int upperBound = 0;
	private int size = 0;
	
	/**
	 * Adds an element to the tree.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule. 
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after adding, or you will receive no credit. 
	 * See the project writeup for details.
	 * 
	 * @param element
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(T element) throws NullPointerException{
		// TODO		
		if(element == null){
			throw new NullPointerException();
		}
		upperBound++;
		size++;
		super.add(element);
		if(this.height() > Math.log(upperBound)/Math.log(3/2)){
			BSTNode<T> scapegoat = this.getJustAdded().getParent();
			while(3*this.subtreeSize(scapegoat) <= 2*this.subtreeSize(scapegoat.getParent())){
				scapegoat = scapegoat.getParent();
			}
			BSTInterface<T> scapegoatSubtree = new BinarySearchTree<T>(scapegoat);
			scapegoatSubtree.balance();
			Iterator<T> itr = scapegoatSubtree.preorderIterator();
			while(itr.hasNext()){
				super.add(itr.next());
			}
		}
	}
	
	/**
	 * Attempts to remove one copy of an element from the tree, returning true
	 * if and only if such a copy was found and removed.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule.
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after removing, or you will receive no credit. 
	 * See the project writeup for details.

	 * @param element
	 * @return true if and only if an element removed
	 * @throws NullPointerException if element is null
	 */
	@Override
	public boolean remove(T element) throws NullPointerException{
		// TODO
		if(element == null){
			throw new NullPointerException();
		}
		if(super.remove(element)){
		size--;
		
		if(upperBound > 2*this.size()){
			this.balance();
			upperBound = size;
		}
		return true;
		}
		return false;
	}

}
