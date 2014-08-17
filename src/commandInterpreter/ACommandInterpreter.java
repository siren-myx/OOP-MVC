package commandInterpreter;

import graphics.Avatar;
import graphics.BridgeScene;
import animationCommands.ApproachAnimationCommand;
import animationCommands.CallAnimationCommand;
import animationCommands.ClapAnimationCommand;
import animationCommands.ClapAnimator;
import animationCommands.ClearanceManager;
import animationCommands.CommandListAnimationCommand;
import animationCommands.DefineAnimationCommand;
import animationCommands.FailAnimationCommand;
import animationCommands.MoveAnimationCommand;
import animationCommands.PassAnimationCommand;
import animationCommands.ProceedAllAnimationCommand;
import animationCommands.ProceedAnimationCommand;
import animationCommands.RepeatAnimationCommand;
import animationCommands.RotateLeftArmAnimationCommand;
import animationCommands.RotateRightArmAnimationCommand;
import animationCommands.SayAnimationCommand;
import animationCommands.SleepAnimationCommand;
import animationCommands.SynAnimationCommand;
import animationCommands.SynAnimator;
import animationCommands.SynClapAnimationCommand;
import animationCommands.SynMarchAnimationCommand;
import animationCommands.ThreadAnimationCommand;
import animationCommands.WaitAnimationCommand;
import mvc.APropertyListenerSupport;
import mvc.PropertyListenerSupport;
import database.Database;
import tokens.ANumberToken;
import tokens.ApproachCommand;
import tokens.CallCommand;
import tokens.ClapCommand;
import tokens.DefineCommand;
import tokens.EndToken;
import tokens.FailCommand;
import tokens.MinusToken;
import tokens.MoveCommand;
import tokens.PassCommand;
import tokens.PlusToken;
import tokens.ProceedAllCommand;
import tokens.ProceedCommand;
import tokens.QuoteToken;
import tokens.RepeatCommand;
import tokens.RotateLeftArmCommand;
import tokens.RotateRightArmCommand;
import tokens.SayCommand;
import tokens.ScannerBeanSheet;
import tokens.SleepCommand;
import tokens.StartToken;
import tokens.ThreadCommand;
import tokens.Token;
import tokens.WaitCommand;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@EditablePropertyNames({"Command","Interpreter","avatarCollector"})
@PropertyNames({"Command","ErrorReport","Interpreter","avatarCollector"})
@Tags({"Command Interpreter", "Observable"})
public class ACommandInterpreter implements CommandInterpreter{
	Database<String, Avatar> avatarCollector;
	Database<String, Runnable> commandCollector;
	Avatar[] avatars;
	CommandInterpreter interpreter;
	ScannerBeanSheet tokenBox;
	BridgeScene theScene;
	ClearanceManager theManager;
	Token[] tokens;
	String input, words, errorReport = "";
	int avatarIndex, distance, xDistance, yDistance, index = 0;
	
	CommandListAnimationCommand list = new CommandListAnimationCommand();
	PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	
	public ACommandInterpreter(ClearanceManager aManager) { 
		setCommand(" ");
		theManager = aManager;
		}
	
	public void setCommand(String newVal) {
		input = newVal;
		list.clean();
		}
	
	public void setInterpreter(Token[] strings) { 
		tokens = new Token[strings.length];
		tokens = strings;
		clear();
	}	
	
	public void runInputCommand(){
		new Thread(parseCommand()).start();
	}
	
	
	@Tags({"command parser"})
	public Runnable parseCommand(){
		resetErrorReport();

		//approach
		if (tokens[index] instanceof ApproachCommand){
			 return parseApproach();
			}
		
		//say
		else if (tokens[index] instanceof SayCommand){
			 return parseSay();
		 	}
		 
		//move
		else if (tokens[index] instanceof MoveCommand){	
			 return parseMove();
			}
		 
		//pass
		else if (tokens[index] instanceof PassCommand){
			 return parsePass();
			}

		//fail
		else if (tokens[index] instanceof FailCommand){
			 return parseFail();
			}	
		 
		//repeat
		else if (tokens[index] instanceof RepeatCommand){
			 return parseRepeat();
			}
		 
		//clap
		else if (tokens[index] instanceof ClapCommand){
			 return parseClap();
			}
		 
		 //command list
		else if (tokens[index] instanceof StartToken){
			 return parseCommandList();
			}
		
		//rotate left arm
		else if (tokens[index] instanceof RotateLeftArmCommand){
			 return pasreRotateLeftArm();
			}	
		
		//rotate right arm
		else if (tokens[index] instanceof RotateRightArmCommand){
			 return pasreRotateRightArm();
			}
		
		//sleep
		else if (tokens[index] instanceof SleepCommand){
			 return parseSleep();
			}
		
		//wait
		else if (tokens[index] instanceof WaitCommand){
			 return parseWait();
			}
		
		//proceed
		else if (tokens[index] instanceof ProceedCommand){
			 return parseProceed();
			}
		
		//proceed all
		else if (tokens[index] instanceof ProceedAllCommand){
			 return parseProceedAll();
			}
		
		//define
		else if (tokens[index] instanceof DefineCommand){
			 return parseDefine();
			}
		
		//call
		else if (tokens[index] instanceof CallCommand){
			 return parseCall();
			}
		
		//thread
		else if (tokens[index] instanceof ThreadCommand){
			 return parseThread();
			}
		
		else return null;
	}
	
	@Tags({"approach parser"})
	public ApproachAnimationCommand parseApproach(){
		index++;
		if (avatarCollector.member(tokens[index].getUpperCase())){
			setAvatarIndex(tokens[index]);			
			return new ApproachAnimationCommand(theScene, avatars[getAvatarIndex()]);
			}
		else {
			setErrorReport();
			return null;
			}
	}

	@Tags({"say parser"})
	public SayAnimationCommand parseSay(){
		index++;
		if (tokens[index] instanceof QuoteToken) {
			setWords(tokens[index]);
			return new SayAnimationCommand(theScene, getWords());
			} 
		else{ 
			setErrorReport();
			return null;
			}
		} 	
	
	@Tags({"commandList parser"})
	public Runnable parseCommandList(){
		index++;
		while (!(tokens[index] instanceof EndToken)){
			list.add(parseCommand());
			index++;
			}
		return list;
	}
	
	@Tags({"repeat parser"})
	public Runnable parseRepeat(){
		if (tokens[index+1] instanceof ANumberToken) {
			int repeatedCount = parseNumber();
			index++;
			return new RepeatAnimationCommand(repeatedCount,parseCommand());
			}
		else {
			setErrorReport();
			return null;
			}
	}
	
	@Tags({"pass parser"})
	public PassAnimationCommand parsePass(){ 
		return new PassAnimationCommand(theScene);
	}
	
	@Tags({"fail parser"})
	public FailAnimationCommand parseFail(){
		return new FailAnimationCommand(theScene);
	}	
	
	@Tags({"move parser", "signed move"})
	public MoveAnimationCommand parseMove(){
		index++;
		if (avatarCollector.member(tokens[index].getUpperCase())){
			setAvatarIndex(tokens[index]);
			return new MoveAnimationCommand(avatars[getAvatarIndex()],parseNumber(),parseNumber());
			}
		else {
			setErrorReport();
			return null;
			}
	}
		
	@Tags({"number parser"})
	public int parseNumber(){
		index++;
		if (tokens[index] instanceof PlusToken) {
			index++;
			return Integer.parseInt("+"+tokens[index].getUpperCase());	
			}
		else if (tokens[index] instanceof MinusToken) {
			index++;
			return Integer.parseInt("-"+tokens[index].getUpperCase());
			}
		else if (tokens[index]instanceof ANumberToken){
			return Integer.parseInt(tokens[index].getUpperCase());
			}
		else{ 
			setErrorReport();
			return 0;
			}
	}
	
	@Tags({"rotate left arm parser"})
	public RotateLeftArmAnimationCommand pasreRotateLeftArm(){
		index++;
		if (avatarCollector.member(tokens[index].getUpperCase())){
			setAvatarIndex(tokens[index]);
			return new RotateLeftArmAnimationCommand(avatars[getAvatarIndex()], parseNumber());
			}
		else{ 
			setErrorReport();
			return null;
			}
	}
	
	@Tags({"rotate right arm parser"})
	public RotateRightArmAnimationCommand pasreRotateRightArm(){
		index++;
		if (avatarCollector.member(tokens[index].getUpperCase())){
			setAvatarIndex(tokens[index]);
			return new RotateRightArmAnimationCommand(avatars[getAvatarIndex()], parseNumber());
			}
		else{ 
			setErrorReport();
			return null;
			}
		}	
	
	@Tags({"clap parser"})
	public ClapAnimationCommand parseClap(){
		if (avatarCollector.member(tokens[index].getUpperCase())){
			return new ClapAnimationCommand(avatars[getAvatarIndex()]);
			}
		else{ 
			setErrorReport();
			return null;
			}	
	}
	
	@Tags({"sleep parser"})
	public SleepAnimationCommand parseSleep(){ 
		return new SleepAnimationCommand(parseNumber());
	}
	
	@Tags({"wait parser"})
	public WaitAnimationCommand parseWait(){ 
		return new WaitAnimationCommand(theManager);
	}
	
	@Tags({"proceed parser"})
	public ProceedAnimationCommand parseProceed(){ 
		return new ProceedAnimationCommand(theManager);
	}
	
	@Tags({"proceed all parser"})
	public ProceedAllAnimationCommand parseProceedAll(){ 
		return new ProceedAllAnimationCommand(theManager);
	}
	
	@Tags({"define parser"})
	public DefineAnimationCommand parseDefine(){ 
		index++;
		DefineAnimationCommand define = new DefineAnimationCommand(tokens[index].getUpperCase().toLowerCase(),
				parseCommand(), commandCollector);
		return define;
	}
	
	@Tags({"call parser"})
	public CallAnimationCommand parseCall(){ 
		index++;
		String commandName = tokens[index].getUpperCase().toLowerCase();
		return new CallAnimationCommand(commandName, commandCollector);
	}
	
	@Tags({"thread parser"})
	public ThreadAnimationCommand parseThread(){ 
		index++;
		String commandName = tokens[index].getUpperCase().toLowerCase();
		return new ThreadAnimationCommand(commandName, commandCollector);
	}
	

	@Tags({"asynchronous Arthur"})
	public void asynchronousArthur(){
		SynAnimator Arthur = new SynAnimator(avatarCollector.get("Arthur"));
		SynAnimationCommand a = new SynAnimationCommand(Arthur, 250, 0);
		Thread synArthur = new Thread(a);
		synArthur.start();
	}
	
	@Tags({"asynchronous Galahad"})
	public void asynchronousGalahad(){
		SynAnimator Galahad = new SynAnimator(avatarCollector.get("Galahad"));
		SynAnimationCommand synGalahad = new SynAnimationCommand(Galahad, 250, 0);
		SynAnimationCommand synGalahad2 = new SynAnimationCommand(Galahad, 0, 200);
		Thread actGalahad = new Thread(synGalahad);
		Thread actGalahad2 = new Thread(synGalahad2);
		actGalahad.start();
		actGalahad2.start();
	}
	
	@Tags({"asynchronous Lancelot"})
	public void asynchronousLancelot(){
		SynAnimator Lancelot = new SynAnimator(avatarCollector.get("Lancelot"));
		SynAnimationCommand synLancelot = new SynAnimationCommand(Lancelot, 250, 0);
		SynAnimationCommand synLancelot2 = new SynAnimationCommand(Lancelot, 0, 180);
		Thread actLancelot = new Thread(synLancelot);
		Thread actLancelot2 = new Thread(synLancelot2);
		actLancelot.start();
		actLancelot2.start();
	}
	
	@Tags({"asynchronous Robin"})
	public void asynchronousRobin(){
		SynAnimator Robin = new SynAnimator(avatarCollector.get("Robin"));
		SynAnimationCommand synRobin = new SynAnimationCommand(Robin, 0, 180);
		SynAnimationCommand synRobin2 = new SynAnimationCommand(Robin, 250,0);
		Thread actRobin = new Thread(synRobin);
		Thread actRobin2 = new Thread(synRobin2);
		actRobin.start();
		actRobin2.start();
	}

	@Tags({"asynchronous Guardian"})
	public void asynchronousGuardian(){
		ClapAnimator Guardian = new ClapAnimator(avatarCollector.get("Guardian"));
		SynClapAnimationCommand synGuardian = new SynClapAnimationCommand(Guardian, 200);
		SynClapAnimationCommand synGuardian2 = new SynClapAnimationCommand(Guardian, -150);
		Thread actGuardian = new Thread(synGuardian);
		Thread actGuardian2 = new Thread(synGuardian2);
		actGuardian.start();
		actGuardian2.start();
	}
	
	public void asynchronousAll(){
		 asynchronousGalahad();
		 asynchronousArthur();
		 asynchronousGuardian();
		}
	
	@Tags({"waiting Arthur"})
	public void waitingArthur(){
		//waitForProceed() is called inside the animationCommand.
		Thread waitArthur = new Thread(new SynMarchAnimationCommand(theManager,
				(Avatar) avatarCollector.get("Arthur"),200,100));
		waitArthur.start();
	}
	
	@Tags({"waiting Galahad"})
	public void waitingGalahad(){
		//waitForProceed() is called inside the animationCommand.
		Thread waitGalahad = new Thread(new SynMarchAnimationCommand(theManager,
				(Avatar) avatarCollector.get("Galahad"),200,100));
		waitGalahad.start();
	}
	
	@Tags({"waiting Lancelot"})
	public void waitingLancelot(){
		//waitForProceed() is called inside the animationCommand.
		Thread waitLancelot = new Thread(new SynMarchAnimationCommand(theManager,
				(Avatar) avatarCollector.get("Lancelot"),200,100));
		waitLancelot.start();
	}
	
	@Tags({"waiting Robin"})
	public void waitingRobin(){
		//waitForProceed() is called inside the animationCommand.
		Thread waitRobin = new Thread(new SynMarchAnimationCommand(theManager,
				(Avatar) avatarCollector.get("Robin"),200,100));
		waitRobin.start();
	}

	public void waitingAll(){
		waitingArthur();
		waitingRobin();
		waitingGalahad();
		waitingLancelot();
	}
	
	@Tags({"start animation"})
	public void startAnimation() { 
		theManager.proceedAll();
		}
	
	public void setAvatarData(Database<String, Avatar> theData) { 
		avatarCollector = theData;
		}
	
	public void setScene(BridgeScene aScene){
		theScene = aScene;
		avatars = theScene.getAvatars();
	}
	
	public void clear(){index = 0;}

	public String getCommand() { return input;}
	
	public void setWords(Token aToken) { words = aToken.getUpperCase();}
	public String getWords() {return words;}
	
	public void setAvatarIndex(Token aToken) {avatarIndex = avatarCollector.objectAt(tokens[index].getUpperCase());}
	public int getAvatarIndex() {return avatarIndex;}
	
	@Tags({"Error Resilient"})
	public void setErrorReport() { errorReport ="Input error!";}
	public void resetErrorReport() { errorReport = "";}
	public String getErrorReport() { return errorReport;}
}
