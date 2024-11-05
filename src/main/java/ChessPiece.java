public abstract class ChessPiece {
    private String color;
    private boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return this.check;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
