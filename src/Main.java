import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        while (command != 0) {
            if (command==1){
                HashMap <Integer, MonthlyReport> monthStatFolders=new HashMap<>();
                for (int i = 1; i <4 ; i++) {

                    MonthlyReport mRep =new MonthlyReport("recourses/m.20210"+i+".csv") ;
                    monthStatFolders.put(i,mRep); // теперь данные лежат в мапе из трех папок
                }

            }else if (command==2){
              YearlyReport yRep= new YearlyReport("recourses/y.2021.csv");
            } else if (command==3){

            }else if (command==4){

            }else if (command==5){

            }else {
                System.out.println("Такой команды нет!");
            }

            printMenu();
            command = scanner.nextInt();
        }
        System.out.println("Программа завершена.");
    }
    private static void printMenu () {
        System.out.println("Выберите действие: ");
        System.out.println("1   Считать все месячные отчёты");
        System.out.println("2   Считать годовой отчёт");
        System.out.println("3   Сверить отчёты ");
        System.out.println("4   Вывести информацию о всех месячных отчётах ");
        System.out.println("5   Вывести информацию о годовом отчёте ");
        System.out.println("0   Выход из программы");


    }
}

