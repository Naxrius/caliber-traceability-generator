package com.github.naxrius.traceabilitygenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.starbase.caliber.Requirement;
import com.starbase.caliber.Session;
import com.starbase.caliber.Trace;
import com.starbase.caliber.server.CaliberServer;
import com.starbase.caliber.server.RemoteServerException;

public class TraceabilityGenerator {
	static CaliberServer server = new CaliberServer("CaliberRMProd");
	static Frame f = new Frame();
	static Session session = null;
	static Requirement folder = null;
	static PrintStream out;
	
	public static void main(String[] args) throws FileNotFoundException {
		out = new PrintStream(new FileOutputStream("Output.txt"));
		f.OpenFrame();
	}
	
	// Log user into Caliber
	public static Session UserLogin(String uName, String uPass){
			try {
				session = server.login(uName, uPass);
				return session;
			}catch (Exception e) {
				return session;
			}
	}
	
	// Prints out all the children of a folder and their traces
	public static void TraverseFolder() throws RemoteServerException{
		Requirement[] children = folder.getChildRequirements();
		out.println("------------------------------------------------");
		out.println("Traceability for " + folder.getName());
		
		if(session != null){
			try {
				long startTime = System.currentTimeMillis();
				
				for(int i=0;i<children.length; i++){
					out.println("------------------------------------------------");
					out.print(children[i].getName() + ": ");
					PrintDesc(children[i]);
					out.println();
					PrintTraces(children[i]);
					out.println();
				}
				
				session.logout();
				f.setTextArea("Finished!\n");
				long estimatedTime = (System.currentTimeMillis() - startTime)/1000;
				f.setTextArea("Elapsed time: " + estimatedTime + " seconds.\n");
			} catch(RemoteServerException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Prints all traces to and from a requirement
	public static void PrintTraces(Requirement req) throws RemoteServerException{
		Trace[] tfArray = req.getTracesFrom();
		Trace[] ttArray = req.getTracesTo();
		
		// Print traces from
		out.println("\tTraces from: ");
		if(tfArray.length == 0){
			out.println("\t\tNone");
		}else{
			for(int i=0; i<tfArray.length; i++){
				out.println("\t\t" + tfArray[i].getFromObject().getName());
			}
		}
		out.println();
		
		// Print traces to
		out.println("\tTraces to: ");
		if(ttArray.length == 0){
			out.println("\t\tNone");
		}else{
			for(int i=0; i<ttArray.length; i++){
				out.println("\t\t" + ttArray[i].getToObject().getName());
			}
		}
	}
	
	// Prints the description of a requirement
	public static void PrintDesc(Requirement req) throws RemoteServerException {
		String desc = req.getDescription().getText();
		Document doc = Jsoup.parse(desc);
		out.print(doc.body().text()+"\n");
	}
	
	// Returns a requirement folder based on user input
	public static Requirement GetFolder(int fid) throws RemoteServerException{
		try{
			folder = session.getRequirement(fid);
			return folder;
		} catch(Exception e){
			return folder;
		}
	}
	
	public static Session getSession(){
		return session;
	}
	
	public static Requirement getFolderVar(){
		return folder;
	}
}