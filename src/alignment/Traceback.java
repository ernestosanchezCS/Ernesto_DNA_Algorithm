package alignment;

import java.util.Arrays;

/*
 * Traceback class, returns alignment. 
 * Needs pointer matrix and sequences 1 and 2
 */
public class Traceback {

	protected char[] s1,s2,a1,a2;
	protected double[][] ptr,scores;
	int max;

	public Traceback(char[] s1,char[] s2, double[][] pointers,double[][] scores,double size1,double size2){
		this.s1=s1;
		this.s2=s2;
		ptr=pointers;//size is -> pointers=new double[seq1.length()+1][seq2.length()+1];
		this.scores=scores;
		max=(int) Math.max(size1, size2);
		a1=new char[2*max+1];
		a2=new char[2*max+1];
	}

	public void align(){
		int i= s1.length;
		int j= s2.length;
		int count=0;
		while(i>0||j>0&&count<max){

			if(ptr[i][j]==0.0){
				a1[count]=s1[i-1];
				a2[count]=s2[j-1];
				count+=1;
				i-=1;
				j-=1;
			}

			else if(ptr[i][j]==1.0){
				a1[count]=s1[i-1];
				a2[count]='-';
				count+=1;
				i-=1;
			}
			else{
				a1[count]='-';
				a2[count]=s2[j-1];
				count+=1;
				j-=1;
			}

		}

		//System.out.println(Arrays.toString(a2));
		//System.out.println(Arrays.toString(a1));
		//return a1;
	}


	public void printAlign(){
		System.out.println("Original Sequences are: ");
		System.out.println();
		System.out.println(Arrays.toString(s1));
		System.out.println(Arrays.toString(s2));//original sequences in char array
		System.out.println();
		System.out.println();




		char[] b=new char[a1.length];//flip order of char[]a1 into char[] b
		for (int i = a1.length - 1, j = 0; i >= 0; i--, j++) {
			b[j] = a1[i];
		}
		char[] c=new char[a2.length];//flip the order of char[]a2 into char[]c 
		for (int i = a2.length - 1, j = 0; i >= 0; i--, j++) 
			c[j] = a2[i];
		//about to print b[] with bare empty spaces 
		char[] align1=new char[b.length];
		int i=0,spot=0;
		while(i<b.length){
			if(!(b[i]==0)){
				align1[spot]=b[i];
				spot+=1;
				i+=1;
			}
			else
				i+=1;
		}
		char[] alignFinal1=new char[spot+1];

		for(i=0;i<spot;i++)
			alignFinal1[i]=align1[i];

		System.out.println("One Global Alignment is: ");
		System.out.println();
		System.out.println(Arrays.toString(alignFinal1));//prints alined sequence 1 after char array is reversed 

		char[] align2=new char[c.length];
		int k=0,spot2=0;
		while(k<c.length){
			if(!(c[k]==0)){
				align2[spot2]=c[k];
				spot2+=1;
				k+=1;
			}
			else
				k+=1;
		}
		char[] alignFinal2=new char[spot2+1];

		for(int l=0;l<spot2;l++)
			alignFinal2[l]=align2[l];

		System.out.println(Arrays.toString(alignFinal2));//prints alined sequence 2 after char array is reversed 
		System.out.println();
		System.out.println("Global Alignment has score of: "+scores[s1.length][s2.length]);
		System.out.println();
		//System.out.println(Arrays.deepToString(scores).replace("], ", "]\n"));
	}

}
