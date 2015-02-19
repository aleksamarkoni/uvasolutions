

// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"


import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);


        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }


    void Begin()
    {
        String input;
        StringTokenizer idata;

        while ((input = Main.ReadLn (255)) != null)
        {
            idata = new StringTokenizer (input);
            int durationOfLoanInMonths = Integer.parseInt(idata.nextToken());
            if (durationOfLoanInMonths < 0)
                break;
            double downPayment = Double.parseDouble(idata.nextToken());
            double loanAmount = Double.parseDouble(idata.nextToken());
            int numberOfDepreciationRecords = Integer.parseInt(idata.nextToken());
            double carValue = loanAmount + downPayment;
            double amountToReturnEveryMonth = loanAmount/durationOfLoanInMonths;
            DepreciationRecords depreciationRecords = new DepreciationRecords();
            for (int i = 0; i < numberOfDepreciationRecords; i++) {
                depreciationRecords.addDepreciationRecord(new DepreciationRecord(Main.ReadLn(255)));
            }
            int i;
            for (i = 0; i < durationOfLoanInMonths; i++) {
                carValue -= carValue * depreciationRecords.getNextDepreciationRecord(i);
                //System.out.println("Car Value: " + carValue + " LoanAmount: " + loanAmount);
                //System.out.println("car depreciation: " + depreciationRecords.getNextDepreciationRecord(i));
                if (loanAmount < carValue) {
                    if (i == 1)
                        System.out.println(i + " month");
                    else
                        System.out.println(i + " months");
                    break;
                }
                loanAmount -= amountToReturnEveryMonth;
            }
            if (i == durationOfLoanInMonths) {
                if (i == 1)
                    System.out.println(i + " month");
                else
                    System.out.println(i + " months");
            }
        }
    }

    public class DepreciationRecords {

        int currentDepreciationRecord = 0;
        List<DepreciationRecord> depreciationRecords;

        DepreciationRecords() {
            depreciationRecords = new ArrayList<DepreciationRecord>();
        }

        public void addDepreciationRecord(DepreciationRecord depreciationRecord) {
            depreciationRecords.add(depreciationRecord);
        }

        double getNextDepreciationRecord(int monthValue) {
            if (currentDepreciationRecord + 1 < depreciationRecords.size()) {
                if (depreciationRecords.get(currentDepreciationRecord + 1).depreciationRecordId <= monthValue) {
                    currentDepreciationRecord++;
                }
            }
            return depreciationRecords.get(currentDepreciationRecord).getDepreciationRecordAmount();
        }
    }

    public class DepreciationRecord{

        int depreciationRecordId;
        double depreciationRecordAmount;

        DepreciationRecord(String input) {
            StringTokenizer iData = new StringTokenizer(input);
            depreciationRecordId = Integer.parseInt(iData.nextToken());
            depreciationRecordAmount = Double.parseDouble(iData.nextToken());
        }

        public int getDepreciationRecordId() {
            return depreciationRecordId;
        }

        public void setDepreciationRecordId(int depreciationRecordId) {
            this.depreciationRecordId = depreciationRecordId;
        }

        public double getDepreciationRecordAmount() {
            return depreciationRecordAmount;
        }

        public void setDepreciationRecordAmount(double depreciationRecordAmount) {
            this.depreciationRecordAmount = depreciationRecordAmount;
        }

        public double calculateDepreciation(double value) {
            return value * depreciationRecordAmount;
        }
    }
}

