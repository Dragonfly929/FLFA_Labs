package CNF;

import java.util.*;

public class CFGtoCNF {

    private static final int MAX = 20;
    private static final String[][] gram = new String[MAX][MAX]; //to store entered grammar
    private static int np;
    private String input;
    private String string;
    private int lineCount;
    private String epsilonFound = "";

    // map variable with production ( variable -> production)
    private final Map<String, List<String>> mapVariableProduction = new LinkedHashMap<>();

    public static void main(String[] args) {
        StringBuilder final_string;

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter number of lines:");

        int line_count=sc.nextInt();
        String[] str=new String[line_count];

        for(int i=0;i<line_count;i++){
            System.out.print("Enter line "+(i+1)+" :");
            str[i]=sc.next();
        }

        final_string = new StringBuilder(str[0] + "\n");

        for(int i=1;i<line_count-1;i++)
            final_string.append(str[i]).append("\n");

        final_string.append(str[line_count - 1]);

        CFGtoCNF c = new CFGtoCNF();
        c.setInputandLineCount(final_string.toString(),line_count);
        c.convertCFGtoCNF();
    }
    //S->abb|AaB\nA->aA|bA|e\nB->aa|bb

    public void setString(String string) {
        this.string = string;
    }

    public void setInputandLineCount(String input, int lineCount) {
        this.input = input;
        this.lineCount = lineCount;

    }

    public void convertCFGtoCNF() {
        insertNewStartSymbol();
        convertStringtoMap();
        eliminateEpsilon();
        removeDuplicateKeyValue();
        eliminateSingleVariable();
        toCNF();
        eliminateThreeTerminal();
    }

    private void eliminateSingleVariable() {
        System.out.println("Remove Single Variable in Every Production ... ");
        for (int i = 0; i < lineCount; i++) {
            removeSingleVariable();
        }
        printMap();
    }

    private void eliminateThreeTerminal() {
        System.out.println("Replace two terminal variable with new variable ... ");
        for (int i = 0; i < lineCount; i++) {
            removeThreeTerminal();
        }
        printMap();
    }

    private void eliminateEpsilon() {
        System.out.println("\nRemove Epsilon....");
        for (int i = 0; i < lineCount; i++) {
            removeEpsilon();
        }
        printMap();
    }

    private String[] splitEnter(String input) {
        String[] tmpArray = new String[lineCount];
        for (int i = 0; i < lineCount; i++) {
            tmpArray = input.split("\\n");
        }
        return tmpArray;
    }

    private void printMap() {
        mapVariableProduction.entrySet().stream().map(pair -> pair.getKey() + " -> " + pair.getValue()).forEach(System.out::println);
        System.out.println(" ");
    }

    private void convertStringtoMap() {
        String[] splitedEnterInput = splitEnter(input);
        for (String s : splitedEnterInput) {
            String[] tempString = s.split("->|\\|");
            String variable = tempString[0].trim();
            String[] production = Arrays.copyOfRange(tempString, 1, tempString.length);
            List<String> productionList = new ArrayList<String>();
            // trim the empty space
            for (int k = 0; k < production.length; k++) {
                production[k] = production[k].trim();
            }
            // import array into ArrayList
            Collections.addAll(productionList, production);
            //insert element into map
            mapVariableProduction.put(variable, productionList);
        }
    }

    private void insertNewStartSymbol() {
        String newStart = "S0";
        ArrayList<String> newProduction = new ArrayList<>();
        newProduction.add("S");
        mapVariableProduction.put(newStart, newProduction);
    }

    private void removeEpsilon() {
        Iterator itr = mapVariableProduction.entrySet().iterator();
        Iterator itr2 = mapVariableProduction.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            ArrayList<String> productionRow = (ArrayList<String>) entry.getValue();
            if (productionRow.contains("e")) {
                if (productionRow.size() > 1) {
                    productionRow.remove("e");
                    epsilonFound = entry.getKey().toString();
                } else {
                    // remove if less than 1
                    epsilonFound = entry.getKey().toString();
                    mapVariableProduction.remove(epsilonFound);
                }
            }
        }

        // find B and eliminate them
        while (itr2.hasNext()) {
            Map.Entry entry = (Map.Entry) itr2.next();
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();

            for (int i = 0; i < productionList.size(); i++) {
                String temp = productionList.get(i);

                for (int j = 0; j < temp.length(); j++) {
                    if (epsilonFound.equals(Character.toString(productionList.get(i).charAt(j)))) {

                        if (temp.length() == 2) {

                            // remove specific character in string
                            temp = temp.replace(epsilonFound, "");

                            if (!mapVariableProduction.get(entry.getKey().toString()).contains(temp)) {
                                mapVariableProduction.get(entry.getKey().toString()).add(temp);
                            }

                        } else if (temp.length() == 3) {
                            String deletedTemp = new StringBuilder(temp).deleteCharAt(j).toString();
                            if (!mapVariableProduction.get(entry.getKey().toString()).contains(deletedTemp)) {
                                mapVariableProduction.get(entry.getKey().toString()).add(deletedTemp);
                            }
                        } else if (temp.length() == 4) {
                            String deletedTemp = new StringBuilder(temp).deleteCharAt(j).toString();
                            if (!mapVariableProduction.get(entry.getKey().toString()).contains(deletedTemp)) {
                                mapVariableProduction.get(entry.getKey().toString()).add(deletedTemp);
                            }
                        } else {
                            if (!mapVariableProduction.get(entry.getKey().toString()).contains("e")) {
                                mapVariableProduction.get(entry.getKey().toString()).add("e");
                            }
                        }
                    }
                }
            }
        }
    }

    private void removeDuplicateKeyValue() {
        System.out.println("Remove Duplicate Key Value ... ");
        for (Map.Entry<String, List<String>> stringListEntry : mapVariableProduction.entrySet()) {
            ArrayList<String> productionRow = (ArrayList<String>) ((Map.Entry) stringListEntry).getValue();
            for (int i = 0; i < productionRow.size(); i++) {
                if (productionRow.get(i).contains(((Map.Entry<?, ?>) stringListEntry).getKey().toString())) {
                    productionRow.remove(((Map.Entry<?, ?>) stringListEntry).getKey().toString());
                }
            }
        }
        printMap();
    }

    private void removeSingleVariable() {
        Iterator<Map.Entry<String, List<String>>> itr4 = mapVariableProduction.entrySet().iterator();
        String key = null;


        while (itr4.hasNext()) {
            Map.Entry entry = itr4.next();
            Set<String> set = mapVariableProduction.keySet();
            ArrayList<String> keySet = new ArrayList<String>(set);
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();
            for (int i = 0; i < productionList.size(); i++) {
                String temp = productionList.get(i);
                for (int j = 0; j < temp.length(); j++) {
                    for (int k = 0; k < keySet.size(); k++) {
                        if (keySet.get(k).equals(temp)) {
                            key = entry.getKey().toString();
                            List<String> productionValue = mapVariableProduction.get(temp);
                            productionList.remove(temp);
                            for (int l = 0; l < productionValue.size(); l++) {
                                mapVariableProduction.get(key).add(productionValue.get(l));
                            }
                        }
                    }
                }
            }
        }
    }

    private Boolean checkDuplicateInProductionList(Map<String, List<String>> map, String key) {
        boolean notFound = true;
        Iterator<Map.Entry<String, List<String>>> itr = map.entrySet().iterator();
        outerloop:

        while (itr.hasNext()) {

            Map.Entry entry = (Map.Entry) itr.next();
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();

            for (int i = 0; i < productionList.size(); i++) {
                if (productionList.size() < 2) {

                    if (productionList.get(i).equals(key)) {
                        notFound = false;
                        break outerloop;
                    } else {
                        notFound = true;
                    }
                }
            }
        }
        return notFound;
    }

    private void toCNF() {
        System.out.println("Assign new variable for two non-terminal or one terminal ... ");
        Iterator<Map.Entry<String, List<String>>> itr5 = mapVariableProduction.entrySet().iterator();
        String key = null;
        int asciiBegin = 71; //G
        Map<String, List<String>> tempList = new LinkedHashMap<>();
        while (itr5.hasNext()) {
            Map.Entry entry = (Map.Entry) itr5.next();
            Set<String> set = mapVariableProduction.keySet();
            ArrayList<String> keySet = new ArrayList<String>(set);
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();
            boolean found1 = false;
            boolean found2 = false;
            boolean found = false;
            for (int i = 0; i < productionList.size(); i++) {
                String temp = productionList.get(i);
                for (int j = 0; j < temp.length(); j++) {
                    if (temp.length() == 3) {
                        String newProduction = temp.substring(1, 3); // SA
                        found = checkDuplicateInProductionList(tempList, newProduction) && checkDuplicateInProductionList(mapVariableProduction, newProduction);
                        if (found) {
                            ArrayList<String> newVariable = new ArrayList<>();
                            newVariable.add(newProduction);
                            key = Character.toString((char) asciiBegin);
                            tempList.put(key, newVariable);
                            asciiBegin++;
                        }
                    } else if (temp.length() == 2) { // if only two substring
                        for (int k = 0; k < keySet.size(); k++) {
                            if (!keySet.get(k).equals(Character.toString(productionList.get(i).charAt(j)))) { // if substring not equals to keySet
                                found = false;
                            } else {
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            String newProduction = Character.toString(productionList.get(i).charAt(j));
                            if (checkDuplicateInProductionList(tempList, newProduction) && checkDuplicateInProductionList(mapVariableProduction, newProduction)) {
                                ArrayList<String> newVariable = new ArrayList<>();
                                newVariable.add(newProduction);
                                key = Character.toString((char) asciiBegin);
                                tempList.put(key, newVariable);
                                asciiBegin++;
                            }
                        }
                    } else if (temp.length() == 4) {
                        String newProduction1 = temp.substring(0, 2); // SA
                        String newProduction2 = temp.substring(2, 4); // SA
                        found1 = checkDuplicateInProductionList(tempList, newProduction1) && checkDuplicateInProductionList(mapVariableProduction, newProduction1);
                        found2 = checkDuplicateInProductionList(tempList, newProduction2) && checkDuplicateInProductionList(mapVariableProduction, newProduction2);
                        if (found1) {
                            ArrayList<String> newVariable = new ArrayList<>();
                            newVariable.add(newProduction1);
                            key = Character.toString((char) asciiBegin);
                            tempList.put(key, newVariable);
                            asciiBegin++;
                        }
                        if (found2) {
                            ArrayList<String> newVariable = new ArrayList<>();
                            newVariable.add(newProduction2);
                            key = Character.toString((char) asciiBegin);
                            tempList.put(key, newVariable);
                            asciiBegin++;
                        }
                    }
                }
            }
        }
        mapVariableProduction.putAll(tempList);
        printMap();
    }

    private void removeThreeTerminal() {
        Iterator itr = mapVariableProduction.entrySet().iterator();
        ArrayList<String> keyList = new ArrayList<>();
        Iterator itr2 = mapVariableProduction.entrySet().iterator();

        // obtain key that use to eliminate two terminal and above
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            ArrayList<String> productionRow = (ArrayList<String>) entry.getValue();
            if (productionRow.size() < 2) {
                keyList.add(entry.getKey().toString());
            }
        }

        // find more than three terminal or combination of variable and terminal to eliminate them
        while (itr2.hasNext()) {
            Map.Entry entry = (Map.Entry) itr2.next();
            ArrayList<String> productionList = (ArrayList<String>) entry.getValue();
            if (productionList.size() > 1) {
                for (int i = 0; i < productionList.size(); i++) {
                    String temp = productionList.get(i);
                    for (int j = 0; j < temp.length(); j++) {
                        if (temp.length() > 2) {
                            String stringToBeReplaced1 = temp.substring(j);
                            String stringToBeReplaced2 = temp.substring(0, temp.length() - j);
                            for (String key : keyList) {
                                List<String> keyValues = new ArrayList<>();
                                keyValues = mapVariableProduction.get(key);
                                String[] values = keyValues.toArray(new String[keyValues.size()]);
                                String value = values[0];
                                if (stringToBeReplaced1.equals(value)) {
                                    mapVariableProduction.get(entry.getKey().toString()).remove(temp);
                                    temp = temp.replace(stringToBeReplaced1, key);
                                    if (!mapVariableProduction.get(entry.getKey().toString()).contains(temp)) {
                                        mapVariableProduction.get(entry.getKey().toString()).add(i, temp);
                                    }
                                } else if (stringToBeReplaced2.equals(value)) {
                                    mapVariableProduction.get(entry.getKey().toString()).remove(temp);
                                    temp = temp.replace(stringToBeReplaced2, key);
                                    if (!mapVariableProduction.get(entry.getKey().toString()).contains(temp)) {
                                        mapVariableProduction.get(entry.getKey().toString()).add(i, temp);
                                    }
                                }
                            }
                        } else if (temp.length() == 2) {
                            for (String key : keyList) {
                                List<String> keyValues = new ArrayList<>();
                                keyValues = mapVariableProduction.get(key);
                                String[] values = keyValues.toArray(new String[keyValues.size()]);
                                String value = values[0];
                                for (int pos = 0; pos < temp.length(); pos++) {
                                    String tempChar = Character.toString(temp.charAt(pos));
                                    if (value.equals(tempChar)) {
                                        mapVariableProduction.get(entry.getKey().toString()).remove(temp);
                                        temp = temp.replace(tempChar, key);
                                        if (!mapVariableProduction.get(entry.getKey().toString()).contains(temp)) {
                                            mapVariableProduction.get(entry.getKey().toString()).add(i, temp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (productionList.size() == 1) {
                for (int i = 0; i < productionList.size(); i++) {
                    String temp = productionList.get(i);
                    if (temp.length() == 2) {
                        for (String key : keyList) {
                            List<String> keyValues = new ArrayList<>();
                            keyValues = mapVariableProduction.get(key);
                            String[] values = keyValues.toArray(new String[keyValues.size()]);
                            String value = values[0];
                            for (int pos = 0; pos < temp.length(); pos++) {
                                String tempChar = Character.toString(temp.charAt(pos));
                                if (value.equals(tempChar)) {
                                    mapVariableProduction.get(entry.getKey().toString()).remove(temp);
                                    temp = temp.replace(tempChar, key);
                                    if (!mapVariableProduction.get(entry.getKey().toString()).contains(temp)) {
                                        mapVariableProduction.get(entry.getKey().toString()).add(i, temp);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String concat(String a, String b) //concatenates unique non-terminals
    {
        String r = a;
        r = r.concat(b);
        return r;
    }

    public String search_prod(String p) //returns a concatenated String of variables which can produce string p
    {
        int k;
        String r = "";
        int j = 0;
        while (j < np) {
            k = 1;
            while (gram[j][k] != null) {
                if (gram[j][k].equals(p)) {
                    r = concat(r, gram[j][0]);
                }
                k++;
            }
            j++;
        }
        return r;
    }

    public String gen_comb(String a, String b) //creates every combination of variables from a and b . For eg: BA * AB = {BA, BB, AA, BB}
    {
        String pri = a;
        String re = "";
        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++) {
                pri = "";
                pri = pri + a.charAt(i) + b.charAt(j);
                re = re + search_prod(pri); //searches if the generated productions can be created or not
            }
        return re;
    }
}


