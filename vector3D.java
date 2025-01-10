
public class vector3D {
    private double x;
    private double y;
    private double z;

    public vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String toString() {
        return String.format("(%,.2f, %,.2f, %,.2f)", x, y, z);
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public vector3D normalize() {
        double magnitude = getMagnitude();
        if (magnitude == 0) {
            throw new IllegalStateException("Cannot normalize a zero vector.");
        }
        return new vector3D(x / magnitude, y / magnitude, z / magnitude);
    }

    public vector3D add(vector3D other) {
        return new vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public vector3D multiply(double scalar) {
        return new vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public double dotProduct(vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public double angleBetween(vector3D other) {
        double dotProduct = this.dotProduct(other);
        double magnitude1 = this.getMagnitude();
        double magnitude2 = other.getMagnitude();

        if (magnitude1 == 0 || magnitude2 == 0) {
            throw new IllegalStateException("Cannot compute angle with a zero vector.");
        }
        double cosTheta = dotProduct / (magnitude1 * magnitude2);
        cosTheta = Math.max(-1, Math.min(1, cosTheta)); // Clamp value to avoid NaN in case of precision issues
        return Math.toDegrees(Math.acos(cosTheta));
    }

    public vector3D crossProduct(vector3D other) {
        double i = this.y * other.z - this.z * other.y;
        double j = this.z * other.x - this.x * other.z;
        double k = this.x * other.y - this.y * other.x;
        return new vector3D(i, j, k);
    }


    public static void main(String[] args) {
        vector3D v = new vector3D(1, 2, 3);
        vector3D v2 = new vector3D(4, 5, 6);

        System.out.println("Vector 1: " + v);
        System.out.println("X: " + v.getX());
        System.out.println("Y: " + v.getY());
        System.out.println("Z: " + v.getZ());
        System.out.println("Magnitude: " + v.getMagnitude());
        System.out.println("Normalized Vector 1: " + v.normalize());
        System.out.println("Vector 1 + Vector 2: " + v.add(v2));
        System.out.println("Vector 1 * 2: " + v.multiply(2));
        System.out.println("DotProduct: " + v.dotProduct(v2));
        System.out.println("Angle between V1 and V2: " + v.angleBetween(v2) + " degrees");
        System.out.println("Cross product of V1 and V2: " + v.crossProduct(v2));
    }
}
