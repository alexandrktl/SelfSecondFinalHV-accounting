import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
         printMenu();
        MonthlyReport[] mRep=new MonthlyReport[3];
        YearlyReport[] yRepMap=new YearlyReport[1];

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        //HashMap <String, MonthlyReport> monthStatFolders=new HashMap<>();
        while (command != 0) {

            if (command==1){

                if ( mRep[0]==null){
                    for (int i = 0; i < mRep.length; i++) {

                        mRep[i] = new MonthlyReport("recourses/m.20210" + (i + 1) + ".csv");
                    }


                }else {
                    System.out.println("Файлы уже считаны!");


                }
            }else if (command==2){
              yRepMap[0]=new YearlyReport("recourses/y.2021.csv");

            } else if (command==3){
                if (yRepMap[0]==null && yRepMap[0]==null){
                    System.out.println("Невозможно провести сверку, не все файлы прочитаны!");
                }else {
                    for (int i = 0; i < mRep.length; i++) {
                        MonthlyReport.compareMonthAndYear(mRep[i],yRepMap[0], i+1);
                    }System.out.println("Отчеты успешно прошли проверку!");
            }
            }else if (command==4){

                if(mRep[0]==null){
                    System.out.println("Вывести информацию о всех месячных отчётах невозможно,\nCначала считайте данные из месячных отчетов.");
                }else {
                    for (int i = 0; i < mRep.length; i++) {
                        MonthlyReport.showMonthStatistic(mRep[i],(i+1));
                    }

                }

            }else if (command==5){
                if (yRepMap[0]==null){
                    System.out.println("Невозможно выполнить команду, сначала считайте годовой отчет.");
                }else {
                       YearlyReport.showStatOfYear(yRepMap[0], "recourses/y.2021.csv");
                }


            }else {
                System.out.println("Такой команды нет!");
            }

            printMenu();
            command = scanner.nextInt();
        }
        System.out.println("Программа завершена.");
    }
    private static void printMenu () {
        System.out.println("\nВыберите действие: ");
        System.out.println("1   Считать все месячные отчёты");
        System.out.println("2   Считать годовой отчёт");
        System.out.println("3   Сверить отчёты ");
        System.out.println("4   Вывести информацию о всех месячных отчётах ");
        System.out.println("5   Вывести информацию о годовом отчёте ");
        System.out.println("0   Выход из программы\n");


    }
}

