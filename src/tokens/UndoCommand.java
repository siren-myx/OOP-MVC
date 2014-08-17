package tokens;
import util.annotations.Tags;
@Tags({"undo"})
public class UndoCommand extends ALowerCaseToken{
	public UndoCommand(String newVal) { super(newVal);}
}