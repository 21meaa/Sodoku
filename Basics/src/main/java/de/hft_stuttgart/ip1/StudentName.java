package de.hft_stuttgart.ip1;

public class StudentName {
    public static String getStudentName() {
        return "21meaa1bif";
    }

    public static void main(String [] args) {
        for(int i=2;i<5;i++) {
            System.out.printf("Bei %d Wahlaufgaben muss %s Nr. %d bearbeiten.%n",
                i, getStudentName(), Math.abs(getStudentName().hashCode())%i);
        }
    }
}
