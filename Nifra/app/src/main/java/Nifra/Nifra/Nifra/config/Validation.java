package Nifra.Nifra.Nifra.config;

public class Validation {
    public static String DecimalPointChecker(String string, int maxDecimalPoints) {
        if (string.charAt(0) == '.') string = "0" + string;
        int max = string.length();
        StringBuilder finalString = new StringBuilder();
        boolean after = false;
        int i = 0,decimalPoints = 0;
        char t;
        while (i < max) {
            t = string.charAt(i);
            if (t == '.') {
                after = true;
            } else if(after) {
                decimalPoints++;
                if (decimalPoints > maxDecimalPoints)
                    return finalString.toString();
            }
            finalString.append(t);
            i++;
        }
        return finalString.toString();
    }

    public static void InputsChecker(String name,String description,String price) throws RuntimeException {
        if(name==null||name.equals(""))throw new RuntimeException("Name cannot be empty");
        if(description==null||description.equals(""))throw new RuntimeException("Description cannot be empty");
        if(price==null||price.equals(""))throw new RuntimeException("Price cannot be empty");
    }
}
