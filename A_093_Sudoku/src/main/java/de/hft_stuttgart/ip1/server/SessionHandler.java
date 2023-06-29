package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.common.Session;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class SessionHandler implements Session {

    public List<Sudoku> allSudokus;

    public SessionHandler(){
        allSudokus = new ArrayList<>();
    }

    //allSudoku geht nicht vom client aus

    /**
     * Wo wird das aufgerufen, bzw wie übergibt man size und difficulty?
     */
    @Override
    public int[] addGame(int size, double difficulty) throws RemoteException {
        Sudoku sudoku = new Sudoku(size, difficulty);
        allSudokus.add(sudoku);
        return sudoku.getGrid();
    }

    @Override
    public String getGames() throws RemoteException {
        return allSudokus.toString();
    }

    @Override
    public boolean isSolution(int[] gridPlayer) throws RemoteException{

        int[] solution = allSudokus.get(0).getSol();

        for (int i = 0; i < solution.length; i++){
            if(solution[i] != gridPlayer[i] && gridPlayer[i] != 0){
                return false;
            }
        }

        return true;
    }

    @Override
    public void cleanGames() throws RemoteException{
        allSudokus.clear();
    }

    /**
     * Was gibt das aus?
     */
    @Override
    public String toString() {
        String buffer = "";

        for (Sudoku s: allSudokus){
            buffer += s.toString() + " ";
        }

        return buffer;
    }
}
