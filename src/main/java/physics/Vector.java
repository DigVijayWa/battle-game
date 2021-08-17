package physics;

public class Vector {

  private double x;
  private double y;
  private double angle;
  private double magnitude;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
    this.angle = Math.atan2(y, x);
    this.magnitude = getMagnitude();
  }

  public void setXandY(double x, double y) {
    this.x = x;
    this.y = y;
    this.angle = Math.atan2(y, x);
    this.magnitude = getMagnitude();
  }

  public double getMagnitude() {
    return Math.sqrt(getMagnitudeSquare());
  }

  public double getMagnitudeSquare() {
    return (x * x) + (y * y);
  }

  public Vector additionVector(Vector vector, double timeLapsed) {

    vector = vector.multiplyByScalar(timeLapsed);
    return new Vector(this.getX() + vector.getX(),
        this.getY() + vector.getY());
  }

  public Vector additionVector(Vector vector) {
    return new Vector(this.getX() + vector.getX() * 1,
        this.getY() + vector.getY() * 1);
  }

  public Vector subtractionVector(Vector vector) {
    return new Vector(this.getX() - vector.getX(), this.getY() - vector.getY());
  }

  public Vector divideByScalar(double scalar) {
    return new Vector(this.x/scalar, this.y/scalar);
  }

  public void setXY() {
    this.x = this.magnitude * Math.cos(angle);
    this.y = this.magnitude * Math.sin(angle);
  }

  public Vector normalize() {
    double length = Math.sqrt((this.x * this.x) + (this.y * this.y));
    return new Vector(this.x / length, this.y / length);
  }

  public Vector multiplyByScalar(double scalar) {
    return new Vector(this.x * scalar, this.y * scalar);
  }

  public Vector limitVector(double limit) {
    double magnitudeSquared = this.getMagnitudeSquare();
    Vector result = new Vector(this.x,this.y);
    if(magnitudeSquared > limit*limit) {
      result = this.divideByScalar(Math.sqrt(magnitudeSquared));
      result.multiplyByScalar(limit);
    }
    return result;
  }

  public boolean equals(Vector otherVector, double precision) {
    return Math.abs(this.x - otherVector.getX()) <= precision
        && Math.abs(this.y - otherVector.getY()) <= precision;
  }

  public double distance(Vector otherVector) {
    return Math.sqrt(Math.pow(this.x - otherVector.x, 2) + Math.pow(this.y-otherVector.y, 2));
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getAngle() {
    return angle;
  }

  public Vector setAngleAndMagnitude(double angle, double magnitude) {

    this.angle = angle;
    this.magnitude = magnitude;
    this.x = this.magnitude * Math.cos(this.angle);
    this.y = this.magnitude * Math.sin(this.angle);
    return this;
  }

  public void setMagnitude(double magnitude) { this.magnitude = magnitude; }
}
