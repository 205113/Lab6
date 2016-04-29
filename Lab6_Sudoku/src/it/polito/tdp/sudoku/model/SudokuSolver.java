package it.polito.tdp.sudoku.model;

public class SudokuSolver {
	// La mia classe errata
	/*private int[][]soluzione;
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
	risolviRic(sudoku,0,0);
	if(!risolto)
		System.out.println("Sudoku impossibile");
	return soluzione;
	}
	
	void risolviRic(int[][]sudoku,int riga,int colonna){ 
		int zeri= this.contaZeri(sudoku);
		System.out.println("Zeri:"+zeri);
		Utils.printMatrix(sudoku, 9);
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
		{	for(int i=riga;i<sudoku.length && !risolto;i++){
				for(int j=colonna;j<sudoku[i].length && !risolto;j++){
					if(sudoku[i][j]==0){
							for(int valore=1;valore<10;valore++){
								if(this.mossaValida(sudoku, i, j, valore)&& !risolto){
									sudoku[i][j]=valore;
									risolviRic(sudoku,i,j);
								}
							}if(!risolto)
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
	}*/

	/*
	 * ALTERNATIVA N3 -- TROVO UNA SOLA SOLUZIONE QUESTA É LA CLASSE CHE VA
	 * CHIAMATA DAL CONTROLLER
	 * 
	 */

	public int[][] recursiveSudokuOne(int[][] matrix) {
		return recursiveSudokuSolverOne(matrix, 0);
	}

	/*
	 * FUNZIONE RICORSIVA: Il parametro level identifica il livello nell'albero
	 * della ricorsione. Nel caso del Sudoku, identifica la posizione della
	 * matrice in cui vado a posizionare un nuovo numero.
	 */
	private int[][] recursiveSudokuSolverOne(int[][] matrix, int level) {

		// Condizione di terminazione
		if (level == Utils.dim * Utils.dim) {
			// Ho trovato una nuova soluzione!

			/*if (TestMain.debug) {
				// Stampo la matrice.
				System.out.println("Yeah!");
				Utils.printMatrix(matrix, Utils.dim);
			}*/

			return matrix;
		}

		// Calcolo la riga e la colonna della matrice in base al livello
		int riga = level / Utils.dim;
		int colonna = level % Utils.dim;

		// Posiziono un numero solo se la casella è vuota (contiene uno 0)
		if (matrix[riga][colonna] == 0) {

			// Per ciascuna colonna
			for (int i = 1; i <= Utils.dim; i++) {

				// Aggiungi una nuova regina sulla riga corrente
				matrix[riga][colonna] = i;

				// Controlla se il numero non è già presente
				if (Utils.check(matrix, true)) {
					// Chiama la funzione ricorsiva
					int[][] retMatrix = recursiveSudokuSolverOne(matrix, level + 1);
					if (retMatrix != null)
						return retMatrix;
				}

				// Backtracking: rimuovo la regina appena aggiunta
				matrix[riga][colonna] = 0;
			}

		} else {
			// Altrimenti richiamo semplicemente la funzione
			// ricorsiva sul livello successivo
			int[][] retMatrix = recursiveSudokuSolverOne(matrix, level + 1);
			if (retMatrix != null)
				return retMatrix;
		}

		return null;
	}
}
	