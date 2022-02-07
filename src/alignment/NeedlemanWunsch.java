package alignment;
import java.util.Scanner;

/*
 * E.S, 
 * Program takes DNA sequences as strings and finds a global alignment based on match score mismatch score and gap penalty
 * uses Needleman Wunsch algorithm to find scores, then using class tracback an alignment is found
 */

public class NeedlemanWunsch {

	private int gapPenalty;
	private int mismatchScore;
	private int matchScore;
	private String seq1;
	private String seq2;
	private char[] s1,s2;
	private double[][] scores,pointers,startscores;


	public NeedlemanWunsch(int gapPenalty, int matchScore, int mismatchScore, String seq1, String seq2){
		//data items
		this.gapPenalty=gapPenalty;
		this.matchScore=matchScore;
		this.mismatchScore=mismatchScore;
		this.seq1=seq1;
		this.seq2=seq2;
		s1=seq1.toCharArray();
		s2=seq2.toCharArray();
		scores=new double[seq1.length()+1][seq2.length()+1];
		pointers=new double[seq1.length()+1][seq2.length()+1];
		startscores=new double[seq1.length()+1][seq2.length()+1];
	}

	public String getSeq1() {
		return seq1;
	}
	public void setSeq1(String seq1) {
		this.seq1 = seq1;
	}
	public String getSeq2() {
		return seq2;
	}
	public void setSeq2(String seq2) {
		this.seq2 = seq2;
	}
	public double[][] getScores() {
		return scores;
	}
	public void setScores(double[][] scores) {
		this.scores = scores;
	}
	public double[][] getPointers() {
		return pointers;
	}
	public void setPointers(double[][] pointers) {
		this.pointers = pointers;
	}
	public int getGapPenalty() {
		return gapPenalty;
	}
	public void setGapPenalty(int gapPenalty) {
		this.gapPenalty = gapPenalty;
	}
	public int getMismatchScore() {
		return mismatchScore;
	}
	public void setMismatchScore(int mismatchScore) {
		this.mismatchScore = mismatchScore;
	}
	public int getMatchScore() {
		return matchScore;
	}
	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}
	public char[] getS1() {
		return s1;
	}
	public void setS1(char[] s1) {
		this.s1 = s1;
	}
	public char[] getS2() {
		return s2;
	}
	public void setS2(char[] s2) {
		this.s2 = s2;
	}
	public double[][] getStartscores() {
		return startscores;
	}
	public void setStartscores(double[][] startscores) {
		this.startscores = startscores;
	}


	public void fillMatrix(){ 

		int i,j;

		for (i=0; i<seq1.length()+1; i++)
			scores[i][0] = (i*gapPenalty);
		for (j=0; j<seq2.length()+1; j++)
			scores[0][j] = (j*gapPenalty); // initialize first row and column = i / j * gap penalty for scores matrix

		for (i=0; i<seq1.length()+1; i++)
			startscores[i][0] = (i*gapPenalty);
		for (j=0; j<seq2.length()+1; j++)
			startscores[0][j] = (j*gapPenalty);//just initialized matrix used when user wants just initialized matrix 


		for (i=0; i<seq1.length()+1; i++)
			pointers[i][0] = 1;
		for (j=0; j<seq2.length()+1; j++)//initializing first row and colum of pointers matrix
			pointers[0][j] = 2;

		pointers[0][0]=0;//fixes very first cell

		double score,x,y,z;
		for ( i=1; i<seq1.length()+1; i++){

			for(j=1; j<seq2.length()+1; j++){


				if(s1[i-1]==s2[j-1])
					score = matchScore;
				else
					score=mismatchScore;

				x=scores[i-1][j-1] + score;
				y=scores[i-1][j] + gapPenalty;
				z=scores[i][j-1] + gapPenalty;

				scores[i][j]=Math.max(x,Math.max(y,z));

				if(scores[i][j]== x)
					pointers[i][j]=0;//if u have zero you know came from diagonal top+left

				if(scores[i][j]== y)
					pointers[i][j]=1;//if have 1 came from TOP.  gap in seq1/s1 so char added to seq2/s2

				if(scores[i][j]== z)
					pointers[i][j]=2;//if have 2 came from LEFT. gap in seq2/s2 char added to seq1/s1
			}  
		}


	}
	public void printScoresInitialized(){//prints out initialized matrix 
		for(int i = 0; i < seq1.length()+1; i++){ 
			for(int j = 0; j < seq2.length()+1; j++){
				System.out.print(startscores[i][j] + "   "); 
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public void printScores(){
		System.out.println();
		System.out.println();
		System.out.println("Finished Needleman Wunsch Algorithm (completed matrix) is:");
		System.out.println();
		for(int i = 0; i < seq1.length()+1; i++){ //best way i could print matrix with least distortion 
			for(int j = 0; j < seq2.length()+1; j++){
				System.out.print(scores[i][j] + "   "); 
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public void printPointers(){
		System.out.println();
		System.out.println();
		System.out.println("Traceback 'pointers' Matrix (Direction Guide: 0.0=diagonal, 1.0=up, 2.0=left): ");
		for(int i = 0; i < seq1.length()+1; i++){ 
			for(int j = 0; j < seq2.length()+1; j++){
				System.out.print(pointers[i][j] + "   "); 
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}






	public static void main (String args[]){

		int d=0;
		System.out.println("Hello, ");
		System.out.println();
		System.out.println("DNA Alignment: Needleman Wunsch Method + Traceback. 6\nErnesto Sanchez");
		System.out.println();
		System.out.println();

		rerun:for(int k=0;k<100;k++){//pickup point for program if user wants to re enter new sequences
			
			if(d==6){
				System.out.println("Program terminated.\nHave a nice day. :)");
				break;
			}
			
			System.out.println("To begin program please enter two DNA sequences and related parameters: ");
			System.out.println();
			System.out.println("Entered sequences must meet these requirements." +
					"\n	Must contain only characters A,C,G,T.\n	Must contain all upper case letter.\n	Must not have any spaces");

			String[] sequence=new String[2];
			Scanner scanner = new Scanner(System.in);

			int i=0;
			while (i<2){
				sequence[i]=scanner.nextLine();
				i+=1;
			}
			System.out.println("Plase enter parameters:");
			System.out.println("Entered parameters must meet these requirements." +
					"\nMust contain only integer/whole numbers, include sign!.\nMust contain only Numbers ex. 0-9");
			System.out.println();
			System.out.println("Gap Penalty");
			System.out.println("Match Score");
			System.out.println("Mismatch Score");
			int x=scanner.nextInt();
			int y=scanner.nextInt();
			int z=scanner.nextInt();//now gp stored in x,matchscore in y, mismatchcore in z

			NeedlemanWunsch test = new NeedlemanWunsch(x,y,z, sequence[0], sequence[1]);//creates test object type needlemanwunsch
			test.fillMatrix();//object has table filled now with needleman algorithm
			
			System.out.println();
			System.out.println();
			System.out.println("///successful alignment///");
			System.out.println();
			System.out.println();
			
			for(int p=0;p<100;p++){

				System.out.println("Please select one of the following options:");
				System.out.println("1] Print both sequences.");
				System.out.println("2] Print initialized matrix.");
				System.out.println("3] Result of the Needleman Wunsch (print entire matrix).");
				System.out.println("4] Print Global Optimal alignment.");
				System.out.println("5] Restart Program: New Alignment.");
				System.out.println("6] EXIT");


				d=scanner.nextInt();//selection in d

				if (d==1){
					System.out.println("Original Sequences are: ");
					System.out.println();
					System.out.println(test.s1);
					System.out.println(test.s2);
					System.out.println();
					System.out.println();
				}
				else if(d==2){
					System.out.println("Initialized Matrix is: ");
					System.out.println();
					test.printScoresInitialized();
				}
				else if(d==3){
					test.printScores();
				}
				else if(d==4){
					Traceback trace=new Traceback(test.s1,test.s2,test.pointers,test.scores,test.s1.length,test.s2.length);
					trace.align();
					trace.printAlign();
				}
				else if(d==5){
					break;
				}
				else if (d==6){
					break;
				}
				else {
					System.out.println();
					System.out.println("INVALID ENTRY: Please try again. \n Enter a number 1-6:\n");
					System.out.println();
					
				}
			
				if(d==5){
					break rerun;
				}
				
			
		}
		
		//manual testing commented out below
		//String seq1 = ("GT");
		//String seq2 = ("AG"); the small 2.2 matrix doesnt work 
		/*
		String seq1 = ("GAATTCAGTTA");
		String seq2 = ("GGATCGA");
		NeedlemanWunsch test = new NeedlemanWunsch(0,1,0, seq1, seq2);
		test.fillMatrix();
		test.printScores();
		test.printPointers();
		Traceback trace=new Traceback(test.s1,test.s2,test.pointers,test.scores,test.s1.length,test.s2.length);
		trace.align();
		trace.printAlign();
		 */
	}
}
}
