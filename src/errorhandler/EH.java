package errorhandler;

import java.util.ArrayList;
import java.util.List;

import ast.types.ErrorType;

public class EH {
	
	private static EH instance;
	private List<ErrorType> errors = new ArrayList<>();
	
	private EH(){}
	
	public static EH getEH(){
		if(instance == null){
			instance = new EH();
			return instance;
		}
		return instance;
	}
	
	public boolean anyError(){
		return errors.size()>0;
	}
	
	public void addError(ErrorType error){
		errors.add(error);
	}
	
	public void showErrors(){
		for(ErrorType error : errors)
			System.err.println(error.getMessage());
	}
	
	

}
