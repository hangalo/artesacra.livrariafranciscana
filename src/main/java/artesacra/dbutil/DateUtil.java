/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.dbutil;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date strToDate(String data) {
        if (data == null) {
            return null;
        }
        Date dataF = null;
        try {
            //  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            long timestemp = dateFormat.parse(data).getTime();
            dataF = new Date(timestemp);
        } catch (ParseException pe) {
            System.out.println("Erro ao converter String em data: " + pe.getLocalizedMessage());
        }
        return dataF;
    }

    public static String formataData(Date data) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(data);

        return sdf.format(calendar.getTime());
    }

    public static int calculaIdade(java.util.Date dataNascimento) {

        Calendar dateOfBirth = new GregorianCalendar();

        dateOfBirth.setTime(dataNascimento);

// Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();

// Obtém a idade baseado no ano
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

//se a data de hoje é antes da data de Nascimento, então diminui 1(um)
        if (today.before(dateOfBirth)) {

            age--;

        }

        return age;

    }

     public static int getAge(Date dateOfBirth) {
     

            Calendar today = Calendar.getInstance();
            Calendar birthDate = Calendar.getInstance();
            birthDate.setTime(dateOfBirth);
            if (birthDate.after(today)) {
                throw new IllegalArgumentException("You don't exist yet");
            }
            int todayYear = today.get(Calendar.YEAR);
            int birthDateYear = birthDate.get(Calendar.YEAR);
            int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
            int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
            int todayMonth = today.get(Calendar.MONTH);
            int birthDateMonth = birthDate.get(Calendar.MONTH);
            int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
            int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
            int age = todayYear - birthDateYear;

            // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
            if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
                age--;

                // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
            } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
                age--;
            }
            return age;
        
       
    }
      public static java.sql.Date converterDate(java.util.Date data) {

        try {
            long dataRegista = data.getTime();
            java.sql.Date dataConvertida = new java.sql.Date(dataRegista);
            return dataConvertida;

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public static java.util.Date getDataActual() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.sql.Date data = new java.sql.Date(calendar.getTimeInMillis());
        return new java.sql.Date(data.getTime());

    }

    public static Integer getMesActual() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.sql.Date data = new java.sql.Date(calendar.getTimeInMillis());
        return calendar.get(Calendar.MONTH);

    }

    public static Integer getMesDeUmaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return mes;

    }

    public static Integer getAnoDeUmaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return ano;

    }

    public static Integer getDiaDeUmaData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return dia;

    }

    public static Integer getDiaActual() {
        java.util.Calendar calendar = Calendar.getInstance();
        java.sql.Date data = new java.sql.Date(calendar.getTimeInMillis());
        return calendar.get(Calendar.DATE);
    }
}
