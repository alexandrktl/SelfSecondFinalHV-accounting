import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.HashMap;

public class YearlyReport {
HashMap<Integer, YearlyReportFiles> dataOfYearlyStat=new HashMap<>();
public YearlyReport(String path) {
String textOfYearStat = readFileContentsOrNull(path);
    String[]linesOfText= textOfYearStat.split("\n");
    for (int i = 1; i < linesOfText.length; i++) {
        String oneLine = linesOfText[i];
        String[]parttsOfOneLine=oneLine.split(",");
        int monthOfYearStat= Integer.parseInt(parttsOfOneLine[0]);
        int amountOfYearStat= Integer.parseInt(parttsOfOneLine[1]);
        boolean isExpenseInYearStat= Boolean.parseBoolean(parttsOfOneLine[2]);
        if (!dataOfYearlyStat.containsKey(monthOfYearStat)){
            dataOfYearlyStat.put(monthOfYearStat, new YearlyReportFiles(monthOfYearStat) );
        } YearlyReportFiles yRepFile= dataOfYearlyStat.get(monthOfYearStat);  // это мы вызвали конктретно этот конструктор
        if (isExpenseInYearStat){
            yRepFile.totalExpenses+=amountOfYearStat;                         // тут я хочу в него положить значения
        }else {
            yRepFile.totalProfit+=amountOfYearStat;
        }
    }
    System.out.println("Файл годового отчёта успешно прочитан и обработан. ");
    System.out.println();
}
    private static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
public static void showStatOfYear(YearlyReport yearlyReport, String path){

int difference=0;
int ProfitOfYear=0;
int ExpensesOfYear=0;
    String[] partsOfPath=path.split("\\.");
    int yearNumber=Integer.parseInt(partsOfPath[1]);

    System.out.println("Рассматриваемый год:"+yearNumber );

for (Integer name: yearlyReport.dataOfYearlyStat.keySet()){
    YearlyReportFiles hmInside=yearlyReport.dataOfYearlyStat.get(name);

    difference= hmInside.totalProfit-hmInside.totalExpenses; // прибыль
    ProfitOfYear+= hmInside.totalProfit;
    ExpensesOfYear+= hmInside.totalExpenses;

    System.out.println("Месяц "+ Month.of(name));
    System.out.println( "Прибыль = "+ difference);

}
    System.out.println("Средний расход за все месяцы в году "+(double)(ExpensesOfYear/yearlyReport.dataOfYearlyStat.size() ));
    System.out.println("Средний доход за все месяцы в году "+(double)ProfitOfYear/yearlyReport.dataOfYearlyStat.size());
}

}
