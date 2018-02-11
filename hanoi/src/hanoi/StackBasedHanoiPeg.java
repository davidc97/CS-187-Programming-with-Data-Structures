package hanoi;

import structures.LinkedStack;

/**
 * A {@link StackBasedHanoiPeg} is an implementation of {@link HanoiPeg}.
 * 
 * @author jcollard
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	LinkedStack<HanoiRing> peg;

	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 */
	public StackBasedHanoiPeg() {
		peg = new LinkedStack<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		if(ring == null){
			throw new NullPointerException();
		}
		if(hasRings() && ring.getSize() >= peg.peek().getSize()){
			throw new IllegalHanoiMoveException("Illegal Move");
		}
		peg.push(ring);
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if(!hasRings()){
			throw new IllegalHanoiMoveException("This peg is empty");
		}
		return peg.pop();
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if(!hasRings()){
			throw new IllegalHanoiMoveException("This peg is empty");
		}
		return peg.peek();
	}

	@Override
	public boolean hasRings() {
		return !peg.isEmpty();
	}
}
