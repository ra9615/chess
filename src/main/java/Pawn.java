public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        // Пешка не может остаться на месте
        if (line == toLine && column == toColumn) return false;

        // Проверка на цвет фигуры и направление движения
        int direction = getColor().equals("White") ? 1 : -1;
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        // Проверка на движение вперед на одну клетку
        if (column == toColumn && toLine == line + direction && targetPiece == null) {
            return true;
        }

        // Проверка на первый ход на две клетки
        if (column == toColumn && toLine == line + 2 * direction && targetPiece == null) {
            // Белая пешка на старте (вторая строка)
            if (getColor().equals("White") && line == 1 && chessBoard.board[line + direction][column] == null) {
                return true;
            }
            // Черная пешка на старте (седьмая строка)
            if (getColor().equals("Black") && line == 6 && chessBoard.board[line + direction][column] == null) {
                return true;
            }
        }

        // Проверка на взятие по диагонали
        if (Math.abs(column - toColumn) == 1 && toLine == line + direction) {
            return targetPiece != null && !targetPiece.getColor().equals(this.getColor());
        }

        return false;
    }

    // Метод для проверки, находится ли позиция внутри доски
    private static boolean isWithinBoard(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
