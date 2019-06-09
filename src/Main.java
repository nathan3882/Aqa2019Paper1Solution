import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Failure failure = null;

        queryLoop:
        do {
            System.out.println("Enter the initial word or type exit to exit");
            String initial = scanner.nextLine(); //NINE
            if (initial.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter the second word to compose the first word from!");
            String composeOtherWithThisWord = scanner.nextLine(); //if this is DINNER, initial word "NINE" will be able to be made
            for (char anInitialChar : initial.toCharArray()) {
                int initialCount = getCount(initial, anInitialChar);
                int checkCount = getCount(composeOtherWithThisWord, anInitialChar);
                if (checkCount >= initialCount) {
                    continue;
                } else {
                    failure = new Failure(initial, composeOtherWithThisWord, anInitialChar, initialCount - checkCount);
                    break queryLoop;
                }
            }
            System.out.println("Can compose " + initial + " from " + composeOtherWithThisWord);
        } while (true); //Only purpose is to query the users words so while true isn't an issue

        if (failure != null) failure.enlightenUser();
    }

    private static int getCount(String input, char charToCount) {
        int count = 0;
        for (int i = 0; i < input.toCharArray().length; i++) {
            char aChar = input.charAt(i);
            if (aChar == charToCount) {
                count++;
            }
        }
        return count;
    }

    private static class Failure {

        private final String failedToCompose;
        private final String withThisWord;
        private final char requiredMoreTimes;
        private final int insufficientCharFrequency;

        Failure(String failedToCompose, String withThisWord, char requiredMoreTimes, int insufficientCharFrequency) {
            this.failedToCompose = failedToCompose;
            this.withThisWord = withThisWord;
            this.requiredMoreTimes = requiredMoreTimes;
            this.insufficientCharFrequency = insufficientCharFrequency;
        }

        public void enlightenUser() {
            System.out.println("Can not compose " + getFailedToCompose() + " with " + getWithThisWord() + " as letter" +
                    " '" + getRequiredMoreTimesChar() + "' only occurs " + getInsufficientCharFrequency() + " time/s.");

        }

        @Override
        public String toString() {
            return "Failure{" +
                    "failedToCompose='" + failedToCompose + '\'' +
                    ", withThisWord='" + withThisWord + '\'' +
                    ", requiredMoreTimes=" + requiredMoreTimes +
                    ", insufficientCharFrequency=" + insufficientCharFrequency +
                    '}';
        }

        public String getFailedToCompose() {
            return failedToCompose;
        }

        public String getWithThisWord() {
            return withThisWord;
        }

        public char getRequiredMoreTimesChar() {
            return requiredMoreTimes;
        }

        public int getInsufficientCharFrequency() {
            return insufficientCharFrequency;
        }
    }
}
