package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.LABEL_PATTERN)
@Tags({"Bounded Shape"})
public interface ImageShape extends Line{
    public String getImageFileName() ;  
    public void setImageFileName(String newVal);
}
