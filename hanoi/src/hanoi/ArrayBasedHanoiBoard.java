package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {
	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	StackBasedHanoiPeg p0,p1,p2;
	StackBasedHanoiPeg[] array;
	public ArrayBasedHanoiBoard(int n){
		if(n < 0){
			throw new IllegalArgumentException();
		}
		p0 = new StackBasedHanoiPeg();
		p1 = new StackBasedHanoiPeg();
		p2 = new StackBasedHanoiPeg();
		for(int i = n;i > 0; i--){
			p0.addRing(new HanoiRing(i));
		}
		array = new StackBasedHanoiPeg[3];
		array[0] = p0;
		array[1] = p1;
		array[2] = p2;
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException(
					"Could not perform illegal move.");
		}
		array[move.getToPeg()].addRing(array[move.getFromPeg()].remove());
	}

	@Override
	public boolean isSolved() {
		return !p0.hasRings() && p1.hasRings() && !p2.hasRings() || !p0.hasRings() && !p1.hasRings() && p2.hasRings() || !p0.hasRings() && !p1.hasRings() && !p2.hasRings();
	}

	@Override
	public boolean isLegalMove(HanoiMove move) {
		StackBasedHanoiPeg fromPeg = array[move.getFromPeg()];
		StackBasedHanoiPeg toPeg = array[move.getToPeg()];
		if(!fromPeg.hasRings()){
			return false;
		}
		if(!toPeg.hasRings()){
			return true;
		}
		return fromPeg.getTopRing().getSize() < toPeg.getTopRing().getSize();
	}
}
