import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

}
