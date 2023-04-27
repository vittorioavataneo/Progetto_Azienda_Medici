package org.generation.italy.Progetto_Azienda_Medici.utilities;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.time.LocalDate;

public class StringUtilities {
    public static boolean isNullOrEmpty(String s){
        return s==null || s.length()==0;
    }

    public static LocalDate fromJSONString(String s){
        if(isNullOrEmpty(s)){
            return null;
        }
        return LocalDate.parse(s);
    }

    public static String dateNullController(LocalDate date){
        return date != null ? date.toString() : "";
    }

    public static String fromEnumToString(Enum<?> e){
        String enumName = e.name().toLowerCase();
        return enumName.substring(0, 1).toUpperCase() + enumName.substring(1);
    }

    public static <T extends Enum<T>> T fromStringToEnum(Class<T> enumClass, String value) throws IllegalArgumentException {
        try {
            return Enum.valueOf(enumClass, value);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("La stringa " + value + " non corrisponde a un valore valido per l'enum " + enumClass.getSimpleName());
        }
    }

}
