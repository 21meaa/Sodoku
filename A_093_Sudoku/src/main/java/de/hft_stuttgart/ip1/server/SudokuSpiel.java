package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.common.SchnittstelleServerClient;

import java.rmi.RemoteException;
import java.util.Random;
public class SudokuSpiel implements SchnittstelleServerClient {



        private int[][] grid;
        private int size;
        private String[] symbols;

        public SudokuSpiel(int size)  throws RemoteException {
            this.size = size;
            this.grid = new int[size][size];
            this.symbols = generateSymbols(size);
        }

        // Generiere das Array mit den Symbols für die Zahlen
         @Override
         public String[] generateSymbols(int size)  throws RemoteException {
            String[] symbols = new String[size];
            for (int i = 0; i < size; i++) {
                if (i < 9) {
                    symbols[i] = String.valueOf(i + 1);
                } else {
                    char symbol = (char) ('A' + i - 9);
                    symbols[i] = String.valueOf(symbol);
                }
            }
            return symbols;
        }

    @Override
    public void generate()  throws RemoteException {
            fillDiagonalBlocks();
            solve();
            removeCells();
        }

        // Fülle die diagonalen Blöcke des Sudoku-Rasters

        @Override
        public void fillDiagonalBlocks()  throws RemoteException {
            int blockWidth = (int) Math.sqrt(size);
            for (int block = 0; block < size; block += blockWidth) {
                fillBlock(block, block);
            }
        }

        // Fülle einen einzelnen Block im Sudoku-Raster
        @Override
        public void fillBlock(int row, int col)  throws RemoteException {
            Random rand = new Random();
            String[] nums = new String[size];

            // Zahlen-Array initialisieren
            for (int i = 0; i < size; i++) {
                nums[i] = symbols[i];
            }

            // Zahlen-Array mischen
            shuffleArray(nums);

            for (int i = 0; i < size; i++) {
                int numIndex = (i + row) % size;
                grid[(row + i) % size][(col + i) % size] = getSymbolValue(nums[numIndex]);
            }
        }

        // Konvertiere das Symbol in den entsprechenden Wert
        private int getSymbolValue(String symbol) {


            if (symbol.matches("[0-9]")) {
                return Integer.parseInt(symbol);
            } else {
                char ch = symbol.charAt(0);
                return 10 + (ch - 'A');
            }
        }

        // Mische ein Array
        @Override
        public void shuffleArray(String[] array)  throws RemoteException {
            Random rand = new Random();

            for (int i = array.length - 1; i > 0; i--) {
                int index = rand.nextInt(i + 1);
                String temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }

        // Löse das Sudoku-Rätsel
        public boolean solve()  throws RemoteException{
            // Sudoku-Löser implementieren (siehe vorherige Antwort)
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (grid[row][col] == 0) {
                        for (int num = 1; num <= size; num++) {
                            if (isValid(row, col, num)) {
                                grid[row][col] = num;
                                if (solve()) {
                                    return true;
                                } else {
                                    grid[row][col] = 0;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        // Überprüfe die Gültigkeit einer Zahl in einer bestimmten Position
        @Override
        public boolean isValid(int row, int col, int num)  throws RemoteException {

            // Überprüfe, ob num in der aktuellen Zeile gültig ist
            for (int c = 0; c < size; c++) {
                if (grid[row][c] == num) {
                    return false;
                }
            }

            // Überprüfe, ob num in der aktuellen Spalte gültig ist
            for (int r = 0; r < size; r++) {
                if (grid[r][col] == num) {
                    return false;
                }
            }

            // Überprüfe, ob num im aktuellen Unterquadrat gültig ist
            int sqrt = (int) Math.sqrt(size);
            int boxRowStart = row - row % sqrt;
            int boxColStart = col - col % sqrt;

            for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
                for (int c = boxColStart; c < boxColStart + sqrt; c++) {
                    if (grid[r][c] == num) {
                        return false;
                    }
                }
            }

            return true;
        }

        // Entferne Zellen aus dem Sudoku-Rätsel
        @Override
        public void removeCells()  throws RemoteException{
            Random rand = new Random();
            double fractionToRemove = 0.9; // Bruchteil der Zellen, die entfernt werden sollen

            for (int i = 0; i < size * size; i++) {
                int row = i / size;
                int col = i % size;

                if (rand.nextDouble() <= fractionToRemove) {
                    grid[row][col] = 0;
                }
            }
        }

        // Gib das Sudoku-Raster aus

    @Override
    public void printGrid()  throws RemoteException{
            int blockSize = (int) Math.sqrt(size);
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    String symbol = getSymbol(grid[row][col]);
                    System.out.print(symbol);

                    if ((col + 1) % blockSize == 0 && col != size - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if ((row + 1) % blockSize == 0 && row != size - 1) {
                    for (int i = 0; i < size + blockSize - 1; i++) {
                        System.out.print("-");
                    }
                    System.out.println();
                }
            }
        }
        // Konvertiere den Wert in das entsprechende Symbol
        public String getSymbol(int value)  throws RemoteException {
            if (value == 0) {
                return " ";
            } else if (value < 10) {
                return String.valueOf(value);
            } else {
                char symbol = (char) ('A' + value - 10);
                return String.valueOf(symbol);
            }
        }
    public static void main(String[] args) throws RemoteException {

        SudokuSpiel sudokuSpiel = new SudokuSpiel(9);
        sudokuSpiel.generate();
        sudokuSpiel.printGrid();
    }
    }


