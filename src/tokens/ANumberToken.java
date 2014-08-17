package tokens;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"UpperCase"})
@PropertyNames({"UpperCase","Number"})
@Tags({"Number Token"})
public class ANumberToken extends AToken implements NumberToken{
	int number;
	
	public ANumberToken(String newVal) {
		super(newVal);
		setNumber();
	}
	
	public void setNumber() { number = Integer.parseInt(upperToken);}
	public int getNumber() { return number;}
}


