package algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SticklerThief {

    public static void main (String[] args) {
        List<TestCase> testCases = parseTestCases();

        for(TestCase testCase : testCases) {
            int maxAmmountRobbed = maxAmountRobbed(testCase.getNumberOfHouses(), testCase.getHouseMoneyArray());
            System.out.println(maxAmmountRobbed);
        }
    }

    public static int maxAmountRobbed(int numberOfHouses, int[] houseMoneyArray) {
        int maxAmount = 0;
        int maxSumWithoutPreviousElement = 0;
        int maxSumWithPreviousElement = houseMoneyArray[0];

        if(houseMoneyArray.length == 1) return houseMoneyArray[0];

        for (int index = 1; index < houseMoneyArray.length; index++) {
            int sumForCurrentElement = maxSumWithoutPreviousElement + houseMoneyArray[index];
            maxAmount = Math.max(sumForCurrentElement, maxSumWithPreviousElement);

            maxSumWithoutPreviousElement = maxSumWithPreviousElement;
            maxSumWithPreviousElement = maxAmount;
        }

        return maxAmount;
    }

    private static List<TestCase> parseTestCases() {
        List<TestCase> testCases = new ArrayList<TestCase>();
        Scanner scanner = new Scanner(System.in);

        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        while(numberOfTestCases > 0) {
            int numberOfHouses = Integer.parseInt(scanner.nextLine());

            String[] nextLineTokens = scanner.nextLine().split(" ");
            int[] houseMoneyArray = new int[nextLineTokens.length];

            for(int i = 0; i < nextLineTokens.length; i++) {
                houseMoneyArray[i] = Integer.parseInt(nextLineTokens[i]);
            }

            testCases.add(new TestCase(numberOfHouses, houseMoneyArray));
            numberOfTestCases--;
        }

        return testCases;
    }

    static class TestCase {
        private int numberOfHouses;
        private int[] houseMoneyArray;

        public TestCase(int numberOfHouses, int[] houseMoneyArray) {
            this.numberOfHouses = numberOfHouses;
            this.houseMoneyArray = houseMoneyArray;
        }

        public int getNumberOfHouses() {
            return this.numberOfHouses;
        }

        public int[] getHouseMoneyArray() {
            return this.houseMoneyArray;
        }

        @Override
        public String toString() {
            return numberOfHouses + " " + Arrays.toString(houseMoneyArray);
        }
    }

}
