package pl.edu.agh.debtexecutor.utils;

public class NumberUtils {
    public static boolean isNumeric(String num) {
        if (num == null) {
            return false;
        }
        try {
            Double.parseDouble(num);
            String[] parts = num.split("\\.");
            return parts.length == 1 || Double.parseDouble(parts[1]) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isPositive(String num) {
        if (!isNumeric(num)) return false;
        return Double.parseDouble(num) > 0;
    }
}
