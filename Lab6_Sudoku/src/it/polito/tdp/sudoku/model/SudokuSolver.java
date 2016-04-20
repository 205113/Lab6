package it.polito.tdp.sudoku.model;

public class SudokuSolver {
	private int[][]soluzione;
	private final static int larghezza= 9;
	private final static int altezza= 9;
	private boolean risolto;
	public SudokuSolver() {
		this.soluzione = new int[larghezza][altezza];
		this.risolto=false;
	}

	public int[][] risolvi(int[][]sudoku){
	int zeri= this.contaZeri(sudoku);
	System.out.println("inizio con:"+zeri+" zeri");
	risolviRic(sudoku);
	if(!risolto)
		System.out.println("Sudoku impossibile");
	return soluzione;
	}
	
	void risolviRic(int[][]sudoku){ 
		int zeri= this.contaZeri(sudoku);
		System.out.println("Zeri:"+zeri);
		if(zeri==0 || risolto)
		{
			//risolto
			if(!risolto){
				soluzione=sudoku;
				this.risolto=true;	
			}
			
		}
		else
			//prendo prima casella che ha uno zero e  provo a 
			//cambiarne il numero provando una mossa solo se valida
		{	for(int i=0;i<sudoku.length;i++){
				for(int j=0;j<sudoku[i].length;j++){
					if(sudoku[i][j]==0){
							for(int valore=1;valore<10;valore++){
								if(this.mossaValida(sudoku, i, j, valore)){
									sudoku[i][j]=valore;
									int[][]parziale=sudoku;
									/*int[][] parziale=new int[sudoku.length][sudoku[i].length];
										for(int k=0;k<parziale.length;k++){
											for(int h=0;h<parziale[k].length;h++)
												parziale[k][h]=sudoku[k][h];
										}*/
									risolviRic(parziale);
								}
							}
							sudoku[i][j]=0;
					}
				}
		}

		}
	}
	private boolean mossaValida(int[][]sudoku,int riga,int colonna,int valore){
		for (int i = 0; i < 9; i++) {
			if (valore == sudoku[riga][i])
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (valore == sudoku[i][colonna])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if (riga > 2)
			if (riga > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if (colonna > 2)
			if (colonna > 5)
				cornerY = 6;
			else
				cornerY = 3;
		for (int i = cornerX; i < 10 && i < cornerX + 3; i++)
			for (int j = cornerY; j < 10 && j < cornerY + 3; j++)
				if (valore == sudoku[i][j])
					return false;
		return true;
	}
	private int contaZeri(int[][]sudoku){
		int zeri=0;
		for(int i=0;i<sudoku.length;i++){
			for(int j=0;j<sudoku[i].length;j++){
				if(sudoku[i][j]==0)
					zeri++;
			}
		}
		return zeri;
	}
}
	