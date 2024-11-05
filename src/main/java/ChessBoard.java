public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell

                // Сбрасываем флаг check после первого хода короля или ладьи
                board[endLine][endColumn].setCheck(false);

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public Object getFigure(int line, int column) {
        if (line >= 0 && line < 8 && column >= 0 && column < 8) {
            return board[line][column];
        }
        return null;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    // Рокировка с ладьей в 0-ом столбце
    public boolean castling0() {
        int row = nowPlayer.equals("White") ? 0 : 7;

        if (board[row][4] instanceof King && board[row][0] instanceof Rook &&
                board[row][4].getCheck() && board[row][0].getCheck() &&
                board[row][1] == null && board[row][2] == null && board[row][3] == null &&
                !((King) board[row][4]).isUnderAttack(this, row, 2)) {

            board[row][2] = board[row][4];
            board[row][3] = board[row][0];
            board[row][4] = null;
            board[row][0] = null;
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }

    // Рокировка с ладьей в 7-ом столбце
    public boolean castling7() {
        int row = nowPlayer.equals("White") ? 0 : 7;

        if (board[row][4] instanceof King && board[row][7] instanceof Rook &&
                board[row][4].getCheck() && board[row][7].getCheck() &&
                board[row][5] == null && board[row][6] == null &&
                !((King) board[row][4]).isUnderAttack(this, row, 6)) {

            board[row][6] = board[row][4];
            board[row][5] = board[row][7];
            board[row][4] = null;
            board[row][7] = null;
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }
}
