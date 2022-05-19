package wooteco.subway.domain;

public class FareCalculator {

    private static final int BASIC_FARE = 1250;
    private static final double BASIC_THRESHOLD_DISTANCE = 10.0;
    private static final double LONG_RANGE_THRESHOLD_DISTANCE = 50.0;
    private static final int BASIC_DISTANCE_RATE = 5;
    private static final int LONG_RANGE_DISTANCE_RATE = 8;
    private static final int OVER_FARE = 100;

    private FareCalculator() {
    }

    private static class Holder {
        private static final FareCalculator instance = new FareCalculator();
    }

    public static FareCalculator getInstance() {
        return Holder.instance;
    }

    public int calculate(int distance) {
        return BASIC_FARE + calculateOverFare(distance);
    }

    private int calculateOverFare(int distance) {
        int fare = 0;
        if (distance <= 10) {
            return 0;
        }
        if (distance > 50) {
            fare += (int) (Math.ceil((distance - LONG_RANGE_THRESHOLD_DISTANCE) / LONG_RANGE_DISTANCE_RATE)
                    * OVER_FARE);
        }
        fare += (int) (Math.min(Math.ceil((distance - BASIC_THRESHOLD_DISTANCE) / BASIC_DISTANCE_RATE),
                LONG_RANGE_DISTANCE_RATE) * OVER_FARE);
        return fare;
    }
}
