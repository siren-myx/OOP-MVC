package tokens;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"UpperCase"})
@PropertyNames({"UpperCase","LowerCase"})
@Tags({"approach"})
public abstract class ALowerCaseToken extends AToken implements LowerCaseToken{
	String lowerToken;
	
	public ALowerCaseToken(String newVal) {
		super(newVal);
		setLowerCase();
	}

	public void setLowerCase(){ lowerToken = upperToken.toLowerCase();}	
	public String getLowerCase() { return lowerToken;}		
}