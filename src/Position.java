public class Position {

  private double x,y;

  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public void moveTo(Position p) {
    this.x = p.getX();
    this.y = p.getY();
  }

  public void boundOff(Position bounds) {
    this.x = Math.min(this.x, bounds.x);
    this.x = Math.max(this.x, 20);

    this.y = Math.min(this.y, bounds.y - 30);
    this.y = Math.max(this.y, 20);
  }

  public double distanceTo(Position other) {
    return Math.sqrt(
            (this.x - other.x) * (this.x - other.x) +
            (this.y - other.y) * (this.y - other.y));
  }

  public boolean isNear(Position p, double margin) {
    return (Math.abs(this.distanceTo(p)) <= margin);
  }

  public Position addPosition(Position p) {
    return new Position(this.x + p.x, this.y + p.y);
  }

  public Position multiply(double factor) {
    return new Position(this.x * factor, this.y * factor);
  }

}
