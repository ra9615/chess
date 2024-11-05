public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    public void setCheck(boolean check) {
        super.setCheck(check);
    }

    public boolean getCheck() {
        return super.getCheck();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на корректность позиции
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Король не может остаться на месте
        if (line == toLine && column == toColumn) return false;

        // Проверка, что в конечной позиции находится фигура другого цвета
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        // Король может ходить только на одну клетку
        int dx = Math.abs(line - toLine);
        int dy = Math.abs(column - toColumn);
        return dx <= 1 && dy <= 1;
    }

    // Метод для проверки, находится ли позиция внутри доски
    private static boolean isWithinBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    // Метод проверки, под атакой ли находится король
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor()) &&
                        piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
