package tokens;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"UpperCase"})
@PropertyNames({"UpperCase"})
@Tags({"A Token"})
public class AToken implements Token{
	String upperToken;
	public AToken(String newVal) {
		setUpperCase(newVal);
	}

	public void setUpperCase(String newVal) { upperToken = newVal;} 
	public String getUpperCase() { return upperToken;}
}