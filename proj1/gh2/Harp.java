package gh2;

public class Harp extends GuitarString{
    public Harp(double frequency) {
        super(frequency);
    }

    @Override
    public void tic() {
        double first = buffer.removeFirst();
        double second = sample();
        double last = (first + second) / 2 * 2;
        last = 0 - last;
        buffer.addLast(last);
    }
}
