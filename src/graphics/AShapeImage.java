package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.LABEL_PATTERN) 
@Tags({"Bounded Shape"})
public class AShapeImage extends ALine implements ImageShape {
	 String imageFileName;
     public AShapeImage (String initImageFileName, int iX, int iY, int iW, int iH) {
     	super(iX, iY, iW, iH);
    	imageFileName = initImageFileName;
     }          

    public String getImageFileName() {return imageFileName;}  
    public void setImageFileName(String newVal) {imageFileName = newVal;}     
}
