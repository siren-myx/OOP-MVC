package graphics;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Kight Speak Turn"})
public class AKightTurn implements KightTurn{
	boolean turn;
	public AKightTurn(boolean newVal){
		turn = newVal;
	}
	
	public void setKnightSay() { turn = true;}
	public void setGuardianSay() { turn = false;}
	public boolean getTurn() { return turn;}
}
