import java.io.FileReader;
import java.io.IOException;

import codeGeneration.ExecuteCGVisitor;
import errorhandler.EH;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import parser.Parser;
import scanner.Scanner;
import visitor.IdentificationVisitor;
import visitor.OffsetVisitor;
import visitor.TypeCheckingVisitor;
import visitor.Visitor;

public class Main {
	public static void main(String args[]) throws IOException {
	      if (args.length<1) {
	        System.err.println("Pass me the name of the input file.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("The file "+args[0]+" could not be opened.");
			return;
		}
		
		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);
		parser.run();
		
		Visitor iv = new IdentificationVisitor();
		Visitor tc = new TypeCheckingVisitor();
		Visitor ov = new OffsetVisitor();
		Visitor cg = new ExecuteCGVisitor(args[0], args[1]);
		
		parser.getAST().accept(iv, null);
		parser.getAST().accept(tc, null);
		parser.getAST().accept(ov, null);
		parser.getAST().accept(cg, null);
						
		if(EH.getEH().anyError()){			
			EH.getEH().showErrors();
		}
		else{
			IntrospectorModel model=new IntrospectorModel("Program",parser.getAST());
			new IntrospectorTree("Introspector", model);		
		}
	}

}