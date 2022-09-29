import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public static HashMap showMonthStatistic;
    HashMap<String, MonthlyReportFiles> dataOfMonthlyStat=new HashMap<>();
    public MonthlyReport(String path) {
        String textOfMonthStat = readFileContentsOrNull(path);
        String[]linesOfText= textOfMonthStat.split("\n");
        for (int i = 1; i < linesOfText.length; i++) {
            String oneLine = linesOfText[i];
            String[]parttsOfOneLine=oneLine.split(",");
            String itemName= parttsOfOneLine[0];
            int quantityOfStat= Integer.parseInt(parttsOfOneLine[2]);
            int sumOfOneOfStat= Integer.parseInt(parttsOfOneLine[3]);
            boolean isExpenseInStat= Boolean.parseBoolean(parttsOfOneLine[1]);
            if (!dataOfMonthlyStat.containsKey(itemName)){
                dataOfMonthlyStat.put(itemName, new MonthlyReportFiles(itemName) );
            } MonthlyReportFiles mRepFile= dataOfMonthlyStat.get(itemName);  // это мы вызвали конктретно этот конструктор
            if (isExpenseInStat){
                mRepFile.totalExpenses+=quantityOfStat*sumOfOneOfStat;      // тут я хочу в него положить значения
            }else {
                mRepFile.totalProfit+=quantityOfStat*sumOfOneOfStat;
            }
        }
        System.out.println("Файл месячного отчёта успешно прочитан и обработан. ");
        System.out.println();
    }
    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
    public static void showMonthStatistic(MonthlyReport monthlyReport, int i) {
        int maxProfOfMonth=0;
        int maxExpenseOfMonth=0;
        String nameOfMostProf="";
        String nameOfMostExp="";
        System.out.println( "Месяц-"+ Month.of(i));
        for(String name:monthlyReport.dataOfMonthlyStat.keySet()){
            MonthlyReportFiles hmInside = monthlyReport.dataOfMonthlyStat.get(name);
            if (hmInside.totalProfit>maxProfOfMonth){
                maxProfOfMonth=hmInside.totalProfit;
                nameOfMostProf=name;
            }
            else if (hmInside.totalExpenses>maxExpenseOfMonth){
                maxExpenseOfMonth=hmInside.totalExpenses;
                nameOfMostExp=name;

            }



        }System.out.println( "Самый прибыльный товар - "+nameOfMostProf + ". выручено- " +maxProfOfMonth);
        System.out.println("Самая большая трата - "+nameOfMostExp + ". потрачено- " +maxExpenseOfMonth);

    }

}
