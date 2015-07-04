/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *Backtracking + row,column,3x3zone checks
 * @author rb
 */

public class Sudoku {


public static int a[][] = {	{0, 6, 1, 0, 0, 7, 0, 0, 3},
				{0, 9, 2, 0, 0, 3, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 8, 5, 3, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 5, 0, 4},
				{5, 0, 0, 0, 0, 8, 0, 0, 0},
				{0, 4, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 1, 6, 0, 8, 0, 0},
				{6, 0, 0, 0, 0, 0, 0, 0, 0}
				};

public static  int counter;

public static boolean check(int x, int si, int sj){
	for(int j=0;j<9;j++){ //row
		if(j == sj) continue;
		if(a[si][j] == x) return false;
	}
	for(int i=0;i<9;i++){ //column
		if(i == si) continue;
		if(a[i][sj] == x) return false;
	}
	//3x3 zone
	int top = si - (si%3);
	int left = sj - (sj%3);
	for(int i=0;i<3;i++){
		for(int j=0;j<3;j++){
			if(i+top == si && j+left == sj) continue;
			if(x == a[i+top][j+left]) return false;
		}
	}

	return true;

}

public static  void print(){
	System.out.println("-  -  -  -  -  -");
	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++){
			System.out.print(a[i][j]+"  ");
		}System.out.println("");
	}
	System.out.println("-  -  -  -  -  -");
}

public static  boolean solve(int si, int sj){

	//next cell
	int ni=si, nj=sj;
	if(sj == 8){
		if(si == 8){
			return true;
		}
		ni++;
		nj = 0;
	}else{
		nj++;
	}


	if(a[si][sj] != 0){
		if(check(a[si][sj], si, sj)){
			counter++; //statistics

			return solve(ni, nj);
		}else{
			return false;
		}
	}
	else{
		for(int i=1;i<=9;i++){
			if(check(i, si, sj)){
				counter++; //statistics

				a[si][sj] = i;
				boolean OK = solve(ni, nj);
				if(OK) return true;
				a[si][sj] = 0;//unset
			}
		}
		return false;
	}
}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
	counter = 0;
	solve(0, 0);
	print();
	System.out.println("Recursion steps: "+ counter);

    }
    
}


