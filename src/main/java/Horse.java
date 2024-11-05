public class Horse extends ChessPiece {
    public Horse(String color) {
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

        // Конь не может остаться на месте
        if (line == toLine && column == toColumn) return false;

        // Проверка, что в конечной позиции находится фигура другого цвета или пустая клетка
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        // Проверка, что ход буквой "Г" (разница в координатах 2 и 1)
        int dx = Math.abs(line - toLine);
        int dy = Math.abs(column - toColumn);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    // Метод для проверки, находится ли позиция внутри доски
    private static boolean isWithinBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
