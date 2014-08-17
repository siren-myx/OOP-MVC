package tokens;
import commandInterpreter.CommandInterpreter;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"Input"})
@PropertyNames({"Input","Token"})
@Tags({"Scanner Bean"})
public class ScannerBean implements ScannerBeanSheet{
	CommandInterpreter AInterpreter;
	Token[] tokens;
	Token[] scannedTokens;
	String scannedString;
	int arrayIndex;
	
	public ScannerBean(CommandInterpreter newObject){
		AInterpreter = newObject;
		setAnalyzer();
	}

	public void setAnalyzer(){
		setInput(AInterpreter.getCommand());
		clear();// Watch out !!! It cleans all history.
		setScanner();
		setToken();
		setCommandTokens();
	}

	
	public void setInput(String newVal){
		tokens = new Token[100];
		scannedString = newVal;
	}
	
	public void clear(){
		arrayIndex = 0;
	}
	
	public void setToken(){
		scannedTokens = new Token[arrayIndex];
		for (int i = 0; i<arrayIndex; i++){
			scannedTokens[i] = tokens[i]; 
		}
	}	
	
	public void setCommandTokens(){
		AInterpreter.setInterpreter(scannedTokens);
	}
	
	public String getInput(){return scannedString;}
	public Token[] getToken(){return scannedTokens;}
	
	
	public void setScanner()	{	
		int startIndex;						// substring beginning index
		int index = 0;						// substring counting index
		String scannedPiece;
		char scannedChar;
		
		while (index < scannedString.length() )	{
			char nextLetter = scannedString.charAt(index);
			if (Character.isDigit(nextLetter) == true)	{							
				startIndex = index;
				while (Character.isDigit(nextLetter) == true)	{
					index++;
					nextLetter = scannedString.charAt(index);
				}	
				scannedPiece = scannedString.substring(startIndex,index);
				tokens[arrayIndex] = new ANumberToken(scannedPiece);
				arrayIndex++;
			}
					
			if (Character.isLetter(nextLetter) == true)	{		
				startIndex = index;	
				while (Character.isLetter(nextLetter) == true){
					index++;
					nextLetter = scannedString.charAt(index);
					}		
				scannedPiece = scannedString.substring(startIndex,index);
				if (scannedPiece.equalsIgnoreCase("move")){
					tokens[arrayIndex] = new MoveCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("say")){
					tokens[arrayIndex] = new SayCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("pass")){
					tokens[arrayIndex] = new PassCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("rotateLeftArm")){
					tokens[arrayIndex] = new RotateLeftArmCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("rotateRightArm")){
					tokens[arrayIndex] = new RotateRightArmCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("repeat")){
					tokens[arrayIndex] = new RepeatCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("define")){
					tokens[arrayIndex] = new DefineCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("call")){
					tokens[arrayIndex] = new CallCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("thread")){
					tokens[arrayIndex] = new ThreadCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("proceed")){
					tokens[arrayIndex] = new ProceedCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("proceedAll")){
					tokens[arrayIndex] = new ProceedAllCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("wait")){
					tokens[arrayIndex] = new WaitCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("fail")){
					tokens[arrayIndex] = new FailCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("sleep")){
					tokens[arrayIndex] = new SleepCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("undo")){
					tokens[arrayIndex] = new UndoCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("redo")){
					tokens[arrayIndex] = new RedoCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("approach")){
					tokens[arrayIndex] = new ApproachCommand(scannedPiece);
					arrayIndex++;
					}
				else if (scannedPiece.equalsIgnoreCase("clap")){
					tokens[arrayIndex] = new ClapCommand(scannedPiece);
					arrayIndex++;
					}
				else{
					scannedPiece = scannedString.substring(startIndex,index);
					tokens[arrayIndex] = new WordToken(scannedPiece);
					arrayIndex++;
					}
			}			
			
			if (nextLetter == '"')	{	
				startIndex = index;
				 
				boolean quoteCount = true;				
				while (quoteCount == true)	{
					index ++;	
					char doubleQuoteDetector = scannedString.charAt(index);			
					if (doubleQuoteDetector == '"') 
						quoteCount = false;
				}	
				scannedPiece = scannedString.substring(startIndex,index+1);
				tokens[arrayIndex] = new QuoteToken(scannedPiece);
				arrayIndex++;
			}
			
			if (nextLetter == '{')	{
				scannedChar = scannedString.charAt(index);
				tokens[arrayIndex] = new StartToken(Character.toString(scannedChar));
				arrayIndex++;
			}
			
			if (nextLetter == '}') {
				scannedChar = scannedString.charAt(index);
				tokens[arrayIndex] = new EndToken(Character.toString(scannedChar));
				arrayIndex++;
			}	
			
			if (nextLetter == '+')	{	
				scannedChar = scannedString.charAt(index);
				tokens[arrayIndex] = new PlusToken(Character.toString(scannedChar));
				arrayIndex++;
			}
			
			if (nextLetter == '-')	{		
				scannedChar = scannedString.charAt(index);
				tokens[arrayIndex] = new MinusToken(Character.toString(scannedChar));
				arrayIndex++;
			}
			index++;
		}
	}
}
