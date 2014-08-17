package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.STRING_PATTERN)
@Tags({"Bounded Shape"})
public interface StringShape extends Point{
	public String getText();
	public void setText(String newText);
	public void clear();  

}
