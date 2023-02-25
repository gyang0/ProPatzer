import java.util.ArrayList;
import java.util.HashMap;

/**
 * AI for user to play against.
 *
 * Current makes random moves from the first possible piece.
 */

public class HackberryAI {
    private char side;
    private int depth;

    private final int NUM_SQUARES = 8;

    // Constructors
    public HackberryAI(){
        this.side = 'b';
        this.depth = 0;
    }

    public HackberryAI(char side, int depth){
        this.side = side;
        this.depth = depth;
    }

    public void promotePawn(Piece[][] pieces, int x, int y){
        int choice = (int)(Math.random() * 4);

        switch(choice){
            case 0:
                pieces[x][y].setPiece(x, y, this.side == 'w' ? "wr" : "br", this.side);
                break;
            case 1:
                pieces[x][y].setPiece(x, y, this.side == 'w' ? "wn" : "bn", this.side);
                break;
            case 2:
                pieces[x][y].setPiece(x, y, this.side == 'w' ? "wb" : "bb", this.side);
                break;
            case 3:
                pieces[x][y].setPiece(x, y, this.side == 'w' ? "wq" : "bq", this.side);
                break;
        }
    }

    /*
    public boolean stillInOpening(){
        for(int i = 0; i < OpeningBook.continuations.length; i++){
            boolean matches = false;
            for(int j = 0; j < PGN.length; j++)
        }
    }*/

    // Actual AI goes here
    // Find the 5 moves that offer the best position
    public void makeMove(Piece[][] pieces, int[] mostRecentPieceMov, HashMap<Piece, ArrayList<int[]>> piecesW, HashMap<Piece, ArrayList<int[]>> piecesB, int[] prevCoords){
        /*if(this.stillInOpening()){
            //playOpening();
            return;
        }*/

        if(this.side == 'w'){
            // Choose a random move
            for(Piece p : piecesW.keySet()){
                if(piecesW.get(p).size() > 0){

                    int randIndex = (int)(Math.random() * piecesW.get(p).size());
                    int[] arr = piecesW.get(p).get(randIndex);

                    prevCoords = arr;


                    Notation.updateMoves(arr[0], arr[1], pieces[arr[0]][arr[1]]);
                    p.playMove(arr[0], arr[1], pieces, piecesW, piecesB, prevCoords, true);

                    // Promotion
                    if(arr[1] == 0 && pieces[arr[0]][arr[1]].getType().equals("wp")){
                        Notation.updateMoves(arr[0], arr[1], pieces[arr[0]][arr[1]]);
                        promotePawn(pieces, arr[0], arr[1]);
                        return;
                    }

                    mostRecentPieceMov[0] = arr[0];
                    mostRecentPieceMov[1] = arr[1];

                    return;
                }
            }

        }

        else if(this.side == 'b'){
            // Choose a random move
            for(Piece p : piecesB.keySet()){
                if(piecesB.get(p).size() > 0){

                    int randIndex = (int)(Math.random() * piecesB.get(p).size());
                    int[] arr = piecesB.get(p).get(randIndex);

                    prevCoords = arr;

                    Notation.updateMoves(arr[0], arr[1], pieces[arr[0]][arr[1]]);
                    p.playMove(arr[0], arr[1], pieces, piecesW, piecesB, prevCoords, true);

                    // Promotion
                    if(arr[1] == NUM_SQUARES - 1 && pieces[arr[0]][arr[1]].getType().equals("bp")){
                        Notation.updateMoves(arr[0], arr[1], pieces[arr[0]][arr[1]]);
                        promotePawn(pieces, arr[0], arr[1]);
                        return;
                    }

                    mostRecentPieceMov[0] = arr[0];
                    mostRecentPieceMov[1] = arr[1];

                    return;
                }
            }
        }

    }

}
