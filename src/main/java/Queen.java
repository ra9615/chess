public class Queen extends ChessPiece {
    public Queen(String color) {
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

        // Фигура не может остаться на месте
        if (line == toLine && column == toColumn) return false;

        // Проверка, что в конечной позиции находится фигура другого цвета
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        // Проверка на вертикальное, горизонтальное или диагональное движение
        int dx = Math.abs(line - toLine);
        int dy = Math.abs(column - toColumn);
        if (line == toLine || column == toColumn || dx == dy) {
            int stepLine = (toLine - line) == 0 ? 0 : (toLine - line) / dx;
            int stepColumn = (toColumn - column) == 0 ? 0 : (toColumn - column) / dy;

            // Проверяем, что на пути нет фигур
            for (int i = 1; i < Math.max(dx, dy); i++) {
                if (chessBoard.board[line + i * stepLine][column + i * stepColumn] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isWithinBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
