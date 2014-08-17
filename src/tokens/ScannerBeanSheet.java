package tokens;


public interface ScannerBeanSheet {
	public void setAnalyzer();
	public void setInput(String newVal);
	public void setCommandTokens();
	public void setScanner();	
	public void setToken();	
	public String getInput();
	public Token[] getToken();
}
