import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String inputFile = "input.txt";
    private static final String outputFile = "output.txt";

    public static void main(String[] args) throws IOException {
        StringBuilder itog = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        TreeMap <Integer, Integer> start_and_cost = new TreeMap<>();
        TreeMap <Integer, Integer> finish_and_worktime = new TreeMap<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i<n; i++ ){
            List<Integer> data_event = Arrays.stream(reader.readLine().split(" ")).mapToInt(l->Integer.parseInt(l)).collect(
                    ()->new ArrayList<Integer>(), // создаем ArrayList
                    (list, item)->list.add(item), // добавляем в список элемент
                    (list1, list2)-> list1.addAll(list2));
            //String[] event = reader.readLine().split(" ");
            Integer start = data_event.get(0);//Integer.parseInt(event[0]);
            Integer finish = data_event.get(1);//Integer.parseInt(event[1]);
            Integer cost = data_event.get(2);//Integer.parseInt(event[2]);

            Integer ex_cost = start_and_cost.get(start);
            Integer ex_worktime = finish_and_worktime.get(finish);

            if (ex_cost!=null){
                start_and_cost.put(start, ex_cost+cost);
            }
            else {
                start_and_cost.put(start, cost);
            }

            if (ex_worktime!=null){
                finish_and_worktime.put(finish, ex_worktime+finish-start);
            }
            else {
                finish_and_worktime.put(finish, finish-start);
            }
        }

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i<m; i++ ){
            //String[] event = reader.readLine().split(" ");
            //Integer start = Integer.parseInt(event[0]);
            //Integer finish = Integer.parseInt(event[1]);
            //String type = event[2];
            List<Integer> data_event = Arrays.stream(reader.readLine().split(" ")).mapToInt(l->Integer.parseInt(l)).collect(
                    ()->new ArrayList<Integer>(), // создаем ArrayList
                    (list, item)->list.add(item), // добавляем в список элемент
                    (list1, list2)-> list1.addAll(list2));
            //String[] event = reader.readLine().split(" ");
            Integer start = data_event.get(0);//Integer.parseInt(event[0]);
            Integer finish = data_event.get(1);//Integer.parseInt(event[1]);
            Integer type = data_event.get(2);//Integer.parseInt(event[2]);

            NavigableMap<Integer, Integer> interesting ;
            if (type.equals(1)){
                interesting =  start_and_cost.subMap(start, true, finish, true);
            }
            else {
                interesting = finish_and_worktime.subMap(start, true, finish, true);
            }
            itog.append(interesting.values().stream().reduce((x,y)->x+y).orElse(0)+ " ");
        }


        FileWriter file = new FileWriter(outputFile);
        file.write(itog.substring(0));
        file.close();
    }
}
