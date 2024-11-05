public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        if (line == toLine && column == toColumn) return false;

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        int dx = Math.abs(line - toLine);
        int dy = Math.abs(column - toColumn);
        if (dx == dy) {
            int stepLine = (toLine - line) / dx;
            int stepColumn = (toColumn - column) / dy;
            for (int i = 1; i < dx; i++) {
                if (chessBoard.board[line + i * stepLine][column + i * stepColumn] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Метод для проверки, находится ли позиция внутри доски
    private static boolean isWithinBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
